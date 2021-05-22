package org.ucm.tp1.control.commands;

import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends NoPCommand  {
	
	private String filename;
	private static final String help = "[s]ave <filename>";

	public SaveCommand() {
		super("[s]ave", "s", "saves the current state of the game in the file", help);
	}

	@Override
	public boolean execute(Game game) throws IOException {
		FileWriter save =  null;
		try {
			save = new FileWriter(filename);
			save.write(game.serialize());
		} finally {
			if(save != null) save.close();
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
    	if(commandWords[0].equalsIgnoreCase("save") || commandWords[0].equalsIgnoreCase("s")) {
			if(commandWords.length != 2) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else {
				this.filename = commandWords[1];
				return this;
			}
		}
		return null;
	}

}
