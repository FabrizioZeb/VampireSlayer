package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.exceptions.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class AddCommand extends NoPCommand {

    private int x;
    private int y;
    private static final String help = "[a]dd <x> <y>";

    public AddCommand(){
        super("[a]dd", "a", "add a slayer in position x, y", help);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException {
        try {
            if (game.addSlayer(x, y)) {
                game.update();
                return true;
            }
        }
        catch (NotEnoughCoinsException | UnvalidPositionException nec){
            System.out.println(nec.getMessage());
            throw new CommandExecuteException(String.format("[ERROR]: Failed to add Slayer"), nec);
        }
        return false;
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException{
        if(commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase("a")){
            if(commandWords.length != 3) throw new CommandParseException("[ERROR]: + Command " + name + " :" + incorrectNumberOfArgsMsg);
            else {
                if(isNumeric(commandWords[1]) && isNumeric(commandWords[2])){
                    this.x = Integer.parseInt(commandWords[1]);
                    this.y = Integer.parseInt(commandWords[2]);
                    return this;
                }
                else throw new CommandParseException("ERROR]: Invalid argument for add slayer command, number expected:" + help);
            }
        }
        return null;
    }



}
