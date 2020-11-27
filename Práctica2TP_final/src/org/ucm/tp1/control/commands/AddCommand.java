package org.ucm.tp1.control.commands;

import org.ucm.tp1.logic.Game;

public class AddCommand extends NoPCommand {
    public AddCommand(){
        super("[A]dd", "A", "Add a Slayer in x,y", "'(Command> add x y)'");
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
