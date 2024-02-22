package org.vitaliistf.souvenirs.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {

    private final List<String> errors;

    /**
     * Constructs a new ValidationResult.
     */
    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    /**
     * Adds an error message to the validation result.
     *
     * @param error The error message to add.
     */
    public void addError(String error) {
        errors.add(error);
    }

    /**
     * Checks if the validation was successful.
     *
     * @return True if there are no errors, false otherwise.
     */
    public boolean isSuccessful() {
        return errors.isEmpty();
    }

    /**
     * Retrieves the list of errors.
     *
     * @return The list of errors.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
}
