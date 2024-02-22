package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.List;

/**
 * Command implementation for retrieving manufacturers with souvenirs priced less than a given maximum price.
 */
public class GetManufacturersByMaxPriceCommand implements Command {
    private final MainController controller;
    private final InputReader reader;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs a GetManufacturersByMaxPriceCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing manufacturer operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public GetManufacturersByMaxPriceCommand(MainController controller, InputReader reader,
                                             ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by prompting the user to enter a maximum price and retrieving a list of manufacturers
     * with souvenirs priced less than the given maximum price from the MainController.
     * Displays the result in a table format using the ManufacturerTableView.
     */
    @Override
    public void execute() {
        double price = reader.getDouble("➡️Enter max price: ");
        List<Manufacturer> manufacturers = controller.getWhereSouvenirPriceIsLess(price);
        System.out.println(manufacturerView.generateTable(manufacturers));
    }
}
