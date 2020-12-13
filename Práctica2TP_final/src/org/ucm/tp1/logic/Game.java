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

	public void addSlayer(int x, int y){
		gameob.addSlayer(x,y);
	}


	//update

	public void update(){
		gameob.update();
	}

	//attack

	public void attack(){
		gameob.attack();
	}

	//add vampires

	public void addVampire(){
		gameob.addVampire();
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
	//Rand
	public Random getRand() {
		return rand;
	}
	//Cycles
	public int getCycles() {
		return this.cycles;
	}


	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
