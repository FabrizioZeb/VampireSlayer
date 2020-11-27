package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class NoneCommand extends NoPCommand{
    public NoneCommand(){
        super("[U]pdate: ","U","update the game.", "");
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
