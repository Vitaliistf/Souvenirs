package org.vitaliistf.souvenirs.validation;

import org.vitaliistf.souvenirs.model.Souvenir;

import java.time.LocalDate;

/**
 * Validator implementation for validating Souvenir objects.
 */
public class SouvenirValidator implements Validator<Souvenir> {

    /**
     * Validates a Souvenir object.
     *
     * @param souvenir The Souvenir object to validate.
     * @return The ValidationResult object containing validation errors, if any.
     */
    public ValidationResult validate(Souvenir souvenir) {
        ValidationResult result = new ValidationResult();

        if (souvenir == null) {
            result.addError("Souvenir cannot be null.");
            return result;
        }

        if (souvenir.getName() == null || souvenir.getName().isBlank()) {
            result.addError("Name cannot be empty.");
        }
        if (souvenir.getManufacturerId() <= 0) {
            result.addError("Manufacturer ID must be greater than 0.");
        }
        if (souvenir.getProductionDate() == null || souvenir.getProductionDate().isAfter(LocalDate.now())) {
            result.addError("Production date must be in the past.");
        }
        if (souvenir.getPrice() <= 0) {
            result.addError("Price cannot be negative or zero.");
        }

        return result;
    }

}

