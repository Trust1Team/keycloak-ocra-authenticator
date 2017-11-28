package com.t1t.keycloak.action;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * Created by nickpack on 15/08/2017.
 */
public class KeycloakOcraMobilenumberRequiredActionFactory implements RequiredActionFactory {
    private static Logger logger = Logger.getLogger(KeycloakOcraMobilenumberRequiredActionFactory.class);
    private static final KeycloakOcraMobilenumberRequiredAction SINGLETON = new KeycloakOcraMobilenumberRequiredAction();

    public RequiredActionProvider create(KeycloakSession session) {
        logger.debug("create called ...");
        return SINGLETON;
    }

    public String getId() {
        logger.debug("getId called ... returning " + KeycloakOcraMobilenumberRequiredAction.PROVIDER_ID);
        return KeycloakOcraMobilenumberRequiredAction.PROVIDER_ID;
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
