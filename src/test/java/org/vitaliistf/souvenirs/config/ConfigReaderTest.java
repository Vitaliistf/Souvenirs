package org.vitaliistf.souvenirs.config;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class ConfigReaderTest {

    @Test
    public void testExistingProperty() {
        String propertyName = "existing.property";
        String expectedValue = "existingValue";

        Optional<String> propertyValue = ConfigReader.getProperty(propertyName);

        Assert.assertTrue(propertyValue.isPresent());
        Assert.assertEquals(propertyValue.get(), expectedValue);
    }

    @Test
    public void testNonExistingProperty() {
        String propertyName = "non.existing.property";

        Optional<String> propertyValue = ConfigReader.getProperty(propertyName);

        Assert.assertFalse(propertyValue.isPresent());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionIsThrown() {
        /* For this test, you need to prepare invalid environment where IOException is thrown
           (delete application.properties) */
        String invalidPropertyName = "invalid.property.name";
        ConfigReader.getProperty(invalidPropertyName);
    }
}

