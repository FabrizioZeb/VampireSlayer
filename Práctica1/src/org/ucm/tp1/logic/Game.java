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
	private GameObjectBoard gameob;
	
	
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
		this.gameob = new GameObjectBoard(); 
		this.slayerlist = gameob.getSlayerlists();
		this.vampirelist = gameob.getVampirelists();
		
		this.player = new Player();
		this.perdido = false;
		this.numciclos = 0;
	}
	
	public int MaxCant() {
		return getDificultad().getDim_x() * getDificultad().getDim_y();
	}
	

	public boolean Empty(int x, int y) {
		return gameob.Vacio(x, y);
	}
	
	
	
	public boolean shouldAddVampire() {
		return (vampirelist.getRemainingV() > 0 && rand.nextDouble() < dificultad.getVampireFrequency());
	}
	
	
	public void addVampire(Vampire vm) {
		gameob.addVampire(vm);
		
	}
	
	//DRAW
	
	public String position(int x, int y) { //pasar contenido a gob
		String space = " ";
		for(int i = 0; i < vampirelist.getNumV(); i++) {
			if(vampirelist.getLista()[i].getX() == x && vampirelist.getLista()[i].getY() == y)
				return vampirelist.getLista()[i].representarV();
		}
		for(int i = 0; i < slayerlist.getNumS(); i++) {
			if(slayerlist.getLista()[i].getX() == x && slayerlist.getLista()[i].getY() == y)
				return slayerlist.getLista()[i].representarS();
		}
		
		return space;
			
	}
	
	public String draw() {
		String s = "Number of cycles: " + getnumCiclos() + "\n";
		s += "Coins: " + getMonedas() + "\n";
		s += "Remainings vampires: " + getRemainingV() + "\n";
		s += "Vampires on the board: " + getNumV() + "\n";
		return s;
	}
	
	//ACTION
	
	public void situarSlayer(int x, int y) {
		//Slayer sl = 
		//slayerlist.anadirS(sl);
		
	}
	
	
	
	
	
	
	
	public void update() {
		vampirelist.update(this);
		slayerlist.update(this);
	}
	
	
	
	private boolean gameover() {
		int i = 0;
		
		while(i < dificultad.getDim_x() && !perdido) {
			if(gameob.vmpInXY(1,0) != null) {
				this.perdido = true;
			}
			i++;
		}
		return perdido;
	}
	
	
	
	
	
	//Getters y setters:	
	
	public SlayerList getSlayerlist() {
		return slayerlist;
	}
	
	public GameObjectBoard getGameob() {
		return gameob;
	}


	public void setGameob(GameObjectBoard gameob) {
		this.gameob = gameob;
	}


	public int getNumV() {
		return vampirelist.getNumV();
	}
	
	public int getRemainingV() {
		return vampirelist.getRemainingV();
	}

	public void setSlayerlist(SlayerList slayerlist) {
		this.slayerlist = slayerlist;
	}

	public VampireList getVampirelist() {
		return vampirelist;
	}

	public void setVampirelist(VampireList vampirelist) {
		this.vampirelist = vampirelist;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}

	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public long getRdseed() {
		return rdseed;
	}

	public void setRdseed(long rdseed) {
		this.rdseed = rdseed;
	}

	public Level getDificultad() {
		return dificultad;
	}

	public void setDificultad(Level dificultad) {
		this.dificultad = dificultad;
	}

	public int getNumciclos() {
		return numciclos;
	}

	public void setNumciclos(int numciclos) {
		this.numciclos = numciclos;
	}

	public boolean isFinjuego() {
		return finjuego;
	}

	public void setFinjuego(boolean finjuego) {
		this.finjuego = finjuego;
	}

	public boolean isPerdido() {
		return perdido;
	}

	public void setPerdido(boolean perdido) {
		this.perdido = perdido;
	}

	public int getnumCiclos() {
		return numciclos;
	}
	
	public void establecerCiclos(int i) {
		this.numciclos = i;
	}
	
	public int getMonedas() {
		return player.getMonedas();
	}
	
	public int getRandomRow() {
		int randomX = (Integer)null;
		int i = 0;
		boolean set = false;
		int x = dificultad.getDim_x();
		int y = dificultad.getDim_y();
		
		while(i < x && !set) {
			randomX = rand.nextInt(x);
			if(this.getGamePrinter().getBoard()[randomX][y] == " ") set = true;
		}
		return randomX;
	}

	
	
	
}
