package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.util.InputReader;
import org.vitaliistf.souvenirs.validation.ValidationResult;

import java.time.LocalDate;

/**
 * Command implementation for updating a souvenir.
 */
public class UpdateSouvenirCommand implements Command {
    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs an UpdateSouvenirCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing souvenir operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     * @param souvenirView     The SouvenirTableView instance for generating Souvenir view.
     */
    public UpdateSouvenirCommand(MainController controller, InputReader reader,
                                 ManufacturerTableView manufacturerView, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by displaying a table of all souvenirs,
     * prompting the user to enter the ID of the souvenir to update,
     * and prompting for the new name, manufacturer ID, production date, and price.
     * Updates the souvenir using the MainController and displays the result.
     */
    @Override
    public void execute() {
        System.out.println(souvenirView.generateTable(controller.getAllSouvenirs()));

        int id = reader.getInt("➡️Enter souvenir's id: ");
        String name = reader.getString("➡️Enter new souvenir's name: ");

        System.out.println(manufacturerView.generateTable(controller.getAllManufacturers()));

        long manufacturerId = reader.getLong("➡️Enter new manufacturer's id: ");
        LocalDate productionDate = reader.getDate("➡️Enter new production date: ");
        double price = reader.getDouble("➡️Enter new price: ");

        Souvenir souvenir = new Souvenir(name, manufacturerId, productionDate, price);
        souvenir.setId(id);
        ValidationResult result = controller.updateSouvenir(souvenir);

        if (result.isSuccessful()) {
            System.out.println("✅Souvenir is updated successfully.");
        } else {
            System.out.println("⚠️Souvenir is not updated due to following errors: ");
            result.getErrors().forEach(System.out::println);
        }
    }
}
