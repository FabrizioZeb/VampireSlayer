package org.ucm.tp1.logic;

import java.util.EmptyStackException;
import java.util.Random;

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


	@Override
	public String getPositionToString(int x, int y) {
		return null;
	}

	@Override
	public String getInfo() {
		return null;
	}

	public boolean Empty(int x, int y) {
		return gameob.empty(x,y);
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

	//Getters y setters:

	public Random getRand() {
		return rand;
	}

	public IAttack getAttackableInPosition(int x, int y) {
		IAttack obj;
		if(gameob.getObjectInPos(x,y) != null);
	}
	//Coins
	public int getCoins(){
		return gameob.getCoins();
	}

	public void buy(int coins){
		gameob.buy(coins);
	}

}
