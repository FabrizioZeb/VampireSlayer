package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends NoPCommand{
    public UpdateCommand(){
        super("[n]one  ","n","update", "[n]one | []");
    }


    @Override
    public boolean execute(Game game) {
    	game.update();
        return true;
    }

    @Override
    public Command parse(String[] commandWords) {
    	if(commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase("none") || commandWords[0].equalsIgnoreCase("")) {
			if(commandWords.length != 1) System.out.println("Invalid command");
			else return this;
		}
		return null;
    }
}
