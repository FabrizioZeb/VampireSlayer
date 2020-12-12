package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class ExplosiveVampire extends Vampires {

    private static final int DMG = 1;
    private static final int EXPLOSIONDMG = 1;
    private static Game game;


    public ExplosiveVampire(int x, int y){
        super(x,y);
        this.dmg = DMG;
        this.resistance = 5;
        this.cycles = 0;
        this.alive = true;
        this.move = false;
    }

    public void update(Game game){
    }


    public boolean receiveSlayerAttack(int damage) {
        this.resistance = this.resistance - damage;
        return true;
    }


    public boolean receiveVampireAttack(int damage) {
        return false;
    }


    public boolean receiveLightFlash() {
        if(game.getCoins() >= 50)
            setResistance(0);
        return true;
    }


    public boolean receiveGarlicPush() {
        if(game.getCoins() >= 10) {
            game.buy(10);
            if (getX() == game.getDim_X()) setResistance(0);
            else if (getX() < game.getDim_X()) {
                setX(getX() + 1);
                setCycles(0);
            }
        }
        return true;
    }


    public boolean receiveDraculaAttack() {
        return false;
    }


    @Override
    public void attack(){
        if(isAlive()){
            IAttack other = game.getAttackableInPosition(x-1,y);
            if(other != null) other.receiveVampireAttack(DMG);
        }
    }

    public boolean isAlive() {
        return this.resistance > 0;
    }

//Representation

    public String representV() {
        return "EV["+this.resistance+"]";
    }


//Health

    public int getResistance() {
        return this.resistance;
    }

    public void setResistance(int resistance) {
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

    public void Move(){
        if(game.Empty(getX()-1,getY()) && getCycles() % 2 == 0) this.move = true;
        else this.move = false;
    }

//Explode

    public void explode(){
        if(getResistance() == 0){
            for(int i = x-1; i <= x + 1; i++){
                for(int j = y-1; j <= y+1; j++){
                    IAttack other = game.getAttackableInPosition(i,j);
                    if(other != null) other.receiveExplosionDmg(DMG);
                }
            }
        }
    }


}
