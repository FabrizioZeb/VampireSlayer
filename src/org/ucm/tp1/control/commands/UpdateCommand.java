package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
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
    public Command parse(String[] commandWords) throws CommandParseException {
    	if(commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase("none") || commandWords[0].equalsIgnoreCase("")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
    }
}
