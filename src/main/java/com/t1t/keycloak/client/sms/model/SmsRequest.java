package com.t1t.keycloak.client.sms.model;

public class SmsRequest {
    private String gsmNr;
    private String message;
    public SmsRequest() {}
    public SmsRequest(String gsmNr, String message) {
        this.gsmNr = gsmNr;
        this.message = message;
    }

    public String getGsmNr() {
        return gsmNr;
    }

    public void setGsmNr(String gsmNr) {
        this.gsmNr = gsmNr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "gsmNr='" + gsmNr + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
