package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.control.commands.CommandGenerator;

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
    public Command parse(String[] commandWords) {
    	if(commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h")) {
			if(commandWords.length != 1) System.out.println("Invalid command");
			else return this;
		}
		return null;
    }
}
