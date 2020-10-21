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
		this.player = new Player();
		this.perdido = false;
		this.numciclos = 0;
		
	}
	

	public boolean Empty(int x, int y) {
		boolean vacio = false;
		if(vampirelist.Vacio(x, y) && slayerlist.Vacio(x, y)) vacio = true;
		return vacio;
	}
	
	public void addSlayer(Slayer sl) {
		if(player.getMonedas() >= 50 && Empty(sl.getX(), sl.getY())) {
			slayerlist.anadirS(sl);
			player.setMonedas(getMonedas()-50);
			
		}
	}
	
	public void addVampire(Vampire vm) {
		if(Empty(vm.getX(), vm.getY())) {
			vampirelist.anadirV(vm);
		}
	}
	
	
	
	//Qué hay en la posición x e y
	public String position(int x, int y) {
		for(int i = 0; i < vampirelist.getNumV(); i++) {
			if(vampirelist.getLista()[i].getX() == x && vampirelist.getLista()[i].getY() == y)
				return vampirelist.getLista()[i].representarV();
		}
		for(int i = 0; i < slayerlist.getNumS(); i++) {
			if(slayerlist.getLista()[i].getX() == x && slayerlist.getLista()[i].getY() == y)
				return slayerlist.getLista()[i].representarS();
		}
		
		return null;
			
	}
	
	
	
	public String draw() {
		String s = "Number of cycles: " + getnumCiclos() + "\n";
		s += "Coins: " + getMonedas() + "\n";
		s += "Remainings vampires: " + getNumV() + "\n";
		s += "Vampires on the board: " ;
		return s;
	}
	
	public void update() {
		vampirelist.update(this);
		slayerlist.update(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public SlayerList getSlayerlist() {
		return slayerlist;
	}
	
	public int getNumV() {
		return vampirelist.getNumV();
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
		int i;
		boolean set = false;
		if(dificultad.EASY != null) {
			i = 0;
			while(i < 4 && !set) {
				randomX = rand.nextInt(4);
				//if(Si la posicion [randomX][7] = " ") set = true;
				i++;		
			}	
		}
		else if(dificultad.HARD != null) {
			i = 0;
			while(i < 3 && !set) {
				randomX = rand.nextInt(3);
				//if(Si la posicion [randomX][7] = " ") set = true;
				i++;		
			}
		}
		else if(dificultad.INSANE != null) {
			i = 0;
			while(i < 6 && !set) {
				randomX = rand.nextInt(5);
				if(this.getGamePrinter1().getBoard()[randomX][6] == " " ) set = true;
				i++;		
			}	
		}
		return randomX;
	}


	public GamePrinter getGamePrinter1() {
		return gamePrinter;
	}


	
	
	
}
