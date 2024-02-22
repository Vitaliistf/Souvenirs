package org.vitaliistf.souvenirs.validation;

import org.vitaliistf.souvenirs.model.Manufacturer;

/**
 * Validator implementation for validating Manufacturer objects.
 */
public class ManufacturerValidator implements Validator<Manufacturer> {

    /**
     * Validates a Manufacturer object.
     *
     * @param manufacturer The Manufacturer object to validate.
     * @return The ValidationResult object containing validation errors, if any.
     */
    public ValidationResult validate(Manufacturer manufacturer) {
        ValidationResult result = new ValidationResult();

        if (manufacturer == null) {
            result.addError("Manufacturer cannot be null.");
            return result;
        }

        if (manufacturer.getName() == null || manufacturer.getName().isBlank()) {
            result.addError("Name cannot be empty.");
        }

        if (manufacturer.getCountry() == null || manufacturer.getCountry().isBlank()) {
            result.addError("Country cannot be empty.");
        }

        return result;
    }
}