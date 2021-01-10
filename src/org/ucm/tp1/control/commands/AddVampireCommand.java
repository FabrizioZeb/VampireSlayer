package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
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
	public boolean execute(Game game) {
		return game.addSelectedVampire(type, x, y);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase("vampire") || commandWords[0].equalsIgnoreCase("v")){
            if(commandWords.length < 3) System.out.println("Invalid command");
            else if(commandWords.length > 4) System.out.println("Invalid command");
            else if(commandWords.length == 3) {
            	this.type = "v";
				if(isNumeric(commandWords[1]) && isNumeric(commandWords[2])) {
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
            	if(isNumeric(commandWords[2]) && isNumeric(commandWords[3])){
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
