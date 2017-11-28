package com.t1t.keycloak.authenticator;

/**
 * Created by joris on 18/11/2016.
 */
public class KeycloakOcraAuthenticatorConstants {
    public static final String ATTR_MOBILE = "mobile_number";
    public static final String ANSW_OCRA_CODE = "ocraCode";

    public static final String CONF_PRP_OCRA_CODE_TTL = "ocra-auth.code.ttl";
    public static final String CONF_PRP_OCRA_CODE_LENGTH = "ocra-auth.code.length";
    public static final String CONF_PRP_OCRA_TEXT = "ocra-auth.msg.text";

    // AWS
    public static final String CONF_PRP_OCRA_CLIENTTOKEN = "ocra-auth.sms.clienttoken";
    public static final String CONF_PRP_OCRA_CLIENTSECRET = "ocra-auth.sms.clientsecret";

    // User credentials (used to persist the sent sms code + expiration time cluster wide)
    public static final String USR_CRED_MDL_OCRA_CODE = "ocra-auth.code";
    public static final String USR_CRED_MDL_OCRA_EXP_TIME = "ocra-auth.exp-time";
}
