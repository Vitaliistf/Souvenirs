package org.vitaliistf.souvenirs.repository.implementation;

import org.vitaliistf.souvenirs.config.ConfigReader;
import org.vitaliistf.souvenirs.filemanager.FileManager;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.repository.ManufacturerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of the ManufacturerRepository interface that stores manufacturers in memory.
 * (Usage of "Singleton" design pattern.)
 */
public class InMemoryManufacturerRepository implements ManufacturerRepository {

    private final FileManager<Manufacturer> fileManager;
    private final Set<Manufacturer> manufacturers;
    private static InMemoryManufacturerRepository instance;

    /**
     * Constructs a new InMemoryManufacturerRepository instance.
     *
     * @throws RuntimeException If an error occurs while reading the file path from the properties file.
     */
    private InMemoryManufacturerRepository() {
        this.fileManager = new FileManager<>(ConfigReader.getProperty("manufacturers.file-path").orElseThrow(
                    () -> new RuntimeException(
                            "Manufacturers file path is not specified in application.properties."
                    )));
        this.manufacturers = fileManager.loadFromFile();
    }

    /**
     * Gets the singleton instance of the repository.
     *
     * @return The singleton instance of InMemoryManufacturerRepository.
     */
    public static InMemoryManufacturerRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryManufacturerRepository();
        }
        return instance;
    }

    /**
     * Adds a manufacturer to the repository.
     *
     * @param manufacturer The manufacturer to add.
     * @return true if the manufacturer was successfully added, false otherwise.
     */
    @Override
    public boolean add(Manufacturer manufacturer) {
        manufacturer = manufacturer.clone();
        manufacturer.setId(getNextId());
        boolean result = manufacturers.add(manufacturer);
        if (result) {
            fileManager.saveToFile(manufacturers);
        }
        return result;
    }

    /**
     * Updates an existing manufacturer in the repository.
     *
     * @param updatedManufacturer The updated manufacturer.
     * @return true if the manufacturer was successfully updated, false otherwise.
     */
    @Override
    public boolean update(Manufacturer updatedManufacturer) {
        // If such manufacturer is present - revert repository to its state before deletion
        updatedManufacturer = updatedManufacturer.clone();
        Optional<Manufacturer> presentManufacturer = getById(updatedManufacturer.getId());
        if (remove(updatedManufacturer.getId()) && manufacturers.add(updatedManufacturer)) {
            fileManager.saveToFile(manufacturers);
            return true;
        } else {
            presentManufacturer.ifPresent(manufacturers::add);
        }
        return false;
    }

    /**
     * Removes a manufacturer from the repository by its ID.
     *
     * @param id The ID of the manufacturer to remove.
     * @return true if the manufacturer was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Long id) {
        boolean result = manufacturers.removeIf(manufacturer -> manufacturer.getId() == id);
        if (result) {
            fileManager.saveToFile(manufacturers);
        }
        return result;
    }

    /**
     * Retrieves all manufacturers from the repository.
     *
     * @return A list of all manufacturers in the repository.
     */
    @Override
    public List<Manufacturer> getAll() {
        return manufacturers.stream()
                .map(Manufacturer::clone)
                .toList();
    }

    /**
     * Retrieves a manufacturer from the repository by its ID.
     *
     * @param id The ID of the manufacturer to retrieve.
     * @return An Optional containing the retrieved manufacturer,
     * or an empty Optional if the manufacturer was not found.
     */
    @Override
    public Optional<Manufacturer> getById(Long id) {
        return manufacturers.stream()
                .filter(manufacturer -> manufacturer.getId() == id)
                .map(Manufacturer::clone)
                .findFirst();
    }

    /**
     * Retrieves all manufacturers from the repository with the specified country.
     *
     * @param country The country of the manufacturers to retrieve.
     * @return A list of manufacturers with the specified country.
     */
    @Override
    public List<Manufacturer> getByCountry(String country) {
        return manufacturers.stream()
                .filter(manufacturer -> manufacturer.getCountry().equals(country))
                .map(Manufacturer::clone)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the next available ID for a manufacturer.
     *
     * @return The next available ID.
     */
    private long getNextId() {
        return manufacturers.stream()
                .map(Manufacturer::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}