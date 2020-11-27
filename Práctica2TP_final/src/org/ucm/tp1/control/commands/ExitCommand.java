package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends NoPCommand {
    public ExitCommand(){
        super("[E]xit","E","finish the program","");
    }
    @Override
    public boolean execute(Game game){
        game.exit();
        return false;
    }

    @Override
    public Command parse(String[] commandWords) {
        return null;
    }

}
