package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class PrintModeCommand extends Command {

    public PrintModeCommand(){
        super("[P]rintMode","P","change print mode [Db]","");
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
