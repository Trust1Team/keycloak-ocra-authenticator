package com.t1t.keycloak.client.ocra;

import com.t1t.keycloak.client.ocra.model.ChallengeResponse;
import com.t1t.keycloak.client.ocra.utils.RandomString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

import static org.junit.Assert.*;

public class OcraServiceTest {
    private static final Logger log = LoggerFactory.getLogger(OcraServiceTest.class.getName());
    private RandomString randomGenerator;

    @Test
    public void testOcraService() throws Exception {
        randomGenerator = new RandomString(16, new SecureRandom(),RandomString.alphanum);
        OcraService ocraService = new OcraService();
        ChallengeResponse challengeResponse = ocraService.getCode("sessionABC", randomGenerator.nextString());
        log.info("Challenge Response: {}", challengeResponse);
        assertNotNull(challengeResponse);
        assertNotNull(challengeResponse.getId());
        assertNotNull(challengeResponse.getQs());
        assertNotNull(challengeResponse.getRs());
    }

}