package org.vitaliistf.souvenirs.menu.command.implementation;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.model.Souvenir;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.List;

/**
 * Command implementation for retrieving souvenirs by country.
 */
public class GetSouvenirsByCountryCommand implements Command {

    private final MainController controller;
    private final InputReader reader;
    private final SouvenirTableView souvenirView;

    /**
     * Constructs a GetSouvenirsByCountryCommand with the specified MainController and InputReader.
     *
     * @param controller   The MainController instance for managing souvenir operations.
     * @param reader       The InputReader instance for reading user input.
     * @param souvenirView The SouvenirTableView instance for generating Souvenir view.
     */
    public GetSouvenirsByCountryCommand(MainController controller, InputReader reader,
                                        SouvenirTableView souvenirView) {
        this.controller = controller;
        this.reader = reader;
        this.souvenirView = souvenirView;
    }

    /**
     * Executes the command by displaying the list of available countries,
     * prompting the user to enter a country, and retrieving souvenirs from that country using the MainController.
     * Displays the result in a table format using the SouvenirTableView.
     */
    @Override
    public void execute() {
        System.out.println("There are following countries present in the system: ");
        controller.getManufacturersCountries().forEach(System.out::println);
        String country = reader.getString("➡️Enter country: ");
        List<Souvenir> souvenirs = controller.getSouvenirsByCountry(country);
        System.out.println(souvenirView.generateTable(souvenirs));
    }

}


