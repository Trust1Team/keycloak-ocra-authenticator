package com.t1t.keycloak.client.ocra;

import com.t1t.keycloak.client.ocra.model.ChallengeResponse;
import com.t1t.keycloak.client.ocra.model.ValidationResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public interface OcraClient {

    @POST("ocra/challenge")
    Call<ChallengeResponse> getChallenge();

    @POST("ocra/validate")
    Call<ValidationResponse> validate();
}
