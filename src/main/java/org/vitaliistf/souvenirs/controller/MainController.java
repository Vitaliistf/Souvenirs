package org.vitaliistf.souvenirs.controller;

import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.service.ManufacturerService;
import org.vitaliistf.souvenirs.service.SouvenirService;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.util.List;
import java.util.Map;

/**
 * Controller class responsible for handling requests related to manufacturers and souvenirs.
 * (Usage of "Facade" design pattern.)
 */
public class MainController {

    private final ManufacturerService manufacturerService;
    private final SouvenirService souvenirService;

    /**
     * Constructs a new MainController with the specified services.
     *
     * @param manufacturerService The service for managing manufacturers.
     * @param souvenirService     The service for managing souvenirs.
     */
    public MainController(ManufacturerService manufacturerService, SouvenirService souvenirService) {
        this.manufacturerService = manufacturerService;
        this.souvenirService = souvenirService;
    }

    /**
     * Adds a new manufacturer.
     *
     * @param manufacturer The manufacturer to add.
     * @return The result of the validation for adding the manufacturer.
     */
    public ValidationResult addManufacturer(Manufacturer manufacturer) {
        return manufacturerService.addManufacturer(manufacturer);
    }

    /**
     * Updates an existing manufacturer.
     *
     * @param manufacturer The manufacturer to update.
     * @return The result of the validation for updating the manufacturer.
     */
    public ValidationResult updateManufacturer(Manufacturer manufacturer) {
        return manufacturerService.updateManufacturer(manufacturer);
    }

    /**
     * Deletes a manufacturer by its ID.
     *
     * @param id The ID of the manufacturer to delete.
     * @return True if the manufacturer was successfully deleted, otherwise false.
     */
    public boolean deleteManufacturer(Long id) {
        return manufacturerService.deleteManufacturer(id);
    }

    /**
     * Retrieves a list of all manufacturers.
     *
     * @return A list of all manufacturers.
     */
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    /**
     * Retrieves a list of manufacturers where the price of souvenirs is less than the specified price.
     *
     * @param price The maximum price of souvenirs.
     * @return A list of manufacturers.
     */
    public List<Manufacturer> getWhereSouvenirPriceIsLess(double price) {
        return manufacturerService.getManufacturersByMaxPrice(price);
    }

    /**
     * Retrieves a map of all manufacturers with their associated souvenirs.
     *
     * @return A map where the key is the manufacturer and the value is a list of souvenirs.
     */
    public Map<Manufacturer, List<Souvenir>> getAllManufacturersWithSouvenirs() {
        return manufacturerService.getManufacturersWithSouvenirs();
    }

    /**
     * Retrieves a list of manufacturers that produce a specific souvenir in a given year.
     *
     * @param souvenirName The name of the souvenir.
     * @param year         The year of production.
     * @return A list of manufacturers.
     */
    public List<Manufacturer> getManufacturersOfSouvenirByYear(String souvenirName, int year) {
        return manufacturerService.getManufacturersOfSouvenirByYear(souvenirName, year);
    }

    /**
     * Retrieves a list of distinct manufacturers countries.
     *
     * @return A list of countries.
     */
    public List<String> getManufacturersCountries() {
        return manufacturerService.getManufacturersCountries();
    }

    /**
     * Adds a new souvenir.
     *
     * @param souvenir The souvenir to add.
     * @return The result of the validation for adding the souvenir.
     */
    public ValidationResult addSouvenir(Souvenir souvenir) {
        return souvenirService.addSouvenir(souvenir);
    }

    /**
     * Updates an existing souvenir.
     *
     * @param souvenir The souvenir to update.
     * @return The result of the validation for updating the souvenir.
     */
    public ValidationResult updateSouvenir(Souvenir souvenir) {
        return souvenirService.updateSouvenir(souvenir);
    }

    /**
     * Deletes a souvenir by its ID.
     *
     * @param id The ID of the souvenir to delete.
     * @return True if the souvenir was successfully deleted, otherwise false.
     */
    public boolean deleteSouvenir(Long id) {
        return souvenirService.deleteSouvenir(id);
    }

    /**
     * Retrieves a list of all souvenirs.
     *
     * @return A list of all souvenirs.
     */
    public List<Souvenir> getAllSouvenirs() {
        return souvenirService.getAllSouvenirs();
    }

    /**
     * Retrieves a list of souvenirs produced by a specific manufacturer.
     *
     * @param manufacturerId The ID of the manufacturer.
     * @return A list of souvenirs produced by the manufacturer.
     */
    public List<Souvenir> getSouvenirsByManufacturer(Long manufacturerId) {
        return souvenirService.getSouvenirsByManufacturer(manufacturerId);
    }

    /**
     * Retrieves a list of souvenirs from a specific country.
     *
     * @param country The country of origin for souvenirs.
     * @return A list of souvenirs from the specified country.
     */
    public List<Souvenir> getSouvenirsByCountry(String country) {
        return souvenirService.getSouvenirsByCountry(country);
    }

    /**
     * Retrieves a map where the key is the year and the value is a list of souvenirs produced in that year.
     *
     * @return A map where the key is the year and the value is a list of souvenirs.
     */
    public Map<Integer, List<Souvenir>> getSouvenirsByYear() {
        return souvenirService.getSouvenirsByYear();
    }
}
