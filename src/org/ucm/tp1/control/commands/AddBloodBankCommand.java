package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends NoPCommand{
	
	private int x;
	private int y;
	private int z;

    public AddBloodBankCommand(){
        super("[B]ank","B","add a blood bank with cost z in position x, y.","[b]ank <x> <y> <z>:");
    }

    @Override
    public Command parse(String[] commandWords) {
    	 if(commandWords[0].equalsIgnoreCase("bank") || commandWords[0].equalsIgnoreCase("b")){
             if(commandWords.length < 4) System.out.println("Invalid command");
             else if(commandWords.length > 4) System.out.println("Invalid command");
             else {
                 this.x = Integer.parseInt(commandWords[1]);
                 this.y = Integer.parseInt(commandWords[2]);
                 this.z = Integer.parseInt(commandWords[3]);
                 return this;
             }
         }
         return null;
    }

    @Override
    public boolean execute(Game game) {
    	if(game.addBloodBank(x,y,z)){
			game.update();
			return true;
		}
		else return false;
    }
}
