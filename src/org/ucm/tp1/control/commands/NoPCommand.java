package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public abstract class NoPCommand extends Command{

    public NoPCommand(String name, String shortcut, String details, String help){
        super(name,shortcut,details,help);
    }
    public abstract boolean execute(Game game);

    public Command parseNoParamsCommand(String[] commandWords) throws CommandParseException {
        if(commandWords[0].equalsIgnoreCase(name))
            return this;
        return null;
    }
}
