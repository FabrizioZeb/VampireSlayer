package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.list.VampireList;


public abstract class Vampires extends GameObject {

	private static int vampiresOnBoard;
	private static int remainingVampires;

	protected int dmg;
	protected int resistance;
	protected int cycles;
	protected boolean alive;
	protected boolean move;
	protected String vampireType;
	protected String objectLetters;

//Remaining Vampires and Vampires on Board control:
	public static int getRemainingVampires() {
	return remainingVampires;
}

	public static void setRemainingVampires(int remainingVampires) {
		Vampires.remainingVampires = remainingVampires;
	}

	public static int getVampiresOnBoard() {
		return vampiresOnBoard;
	}

	public static void setVampiresOnBoard(int vampiresOnBoard) {
		Vampires.vampiresOnBoard = vampiresOnBoard;
	}

	public static void reduceRemainingVampires() {
		setRemainingVampires(remainingVampires-1);
	}

	public static void addVampiresOnBoard() {
		setVampiresOnBoard(vampiresOnBoard+1);
	}

	public Vampires(int x, int y) {
		super(x,y);
	}

	public boolean isMove(VampireList list){
		if(this.x-1 >= 0 && getCycles() % 2 == 0 && getCycles() > 1) setMove(true);
		else setMove(false);
		return isMove();
	}

	public boolean VampireIn(int x,int y, Game game, int cycles){
		if(!(game.position(x,y) == this) && game.position(x, y) != null) return false;
		else return false;
	}

	public abstract int getResistance();
	public abstract void setResistance(int resistance);
	public abstract int getCycles();
	public abstract void setCycles(int cycles);
	public abstract boolean isAlive();
//	public abstract String toString();
	public abstract String representV();
	public abstract void setPos(int x, int y);
	public abstract boolean isMove();
	public abstract void setMove(boolean move);
	public abstract void attack();
	
	



	
}
