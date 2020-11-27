package org.ucm.tp1.control;

import java.util.Scanner;

import org.ucm.tp1.control.commands.Command;
import org.ucm.tp1.control.commands.CommandGenerator;
import org.ucm.tp1.logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    	this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() {
	    	boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
        		 if (refreshDisplay) printGame();
        		 refreshDisplay = false;
        		 
			  System.out.println(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
		      Command command = CommandGenerator.parse(parameters);
		      if (command != null) { 
		    	  		refreshDisplay = command.execute(game);
		       } 
		       else {
		    	   		System.out.println("[ERROR]: "+ unknownCommandMsg);
		       }
		}
	    
        	if (refreshDisplay) printGame();
    		System.out.println ("[Game over] " + game.getWinnerMessage());

    }

}

