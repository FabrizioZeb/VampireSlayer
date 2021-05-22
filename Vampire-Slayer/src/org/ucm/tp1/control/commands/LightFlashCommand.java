package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends NoPCommand{
	
	private static final int COST = 50;

	public LightFlashCommand() {
		super("[l]ight", "l", "kills all the vampires", "[l]ight");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(game.lightFlash(COST)) {
				game.update();
				return true;
			}
		} catch (NotEnoughCoinsException nec) {
			System.out.println(nec.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Failed to light flash"),nec);
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("light") || commandWords[0].equalsIgnoreCase("l")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
	}

}
