package com.t1t.keycloak.client.ocra;

import com.t1t.keycloak.client.RestServiceBuilder;
import com.t1t.keycloak.client.ocra.model.*;
import com.t1t.lib.ocra.Ocra;
import com.t1t.lib.ocra.utils.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcraService {
    private static final Logger log = LoggerFactory.getLogger(OcraService.class.getName());
    private OcraClient ocraClient;


    public OcraService() throws Exception {
        ocraClient = RestServiceBuilder.getOcraRestClient();
        verifyAndUpdateApplicationConfig();
    }

    private void verifyAndUpdateApplicationConfig() throws Exception {
        UpdateApplicationVersionRequest uavb = new UpdateApplicationVersionRequest();
        uavb.setOcraAlgorithm(RestServiceBuilder.getConfig().getOcraAlgorithm());
        uavb.setOcraSeed(RestServiceBuilder.getConfig().getOcraSeed());
        ApplicationVersionResponse avb = RestExecutor.executeCall(ocraClient.updateApplication(uavb));
        log.debug("Application: {}", avb);
    }

    public ChallengeResponse getCode(String sessionId, String qc) throws Exception {
        ChallengeRequest challengeRequest = new ChallengeRequest();
        //challengeRequest.setQc();
        challengeRequest.setQc(qc);
        challengeRequest.setSessionId(sessionId);
        ChallengeResponse challengeResponse = RestExecutor.executeCall(ocraClient.getChallenge(challengeRequest));
        return challengeResponse;
    }

    public ValidationResponse validate(String sessionId, String qc, ChallengeResponse challengeResponse) throws Exception {
        ApplicationVersionResponse avb = RestExecutor.executeCall(ocraClient.getApplication());
        String qAll = challengeResponse.getQs() + qc;
        String qcHex = Convert.asHex(qAll.getBytes());
        ValidationRequest validationRequest = new ValidationRequest();
        validationRequest.setId(challengeResponse.getId());
        validationRequest.setRc(Ocra.generateOCRA(RestServiceBuilder.getConfig().getOcraAlgorithm(), RestServiceBuilder.getConfig().getOcraSeed(), "0", qcHex,"", sessionId, "" ));
        return RestExecutor.executeCall(ocraClient.validate(validationRequest));
    }
}
