package com.t1t.keycloak.client.sms;

import com.t1t.keycloak.client.sms.model.SmsResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public interface SmsClient {
    @POST("sms")
    Call<SmsResponse> activate();
}
