package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.util.InputReader;

/**
 * Command implementation for deleting a souvenir.
 */
public class DeleteSouvenirCommand implements Command {

    private final MainController controller;
    private final InputReader reader;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs a DeleteSouvenirCommand with the specified MainController and InputReader.
     *
     * @param controller   The MainController instance for managing souvenir operations.
     * @param reader       The InputReader instance for reading user input.
     * @param souvenirView The SouvenirTableView instance for generating Souvenir view.
     */
    public DeleteSouvenirCommand(MainController controller, InputReader reader, SouvenirTableView souvenirView) {
        this.controller = controller;
        this.reader = reader;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by displaying a table of souvenirs and prompting
     * the user to enter the ID of the souvenir to delete.
     * Prints the result of the operation.
     */
    @Override
    public void execute() {
        System.out.println(souvenirView.generateTable(controller.getAllSouvenirs()));

        long id = reader.getLong("➡️Enter souvenir's ID: ");

        if (controller.deleteSouvenir(id)) {
            System.out.println("✅Souvenir is deleted successfully.");
        } else {
            System.out.println("⚠️Souvenir is not deleted. There is no souvenir with such id.");
        }
    }

}
