package com.restfulbooker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfProperties {

    private static final Logger LOG = LogManager.getLogger(ConfProperties.class.getName());
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return properties.getProperty("base_url");
    }

    public static Integer getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public static String getValidUsername() {
        return properties.getProperty("valid_username");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid_password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalid_username");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid_password");
    }

}



