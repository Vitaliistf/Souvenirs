package org.vitaliistf.souvenirs.validation;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ValidationResultTest {

    private ValidationResult result;

    @BeforeMethod
    public void setUp() {
        result = new ValidationResult();
    }

    @Test
    public void testValidationResultSuccess() {
        Assert.assertTrue(result.isSuccessful());
    }

    @Test
    public void testValidationResultFailure() {
        result.addError("Error message");
        Assert.assertFalse(result.isSuccessful());
    }

    @Test
    public void testValidationResultGetErrors() {
        result.addError("Error 1");
        result.addError("Error 2");
        List<String> errors = result.getErrors();
        Assert.assertEquals(errors.size(), 2);
        Assert.assertTrue(errors.contains("Error 1"));
        Assert.assertTrue(errors.contains("Error 2"));
    }

    @Test
    public void testValidationResultImmutability() {
        result.addError("Error message");
        List<String> errors = result.getErrors();
        errors.add("Another error"); // Attempt to modify the list obtained from ValidationResult
        Assert.assertEquals(result.getErrors().size(), 1); // Ensure the original list remains unchanged
    }
}
