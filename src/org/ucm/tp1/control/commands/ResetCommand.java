package org.ucm.tp1.control.commands;


import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends NoPCommand{

    public ResetCommand(){
        super("[r]eset","r" ,"reset game","[r]eset");
    }


    @Override
    public boolean execute(Game game) {
    	game.reset();
        return true;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
    	if(commandWords[0].equalsIgnoreCase("reset") || commandWords[0].equalsIgnoreCase("r")) {
			if(commandWords.length != 1) throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
			else return this;
		}
		return null;
    }
}
