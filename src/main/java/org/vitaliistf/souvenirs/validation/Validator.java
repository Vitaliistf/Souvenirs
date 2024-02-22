package org.vitaliistf.souvenirs.validation;

/**
 * Interface for validating objects of a specific type.
 *
 * @param <T> The type of object to be validated.
 */
public interface Validator<T> {

    /**
     * Validates the given object.
     *
     * @param object The object to be validated.
     * @return The result of the validation.
     */
    ValidationResult validate(T object);

}
