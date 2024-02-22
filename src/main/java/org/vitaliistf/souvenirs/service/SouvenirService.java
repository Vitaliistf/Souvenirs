package org.vitaliistf.souvenirs.service;

import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.repository.ManufacturerRepository;
import org.vitaliistf.souvenirs.repository.SouvenirRepository;
import org.vitaliistf.souvenirs.validation.SouvenirValidator;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A service class providing operations related to souvenirs.
 */
public class SouvenirService {

    private final SouvenirRepository souvenirRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final SouvenirValidator validator;

    /**
     * Constructs a new SouvenirService instance.
     *
     * @param validator              The validator for souvenir objects.
     * @param souvenirRepository     The repository for souvenir objects.
     * @param manufacturerRepository The repository for manufacturer objects.
     */
    public SouvenirService(SouvenirValidator validator, SouvenirRepository souvenirRepository,
                           ManufacturerRepository manufacturerRepository) {
        this.souvenirRepository = souvenirRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.validator = validator;
    }

    /**
     * Adds a new souvenir to the system.
     *
     * @param souvenir The souvenir to add.
     * @return The validation result indicating the success of the operation.
     */
    public ValidationResult addSouvenir(Souvenir souvenir) {
        ValidationResult result = validator.validate(souvenir);
        if (!result.isSuccessful()) {
            return result;
        }

        Optional<Manufacturer> manufacturer = manufacturerRepository.getById(souvenir.getManufacturerId());
        if (manufacturer.isEmpty()) {
            result.addError("There is no such manufacturer in the system.");
        } else if (!souvenirRepository.add(souvenir)) {
            result.addError("This souvenir is already present in the system.");
        }
        return result;
    }

    /**
     * Updates an existing souvenir in the system.
     *
     * @param souvenir The updated souvenir object.
     * @return The validation result indicating the success of the operation.
     */
    public ValidationResult updateSouvenir(Souvenir souvenir) {
        ValidationResult result = validator.validate(souvenir);
        if (!result.isSuccessful()) {
            return result;
        }

        if (souvenirRepository.getById(souvenir.getId()).isEmpty()) {
            result.addError("There is no souvenir with such id in system.");
        } else if (manufacturerRepository.getById(souvenir.getManufacturerId()).isEmpty()) {
            result.addError("There is no such manufacturer in the system.");
        } else if (!souvenirRepository.update(souvenir)) {
            result.addError("This souvenir is already present in the system.");
        }
        return result;
    }

    /**
     * Deletes a souvenir from the system.
     *
     * @param souvenirId The ID of the souvenir to be deleted.
     * @return True if the souvenir was successfully deleted, false otherwise.
     */
    public boolean deleteSouvenir(Long souvenirId) {
        return souvenirRepository.remove(souvenirId);
    }

    /**
     * Retrieves all souvenirs from the system.
     *
     * @return A list containing all souvenirs in the system.
     */
    public List<Souvenir> getAllSouvenirs() {
        return souvenirRepository.getAll();
    }

    /**
     * Retrieves souvenirs associated with a specific manufacturer.
     *
     * @param manufacturerId The ID of the manufacturer.
     * @return A list containing souvenirs associated with the specified manufacturer.
     */
    public List<Souvenir> getSouvenirsByManufacturer(Long manufacturerId) {
        return souvenirRepository.getByManufacturerId(manufacturerId);
    }

    /**
     * Retrieves souvenirs associated with manufacturers from a specific country.
     *
     * @param manufacturerCountry The country of the manufacturers.
     * @return A list containing souvenirs associated with manufacturers from the specified country.
     */
    public List<Souvenir> getSouvenirsByCountry(String manufacturerCountry) {
        return manufacturerRepository.getByCountry(manufacturerCountry)
                .stream()
                .map(m -> souvenirRepository.getByManufacturerId(m.getId()))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves souvenirs grouped by production year.
     *
     * @return A map containing souvenirs grouped by production year.
     */
    public Map<Integer, List<Souvenir>> getSouvenirsByYear() {
        return souvenirRepository.getAll().stream()
                .collect(Collectors.groupingBy(souvenir -> souvenir.getProductionDate().getYear()));
    }

}
