package org.ucm.tp1.logic.gameobjects.slayers;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class Slayer extends GameObject {

    private static final int DMG = 1;
    private int dmg;
    private int resistance;
    private final int COST = 50;
    private int cost;
    private int cycles;
    private boolean alive;

    public Slayer(int x, int y, Game game){
        super(x,y, game);
        this.dmg = DMG;
        this.cost = COST;
        this.resistance = 3;
        this.cycles = 0;
        this.alive = true;
    }

    public boolean isAlive(){
        return this.resistance > 0;
    }

    @Override
    public void update(Game game) {
    }

    public boolean receiveVampireAttack(int damage) {
        this.resistance = this.resistance - damage;
        return true;
    }

    public boolean receiveDraculaAttack() {
        setResistance(0);
        return true;
    }

    @Override
    public void attack() {
        if(isAlive()){
            boolean attacked = false;
            int i = x;
            while(i < game.getDim_X() && !attacked) {
                IAttack other = game.getAttackableInPosition(i, y);
                if (other != null) {
                    attacked = other.receiveSlayerAttack(dmg);
                }
                i++;
            }
        }
    }


//Representation

    public String getIcon(){
        return "S[" + this.resistance +"]";
    }

//Health

    public int getResistance(){
        return this.resistance;
    }

    public void setResistance(int resistance){
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

    public void setCycles(int cycles){
        this.cycles = cycles;
    }


//Cost

    public int getCost() {
        return this.cost;
    }

//Move
    public void move(){

    }
    
    public boolean GameOver() {
    	return  false;
    }

	@Override
	public String getClassToString() {
		return "S";
	}




}
