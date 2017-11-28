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
public class ValidationRequest implements Serializable{
    private Long id;
    private String rc;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public String toString() {
        return "ValidationRequest{" +
                "id=" + id +
                ", rc='" + rc + '\'' +
                '}';
    }
}
