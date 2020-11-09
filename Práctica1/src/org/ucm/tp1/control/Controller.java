package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.logic.Game;

public class Controller {


	private final String prompt = "Command > ";
	private static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	private static final String unknownCommandMsg = String.format("Unknown command");
	private static final String invalidCommandMsg = String.format("Invalid command");
	private static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    private boolean nextstep;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
	    nextstep = false;
    }
    
    private void pinta() {
    	System.out.println(game.pintar());
    }
    
    public void run() {
		// TODO fill your code
    	
    	Scanner sc = new Scanner(System.in);
    	
    	while(!game.isFinjuego()) {
    		//pintar
    		if(!nextstep)
    		pinta();
    		//pedir instrucciones
    		AskUserInstruction(sc);
    		
    		if(!nextstep) {
    			//move
    			game.moveVampires();
    			//attack
    			game.attack();
    			//add vampire
    			game.addVampire(game.ColsandRows(false),game.ColsandRows(true));
    			//retire corpses
    			game.RemoveCorpses();
    			//check if game has finished
    			game.GameFinished();
    		}
    		
    	}
    	System.out.println("[DEBUG] GAME OVER");
    	sc.close();
    }

	private void AskUserInstruction(Scanner sc) {
		boolean validInstruction = false;
		nextstep = true;

		while(!validInstruction) {
			System.out.print(prompt);
			String read = sc.nextLine().trim();
			String[] instructions = read.toLowerCase().split(" ");
			
			if("h".equals(instructions[0]) || "help".equals(instructions[0])) {
				System.out.println(helpMsg);
				validInstruction = true;
			}
			else if("e".equals(instructions[0]) || "exit".equals(instructions[0])) {
				System.out.println("Fin del Juego");
				System.exit(0);
				validInstruction = true;
			}
			else if("r".equals(instructions[0]) || "reset".equals(instructions[0])) {
				game.Reset();
				System.out.println("Reset");
				validInstruction = true;
				nextstep = false;
			}
			else if("a".equals(instructions[0]) || "add".equals(instructions[0])) {
				if(instructions.length == 3 ) {
					int col = Integer.parseInt(instructions[1]);
					int row = Integer.parseInt(instructions[2]);
					if(col <= game.getDificultad().getDim_x() - 2 && row <= game.getDificultad().getDim_y()) {
						game.addSlayerByUser(row,col);
						validInstruction = true;
					}
					else System.out.println(invalidPositionMsg);
				}
				else {
					System.out.println(invalidCommandMsg);
				}
				
			}
			else if("n".equals(instructions[0]) || "none".equals(instructions[0])) {
				System.out.println("Siguiente turno");
				validInstruction = true;
				nextstep = false;
				game.update();
			}
			else {
				System.out.println(unknownCommandMsg);
			}
		}
	}

}

