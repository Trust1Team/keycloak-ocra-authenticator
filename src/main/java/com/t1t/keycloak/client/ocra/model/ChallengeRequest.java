package com.t1t.keycloak.client.ocra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChallengeRequest implements Serializable {
    private String qc;
    private String sessionId;

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }

    public String getSessionId() {return sessionId;}

    public void setSessionId(String sessionId) {this.sessionId = sessionId;}

    @Override
    public String toString() {
        return "ChallengeRequest{" +
                "qc='" + qc + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
