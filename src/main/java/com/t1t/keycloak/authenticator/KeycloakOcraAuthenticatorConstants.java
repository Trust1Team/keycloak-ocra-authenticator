package com.t1t.keycloak.authenticator;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public class KeycloakOcraAuthenticatorConstants {
    //User Properties
    public static final String PROP_MOBILE_NR = "mobile_number";

    //T1G APIs
    public static final String CONF_PRP_URL_OCRA_API = "ocra-auth.url.ocra.api";
    public static final String CONF_PRP_URL_SMS_API = "ocra-auth.url.sms.api";
    public static final String CONF_PRP_API_KEY = "ocra-auth.apikey";

    //SMS message
    public static final String CONF_PRP_OCRA_TEXT = "ocra-auth.msg.text";

    //OCRA config
    public static final String CONF_PRP_OCRA_ALGO = "ocra-auth.ocra.algorithm";
    public static final String CONF_PRP_OCRA_SEED = "ocra-auth.ocra.seed";
    public static final String CONF_PRP_OCRA_TTL = "ocra-auth.ocra.ttl";

    //Form input
    public static final String ATTR_MOBILE = "mobile_number";
    public static final String ANSW_OCRA_CODE = "smsCode";

    //TODO
    // User credentials (used to persist the sent sms code + expiration time cluster wide)
    public static final String USR_CRED_MDL_OCRA_SESSION_ID = "ocra-auth.session.id";
    public static final String USR_CRED_MDL_OCRA_CHALLENGE_ID = "ocra-auth.challenge.id";
    public static final String USR_CRED_MDL_OCRA_QS = "ocra-auth.qs";
    public static final String USR_CRED_MDL_OCRA_CODE = "ocra-auth.code";
    public static final String USR_CRED_MDL_OCRA_EXP_TIME = "ocra-auth.exp-time";
}
