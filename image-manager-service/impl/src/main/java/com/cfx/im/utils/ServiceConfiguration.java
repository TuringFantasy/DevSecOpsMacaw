package com.cfx.im.utils;

import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.cfx.service.api.config.Configuration;

public class ServiceConfiguration {

    private Properties configProperties;
    private static ServiceConfiguration _instance = new ServiceConfiguration();

    public static ServiceConfiguration getInstance() {
        return _instance;
    }

    public void init(Configuration config) {
        this.configProperties = config.toProperties();
    }

    public String getRequiredProperty(String propertyName, String errorMessage) {
        String value = configProperties.getProperty(propertyName);
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    public SecretKey getSecretKey() {
        String decodedDataKeyBytes = getRequiredProperty("io.macaw.objectstorage.key.secret", "Secret key not found in configuration");
        return new SecretKeySpec(decodedDataKeyBytes.getBytes(), "AES");
    }
}
