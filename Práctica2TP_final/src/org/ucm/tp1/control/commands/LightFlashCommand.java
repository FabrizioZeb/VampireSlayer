package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends NoPCommand{
	
	private static final int COST = 50;

	public LightFlashCommand() {
		super("[L]ight", "L", "Kill all vampires", "[l]ight");
	}

	@Override
	public boolean execute(Game game) {
		return game.lightFlash(COST);
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equalsIgnoreCase("light") || commandWords[0].equalsIgnoreCase("l")) {
			if(commandWords.length != 1) System.out.println("Invalid command");
			else return this;
		}
		return null;
	}

}
