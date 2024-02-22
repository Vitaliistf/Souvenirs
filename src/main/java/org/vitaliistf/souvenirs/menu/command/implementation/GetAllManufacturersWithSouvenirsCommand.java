package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Manufacturer;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.util.List;
import java.util.Map;

/**
 * Command implementation for retrieving all manufacturers with their associated souvenirs.
 */
public class GetAllManufacturersWithSouvenirsCommand implements Command {
    private final MainController controller;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs a GetAllManufacturersWithSouvenirsCommand with the specified MainController.
     *
     * @param controller   The MainController instance for managing manufacturer and souvenir operations.
     * @param souvenirView The SouvenirTableView instance for generating Souvenir view.
     */
    public GetAllManufacturersWithSouvenirsCommand(MainController controller, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by retrieving a map of all manufacturers with their associated souvenirs
     * from the MainController and displaying them in a formatted manner.
     */
    @Override
    public void execute() {
        Map<Manufacturer, List<Souvenir>> map = controller.getAllManufacturersWithSouvenirs();
        map.forEach((manufacturer, souvenirs) -> {
            System.out.println(manufacturer);
            System.out.println(souvenirView.generateTable(souvenirs));
        });
    }
}
