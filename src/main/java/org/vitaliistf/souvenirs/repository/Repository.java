package org.vitaliistf.souvenirs.repository;

import java.util.List;
import java.util.Optional;

/**
 * Represents a generic repository interface for storing and retrieving objects of type T.
 *
 * @param <T> The type of objects stored in the repository.
 * @param <V> The type of ID used to identify objects in the repository.
 * (Usage of "Repository" design pattern.)
 */
public interface Repository<T, V> {

    /**
     * Adds an object to the repository.
     *
     * @param object The object to add.
     * @return true if the object is successfully added, false otherwise.
     */
    boolean add(T object);

    /**
     * Updates an object in the repository.
     *
     * @param object The object to update.
     * @return true if the object is successfully updated, false otherwise.
     */
    boolean update(T object);

    /**
     * Removes an object from the repository by its ID.
     *
     * @param id The ID of the object to remove.
     * @return true if the object is successfully removed, false otherwise.
     */
    boolean remove(V id);

    /**
     * Retrieves all objects from the repository.
     *
     * @return A list containing all objects in the repository.
     */
    List<T> getAll();

    /**
     * Retrieves an object from the repository by its ID.
     *
     * @param id The ID of the object to retrieve.
     * @return An Optional containing the object if found, otherwise an empty Optional.
     */
    Optional<T> getById(V id);

}
