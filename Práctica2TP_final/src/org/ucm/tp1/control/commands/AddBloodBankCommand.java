package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends NoPCommand{

    public AddBloodBankCommand(){
        super("[B]ank","B","Add Blood Bank in x,y","[b]ank <x><y><z>");
    }

    @Override
    public Command parse(String[] commandWords) {
        return null;
    }

    @Override
    public boolean execute(Game game) {
        return false;
    }
}
