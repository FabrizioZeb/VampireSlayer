package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends NoPCommand{
	
	private static final int COST = 10;

	public GarlicPushCommand() {
		super("[g]arlic", "g", "pushes back vampires", "[g]arlic");
	}

	@Override
	public boolean execute(Game game) {
		if(game.garlicPush(COST)) {
			game.update();
			return true;
		}else return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("garlic") || commandWords[0].equalsIgnoreCase("g")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
	}

}
