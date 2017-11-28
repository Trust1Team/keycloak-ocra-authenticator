package com.t1t.keycloak;

public class OcraConfig {
    private volatile String ocraSeed;
    private volatile String ocraAlgorithm;
    private volatile String apikey;
    private String uriOcraApi;
    private String uriSmsApi;

    public String getOcraSeed() {
        return ocraSeed;
    }

    public void setOcraSeed(String ocraSeed) {
        this.ocraSeed = ocraSeed;
    }

    public String getOcraAlgorithm() {
        return ocraAlgorithm;
    }

    public void setOcraAlgorithm(String ocraAlgorithm) {
        this.ocraAlgorithm = ocraAlgorithm;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getUriOcraApi() {
        return uriOcraApi;
    }

    public void setUriOcraApi(String uriOcraApi) {
        this.uriOcraApi = uriOcraApi;
    }

    public String getUriSmsApi() {
        return uriSmsApi;
    }

    public void setUriSmsApi(String uriSmsApi) {
        this.uriSmsApi = uriSmsApi;
    }

    @Override
    public String toString() {
        return "OcraConfig{" +
                "apikey='" + apikey + '\'' +
                ", uriOcraApi='" + uriOcraApi + '\'' +
                ", uriSmsApi='" + uriSmsApi + '\'' +
                '}';
    }
}
