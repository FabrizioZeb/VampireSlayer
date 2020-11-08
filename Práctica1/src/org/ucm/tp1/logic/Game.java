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
		return dificultad.getDim_x() * dificultad.getDim_y();
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
	
	public String pintar() {
		return gameob.Stats() + gameob.gameprint();
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
			addSlayer(new Slayer(this,x,y));
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
		setNumciclos(this.numciclos+1);
		player.aumentar10monedas();
		gameob.IncreaseCicles();
		
	}
	
	//ADD VAMPIRES
	
	public boolean shouldAddVampire() {
		return (gameob.RemainingV() > 0 && rand.nextDouble() < dificultad.getVampireFrequency());
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
		
	
	public void ResetPlayer() {
		player.ResetMonedas();
	}
	
	public void ResetNumCiclos() {
		numciclos = 0;
	}
		
	
	//Coins Functions
	
	public int SlayerCost() {
		return 50;
	}
	
	public int CurrentCoins() {
		return player.getMonedas();
	}
	
	public void CoinsPostBuy(int i) {
		player.setMonedas(player.getMonedas()-i);
	}
	
	
	//Level (enum) Funtions
	
	public int cols() {
		return dificultad.getDim_x();
	}
	
	public int rows() {
		return dificultad.getDim_y();
	}
	
	
	
	//Gameprinter Functions
	
	
	public String GameprinterBoardXandY(int x, int y) {
		return gameprinter.getBoard()[x][y];
	}
	
	public void SetinBoard(int col, int row, String icon) {
		gameprinter.setinBoard(col, row, icon);
	}
	
	
	//Gameob Functions
	
	public int GameobGetsXorYofVorS(int i, boolean xory, boolean sorv) {
		int pos = 0;
		if(sorv == false) pos = gameob.TakeVPos(i, xory);
		else if(sorv == true) pos = gameob.TakeSPos(i, xory);
		return pos;
	}
	
	public void GameobVorSTakeDmg(int i, int dmg, boolean sorv) {
		if(sorv == false) gameob.VampireTakeDmg(i, dmg);
		else if(sorv == true) gameob.SlayerTakeDmg(i, dmg);
	}
	
	public int GameobNumVorNumS(boolean sorv) {
		int pos = 0;
		if(sorv == false) pos = gameob.GetNumV();
		else if(sorv == true) pos = gameob.GetNumS();
		return pos;
	}
		

	
	//Gameob Functions
	
	
	
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
