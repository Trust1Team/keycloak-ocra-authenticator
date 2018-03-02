package com.t1t.keycloak.authenticator;

import com.t1t.keycloak.client.ocra.OcraService;
import com.t1t.keycloak.client.ocra.model.ChallengeResponse;
import com.t1t.keycloak.client.ocra.model.ValidationResponse;
import com.t1t.keycloak.client.ocra.utils.RandomString;
import com.t1t.keycloak.client.sms.model.SmsService;
import okhttp3.Challenge;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public class KeycloakOcraAuthenticator implements Authenticator {
    private static Logger logger = Logger.getLogger(Authenticator.class);
    public static final String CREDENTIAL_TYPE = "ocra_validation";
    private OcraService ocraService;
    private SmsService smsService;
    private RandomString randomStringGenerator;

    public KeycloakOcraAuthenticator(OcraService ocraService, SmsService smsService) {
        this.ocraService = ocraService;
        this.smsService = smsService;
        randomStringGenerator = new RandomString(16, new SecureRandom(), RandomString.alphanum);
    }

    private enum CODE_STATUS {
        VALID,
        INVALID,
        EXPIRED
    }


    public void authenticate(AuthenticationFlowContext context) {
        logger.debug("authenticate called ... context = " + context);
        UserModel user = context.getUser();
        AuthenticatorConfigModel config = context.getAuthenticatorConfig();

        List<String> mobileNumberCreds = user.getAttribute(KeycloakOcraAuthenticatorConstants.PROP_MOBILE_NR);

        String mobileNumber = null;

        if (mobileNumberCreds != null && !mobileNumberCreds.isEmpty()) {
            mobileNumber = mobileNumberCreds.get(0);
        }

        if (mobileNumber != null) {
            //generate qc (challenge) and sessionId
            String qc = randomStringGenerator.nextString();
            String sessionID = UUID.randomUUID().toString();

            long ttl = KeycloakOcraAuthenticatorUtil.getConfigLong(config, KeycloakOcraAuthenticatorConstants.CONF_PRP_OCRA_TTL, 10 * 60L); // 10 minutes in s

            logger.debug("Using ttl " + ttl + " (s)");

            ChallengeResponse challengeResp = null;
            try {
                challengeResp = ocraService.getCode(sessionID, qc);
                String code = challengeResp.getRs();
                logger.debug("Debug code: " + code);
            } catch (Exception e) {
                logger.error("Error generating OCRA code using OCRA API: " + e.getMessage());
            }

            storeCode(context, challengeResp.getRs(), sessionID, challengeResp.getId().toString(), challengeResp.getQs()); // s --> ms:: new Date().getTime() + (ttl * 1000)
            if (KeycloakOcraAuthenticatorUtil.sendSmsCode(smsService, mobileNumber, challengeResp.getRs(), context.getAuthenticatorConfig())) {
                Response challenge = context.form().createForm("ocra-validation.ftl");
                context.challenge(challenge);
            } else {
                Response challenge = context.form()
                        .setError("SMS could not be sent.")
                        .createForm("ocra-validation-error.ftl");
                context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
            }
        } else {
            // The mobile number is NOT configured --> complain
            Response challenge = context.form()
                    .setError("Missing mobile number")
                    .createForm("ocra-validation-error.ftl");
            context.failureChallenge(AuthenticationFlowError.CLIENT_CREDENTIALS_SETUP_REQUIRED, challenge);
        }
    }


    public void action(AuthenticationFlowContext context) {
        logger.debug("action called ... context = " + context);
        CODE_STATUS status = validateCode(context);
        Response challenge = null;
        switch (status) {
            case EXPIRED:
                challenge = context.form()
                        .setError("code is expired")
                        .createForm("ocra-validation.ftl");
                context.failureChallenge(AuthenticationFlowError.EXPIRED_CODE, challenge);
                break;

            case INVALID:
                if (context.getExecution().getRequirement() == AuthenticationExecutionModel.Requirement.OPTIONAL ||
                        context.getExecution().getRequirement() == AuthenticationExecutionModel.Requirement.ALTERNATIVE) {
                    logger.debug("Calling context.attempted()");
                    context.attempted();
                } else if (context.getExecution().getRequirement() == AuthenticationExecutionModel.Requirement.REQUIRED) {
                    challenge = context.form()
                            .setError("Invalid code specified, please enter it again")
                            .createForm("ocra-validation.ftl");
                    context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
                } else {
                    // Something strange happened
                    logger.warn("Undefined execution ...");
                }
                break;

            case VALID:
                context.success();
                break;

        }
    }

    /**
     * Store the code + expiration time + sessionId in a UserCredential. Keycloak will persist these in the DB.
     * When the code is validated on another node (in a clustered environment) the other nodes have access to it's values too.
     */
    private void storeCode(AuthenticationFlowContext context, String code, String sessionId, String challengeId, String qs) {
        UserCredentialModel credentials = new UserCredentialModel();

        credentials.setType(KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_CODE);
        credentials.setValue(code);

        credentials.setType(KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_SESSION_ID);
        credentials.setValue(sessionId);

        credentials.setType(KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_CHALLENGE_ID);
        credentials.setValue(challengeId);

        credentials.setType(KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_QS);
        credentials.setValue(qs);

        context.getSession().userCredentialManager().updateCredential(context.getRealm(), context.getUser(), credentials);
    }


    protected CODE_STATUS validateCode(AuthenticationFlowContext context) {
        CODE_STATUS result = CODE_STATUS.INVALID;

        logger.debug("validateCode called ... ");
        MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
        String enteredCode = formData.getFirst(KeycloakOcraAuthenticatorConstants.ANSW_OCRA_CODE);
        KeycloakSession session = context.getSession();

        // TODO look in which cach this can be stored!!! from KC 3.2.x not working
        List codeCreds = session.userCredentialManager().getStoredCredentialsByType(context.getRealm(), context.getUser(), KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_CODE);
        List sessionIds = session.userCredentialManager().getStoredCredentialsByType(context.getRealm(), context.getUser(), KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_SESSION_ID);
        List challengeIds = session.userCredentialManager().getStoredCredentialsByType(context.getRealm(), context.getUser(), KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_CHALLENGE_ID);
        List qss = session.userCredentialManager().getStoredCredentialsByType(context.getRealm(), context.getUser(), KeycloakOcraAuthenticatorConstants.USR_CRED_MDL_OCRA_QS);

        CredentialModel expectedCode = (CredentialModel) codeCreds.get(0);
        CredentialModel sessionId = (CredentialModel) sessionIds.get(0);
        CredentialModel challengeId = (CredentialModel) challengeIds.get(0);
        CredentialModel qs = (CredentialModel) qss.get(0);
        ChallengeResponse crs = new ChallengeResponse();
        crs.setId(Long.valueOf(challengeId.getValue()));
        crs.setQs(qs.getValue());


        logger.debug("Expected code = " + expectedCode.getValue());
        try {
            ValidationResponse valRes = ocraService.validate(sessionId.getValue(), enteredCode, crs);
            if(valRes.getValidationResult().booleanValue()) result = enteredCode.equals(expectedCode.getValue()) ? CODE_STATUS.VALID : CODE_STATUS.INVALID;
            else result = CODE_STATUS.INVALID;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return  CODE_STATUS.INVALID;
        }

        logger.debug("result : " + result);
        return result;
    }

    public boolean requiresUser() {
        logger.debug("requiresUser called ... returning true");
        return true;
    }

    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        logger.debug("configuredFor called ... session=" + session + ", realm=" + realm + ", user=" + user);
        return true;
    }

    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        logger.debug("setRequiredActions called ... session=" + session + ", realm=" + realm + ", user=" + user);
    }

    public void close() {
        logger.debug("close called ...");
    }

}
