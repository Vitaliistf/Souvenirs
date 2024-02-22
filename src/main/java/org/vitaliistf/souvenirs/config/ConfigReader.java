package org.vitaliistf.souvenirs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from the application.properties file.
 */
public class ConfigReader {

    private static final String PROPERTIES_FILE = "application.properties";

    /**
     * Retrieves the value of the specified property from the configuration file.
     *
     * @param propertyName The name of the property to retrieve.
     * @return An Optional containing the value of the property if found, or empty if the property does not exist.
     * @throws RuntimeException If an error occurs while reading the configuration file.
     */
    public static Optional<String> getProperty(String propertyName) {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new IOException("application.properties file not found.");
            }
            properties.load(inputStream);
            return Optional.ofNullable(properties.getProperty(propertyName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
