package org.vitaliistf.souvenirs;

import org.vitaliistf.souvenirs.controller.MainController;
import org.vitaliistf.souvenirs.menu.Menu;
import org.vitaliistf.souvenirs.menu.command.CommandPool;
import org.vitaliistf.souvenirs.menu.view.ManufacturerTableView;
import org.vitaliistf.souvenirs.menu.view.SouvenirTableView;
import org.vitaliistf.souvenirs.repository.implementation.InMemoryManufacturerRepository;
import org.vitaliistf.souvenirs.repository.implementation.InMemorySouvenirRepository;
import org.vitaliistf.souvenirs.service.ManufacturerService;
import org.vitaliistf.souvenirs.service.SouvenirService;
import org.vitaliistf.souvenirs.util.InputReader;
import org.vitaliistf.souvenirs.validation.ManufacturerValidator;
import org.vitaliistf.souvenirs.validation.SouvenirValidator;

/**
 * Entry point for the Souvenirs application.
 */
public class Main {

    /**
     * Main method to start the application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize services and dependencies
        ManufacturerValidator manufacturerValidator = new ManufacturerValidator();
        SouvenirValidator souvenirValidator = new SouvenirValidator();

        ManufacturerService manufacturerService = new ManufacturerService(
                manufacturerValidator,
                InMemorySouvenirRepository.getInstance(),
                InMemoryManufacturerRepository.getInstance());
        SouvenirService souvenirService = new SouvenirService(
                souvenirValidator,
                InMemorySouvenirRepository.getInstance(),
                InMemoryManufacturerRepository.getInstance());

        MainController controller = new MainController(manufacturerService, souvenirService);

        InputReader reader = new InputReader(System.in);
        ManufacturerTableView manufacturerTableView = new ManufacturerTableView();
        SouvenirTableView souvenirTableView = new SouvenirTableView();
        CommandPool factory = new CommandPool(controller, reader, manufacturerTableView, souvenirTableView);

        // Create and display the menu
        Menu menu = new Menu(factory, reader);
        menu.displayMenu();

        reader.close();
    }
}