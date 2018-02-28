package com.t1t.keycloak.authenticator;

import com.t1t.keycloak.client.ocra.OcraService;
import com.t1t.keycloak.client.sms.model.SmsService;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.ConfigurableAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public class KeycloakOcraAuthenticatorFactory implements AuthenticatorFactory, ConfigurableAuthenticatorFactory {

    public static final String PROVIDER_ID = "ocra-authentication";

    private static Logger logger = Logger.getLogger(AuthenticatorFactory.class);
    private static KeycloakOcraAuthenticator SINGLETON;


    public static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.OPTIONAL,
            AuthenticationExecutionModel.Requirement.DISABLED};

    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();

    static {
        ProviderConfigProperty property;

        // SMS Text
        property = new ProviderConfigProperty();
        property.setName(KeycloakOcraAuthenticatorConstants.CONF_PRP_OCRA_TEXT);
        property.setLabel("Template of text to send to the user");
        property.setType(ProviderConfigProperty.STRING_TYPE);
        property.setHelpText("Use %sms-code% as placeholder for the generated OCRA code. Use %user% and %password% as placeholder when 'In message' authentication is used.");
        configProperties.add(property);

        try {
            SINGLETON = new KeycloakOcraAuthenticator(new OcraService(), new SmsService());
            logger.info("Kc Ocra Authenticator instance created.");
        } catch (Exception e) {
            logger.error("Error upon initializing the OCRA context:" + e.getMessage());
        }
    }

    public String getId() {
        logger.debug("getId called ... returning " + PROVIDER_ID);
        return PROVIDER_ID;
    }

    public Authenticator create(KeycloakSession session) {
        logger.debug("create called ... returning " + SINGLETON);
        return SINGLETON;
    }


    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        logger.debug("getRequirementChoices called ... returning " + REQUIREMENT_CHOICES);
        return REQUIREMENT_CHOICES;
    }

    public boolean isUserSetupAllowed() {
        logger.debug("isUserSetupAllowed called ... returning true");
        return true;
    }

    public boolean isConfigurable() {
        logger.debug("isConfigurable called ... returning true");
        return true;
    }

    public String getHelpText() {
        logger.debug("getHelpText called ...");
        return "Validates an OCRA OTP sent by SMS.";
    }

    public String getDisplayType() {
        String result = "OCRA Authentication";
        logger.debug("getDisplayType called ... returning " + result);
        return result;
    }

    public String getReferenceCategory() {
        logger.debug("getReferenceCategory called ... returning sms-auth-code");
        return "sms-auth-code";
    }

    public List<ProviderConfigProperty> getConfigProperties() {
        logger.debug("getConfigProperties called ... returning " + configProperties);
        return configProperties;
    }

    public void init(Config.Scope config) {
        logger.debug("init called ... config.scope = " + config);
    }

    public void postInit(KeycloakSessionFactory factory) {
        logger.debug("postInit called ... factory = " + factory);
    }

    public void close() {
        logger.debug("close called ...");
    }
}
