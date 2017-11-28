package com.t1t.keycloak.client.sms.model;

import com.t1t.keycloak.client.RestServiceBuilder;
import com.t1t.keycloak.client.ocra.RestExecutor;
import com.t1t.keycloak.client.sms.SmsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsService {
    private final static Logger log = LoggerFactory.getLogger(SmsService.class.getName());
    private SmsClient smsClient;

    public SmsService() throws Exception {
        this.smsClient = RestServiceBuilder.getSmsRestClient();
    }

    public Boolean sendSms (String gsmNr, String message) throws Exception {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setGsmNr(gsmNr);
        smsRequest.setMessage(message);
        SmsResponse smsResponse = RestExecutor.executeCall(smsClient.sendSms(smsRequest));
        String result = smsResponse.getResult();
        //TODO response should be better - now 0 == true -> not logic
        return true;
    }
}
