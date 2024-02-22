package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.util.InputReader;

/**
 * Command implementation for deleting a manufacturer.
 */
public class DeleteManufacturerCommand implements Command {

    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs a DeleteManufacturerCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing manufacturer operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public DeleteManufacturerCommand(MainController controller, InputReader reader,
                                     ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by displaying a table of manufacturers and prompting the user to enter the ID
     * of the manufacturer to delete. Prints the result of the operation.
     */
    @Override
    public void execute() {
        System.out.println(manufacturerView.generateTable(controller.getAllManufacturers()));
        long id = reader.getLong("➡️Enter manufacturer's ID: ");

        if (controller.deleteManufacturer(id)) {
            System.out.println("✅Manufacturer is deleted successfully.");
        } else {
            System.out.println("⚠️Manufacturer is not deleted. There is no manufacturer with such id.");
        }
    }

}
