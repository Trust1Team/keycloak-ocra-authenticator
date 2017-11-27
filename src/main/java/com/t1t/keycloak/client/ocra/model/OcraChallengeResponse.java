package com.t1t.keycloak.client.ocra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OcraChallengeResponse implements Serializable {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("rs")
    @Expose
    private String rs;
    @SerializedName("qs")
    @Expose
    private String qs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "OcraChallengeResponse{" +
                "id=" + id +
                ", rs='" + rs + '\'' +
                ", qs='" + qs + '\'' +
                '}';
    }
}
