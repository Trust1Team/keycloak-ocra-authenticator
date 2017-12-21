package com.t1t.keycloak.client.sms.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
//TODO powermock
public class SmsServiceTest {
    @Ignore
    @Test
    public void sendSms() throws Exception {
        SmsService smsService = new SmsService();
        Boolean response = smsService.sendSms("32478404976", "Hi malaka");
        assertTrue(response);
    }

}