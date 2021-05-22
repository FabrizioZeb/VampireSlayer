package org.ucm.tp1.control.commands;

import java.io.IOException;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public abstract class NoPCommand extends Command{

    public NoPCommand(String name, String shortcut, String details, String help){
        super(name,shortcut,details,help);
    }
    public abstract boolean execute(Game game) throws CommandExecuteException, IOException;

    public Command parseNoParamsCommand(String[] commandWords) throws CommandParseException {
        if(commandWords[0].equalsIgnoreCase(name))
            return this;
        return null;
    }
}
