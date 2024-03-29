package org.ucm.tp1.logic;

import java.util.EmptyStackException;
import java.util.Random;

import org.ucm.tp1.exceptions.DraculaIsAliveException;
import org.ucm.tp1.exceptions.NoMoreVampiresException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.exceptions.UnvalidPositionException;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.logic.gameobjects.slayers.Slayer;
import org.ucm.tp1.logic.gameobjects.vampires.Vampire;
import org.ucm.tp1.logic.gameobjects.vampires.ExplosiveVampire;
import org.ucm.tp1.logic.gameobjects.vampires.Dracula;
import org.ucm.tp1.logic.gameobjects.BloodBank;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;
import org.ucm.tp1.view.IPrintable;

public class Game implements IPrintable {

	private GameObjectBoard gameob;
	private Player coins;
	private IPrintable IPrintable;
	private GamePrinter GamePrinter;

	private Random rand;
	private Level level;
	private int cycles;
	private boolean gamefinish;
	private boolean lose;
	private long seed;


	public Game(long seed, Level level) {
		super();
		this.seed = seed;
		this.level = level;
		this.rand = new Random(seed);
		this.gameob = new GameObjectBoard(this);
		this.coins = new Player(this);
		this.IPrintable = new IPrintable() {
			@Override
			public String getPositionToString(int x, int y) {
				return gameob.getPositionToString(x,y);
			}

			@Override
			public String getInfo() {
				String s;
				s = "Number of cycles: " + cycles + "\n";
				s += "Coins: " + coins.getCoins() + "\n";
				s += "Remaining vampires: " + Vampire.getRemainingVampires() + "\n";
				s += "Vampires on the board: " + Vampire.getVampiresOnBoard() + "\n";
				if(Dracula.Alive()){
					s += "Dracula is alive\n";
				}
				return s;
			}
		};
		this.GamePrinter = new GamePrinter(IPrintable,level.getDim_x(), level.getDim_y());
		this.lose = false;
		this.cycles = 0;
	}

	//Initialize Rem Vampires:
	public void InitializeRemainingVampires(){
		Vampire.setRemainingVampires(level.getNumberOfVampires());
	}

	//add object:
	public boolean Empty(int x, int y) {
		return gameob.empty(x,y);
	}


	//draw

	@Override
	public String getPositionToString(int x, int y) {
		return gameob.getPositionToString(x,y);
	}

	@Override
	public String getInfo() {
		String s;
		s = "Number of cycles: " + cycles + "\n";
		s += "Coins: " + coins.getCoins() + "\n";
		s += "Remaining vampires: " + Vampire.getRemainingVampires() + "\n";
		s += "Vampires on the board: " + Vampire.getVampiresOnBoard() + "\n";
		if(Dracula.Alive()){
			s += "Dracula is alive\n";
		}
		return s;
	}

	public String printGame(){
		return GamePrinter.toString();
	}

	//user action

	public void reset(){
		this.rand = new Random(seed);
		this.gameob = new GameObjectBoard(this);
		this.coins = new Player(this);
		InitializeRemainingVampires();
		Vampire.setVampiresOnBoard(0);
		this.cycles = 0;
	}

	public boolean addSlayer(int x, int y) throws UnvalidPositionException, NotEnoughCoinsException {
		if(level.getDim_x() > x && x >= 0 && level.getDim_y() > y && y >= 0){
			Slayer Sl = new Slayer(x,y,this);
			if(coins.getCoins() >= Sl.getCost() && gameob.empty(x, y)) {
				coins.buyAt(Sl.getCost());
				gameob.addSlayer(x,y);
				return true;
			}
			else if(gameob.getObjectInPos(x,y) != null) {
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid Position");
			}	
			else if(coins.getCoins() < Sl.getCost()) {
				throw new NotEnoughCoinsException("[ERROR]: BloodBank cost is " + Sl.getCost() + ": Not enough coins");
			}

		}
		else {
			System.out.println("Invalid position");
			return false;
		}
		return false;
	}
	
	public boolean addBloodBank(int x, int y, int z) throws UnvalidPositionException,NotEnoughCoinsException {
		if(level.getDim_x() > x && x >= 0 && level.getDim_y() > y && y >= 0){
			if(coins.getCoins() < z) {
				throw new NotEnoughCoinsException("[ERROR]: BloodBank cost is " + z + ": Not enough coins");
			}
			else if(gameob.getObjectInPos(x, y) != null){
				throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid Position");
			} 
			else {
				coins.buyAt(z);
				gameob.addBloodBank(x, y, z);
				return true;
			}
		}
		
		else throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid Position");
	}
	
	public boolean garlicPush(int COST) throws NotEnoughCoinsException {
		if(coins.getCoins() > COST){
			coins.buyAt(COST);
			return gameob.garlicPush(COST);
		}
		else throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is" + COST + ": Not enough coins");
		
	}
	
	public boolean lightFlash(int COST) throws NotEnoughCoinsException {
		if(coins.getCoins() > COST) {
			coins.buyAt(COST);
			return gameob.lightFlash(COST);
		}
		else throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is" + COST + ": Not enough coins");
	}
	
	public void superCoins(int COINS) {
		coins.increaseCoins(COINS);
	}
	
	public boolean addSelectedVampire(String type, int x, int y) throws UnvalidPositionException, DraculaIsAliveException, NoMoreVampiresException {
		if(level.getDim_x() > x && level.getDim_y() > y) {
			if (Vampire.getRemainingVampires() == 0) return false;
			else
				if(gameob.getObjectInPos(x,y) == null)
				return gameob.addSelectedVampire(type, x, y);
				else throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
		}
		else throw new UnvalidPositionException("[ERROR]: Position (" + x + ", " + y + "): Unvalid position");
	}
	


	//update

	public void update(){
		coins.aumentar10monedas();
		gameob.update();
		gameob.move();
		gameob.attack();
		addVampire();
		gameob.removeDeadObjects();
		gameob.IncreaseCycles();
		cycles++;
	}
	
	public void recieveBloodBankCoins(int z){
		coins.increaseCoins(z);
	}
	
	

	//attack

	public void attack(){
		gameob.attack();
	}

	//add vampires

	public void addVampire(){
			gameob.addVampire();
	}
	
	public boolean vampireFrequency() {
		return (rand.nextDouble()<level.getVampireFrequency());
	}

	//remove dead objects

	public void removeCorpses(){
		gameob.removeDeadObjects();
	}

	//check end

	public boolean GameFinished() {
		if(gameob.gameOver() || gameob.victory()){
			this.gamefinish = true;
		}
		return this.gamefinish;
	}

	public IAttack getAttackableInPosition(int x, int y) {
		GameObject other = null;
		if(gameob.getObjectInPos(x,y) != null)
			other = gameob.getObjectInPos(x,y);
		return other;
	}

	//Getters y setters:
	//Coins


	public int getCoins(){
		return coins.getCoins();
	}

	public Level getLevel() {
		return level;
	}
	//Dims
	public int getDim_X() {
		return level.getDim_x();
	}

	public int getDim_Y(){
		return level.getDim_y();
	}
	
	public boolean getObject(int x, int y) {
		if(gameob.getObjectInPos(x, y) != null) return true;
		else return false;
					
	}
	//Rand
	public Random getRand() {
		return rand;
	}
	//Cycles
	public int getCycles() {
		return this.cycles;
	}
	//Vampires:
	public int getNumberOfVampires(){
		return level.getNumberOfVampires();
	}


	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public void exit() {
		System.exit(0);
	}
	
	public boolean isFinish(){
		return GameFinished();
	}
	
	//Guardo y cargado
	
	public String serialize() {
		String z;
		z ="Cycles: " + cycles + "\n";
		z +="Level: " + level + "\n";
		z +="Coins: " + coins.getCoins() + "\n";
		z +="Remaining Vampires: " + Vampire.getRemainingVampires() + "\n";
		z +="Vampires on the Board: " + Vampire.getVampiresOnBoard() + "\n\n";
		z +="Game Object List:" + "\n";
		for(int i=0; i < gameob.getListSize();i++) {
			z += gameob.getClassToString(i) + ";" + gameob.getObjectX(i) + ";" + gameob.getObjectY(i) + ";" + gameob.getObjectLife(i);
			if(gameob.getClassToString(i).equals("B")) z += ";" + gameob.getObjectCost(i);
			else if(!gameob.getClassToString(i).equals("S")) z += ";" + gameob.getObjectStep(i);
			z += "\n";
		}
		return z;
	}
	
	
	//Winning msg
	public String getWinnerMessage() {
		return "Winnnnnnn !!!!";
	}
}