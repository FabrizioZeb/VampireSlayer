package org.ucm.tp1.control.commands;


import org.ucm.tp1.logic.Game;

public class ResetCommand extends NoPCommand{

    public ResetCommand(){
        super("[R]eset","R" ,"Resets game.","");
    }


    @Override
    public boolean execute(Game game) {
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        return null;
    }
}
