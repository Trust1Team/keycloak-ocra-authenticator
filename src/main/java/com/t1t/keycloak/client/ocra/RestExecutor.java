package com.t1t.keycloak.client.ocra;

import com.google.gson.Gson;
import okhttp3.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import javax.naming.CommunicationException;
import java.io.IOException;

/**
 * @author Guillaume Vandecasteele
 * @since 2017
 */
public class RestExecutor {
    private static final Logger log = LoggerFactory.getLogger(RestExecutor.class);

    public static final synchronized  <T> T executeCall(Call<T> call) throws Exception {
        try {
            Response<T> response = call.execute();
            if (call.isExecuted() && response.isSuccessful()) {
                log.debug("Response data: {}", new Gson().toJson(response.body()));
                return response.body();
            } else {
                Integer httpCode = null;
                String url = null;
                StringBuilder message = new StringBuilder();
                String jsonError = null;
                if (response.errorBody() != null) {
                    boolean isJson = MediaType.parse("application/json").equals(response.errorBody().contentType());
                    if (isJson) {
                        jsonError = response.errorBody().source().readUtf8();
                    } else {
                        if (StringUtils.isNotBlank(response.errorBody().string())) {
                            log.error("Something went wrong: {}", response.errorBody().string());
                            message.append(response.errorBody().string());
                        }
                    }
                }
                if (response.raw() != null) {
                    log.error("Something went wrong, code: {}, message: {}", response.raw().code(), response.raw().message());
                    httpCode = response.raw().code();
                    if (StringUtils.isNotBlank(message.toString())) {
                        message.append(" - ");
                    }
                    message.append(response.raw().message());
                    if (response.raw().request() != null && response.raw().request().url() != null) {
                        url = response.raw().request().url().toString();
                    }
                }
                throw new CommunicationException(message.toString());
            }
        } catch (IOException ex) {
            log.error("Error executing request: ", ex.getMessage());
            throw new CommunicationException(ex.getMessage());
        }
    }

    public static synchronized <T> T returnData(Call<T> call) throws Exception {
        if (call != null) {
            return executeCall(call);
        }
        return null;
    }
}