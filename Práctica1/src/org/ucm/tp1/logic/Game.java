package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.objects.*;
import org.ucm.tp1.listas.*;
import org.ucm.tp1.view.GamePrinter;

public class Game {

	
	private Player player;
	private GameObjectBoard gameob;
	private GamePrinter gameprinter;
	
	private Random rand;
	private Level dificultad;
	private int numciclos;
	private boolean finjuego;
	private boolean perdido;
	
	
	public Game(long seed, Level level) {
		super();
		this.rand = new Random(seed);
		this.dificultad = level;
		this.gameob = new GameObjectBoard(this); 		
		this.gameprinter = new GamePrinter(this,dificultad.getDim_x(), dificultad.getDim_y());
		this.player = new Player(this);
		this.perdido = false;
		this.numciclos = 0;
	}
	
	public int MaxCant() {
		return getDificultad().getDim_x() * getDificultad().getDim_y();
	}
	

	public boolean Empty(int x, int y) {
		return gameob.Vacio(x, y);
	}
	
	
	public void addSlayer(Slayer sl) {
		gameob.addSlayer(sl);
	}
	
	//DRAW
	
	public String position(int x, int y) {		
		return gameob.getObjectInPos(x,y);
			
	}
	
	public String draw() {
		String s = "Number of cycles: " + getnumCiclos() + "\n";
		s += "Coins: " + getMonedas() + "\n";
		s += "Remainings vampires: " + gameob.getVampirelists().getRemainingV() + "\n";
		s += "Vampires on the board: " + gameob.getVampirelists().getNumV() + "\n";
		return s;
	}
	
	public String gameprint() {
		return gameob.gameprint();
	}
	
	//USER ACTION
	
		//RESET
		
		public void Reset() {
			gameob.ResetGame();
		}
		
		
		//add slayer poner cordenadas.
		public void addSlayerByUser(int x, int y) {
			Slayer sl = new Slayer(this,x,y);
			addSlayer(sl);
		}
		
		public void fire() {
			gameob.Slayersfire();
		}
		
		public void bite() {
			gameob.Vampiresbite();
		}
	
	//UPDATE
	
	public void moveVampires() {
		gameob.moveV();
	}
	
	public void update() {
		setNumciclos(getNumciclos()+1);
		player.aumentar10monedas();
		for(int i = 0; i < gameob.getVampirelists().getNumV(); i++) {
			gameob.getVampirelists().getLista()[i].setCiclos(gameob.getVampirelists().getLista()[i].getCiclos()+1);
		}
		for(int i = 0; i < gameob.getSlayerlists().getNumS(); i++) {
			gameob.getSlayerlists().getLista()[i].setCiclos(gameob.getSlayerlists().getLista()[i].getCiclos()+1);
		}
	}
	
	
	//ADD VAMPIRES
	
	public boolean shouldAddVampire() {
		return (gameob.getVampirelists().getRemainingV() > 0 && rand.nextDouble() < dificultad.getVampireFrequency());
	}
		
	public void addVampire() {
		Vampire vm = new Vampire(this,0, 0);
		gameob.addVampire(vm);
	}
	
	//REMOVE DEAD OBJECTS
	
	public void RemoveCorpses() {
		gameob.RemoveDeadObjs();		
	}
	
	//CHECK END
	
	public void GameFinished() {
		if(gameover() || gamevictory()) {
			this.finjuego = true;
		}
	}
	
	
	
	private boolean gameover() {
		return gameob.GameOver();
	}
	
	private boolean gamevictory() {
		return gameob.Victory();
	}
		
	
	//Getters y setters:	
	
	public GameObjectBoard getGameob() {
		return this.gameob;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public Level getDificultad() {
		return dificultad;
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
	


	public GamePrinter getGameprinter() {
		return gameprinter;
	}

	

	
	
	
}
