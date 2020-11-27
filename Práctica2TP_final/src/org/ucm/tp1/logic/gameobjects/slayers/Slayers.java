package org.ucm.tp1.logic.gameobjects.slayers;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;

public abstract class Slayers extends GameObject {


	protected int cost;
	protected int dmg;
	protected int resistance;
	protected int cycles;
	protected boolean alive;
	protected boolean move;
	protected String slayerType;
	protected String objectLetters;

	public Slayers(int x, int y) {
		super(x,y);
	}

/*	public void attack() {
		if(this.resistance > 0)

	}*/

	public abstract int getResistance();
	public abstract void setResistance(int resistance);
	public abstract int getCycles();
	public abstract void setCycles(int cycles);
	public abstract int getCost();
	public abstract boolean isAlive();

	//	public abstract String toString();
	public abstract String representS();
	public abstract void setPos(int x, int y);
	public abstract void attack();


}
