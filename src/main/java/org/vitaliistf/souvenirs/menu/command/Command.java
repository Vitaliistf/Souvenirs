package org.vitaliistf.souvenirs.menu.command;

/**
 * The Command interface represents a command in the menu system.
 * Implementations of this interface define specific actions to be executed when the command is invoked.
 * (Usage of "Command" design pattern.)
 */
public interface Command {

    /**
     * Executes the command.
     */
    void execute();

}
