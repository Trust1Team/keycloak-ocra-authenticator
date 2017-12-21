package com.t1t.keycloak.action;

import org.keycloak.credential.CredentialProvider;
import org.keycloak.credential.CredentialProviderFactory;
import org.keycloak.models.KeycloakSession;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public class KeycloakOcraMobileNumberCredentialProviderFactory implements CredentialProviderFactory<KeycloakOcraMobileNumberCredentialProvider> {
    @Override
    public String getId() {
        return "mobile_number";
    }

    @Override
    public CredentialProvider create(KeycloakSession session) {
        return new KeycloakOcraMobileNumberCredentialProvider(session);
    }

}
