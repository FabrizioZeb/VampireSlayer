package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends NoPCommand{
	
	private int x;
	private int y;
	private String type;

	public AddVampireCommand() {
		super("[v]ampire", "v", "Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y", "[v]ampire [<type>] <x> <y>.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		return game.addSelectedVampire(type, x, y);
	}

	@Override
	public Command parse(String[] commandWords) {
		
		if(commandWords[0].equalsIgnoreCase("vampire") || commandWords[0].equalsIgnoreCase("v")){
            if(commandWords.length < 3) System.out.println("Invalid command");
            else if(commandWords.length > 4) System.out.println("Invalid command");
            else if(commandWords.length == 3) {
            	this.type = "v";
                this.x = Integer.parseInt(commandWords[1]);
                this.y = Integer.parseInt(commandWords[2]);
                return this;
            	}
            else {
            	this.type = commandWords[1];
            	this.x = Integer.parseInt(commandWords[2]);
            	this.y = Integer.parseInt(commandWords[3]);
            	return this;
            }
        }
        return null;
	}

}
