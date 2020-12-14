package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends NoPCommand {

    private int x;
    private int y;


    public AddCommand(){
        super("[a]dd", "a", "add a slayer in position x, y", "[a]dd <x> <y>");
    }

    @Override
    public boolean execute(Game game) {
        if(game.addSlayer(x,y)) {
        	game.update();
        	return true;
        }
        else return false;
    }

    @Override
    public Command parse(String[] commandWords) {

        if(commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase("a")){
            if(commandWords.length < 3) System.out.println("Invalid command");
            else if(commandWords.length > 3) System.out.println("Invalid command");
            else {
                this.x = Integer.parseInt(commandWords[1]);
                this.y = Integer.parseInt(commandWords[2]);
                return this;
            }
        }
        return null;
    }
}
