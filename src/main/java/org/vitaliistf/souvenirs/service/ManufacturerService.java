package org.vitaliistf.souvenirs.service;

import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.repository.ManufacturerRepository;
import org.vitaliistf.souvenirs.repository.SouvenirRepository;
import org.vitaliistf.souvenirs.validation.ManufacturerValidator;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A service class providing operations related to manufacturers.
 */
public class ManufacturerService {
    private final SouvenirRepository souvenirRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerValidator validator;

    /**
     * Constructs a new ManufacturerService instance.
     *
     * @param validator              The validator for manufacturer objects.
     * @param souvenirRepository     The repository for souvenir objects.
     * @param manufacturerRepository The repository for manufacturer objects.
     */
    public ManufacturerService(ManufacturerValidator validator, SouvenirRepository souvenirRepository,
                               ManufacturerRepository manufacturerRepository) {
        this.souvenirRepository = souvenirRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.validator = validator;
    }

    /**
     * Adds a new manufacturer to the system.
     *
     * @param manufacturer The manufacturer to add.
     * @return The validation result indicating success or failure.
     */
    public ValidationResult addManufacturer(Manufacturer manufacturer) {
        ValidationResult result = validator.validate(manufacturer);
        if (!result.isSuccessful()) {
            return result;
        }

        if (!manufacturerRepository.add(manufacturer)) {
            result.addError("This manufacturer is already present in the system.");
        }
        return result;
    }

    /**
     * Updates an existing manufacturer in the system.
     *
     * @param manufacturer The updated manufacturer information.
     * @return The validation result indicating success or failure.
     */
    public ValidationResult updateManufacturer(Manufacturer manufacturer) {
        ValidationResult result = validator.validate(manufacturer);
        if (!result.isSuccessful()) {
            return result;
        }

        if (manufacturerRepository.getById(manufacturer.getId()).isEmpty()) {
            result.addError("There is no manufacturer with such id in system.");
        } else if (!manufacturerRepository.update(manufacturer)) {
            result.addError("This manufacturer is already present in the system.");
        }
        return result;
    }

    /**
     * Deletes a manufacturer from the system along with its associated souvenirs.
     *
     * @param manufacturerId The ID of the manufacturer to delete.
     * @return true if the manufacturer was successfully deleted, false otherwise.
     */
    public boolean deleteManufacturer(Long manufacturerId) {
        if (manufacturerRepository.remove(manufacturerId)) {
            souvenirRepository.getByManufacturerId(manufacturerId)
                    .stream()
                    .map(Souvenir::getId)
                    .forEach(souvenirRepository::remove);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves all manufacturers present in the system.
     *
     * @return A list of all manufacturers.
     */
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.getAll();
    }

    /**
     * Retrieves manufacturers from whom souvenirs with a price less than or equal to the given price are available.
     *
     * @param price The maximum price of souvenirs.
     * @return A list of manufacturers meeting the criteria.
     */
    public List<Manufacturer> getManufacturersByMaxPrice(double price) {
        return manufacturerRepository.getAll().stream()
                .filter(m ->
                        souvenirRepository.getByManufacturerId(m.getId())
                                .stream()
                                .allMatch(s -> s.getPrice() <= price))
                .toList();
    }

    /**
     * Retrieves a map of manufacturers with their associated souvenirs.
     *
     * @return A map where each manufacturer is mapped to a list of its associated souvenirs.
     */
    public Map<Manufacturer, List<Souvenir>> getManufacturersWithSouvenirs() {
        return manufacturerRepository.getAll().stream()
                .collect(Collectors.toMap(
                        manufacturer -> manufacturer,
                        manufacturer -> souvenirRepository.getByManufacturerId(manufacturer.getId())
                ));
    }

    /**
     * Retrieves manufacturers of a given souvenir produced in the specified year.
     *
     * @param souvenirName The name of the souvenir.
     * @param year         The production year of the souvenir.
     * @return A list of manufacturers producing the specified souvenir in the given year.
     */
    public List<Manufacturer> getManufacturersOfSouvenirByYear(String souvenirName, int year) {
        return souvenirRepository.getByName(souvenirName).stream()
                .filter(souvenir -> souvenir.getProductionDate().getYear() == year)
                .map(souvenir -> manufacturerRepository.getById(souvenir.getManufacturerId()).orElse(null))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of unique countries where manufacturers are located.
     *
     * @return A list of countries where manufacturers are located.
     */
    public List<String> getManufacturersCountries() {
        return getAllManufacturers().stream()
                .map(Manufacturer::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }
}
