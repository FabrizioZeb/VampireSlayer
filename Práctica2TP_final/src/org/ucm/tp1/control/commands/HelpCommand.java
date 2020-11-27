package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class HelpCommand extends NoPCommand {

    public HelpCommand(){
        super("[H]elp: ","H","print this help message","");
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
