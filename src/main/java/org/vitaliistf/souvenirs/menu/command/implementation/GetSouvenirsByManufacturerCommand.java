package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.List;

/**
 * Command implementation for retrieving souvenirs by manufacturer.
 */
public class GetSouvenirsByManufacturerCommand implements Command {

    private final MainController controller;
    private final InputReader reader;
    private final SouvenirTableView souvenirView;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs a GetSouvenirsByManufacturerCommand with the specified MainController and InputReader.
     *
     * @param controller       The MainController instance for managing souvenir operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     * @param souvenirView     The SouvenirTableView instance for generating Souvenir view.
     */
    public GetSouvenirsByManufacturerCommand(MainController controller, InputReader reader,
                                             ManufacturerTableView manufacturerView, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.reader = reader;
        this.manufacturerView = manufacturerView;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by displaying a table of all manufacturers,
     * prompting the user to enter a manufacturer ID, and retrieving souvenirs produced by that manufacturer.
     * Displays the result in a table format using the SouvenirTableView.
     * If no souvenirs are found for the specified manufacturer, prints a warning message.
     */
    @Override
    public void execute() {
        System.out.println(manufacturerView.generateTable(controller.getAllManufacturers()));
        Long manufacturerId = reader.getLong("➡️Enter manufacturer ID: ");
        List<Souvenir> souvenirs = controller.getSouvenirsByManufacturer(manufacturerId);
        if (souvenirs.isEmpty()) {
            System.out.println("⚠️This manufacturer does not exist, or has no souvenirs.");
        } else {
            System.out.println(souvenirView.generateTable(souvenirs));
        }
    }

}
