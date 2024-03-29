package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends NoPCommand {

    public HelpCommand(){
        super("[h]elp","h","show this help","[h]elp");
    }

    @Override
    public boolean execute(Game game) {
    	System.out.println(CommandGenerator.commandHelp());
        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
    	if(commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
    }
}
