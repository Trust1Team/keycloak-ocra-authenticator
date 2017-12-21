package com.t1t.keycloak.client.ocra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditBean implements Serializable {
    private Long id;
    private String gtw_id;
    private Long orgId;
    private String orgName;
    private Long appId;
    private String appName;
    private String appVersion;
    private Long txId;
    private String txSessionId;
    private TxState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGtw_id() {
        return gtw_id;
    }

    public void setGtw_id(String gtw_id) {
        this.gtw_id = gtw_id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public String getTxSessionId() {
        return txSessionId;
    }

    public void setTxSessionId(String txSessionId) {
        this.txSessionId = txSessionId;
    }

    public TxState getState() {
        return state;
    }

    public void setState(TxState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AuditBean{" +
                "id=" + id +
                ", gtw_id='" + gtw_id + '\'' +
                ", orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", appId=" + appId +
                ", appName='" + appName + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", txId=" + txId +
                ", txSessionId='" + txSessionId + '\'' +
                ", state=" + state +
                '}';
    }
}
