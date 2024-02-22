package org.vitaliistf.souvenirs.repository;

import org.vitaliistf.souvenirs.model.Manufacturer;

import java.util.List;

/**
 * Represents a repository interface for managing Manufacturer objects.
 */
public interface ManufacturerRepository extends Repository<Manufacturer, Long> {

    /**
     * Retrieves a list of manufacturers by country.
     *
     * @param country The country to filter by.
     * @return A list of manufacturers from the specified country.
     */
    List<Manufacturer> getByCountry(String country);

}
