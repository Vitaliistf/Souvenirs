package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.util.InputReader;
import org.vitaliistf.souvenirs.validation.ValidationResult;

/**
 * Command implementation for updating a manufacturer.
 */
public class UpdateManufacturerCommand implements Command {

    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs an UpdateManufacturerCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing manufacturer operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public UpdateManufacturerCommand(MainController controller, InputReader reader,
                                     ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by displaying a table of all manufacturers,
     * prompting the user to enter the ID of the manufacturer to update,
     * and prompting for the new name and country.
     * Updates the manufacturer using the MainController and displays the result.
     */
    @Override
    public void execute() {
        System.out.println(manufacturerView.generateTable(controller.getAllManufacturers()));
        int id = reader.getInt("➡️Enter manufacturer's id: ");
        String name = reader.getString("➡️Enter new manufacturer's name: ");
        String country = reader.getString("➡️Enter new manufacturer's country: ");

        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        ValidationResult result = controller.updateManufacturer(manufacturer);

        if (result.isSuccessful()) {
            System.out.println("✅Manufacturer is updated successfully.");
        } else {
            System.out.println("⚠️Manufacturer is not updated due to following errors: ");
            result.getErrors().forEach(System.out::println);
        }
    }

}
