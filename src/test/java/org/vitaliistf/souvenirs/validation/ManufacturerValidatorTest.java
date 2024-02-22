package org.vitaliistf.souvenirs.validation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.model.Manufacturer;

public class ManufacturerValidatorTest {

    private ManufacturerValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new ManufacturerValidator();
    }

    @DataProvider(name = "manufacturerData")
    public Object[][] manufacturerData() {
        return new Object[][] {
                { null, false, 1},
                { new Manufacturer("ABC Inc.", "USA"), true, 0 },
                { new Manufacturer("", "USA"), false, 1 },
                { new Manufacturer("ABC Inc.", ""), false, 1 },
                { new Manufacturer("", ""), false, 2 }
        };
    }

    @Test(dataProvider = "manufacturerData")
    public void testValidateManufacturer(Manufacturer manufacturer, boolean expectedSuccess, int expectedErrorCount) {
        ValidationResult validationResult = validator.validate(manufacturer);

        Assert.assertEquals(validationResult.isSuccessful(), expectedSuccess);
        Assert.assertEquals(validationResult.getErrors().size(), expectedErrorCount);
    }
}
