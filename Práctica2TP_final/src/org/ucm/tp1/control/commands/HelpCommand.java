package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class HelpCommand extends NoPCommand {

    public HelpCommand(){
        super("[H]elp","H","print this help message","[h]elp");
    }

    @Override
    public boolean execute(Game game) {
    	commandHelp();
        return false;
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
