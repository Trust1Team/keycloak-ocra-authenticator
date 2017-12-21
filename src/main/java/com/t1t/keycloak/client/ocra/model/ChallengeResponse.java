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
public class ChallengeResponse implements Serializable {
    private Long id;
    private String rs;
    private String qs;

    public ChallengeResponse() {}

    public ChallengeResponse(Long id, String rs, String qs) {
        this.id = id;
        this.rs = rs;
        this.qs = qs;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "ChallengeResponse{" +
                "id=" + id +
                ", rs='" + rs + '\'' +
                ", qs='" + qs + '\'' +
                '}';
    }
}
