package org.vitaliistf.souvenirs.validation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.time.LocalDate;

public class SouvenirValidatorTest {

    private SouvenirValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new SouvenirValidator();
    }

    @DataProvider(name = "souvenirData")
    public Object[][] souvenirData() {
        return new Object[][] {
                { null, false, 1},
                { new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), 10.99), true, 0 },
                { new Souvenir("", 1, LocalDate.of(2023, 5, 15), 10.99), false, 1 },
                { new Souvenir("Mug", -1, LocalDate.of(2023, 5, 15), 12.49), false, 1 },
                { new Souvenir("T-Shirt", 2, LocalDate.of(2025, 5, 15), 19.99), false, 1 },
                { new Souvenir("Keychain", 1, LocalDate.of(2023, 5, 15), -10.99), false, 1 }
        };
    }

    @Test(dataProvider = "souvenirData")
    public void testValidateSouvenir(Souvenir souvenir, boolean expectedSuccess, int expectedErrorCount) {
        ValidationResult validationResult = validator.validate(souvenir);

        Assert.assertEquals(validationResult.isSuccessful(), expectedSuccess);
        Assert.assertEquals(validationResult.getErrors().size(), expectedErrorCount);
    }
}
