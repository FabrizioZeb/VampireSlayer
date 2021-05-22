package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.exceptions.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends NoPCommand{
	
	private int x;
	private int y;
	private int z;
	private static final String help = "[b]ank <x> <y> <z>";

    public AddBloodBankCommand(){
        super("[B]ank","B","add a blood bank with cost z in position x, y.",help);
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
    	 if(commandWords[0].equalsIgnoreCase("bank") || commandWords[0].equalsIgnoreCase("b")){
             if(commandWords.length != 4) throw new CommandParseException("[ERROR]: + Command " + name + " :" + incorrectNumberOfArgsMsg);
             else {
                 if(CheckNumber(commandWords[1]) && CheckNumber(commandWords[2]) && CheckNumber(commandWords[3])) {
                     this.x = Integer.parseInt(commandWords[1]);
                     this.y = Integer.parseInt(commandWords[2]);
                     this.z = Integer.parseInt(commandWords[3]);
                     return this;
                 }
                 else throw new CommandParseException("ERROR]: Invalid argument for add bloodbank command, number expected:" + help);
             }
         }
         return null;
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {
    	try {
            if (game.addBloodBank(x, y, z)) {
                game.update();
                return true;
            }
        } catch (NotEnoughCoinsException | UnvalidPositionException nec) {
            System.out.println(nec.getMessage());
            throw new CommandExecuteException(String.format("[ERROR]: Failed to add Blood Bank"), nec);
        }
        return false;
    }
}
