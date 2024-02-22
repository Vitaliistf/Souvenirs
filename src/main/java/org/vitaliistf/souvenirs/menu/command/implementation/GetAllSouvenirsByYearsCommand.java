package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.util.List;
import java.util.Map;

/**
 * Command implementation for retrieving all souvenirs grouped by year.
 */
public class GetAllSouvenirsByYearsCommand implements Command {

    private final MainController controller;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs a GetAllSouvenirsByYearsCommand with the specified MainController.
     *
     * @param controller   The MainController instance for managing souvenir operations.
     * @param souvenirView The SouvenirTableView instance for generating Souvenir view.
     */
    public GetAllSouvenirsByYearsCommand(MainController controller, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by retrieving a map of all souvenirs grouped by year from the MainController
     * and displaying them in a formatted manner.
     */
    @Override
    public void execute() {
        Map<Integer, List<Souvenir>> map = controller.getSouvenirsByYear();
        map.forEach((year, souvenirs) -> {
            System.out.println("Year: " + year);
            System.out.println(souvenirView.generateTable(souvenirs));
        });
    }

}
