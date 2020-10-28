package org.ucm.tp1.control;

import java.util.Scanner;

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
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
	    nextstep = false;
    }
    
    public void pinta() {
    	
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() {
		// TODO fill your code
    	
    	Scanner sc = new Scanner(System.in);
    	
    	while(true) {
    		//pintar
    		if(!nextstep) pinta();
    		
    		//pedir instrucciones
    		AskUserInstruction(sc);
    		
    		if(!nextstep) {
    			//move
    			//attack
    			//retire corpses
    			//check if game has finished
    		}
    		
    	}
    	//sc.close();
    }

	private void AskUserInstruction(Scanner sc) {
		boolean validInstruction = false;
		nextstep = true;

		while(!validInstruction) {
			System.out.print(prompt);
			String read = sc.nextLine();
			String[] instructions = read.toLowerCase().split(" ");
			
			if("h".equals(instructions[0]) || "help".equals(instructions[0])) {
				System.out.println("Te ayudo");
				validInstruction = true;
			}
			else if("e".equals(instructions[0]) || "exit".equals(instructions[0])) {
				System.out.println("Salgo");
				validInstruction = true;
			}
			else if("r".equals(instructions[0]) || "reset".equals(instructions[0])) {
				System.out.println("Reset");
				validInstruction = true;
			}
			else if("a".equals(instructions[0]) || "add".equals(instructions[0])) {
				if(instructions.length > 1) {
					String[] instructions2 = instructions[1].split(",");
					String[] instructions3 = instructions2[0].split("(");
					int x = Integer.parseInt(instructions3[1]);
					String[] instructions4 = instructions2[1].split(")");
					int y = Integer.parseInt(instructions4[0]);
					game.addSlayerByUser(x, y);
					
				}
				else {
					System.out.println(invalidCommandMsg);
				}
				System.out.println("Add Slayer");
				validInstruction = true;
			}
			else if("n".equals(instructions[0]) || "none".equals(instructions[0])) {
				System.out.println("Pues nada capo no se para que estás jugando");
				validInstruction = true;
				nextstep = false;
			}
			else if("d".equals(instructions[0]) || "dispara".equals(instructions[0])) {
				if(instructions.length > 1)	{
					validInstruction = true;
					nextstep = false;
				}
				else {
					System.out.println(invalidCommandMsg);
				}
			}
			else {
				System.out.println(unknownCommandMsg);
			}
		}
	}

}

