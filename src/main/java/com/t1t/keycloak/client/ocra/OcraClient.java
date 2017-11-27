package com.t1t.keycloak.client.ocra;

import com.t1t.keycloak.client.ocra.model.OcraChallengeResponse;
import com.t1t.keycloak.client.ocra.model.OcraValidationResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public interface OcraClient {

    @POST("ocra/challenge")
    Call<OcraChallengeResponse> getChallenge();

    @POST("ocra/validate")
    Call<OcraValidationResponse> validate();
}
