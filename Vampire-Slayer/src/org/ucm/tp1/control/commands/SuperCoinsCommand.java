package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends NoPCommand{
	
	private static final int COINS = 1000;

	public SuperCoinsCommand() {
		super("[c]oins", "c", "add 1000 coins", "[c]oins");
	}

	@Override
	public boolean execute(Game game) {
		game.superCoins(COINS);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("coins") || commandWords[0].equalsIgnoreCase("c")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
	}

}
