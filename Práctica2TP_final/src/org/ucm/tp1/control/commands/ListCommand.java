package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class ListCommand extends Command {

    public  ListCommand(){
        super("[L]ist","L","print the list of available slayers.","");
    }
    @Override
    public boolean execute(Game game){
        System.out.println(/*Listofslayers*/);
        return true;
    }

    public Command parse(String[] commandWords) {
        return null;
    }
}
