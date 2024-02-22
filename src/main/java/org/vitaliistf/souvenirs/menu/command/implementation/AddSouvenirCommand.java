package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.util.InputReader;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.time.LocalDate;

/**
 * Command implementation for adding a new souvenir.
 */
public class AddSouvenirCommand implements Command {
    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs an AddSouvenirCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing souvenir operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public AddSouvenirCommand(MainController controller, InputReader reader, ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by prompting the user to enter details of the souvenir and
     * adding it via the controller.
     * Prints the result of the operation.
     */
    @Override
    public void execute() {
        String name = reader.getString("➡️Enter souvenir's name: ");

        System.out.println(manufacturerView.generateTable(controller.getAllManufacturers()));

        long manufacturerId = reader.getLong("➡️Enter manufacturer's id: ");
        LocalDate productionDate = reader.getDate("➡️Enter production date: ");
        double price = reader.getDouble("➡️Enter price: ");

        Souvenir souvenir = new Souvenir(name, manufacturerId, productionDate, price);
        ValidationResult result = controller.addSouvenir(souvenir);

        if (result.isSuccessful()) {
            System.out.println("✅Souvenir is added successfully.");
        } else {
            System.out.println("⚠️Souvenir is not added due to following errors: ");
            result.getErrors().forEach(System.out::println);
        }
    }
}

