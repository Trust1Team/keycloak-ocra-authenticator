package com.t1t.keycloak.client.ocra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationVersionResponse {
    private String gatewayAppId;
    private String gatewayAppVersion;
    private Organization organization;
    private String name;
    private String description;
    private Long challengeCounter;
    private Long validationCounter;
    private Date createdOn;
    private Date updatedOn;
    private String ocraAlgorithm;

    public ApplicationVersionResponse(String gatewayAppId, String gatewayAppVersion, Organization organization, String name, String description, Long challengeCounter, Long validationCounter, Date createdOn, Date updatedOn, String ocraAlgorithm) {
        this.gatewayAppId = gatewayAppId;
        this.gatewayAppVersion = gatewayAppVersion;
        this.organization = organization;
        this.name = name;
        this.description = description;
        this.challengeCounter = challengeCounter;
        this.validationCounter = validationCounter;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.ocraAlgorithm = ocraAlgorithm;
    }

    public String getGatewayAppId() {
        return gatewayAppId;
    }

    public void setGatewayAppId(String gatewayAppId) {
        this.gatewayAppId = gatewayAppId;
    }

    public String getGatewayAppVersion() {
        return gatewayAppVersion;
    }

    public void setGatewayAppVersion(String gatewayAppVersion) {
        this.gatewayAppVersion = gatewayAppVersion;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

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

    public Long getChallengeCounter() {
        return challengeCounter;
    }

    public void setChallengeCounter(Long challengeCounter) {
        this.challengeCounter = challengeCounter;
    }

    public Long getValidationCounter() {
        return validationCounter;
    }

    public void setValidationCounter(Long validationCounter) {
        this.validationCounter = validationCounter;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getOcraAlgorithm() {return ocraAlgorithm;}

    public void setOcraAlgorithm(String ocraAlgorithm) {this.ocraAlgorithm = ocraAlgorithm;}

    @Override
    public String toString() {
        return "ApplicationVersionResponse{" +
                "gatewayAppId='" + gatewayAppId + '\'' +
                ", gatewayAppVersion='" + gatewayAppVersion + '\'' +
                ", organization=" + organization +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", challengeCounter=" + challengeCounter +
                ", validationCounter=" + validationCounter +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", ocraAlgorithm='" + ocraAlgorithm + '\'' +
                '}';
    }
}
