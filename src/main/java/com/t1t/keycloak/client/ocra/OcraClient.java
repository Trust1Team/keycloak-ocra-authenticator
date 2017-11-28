package com.t1t.keycloak.client.ocra;

import com.t1t.keycloak.client.ocra.model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public interface OcraClient {

    @POST("ocra/challenge")
    Call<ChallengeResponse> getChallenge(@Body ChallengeRequest request);

    @POST("ocra/validate")
    Call<ValidationResponse> validate(@Body ValidationRequest request);

    @GET("applications")
    Call<ApplicationVersionResponse> getApplication();

    @POST("applications")
    Call<ApplicationVersionResponse> updateApplication(@Body UpdateApplicationVersionRequest request);
}
