package org.vitaliistf.souvenirs.menu;

import org.vitaliistf.souvenirs.menu.command.Command;
import org.vitaliistf.souvenirs.menu.command.CommandPool;
import org.vitaliistf.souvenirs.util.InputReader;

import java.util.Optional;

/**
 * Represents a menu for interacting with the system.
 */
public class Menu {

    private final InputReader reader;
    private final CommandPool commandPool;

    /**
     * Initializes a new instance of the Menu class.
     *
     * @param commandPool The command pool.
     * @param reader      The input reader to read user choices.
     */
    public Menu(CommandPool commandPool, InputReader reader) {
        this.commandPool = commandPool;
        this.reader = reader;
    }

    /**
     * Displays the menu and handles user interactions.
     */
    public void displayMenu() {
        int choice;
        do {
            printMenuOptions();
            choice = reader.getInt("➡️Choose option: ");
            executeCommand(choice);
            System.out.println("-------------------End of previous operation-------------------\n\n\n");
        } while (choice != 0);
    }

    /**
     * Displays the menu options.
     */
    private void printMenuOptions() {
        System.out.println("""
            MENU
                
            1. Add manufacturer.
            2. Update manufacturer.
            3. Delete manufacturer.
            4. Get all manufacturers.
                
            5. Add souvenir.
            6. Update souvenir.
            7. Delete souvenir.
            8. Get all souvenirs.
                
            9. Get souvenirs by manufacturer.
            10. Get souvenirs by country.
            11. Get manufacturers whose souvenirs prices are less.
            12. Get all manufacturers with their souvenirs.
            13. Get manufacturers by souvenir name and year of production.
            14. Get all souvenirs by years.
                
            0. Exit.
            """);
    }

    /**
     * Executes the command based on user choice.
     */
    private void executeCommand(int choice) {
        Optional<Command> command = commandPool.getCommand(choice);
        if (command.isPresent()) {
            command.get().execute();
        } else if (choice != 0) {
            System.out.println("⚠️Unknown command. Try again.");
        }
    }

}
