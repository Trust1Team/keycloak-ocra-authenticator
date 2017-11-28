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
public class UpdateApplicationVersionRequest implements Serializable {
    private String name;
    private String description;
    private String ocraAlgorithm;
    private String ocraSeed;
    private Long ocraCounter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOcraAlgorithm() {return ocraAlgorithm;}

    public void setOcraAlgorithm(String ocraAlgorithm) {this.ocraAlgorithm = ocraAlgorithm;}

    public String getOcraSeed() {return ocraSeed;}

    public void setOcraSeed(String ocraSeed) {this.ocraSeed = ocraSeed;}

    public Long getOcraCounter() {return ocraCounter;}

    public void setOcraCounter(Long ocraCounter) {this.ocraCounter = ocraCounter;}

    @Override
    public String toString() {
        return "UpdateApplicationVersionRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ocraAlgorithm='" + ocraAlgorithm + '\'' +
                ", ocraCounter=" + ocraCounter +
                '}';
    }
}
