package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SerializeCommand extends NoPCommand {

	public SerializeCommand() {
		super("seriali[z]e", "z", "serialize the game in the current state", "seriali[z]e");
	}

	@Override
	public boolean execute(Game game){
		System.out.println(game.serialize());
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
    	if(commandWords[0].equalsIgnoreCase("serialize") || commandWords[0].equalsIgnoreCase("z")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
	}

}
