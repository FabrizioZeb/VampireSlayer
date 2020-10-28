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
		this.rand = new Random();
		this.rdseed = seed;
		this.dificultad = level;
		this.gameob = new GameObjectBoard(); 		
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
	
	public void addSlayer(Slayer sl) {
		gameob.addSlayer(sl);
	}
	
	//DRAW
	
	public String position(int x, int y) { //pasar contenido a gob		
		return gameob.getObjectInPos(x,y);
			
	}
	
	public String draw() {
		String s = "Number of cycles: " + getnumCiclos() + "\n";
		s += "Coins: " + getMonedas() + "\n";
		s += "Remainings vampires: " + getRemainingV() + "\n";
		s += "Vampires on the board: " + getNumV() + "\n";
		return s;
	}
	
	//ACTION
	
	//add slayer poner cordenadas.
	public void addSlayerByUser(int x, int y) {
		Slayer sl = new Slayer(x,y);
		addSlayer(sl);
	}
	
	
	
	
	
	
	public void update() {
		vampirelist.update(this);
		slayerlist.update(this);
		setNumciclos(getNumciclos()+1);
	}
	
	//RESET
	
	public void Reset() {
		setNumciclos(0);
		getPlayer().setMonedas(50);
		gameob.getVampirelists().setNumV(0);
		gameob.getVampirelists().setRemainingV(dificultad.getNumberOfVampires());
		gameob.getSlayerlists().setNumS(0);
	}
	
	
	private boolean gameover() {
		return gameob.GameOver();
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
