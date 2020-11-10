package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.objects.*;
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
	private long seed;
	
	
	public Game(long seed, Level level) {
		super();
		this.rand = new Random(seed);
		this.seed = seed;
		this.dificultad = level;
		this.gameob = new GameObjectBoard(this); 		
		this.gameprinter = new GamePrinter(this,dificultad.getDim_x(), dificultad.getDim_y());
		this.player = new Player(this);
		this.perdido = false;
		this.numciclos = 0;
	}
	
	//Máxima cantidad de objectos en el tablero
	public int MaxCant() {
		return dificultad.getDim_x() * dificultad.getDim_y();
	}
	

	public boolean Empty(int x, int y) {
		return gameob.Vacio(x, y);
	}
	
	
	public boolean CorrectVmFrec() {
		return (rand.nextDouble() < dificultad.getVampireFrequency());
	}
	

	//DRAW
		
		public String position(int x, int y) {		
			return gameob.getObjectInPos(x,y);
				
		}
		
		
		private String StatsofGame() {
			String s = "Number of cycles: " + this.numciclos + "\n";
			s += "Coins: " + player.getMonedas() + "\n";
			return s;
		}
		
		
		public String pintar() {
			return StatsofGame() + gameob.StatsofVampires() + gameprinter.toString();
		}
	
	
	//USER ACTION
	
		//RESET
		
		public void Reset() {
			this.rand = new Random(seed);
			this.gameob = new GameObjectBoard(this);
			this.player = new Player(this);
			this.numciclos = 0;
		}
		
		
		//ADD SLAYER
		public void addSlayerByUser(int x, int y) {
			gameob.addSlayer(new Slayer(this,x,y));
		}
		

	//UPDATE
	
		public void moveVampires() {
			gameob.moveV();
		}
		
	
		public void attack() {
			gameob.attack();
		}
	
		//ADD VAMPIRES
		
		public void addVampire(int x, int y) {
			gameob.addVampire(new Vampire(this,x,y));
		}

		//REMOVE DEAD OBJECTS
		
		public void RemoveCorpses() {
			gameob.RemoveDeadObjs();		
		}
		
		//CHECK END
		
		public void GameFinished() {
			if(gameob.GameOver() || gameob.Victory()) {
				this.finjuego = true;
			}
		}
			

		public void update() {
			setNumciclos(this.numciclos+1);
			player.aumentar10monedas();
			gameob.IncreaseCicles();
			
		}
		


	//Gameprinter Functions
	
	
	public String GameprinterBoardXandY(int x, int y) {
		return gameprinter.getBoard()[x][y];
	}
	
	public void SetinBoard(int col, int row, String icon) {
		gameprinter.setinBoard(col, row, icon);
	}
	
	
	public int ColsandRows(boolean corr) {
		if (corr == false) return dificultad.getDim_x();
		else return dificultad.getDim_y();
	}
	
	//Buying Checks
	
	public boolean Buyable(Slayer sl) {
		return (player.getMonedas() >= sl.getCoste());
	}
	
	public void Bought(Slayer sl) {
		player.buyAt(sl.getCoste());
	}
	
	
	//Attacks
	
	public void attackVampire(int row, int col) {
		gameob.attackV(row,col);
		
	}

	public void attackSlayer(int row, int col) {
		gameob.attackS(row,col);
		
	}
	
	
	//Getters y setters:	
	

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



	public GamePrinter getGameprinter() {
		return gameprinter;
	}

	
	

	

	
	
	
}
