package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NoMoreVampiresException;
import org.ucm.tp1.exceptions.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends NoPCommand{
	
	private int x;
	private int y;
	private String type;
	private static final String TypesV = "Type = {\"\"|\"D\"|\"E\"}";
	private static final String help = "[v]ampire [<type>] <x> <y>";

	public AddVampireCommand() {
		super("[v]ampire", "v", "Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y", help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try{
			if(game.addSelectedVampire(type,x,y))
			return true;
		}
		catch (UnvalidPositionException | NoMoreVampiresException upe){
			System.out.println(upe.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: Fail to add vampire"),upe);
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase("vampire") || commandWords[0].equalsIgnoreCase("v")){
            if(commandWords.length < 3) System.out.println("Invalid command");
            else if(commandWords.length > 4) System.out.println("Invalid command");
            else if(commandWords.length == 3) {
            	this.type = "v";
				if(CheckNumber(commandWords[1]) && CheckNumber(commandWords[2])) {
					this.x = Integer.parseInt(commandWords[1]);
					this.y = Integer.parseInt(commandWords[2]);
					return this;
				}
				else throw new CommandParseException("\"ERROR]: Invalid argument for add vampire command, number expected:" + help);
			}
            else {
            	if(commandWords[1].equalsIgnoreCase("v") || commandWords[1].equalsIgnoreCase("d") ||commandWords[1].equalsIgnoreCase("e")){
					this.type = commandWords[1];
				}
            	else throw new CommandParseException("[ERROR]: Unvalid Type: " + TypesV + ". " + help);
            	if(CheckNumber(commandWords[2]) && CheckNumber(commandWords[3])){
					this.x = Integer.parseInt(commandWords[2]);
					this.y = Integer.parseInt(commandWords[3]);
					return this;
				}
            	else throw new CommandParseException("ERROR]: Invalid argument for add vampire command, number expected:" + help);
            }
        }
        return null;
	}

}
