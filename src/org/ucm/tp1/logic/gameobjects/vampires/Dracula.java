package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class Dracula extends Vampire{

	private static boolean ALIVE = false;
	
	
    public Dracula(int x, int y, Game game) {
        super(x, y, game);
        this.resistance = 5;
        this.cycles = 0;
        this.alive = true;
        this.move = false;
    }

    @Override
	public boolean isAlive(){
		if(this.resistance > 0) ALIVE = true;
		else ALIVE = false;
		return ALIVE;
	}


	public static boolean Alive() {
		return ALIVE;
	}

    public void update(Game game){
		this.cycles++;
		
    }


/*    public boolean receiveSlayerAttack(int damage) {
        this.resistance = this.resistance - damage;
        return true;
    }


    public boolean receiveVampireAttack(int damage) {
        return false;
    }


    public boolean receiveLightFlash() {
        return false;
    }


    public boolean receiveGarlicPush() {
        return super.receiveGarlicPush();
    }


    public boolean receiveDraculaAttack() {
        return false;
    }

    @Override
    public boolean receiveExplosionDmg(int damage) {
        return false;
    }*/


    @Override
    public void attack(){
        if(isAlive()){
            IAttack other = game.getAttackableInPosition(x-1,y);
            if(other != null) other.receiveDraculaAttack();
        }
    }




	


//Representation

    @Override
    public String getIcon() {
        return "D["+this.resistance+"]";
    }
    
	@Override
	public String getClassToString() {
		return "D";
	}


//Health

    public int getResistance() {
        return this.resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }


//Coordinates


    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

//Cycles

    public int getCycles() {
        return this.cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

//Move

    public boolean isMove(){
        return this.move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void move(){
        super.move();
    }
    
    public boolean GameOver() {
    	return super.GameOver();
    }




}
