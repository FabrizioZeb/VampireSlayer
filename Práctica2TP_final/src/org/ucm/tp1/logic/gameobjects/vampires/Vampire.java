package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class Vampire extends Vampires implements IAttack{

    private static final int DMG = 1;
    private static Game game;

    public Vampire(int x, int y){
        super(x,y);
        this.dmg = DMG;
        this.resistance = 5;
        this.cycles = 0;
        this.alive = true;
        this.move = false;
    }

    public void update(Game game){
        Move();
        if(!VampireIn(this.x-1,this.y,game,this.cycles)) {
            attack();
            this.cycles++;
        }
        else if(isMove() && game.position(this.x-1,this.y) == null){
            this.y--;
        }
        else


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
        return "V["+this.resistance+"]";
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
    
//Recibe el daño (nuevo)    
    public boolean receiveSlayerAttack(int damage) {
    	if(this.alive) {
    		this.resistance -= damage;
    		return true;
    	}
    	else return false;
    }


}
