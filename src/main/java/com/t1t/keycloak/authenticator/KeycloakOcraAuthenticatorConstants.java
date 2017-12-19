package com.t1t.keycloak.authenticator;

/**
 * Created by joris on 18/11/2016.
 */
public class KeycloakOcraAuthenticatorConstants {
    //T1G APIs
    public static final String CONF_PRP_URL_OCRA_API = "ocra-auth.url.ocra.api";
    public static final String CONF_PRP_URL_SMS_API = "ocra-auth.url.sms.api";
    public static final String CONF_PRP_API_KEY = "ocra-auth.apikey";

    //SMS message
    public static final String CONF_PRP_OCRA_TEXT = "ocra-auth.msg.text";

    //OCRA config
    public static final String CONF_PRP_OCRA_ALGO = "ocra-auth.ocra.algorithm";
    public static final String CONF_PRP_OCRA_SEED = "ocra-auth.ocra.seed";

    //TODO
    public static final String ATTR_MOBILE = "mobile_number";
    public static final String ANSW_OCRA_CODE = "ocraCode";

    //TODO
    // User credentials (used to persist the sent sms code + expiration time cluster wide)
    public static final String USR_CRED_MDL_OCRA_CODE = "ocra-auth.code";
    public static final String USR_CRED_MDL_OCRA_EXP_TIME = "ocra-auth.exp-time";
}
