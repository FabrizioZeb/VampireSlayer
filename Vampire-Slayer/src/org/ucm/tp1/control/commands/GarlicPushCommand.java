package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends NoPCommand{
	
	private static final int COST = 10;

	public GarlicPushCommand() {
		super("[g]arlic", "g", "pushes back vampires", "[g]arlic");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try{
			if(game.garlicPush(COST)) {
				game.update();
				return true;
			}
		} catch (NotEnoughCoinsException nec){
			System.out.println(nec.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to garlic push"),nec);
		}
		return false;
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
