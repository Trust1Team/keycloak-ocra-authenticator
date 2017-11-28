package com.t1t.keycloak.authenticator;

import org.keycloak.credential.CredentialProvider;
import org.keycloak.credential.CredentialProviderFactory;
import org.keycloak.models.KeycloakSession;

/**
 * Created by nickpack on 09/08/2017.
 */
public class KeycloakOcraAuthenticatorCredentialProviderFactory implements CredentialProviderFactory<KeycloakOcraAuthenticatorCredentialProvider> {
    @Override
    public String getId() {
        return "smsCode";
    }

    @Override
    public CredentialProvider create(KeycloakSession session) {
        return new KeycloakOcraAuthenticatorCredentialProvider(session);
    }
}
