package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class BloodBank extends GameObject {
	protected int resistance;
	protected int cycles;
	protected boolean alive;
	protected int cost;
	
	public BloodBank(int x, int y, int z, Game game){
        super(x,y, game);
        this.cost = z;
        this.resistance = 1;
        this.cycles = 0;
        this.alive = true;
    }

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}

	@Override
	public void setX(int x) {
		this.x = x;
		
	}

	@Override
	public void setResistance(int hp) {
		this.resistance = hp;
		
	}

	@Override
	public int getResistance() {
		return this.resistance;
	}

	@Override
	public boolean isAlive() {
		return this.resistance > 0;
	}

	@Override
	public int getCycles() {
		return this.cycles;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Game game) {
		game.recieveBloodBankCoins(this.cost/10);
	}

	@Override
	public String getIcon() {
		return "B[" + this.cost + "]";
	}

	@Override
	public void setCycles(int i) {
		this.cycles = i;
	}

	@Override
	public void attack() {		
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		return false;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		setResistance(0);
		return true;
	}

	@Override
	public boolean receiveLightFlash() {
		return false;
	}

	@Override
	public boolean receiveGarlicPush() {
		return false;
	}

	@Override
	public boolean receiveDraculaAttack() {
		setResistance(0);
		return true;
	}

	@Override
	public boolean receiveExplosionDmg(int damage) {
		return false;
	}
	
	@Override
	public boolean GameOver(){
		return false;
	}

}
