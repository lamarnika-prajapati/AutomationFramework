package org.tcs.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads values from config.properties in resources folder.
 */
public class ConfigReader {
    private static final Logger LOGGER = LogManager.getLogger(ConfigReader.class);
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in resources folder");
            }

            props.load(input);
            LOGGER.info("✅ Loaded {} properties from config.properties", props.size());

        } catch (IOException e) {
            LOGGER.error("❌ Failed to load config.properties file", e);
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    /**
     * Returns property value for given key.
     *
     * @throws RuntimeException if key not found
     */
    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            LOGGER.error("❌ Property '{}' not found in config.properties", key);
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        LOGGER.debug("Fetched property [{}={}]", key, value);
        return value.trim();
    }
}
