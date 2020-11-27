package org.ucm.tp1.logic.gameobjects.slayers;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class Slayer extends Slayers {

    private static final int DMG = 1;
    private static Game game;

    public Slayer(int x, int y){
        super(x,y);
        this.dmg = DMG;
        this.resistance = 5;
        this.cost = 50;
        this.cycles = 0;
        this.alive = true;
    }

    public boolean isAlive(){
        return this.resistance > 0;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void attack() {
        if(isAlive()){
            IAttack other = game.getAttackableInPosition(x,y);
            if(other != null)
                other.receiveSlayerAttack(dmg);
        }
    }

/*  public String toString() {
        return null;
    }*/


//Representation

    public String representS(){
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
    @Override
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
    @Override
    public int getCost() {
        return this.cost;
    }



}
