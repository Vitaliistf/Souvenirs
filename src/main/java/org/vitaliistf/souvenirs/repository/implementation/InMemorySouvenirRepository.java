package org.vitaliistf.souvenirs.repository.implementation;

import org.vitaliistf.souvenirs.config.ConfigReader;
import org.vitaliistf.souvenirs.filemanager.FileManager;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.repository.SouvenirRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of the SouvenirRepository interface that stores souvenirs in memory.
 * (Usage of "Singleton" design pattern.)
 */
public class InMemorySouvenirRepository implements SouvenirRepository {

    private final FileManager<Souvenir> fileManager;
    private final Set<Souvenir> souvenirs;
    private static InMemorySouvenirRepository instance;

    /**
     * Constructs a new InMemorySouvenirRepository instance.
     *
     * @throws RuntimeException If an error occurs while reading the file path from the properties file.
     */
    private InMemorySouvenirRepository() {
        this.fileManager = new FileManager<>(ConfigReader.getProperty("souvenirs.file-path").orElseThrow(
                    () -> new RuntimeException("Souvenirs file path is not specified in application.properties.")));

        this.souvenirs = fileManager.loadFromFile();
    }

    /**
     * Gets the singleton instance of the repository.
     *
     * @return The singleton instance of InMemorySouvenirRepository.
     */
    public static InMemorySouvenirRepository getInstance() {
        if (instance == null) {
            instance = new InMemorySouvenirRepository();
        }
        return instance;
    }

    /**
     * Adds a souvenir to the repository.
     *
     * @param souvenir The souvenir to add.
     * @return true if the souvenir was successfully added, false otherwise.
     */
    @Override
    public boolean add(Souvenir souvenir) {
        souvenir = souvenir.clone();
        souvenir.setId(getNextId());
        boolean result = souvenirs.add(souvenir);
        if (result) {
            fileManager.saveToFile(souvenirs);
        }
        return result;
    }

    /**
     * Updates an existing souvenir in the repository.
     *
     * @param updatedSouvenir The updated souvenir.
     * @return true if the souvenir was successfully updated, false otherwise.
     */
    @Override
    public boolean update(Souvenir updatedSouvenir) {
        updatedSouvenir = updatedSouvenir.clone();
        // If souvenir with such data is present - reverting repository to its state before deletion
        Optional<Souvenir> presentSouvenir = getById(updatedSouvenir.getId());
        if (remove(updatedSouvenir.getId()) && souvenirs.add(updatedSouvenir)) {
            fileManager.saveToFile(souvenirs);
            return true;
        } else {
            presentSouvenir.ifPresent(souvenirs::add);
        }
        return false;
    }

    /**
     * Removes a souvenir from the repository by its ID.
     *
     * @param id The ID of the souvenir to remove.
     * @return true if the souvenir was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(Long id) {
        boolean result = souvenirs.removeIf(s -> s.getId() == id);
        if (result) {
            fileManager.saveToFile(souvenirs);
        }
        return result;
    }

    /**
     * Retrieves all souvenirs from the repository.
     *
     * @return A list of all souvenirs in the repository.
     */
    @Override
    public List<Souvenir> getAll() {
        return souvenirs.stream()
                .map(Souvenir::clone)
                .toList();
    }

    /**
     * Retrieves a souvenir from the repository by its ID.
     *
     * @param id The ID of the souvenir to retrieve.
     * @return An Optional containing the retrieved souvenir, or an empty Optional if the souvenir was not found.
     */
    @Override
    public Optional<Souvenir> getById(Long id) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getId() == id)
                .map(Souvenir::clone)
                .findFirst();
    }

    /**
     * Retrieves all souvenirs from the repository with the specified name.
     *
     * @param name The name of the souvenirs to retrieve.
     * @return A list of souvenirs with the specified name.
     */
    @Override
    public List<Souvenir> getByName(String name) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getName().equals(name))
                .map(Souvenir::clone)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all souvenirs from the repository belonging to the specified manufacturer.
     *
     * @param manufacturerId The ID of the manufacturer.
     * @return A list of souvenirs belonging to the specified manufacturer.
     */
    @Override
    public List<Souvenir> getByManufacturerId(Long manufacturerId) {
        return souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturerId() == manufacturerId)
                .map(Souvenir::clone)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the next available ID for a souvenir.
     *
     * @return The next available ID.
     */
    private long getNextId() {
        return souvenirs.stream()
                .map(Souvenir::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }
}