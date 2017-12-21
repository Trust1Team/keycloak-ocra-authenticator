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
public class ValidationResponse implements Serializable{
    Boolean validationResult;
    AuditBean auditRecord;

    public ValidationResponse() {}

    public ValidationResponse(Boolean validationResult, AuditBean auditRecord) {
        this.validationResult = validationResult;
        this.auditRecord = auditRecord;
    }

    public Boolean getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(Boolean validationResult) {
        this.validationResult = validationResult;
    }

    public AuditBean getAuditRecord() {
        return auditRecord;
    }

    public void setAuditRecord(AuditBean auditRecord) {
        this.auditRecord = auditRecord;
    }

    @Override
    public String toString() {
        return "ValidationResponse{" +
                "validationResult=" + validationResult +
                ", auditRecord=" + auditRecord +
                '}';
    }
}
