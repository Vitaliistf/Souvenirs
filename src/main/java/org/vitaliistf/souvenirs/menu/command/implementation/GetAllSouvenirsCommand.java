package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Souvenir;

import java.util.List;

/**
 * Command implementation for retrieving all souvenirs.
 */
public class GetAllSouvenirsCommand implements Command {

    private final MainController controller;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs a GetAllSouvenirsCommand with the specified MainController.
     *
     * @param controller   The MainController instance for managing souvenir operations.
     * @param souvenirView The SouvenirTableView instance for generating Souvenir view.
     */
    public GetAllSouvenirsCommand(MainController controller, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by retrieving a list of all souvenirs from the MainController
     * and displaying them in a table format using the SouvenirTableView.
     */
    @Override
    public void execute() {
        List<Souvenir> souvenirs = controller.getAllSouvenirs();
        System.out.println(souvenirView.generateTable(souvenirs));
    }

}
