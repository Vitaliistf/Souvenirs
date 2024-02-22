package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.List;

/**
 * Command implementation for retrieving manufacturers of a given souvenir for a specific year.
 */
public class GetManufacturersBySouvenirAndYearCommand implements Command {
    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs a GetManufacturersBySouvenirAndYearCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing manufacturer operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public GetManufacturersBySouvenirAndYearCommand(MainController controller, InputReader reader,
                                                    ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by prompting the user to enter the name of a souvenir and a year,
     * and retrieving a list of manufacturers producing that souvenir in the specified year from the MainController.
     * Displays the result in a table format using the ManufacturerTableView.
     */
    @Override
    public void execute() {
        String souvenirName = reader.getString("➡️Enter souvenir name: ");
        int year = reader.getInt("➡️Enter year: ");
        List<Manufacturer> manufacturers = controller.getManufacturersOfSouvenirByYear(souvenirName, year);
        System.out.println(manufacturerView.generateTable(manufacturers));
    }
}
