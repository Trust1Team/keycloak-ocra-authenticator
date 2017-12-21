package com.t1t.keycloak.client;

import com.google.gson.*;
import com.t1t.keycloak.OcraConfig;
import com.t1t.keycloak.OcraConfigParser;
import com.t1t.keycloak.client.ocra.OcraClient;
import com.t1t.keycloak.client.sms.SmsClient;
import com.t1t.keycloak.utils.UriUtils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;

/**
 * @Author Michallis Pashidis
 * @Since 2017
 */
public final class RestServiceBuilder {
    private static final Logger log = LoggerFactory.getLogger(RestServiceBuilder.class);
    /* Headers */
    private static final String APIKEY_HEADER_NAME = "apikey";

    private RestServiceBuilder() {
    }

    private static OcraConfig config;

    /**
     * Get SMS rest client and provision apikey.
     *
     * @return
     */
    public static SmsClient getSmsRestClient() throws Exception {
        initConfig();
        return getClient(UriUtils.uriFinalSlashAppender(config.getUriSmsApi()), SmsClient.class, config.getApikey());
    }

    private static void initConfig() throws FileNotFoundException {
        if (config == null) config = (new OcraConfigParser()).getOcraConfig();
    }

    /**
     * Get Ocra rest client and provision apikey.
     *
     * @return
     * @throws Exception
     */
    public static OcraClient getOcraRestClient() throws Exception {
        initConfig();
        return getClient(UriUtils.uriFinalSlashAppender(config.getUriOcraApi()), OcraClient.class, config.getApikey());
    }


    /**
     * Return an abstract client REST interface
     *
     * @param uri
     * @param iFace
     * @param apikey
     * @param <T>
     * @return
     */
    private static <T> T getClient(String uri, Class<T> iFace, String apikey) throws Exception {
        GsonBuilder builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        try {
            Builder retrofitBuilder = new Builder()
                    .client(gethttpClient(apikey))
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .baseUrl(uri);
            return retrofitBuilder.build().create(iFace);
        } catch (Exception ex) {
            log.error("Error creating client: ", ex);
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Builds a http client instance.
     *
     * @param apikey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyManagementException
     * @throws KeyStoreException
     * @throws IOException
     */
    private static OkHttpClient gethttpClient(final String apikey) throws NoSuchAlgorithmException, CertificateException, KeyManagementException, KeyStoreException, IOException {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        final boolean apikeyPresent = StringUtils.isNotBlank(apikey);
        if (apikeyPresent) {
            okHttpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    if (apikeyPresent) {
                        requestBuilder.addHeader(APIKEY_HEADER_NAME, apikey);
                    }
                    return chain.proceed(requestBuilder.build());
                }
            });
        }
        return okHttpBuilder.build();
    }

    public static OcraConfig getConfig() {
        return config;
    }
}