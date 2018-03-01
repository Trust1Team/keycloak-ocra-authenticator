package com.t1t.keycloak.action;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public class KeycloakOcraMobileNumberRequiredActionFactory implements RequiredActionFactory {
    private static Logger logger = Logger.getLogger(RequiredActionFactory.class);
    private static final KeycloakOcraMobileNumberRequiredAction SINGLETON = new KeycloakOcraMobileNumberRequiredAction();

    public RequiredActionProvider create(KeycloakSession session) {
        logger.debug("create called ...");
        return SINGLETON;
    }

    public String getId() {
        logger.debug("getId called ... returning " + KeycloakOcraMobileNumberRequiredAction.PROVIDER_ID);
        return KeycloakOcraMobileNumberRequiredAction.PROVIDER_ID;
    }

    public String getDisplayText() {
        logger.debug("getDisplayText called ...");
        return "Mobile Number";
    }

    public void init(Config.Scope config) {
        logger.debug("init called ...");
    }

    public void postInit(KeycloakSessionFactory factory) {
        logger.debug("postInit called ...");
    }

    public void close() {
        logger.debug("getId close ...");
    }
}
