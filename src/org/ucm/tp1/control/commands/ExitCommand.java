package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends NoPCommand {
    public ExitCommand(){
        super("[e]xit","e","exit game","[e]xit");
    }
    @Override
    public boolean execute(Game game){
        game.exit();
        return true;
    }

    @Override
    public Command parse(String[] commandWords) {
    	if(commandWords[0].equalsIgnoreCase("exit") || commandWords[0].equalsIgnoreCase("e")) {
			if(commandWords.length != 1) System.out.println("Invalid command");
			else return this;
		}
		return null;
    }

}
