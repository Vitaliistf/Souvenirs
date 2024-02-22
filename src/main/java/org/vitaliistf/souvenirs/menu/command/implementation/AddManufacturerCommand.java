package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.util.InputReader;
import org.vitaliistf.souvenirs.validation.ValidationResult;

/**
 * Command implementation for adding a new manufacturer.
 */
public class AddManufacturerCommand implements Command {
    private final MainController controller;
    private final InputReader reader;

    /**
     * Constructs an AddManufacturerCommand with the specified MainController and InputReader.
     *
     * @param controller The MainController instance for managing manufacturer operations.
     * @param reader     The InputReader instance for reading user input.
     */
    public AddManufacturerCommand(MainController controller, InputReader reader) {
        this.controller = controller;
        this.reader = reader;
    }

    /**
     * Executes the command by prompting the user to enter details of the manufacturer
     * and adding it via the controller. Prints the result of the operation.
     */
    @Override
    public void execute() {
        String name = reader.getString("➡️Enter manufacturer's name: ");
        String country = reader.getString("➡️Enter manufacturer's country: ");

        Manufacturer manufacturer = new Manufacturer(name, country);
        ValidationResult result = controller.addManufacturer(manufacturer);

        if (result.isSuccessful()) {
            System.out.println("✅Manufacturer is added successfully.");
        } else {
            System.out.println("⚠️Manufacturer is not added due to following errors: ");
            result.getErrors().forEach(System.out::println);
        }
    }
}
