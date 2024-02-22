package org.vitaliistf.souvenirs.menu.view;

import org.vitaliistf.souvenirs.model.Souvenir;

/**
 * Implementation of TableView for Souvenir objects.
 */
public class SouvenirTableView implements TableView<Souvenir> {

    /**
     * Retrieves the title of the table.
     *
     * @return The title of the table.
     */
    @Override
    public String getTitle() {
        return "Following souvenirs are present in the system right now:";
    }

    /**
     * Retrieves the headers of the table.
     *
     * @return An array representing the headers.
     */
    @Override
    public String[] getHeaders() {
        return new String[]{"ID", "Name", "Manufacturer ID", "Production date", "Price"};
    }

    /**
     * Retrieves the row data for a specific souvenir.
     *
     * @param souvenir The souvenir for which to retrieve row data.
     * @return An array representing the data for a single row.
     */
    @Override
    public String[] getRowData(Souvenir souvenir) {
        return new String[]{
                String.valueOf(souvenir.getId()),
                souvenir.getName(),
                String.valueOf(souvenir.getManufacturerId()),
                String.valueOf(souvenir.getProductionDate()),
                String.valueOf(souvenir.getPrice())
        };
    }

}
