package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends NoPCommand {

    private int x;
    private int y;


    public AddCommand(){
        super("[A]dd", "A", "Add a Slayer in x,y", "'(Command> add x y)'");
    }

    @Override
    public boolean execute(Game game) {
        game.addSlayer(x,y);

    }

    @Override
    public Command parse(String[] commandWords) {


        if(commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase("a")){
            if(commandWords.length == 1) System.out.println("Invalid command");
            else if(commandWords.length > 3) System.out.println("Invalid command");
            else if(commandWords.length == 3 && (Integer.parseInt(commandWords[1]) < 3 || Integer.parseInt(commandWords[1]) > 1 || commandWords[2].length() > 1)){
                System.out.println("Invalid position");
            }
            else {
                this.x = Integer.parseInt(commandWords[2]);
                this.y = Integer.parseInt(commandWords[3]);
                return this;
            }
        }
        return null;
    }
}
