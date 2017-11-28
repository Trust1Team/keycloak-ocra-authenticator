package com.t1t.keycloak;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by michallispashidis on 31/10/2017.
 * Reads a predef file in /usr/local/ocra/ocra.conf to retrieve:
 * <ul>
 *     <li>OCRA seed</li>
 *     <li>OCRA algorithm</li>
 * </ul>
 */
public class OcraConfigParser implements Serializable {
    /*Logger*/
    private static Logger log = LoggerFactory.getLogger(OcraConfigParser.class.getName());
    private static String OCRA_SEED = "keycloak-ocra.ocra.seed";
    private static String OCRA_ALGORITHM = "keycloak-ocra.ocra.algorithm";
    private static String APIKEY = "keycloak-ocra.apikey";
    private static String URI_OCRA_API = "keycloak-ocra.ocra-api-uri";
    private static String URI_SMS_API = "keycloak-ocra.sms-api-uri";

    private static Path ocraConfigPath = Paths.get("/usr/local/ocra/ocra.conf");
    private OcraConfig ocraConfig;

    public OcraConfigParser() throws FileNotFoundException {
        this(ocraConfigPath);
    }

    //config by path param
    public OcraConfigParser(Path ocraPath) throws FileNotFoundException {
        this.ocraConfig = new OcraConfig();
        Config config;
        //resolve configuration
        if (ocraPath != null && ocraPath.toFile().exists()) {
            config = ConfigFactory.parseFile(ocraPath.toFile());
            this.ocraConfig.setOcraSeed(config.getString(OCRA_SEED));
            this.ocraConfig.setOcraAlgorithm(config.getString(OCRA_ALGORITHM));
            this.ocraConfig.setApikey(config.getString(APIKEY));
            this.ocraConfig.setUriOcraApi(config.getString(URI_OCRA_API));
            this.ocraConfig.setUriSmsApi(config. getString(URI_SMS_API));
        } else
            throw new FileNotFoundException(ocraConfigPath.toAbsolutePath().toString());
    }

    public OcraConfig getOcraConfig() {
        return ocraConfig;
    }
}
