package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends NoPCommand{
	
	private static final int COST = 10;

	public GarlicPushCommand() {
		super("[G]arlic", "G", "Push all vampires", "[g]arlic");
	}

	@Override
	public boolean execute(Game game) {
		return game.garlicPush(COST);
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equalsIgnoreCase("garlic") || commandWords[0].equalsIgnoreCase("g")) {
			if(commandWords.length != 1) System.out.println("Invalid command");
			else return this;
		}
		return null;
	}

}
