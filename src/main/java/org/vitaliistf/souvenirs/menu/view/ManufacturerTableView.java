package org.vitaliistf.souvenirs.menu.view;

import org.vitaliistf.souvenirs.model.Manufacturer;

/**
 * Implementation of TableView for Manufacturer objects.
 */
public class ManufacturerTableView implements TableView<Manufacturer> {

    /**
     * Retrieves the title of the table.
     * @return The title of the table.
     */
    @Override
    public String getTitle() {
        return "Following manufacturers are present in the system right now:";
    }

    /**
     * Retrieves the headers of the table.
     * @return An array representing the headers.
     */
    @Override
    public String[] getHeaders() {
        return new String[]{"ID", "Name", "Country"};
    }

    /**
     * Retrieves the row data for a specific manufacturer.
     * @param manufacturer The manufacturer for which to retrieve row data.
     * @return An array representing the data for a single row.
     */
    @Override
    public String[] getRowData(Manufacturer manufacturer) {
        return new String[]{
                String.valueOf(manufacturer.getId()),
                manufacturer.getName(),
                manufacturer.getCountry()};
    }

}
