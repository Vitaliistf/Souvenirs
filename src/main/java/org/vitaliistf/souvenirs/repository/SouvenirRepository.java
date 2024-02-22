package org.vitaliistf.souvenirs.repository;

import org.vitaliistf.souvenirs.model.Souvenir;

import java.util.List;

/**
 * Represents a repository interface for managing Souvenir objects.
 */
public interface SouvenirRepository extends Repository<Souvenir, Long> {

    /**
     * Retrieves a list of souvenirs by name.
     *
     * @param name The name of the souvenir to search for.
     * @return A list of souvenirs with the specified name.
     */
    List<Souvenir> getByName(String name);

    /**
     * Retrieves a list of souvenirs by manufacturer ID.
     *
     * @param manufacturerId The ID of the manufacturer to filter by.
     * @return A list of souvenirs produced by the specified manufacturer.
     */
    List<Souvenir> getByManufacturerId(Long manufacturerId);

}
