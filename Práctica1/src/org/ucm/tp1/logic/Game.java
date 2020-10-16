package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.objects.*;
import org.ucm.tp1.listas.*;

public class Game {
	
	private SlayerList slayerlist;
	private VampireList vampirelist;
	private Random rand;
	private long rdseed;
	private Level dificultad;
	private boolean finjuego;
	private boolean perdido;
	
	public Game(long seed, Level level) {
		//to-do
		this.rdseed = seed;
		this.dificultad = level;
		this.slayerlist = new SlayerList();
		this.vampirelist = new VampireList();
		this.finjuego = false;
		
	}
	
	
}
