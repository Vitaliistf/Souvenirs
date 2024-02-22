package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.model.Manufacturer;

import java.util.List;

/**
 * Command implementation for retrieving all manufacturers.
 */
public class GetAllManufacturersCommand implements Command {

    private final MainController controller;
    private final ManufacturerTableView manufacturerView;

    /**
     * Constructs a GetAllManufacturersCommand with the specified MainController.
     *
     * @param controller       The MainController instance for managing manufacturer operations.
     * @param manufacturerView The ManufacturerTableView instance for generating Manufacturer view.
     */
    public GetAllManufacturersCommand(MainController controller, ManufacturerTableView manufacturerView) {
        this.controller = controller;
        this.manufacturerView = manufacturerView;
    }

    /**
     * Executes the command by retrieving a list of all manufacturers from the MainController
     * and displaying them in a table format using the ManufacturerTableView.
     */
    @Override
    public void execute() {
        List<Manufacturer> manufacturers = controller.getAllManufacturers();
        System.out.println(manufacturerView.generateTable(manufacturers));
    }
}
