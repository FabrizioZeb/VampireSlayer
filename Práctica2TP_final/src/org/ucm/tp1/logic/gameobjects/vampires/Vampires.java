package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.list.SlayerList;
import org.ucm.tp1.logic.list.VampireList;
import org.ucm.tp1.logic.gameobjects.IAttack;


public abstract class Vampires extends GameObject {

	private static int REMAINING_VAMPIRES;

	protected int dmg;
	protected int resistance;
	protected int cycles;
	protected boolean alive;
	protected boolean move;
	protected String vampireType;
	protected String objectLetters;

	//Construye un nuevo slayer, por lo que cyclos = 0
	public Vampires(int x, int y) {
		super(x,y);
	}

	public static int getRemainingVampires() {
		return REMAINING_VAMPIRES;
	}

	public static void setRemainingVampires(int remainingVampires) {
		REMAINING_VAMPIRES = remainingVampires;
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
