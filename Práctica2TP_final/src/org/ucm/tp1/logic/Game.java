package org.ucm.tp1.logic;

import java.util.EmptyStackException;
import java.util.Random;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;
import org.ucm.tp1.view.IPrintable;

public class Game implements IPrintable {

	private GameObjectBoard gameob;
	private IPrintable IPrintable;

	private Random rand;
	private Level level;
	private int cycles;
	private boolean gamefinish;
	private boolean lose;
	private long seed;


	public Game(long seed, Level level) {
		super();
		this.rand = new Random(seed);
		this.seed = seed;
		this.level = level;
		this.gameob = new GameObjectBoard(this);
		this.lose = false;
		this.cycles = 0;
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
		return gameob.getInfo();
	}

	//user action

	public void reset(){
		this.rand = new Random(seed);
		this.gameob = new GameObjectBoard(this);
		this.cycles = 0;
	}

	public boolean addSlayer(int x, int y){
		if(level.getDim_x() > x && x > 0 && level.getDim_y() > y && y > 0)
				return gameob.addSlayer(x,y);
		else {
			System.out.println("Invalid position");
			return false;
		}
	}
	
	public boolean addBloodBank(int x, int y, int z) {
		if(level.getDim_x() > x && x > 0 && level.getDim_y() > y && y > 0)
			return gameob.addBloodBank(x,y,z);
		else {
			System.out.println("Invalid position");
			return false;
		}
	}
	
	public boolean garlicPush(int COST) {
		return gameob.garlicPush(COST);
	}
	
	public boolean lightFlash(int COST) {
		return gameob.lightFlash(COST);
	}
	
	public boolean superCoins(int COINS) {
		return gameob.superCoins(COINS);
	}
	
	public boolean addSelectedVampire(String type, int x, int y) {
		return gameob.addSelectedVampire(type, x, y);
	}
	


	//update

	public void update(){
		gameob.update();
	}
	
	public void recieveBloodBankCoins(int z){
		gameob.recieveBloodBankCoins(z);
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

	public void GameFinished() {
		if(gameob.gameOver() ||gameob.victory()){
			this.gamefinish = true;
		}
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
		return gameob.getCoins();
	}

	public void buy(int coins){
		gameob.buy(coins);
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
		if(gameob.getObjectInPos(x, y)!=null) return true;
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
}
