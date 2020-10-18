package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.objects.*;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.listas.*;

public class Game {
	
	private SlayerList slayerlist;
	private VampireList vampirelist;
	
	private Player player;
	private GamePrinter gamePrinter;
	
	
	private Random rand;
	private long rdseed;
	
	private Level dificultad;
	private int numciclos;
	private boolean finjuego;
	private boolean perdido;
	
	public Game(long seed, Level level) {
		//to-do
		this.rdseed = seed;
		this.dificultad = level;
		this.slayerlist = new SlayerList();
		this.vampirelist = new VampireList();
//		this.player = new Player();
		this.perdido = false;
		this.numciclos = 0;
		
	}
	

	
	public void anadirSlayer(Slayer sl) {
		if(player.obtenerMonedas() >= 50 && slayerlist.Vacio(sl.obtenercordX(), sl.obtenercordY())) {
			this.gamePrinter.
			player.establecerMonedas(player.obtenerMonedas()-50);
		}
	}
	
	
	public int obotenernCiclos() {
		return numciclos;
	}
	
	public void establecerCiclos(int i) {
		this.numciclos = i;
	}
	
	public String draw() {
		String s = "Number of cycles: " + obotenernCiclos() + "\n"
//		+ "Coins: " obtenerMonedas() + "\n"
//			+ "Remainings vampires: " vampirelist.numVampiros() + "\n"
				+ "Vampires on the board: ";
		return s;
	}




	
	
	
}
