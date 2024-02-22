package org.vitaliistf.souvenirs.menu.command;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.MenuOptions;
import org.vitaliistf.souvenirs.menu.command.implementation.*;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Object pool for retrieving commands.
 * (Usage of "Object Pool" design pattern.)
 */
public class CommandPool {
    /* Note: This pool does not provide any mechanisms for acquiring and releasing objects
        because it is not necessary in the context of this application. */
    private final Map<Integer, Command> pool;

    /**
     * Constructs a CommandFactory with the specified dependencies.
     *
     * @param controller       The MainController instance for managing operations.
     * @param reader           The InputReader instance for reading user input.
     * @param manufacturerView The view for displaying manufacturer-related information.
     * @param souvenirView     The view for displaying souvenir-related information.
     */
    public CommandPool(MainController controller, InputReader reader, ManufacturerTableView manufacturerView,
                       SouvenirTableView souvenirView) {
        this.pool = new HashMap<>();
        initializeCommandPool(controller, reader, manufacturerView, souvenirView);
    }

    /**
     * Retrieves a command object based on the user's choice.
     *
     * @param choice The user's choice representing the desired action.
     * @return An Optional of Command corresponding to the user's choice, or empty Optional if the choice is invalid.
     */
    public Optional<Command> getCommand(int choice) {
        return Optional.ofNullable(pool.get(choice));
    }

    private void initializeCommandPool(MainController controller, InputReader reader,
                                       ManufacturerTableView manufacturerView, SouvenirTableView souvenirView) {
        pool.put(MenuOptions.ADD_MANUFACTURER,
                new AddManufacturerCommand(controller, reader));
        pool.put(MenuOptions.UPDATE_MANUFACTURER,
                new UpdateManufacturerCommand(controller, reader, manufacturerView));
        pool.put(MenuOptions.DELETE_MANUFACTURER,
                new DeleteManufacturerCommand(controller, reader, manufacturerView));
        pool.put(MenuOptions.GET_ALL_MANUFACTURERS,
                new GetAllManufacturersCommand(controller, manufacturerView));
        pool.put(MenuOptions.ADD_SOUVENIR,
                new AddSouvenirCommand(controller, reader, manufacturerView));
        pool.put(MenuOptions.UPDATE_SOUVENIR,
                new UpdateSouvenirCommand(controller, reader, manufacturerView, souvenirView));
        pool.put(MenuOptions.DELETE_SOUVENIR,
                new DeleteSouvenirCommand(controller, reader, souvenirView));
        pool.put(MenuOptions.GET_ALL_SOUVENIRS,
                new GetAllSouvenirsCommand(controller, souvenirView));
        pool.put(MenuOptions.GET_SOUVENIRS_BY_MANUFACTURER,
                new GetSouvenirsByManufacturerCommand(controller, reader, manufacturerView, souvenirView));
        pool.put(MenuOptions.GET_SOUVENIRS_BY_COUNTRY,
                new GetSouvenirsByCountryCommand(controller, reader, souvenirView));
        pool.put(MenuOptions.GET_MANUFACTURERS_BY_MAX_PRICE,
                new GetManufacturersByMaxPriceCommand(controller, reader, manufacturerView));
        pool.put(MenuOptions.GET_ALL_MANUFACTURERS_WITH_SOUVENIRS,
                new GetAllManufacturersWithSouvenirsCommand(controller, souvenirView));
        pool.put(MenuOptions.GET_MANUFACTURERS_BY_SOUVENIR_AND_YEAR,
                new GetManufacturersBySouvenirAndYearCommand(controller, reader, manufacturerView));
        pool.put(MenuOptions.GET_ALL_SOUVENIRS_BY_YEARS,
                new GetAllSouvenirsByYearsCommand(controller, souvenirView));
    }

}
