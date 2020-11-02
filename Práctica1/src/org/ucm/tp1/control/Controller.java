package org.ucm.tp1.control;

import java.util.Scanner;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.logic.Game;

public class Controller {


	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    private boolean nextstep;
    private GamePrinter gameprinter;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
	    nextstep = false;
    }
    
    public void pinta() {
    	System.out.println(game.draw());
    	System.out.println(game.getGameob().gameprint());
    }
    
    public void  printGame() {
   	 System.out.println(game);
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
    			game.getGameob().Vampiresbite();
    			//add vampire
    			game.addVampire();
    			//retire corpses
    			game.RemoveCorpses();
    			//check if game has finished
    			game.GameFinished();
    		}
    		
    	}
    	//sc.close();
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
				System.out.println("Reset");
				validInstruction = true;
			}
			else if("a".equals(instructions[0]) || "add".equals(instructions[0])) {
				if(instructions.length > 1) {
					String[] instructions2 = instructions[1].split(",");
					int x = Integer.parseInt(instructions2[0].substring(1));
					int y = Integer.parseInt(instructions2[1].substring(0, 1));
					game.addSlayerByUser(x, y);	
				}
				else {
					System.out.println(invalidCommandMsg);
				}
				System.out.println("Add Slayer");
				validInstruction = true;
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

