package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.IAttack;

public class ExplosiveVampire extends Vampire {

    private static final int DMG = 1;
    private static final int EXPLOSIONDMG = 1;


    public ExplosiveVampire(int x, int y, Game game){
        super(x,y, game);
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

    @Override
    public void attack(){
    super.attack();
    }

    public boolean isAlive() {
        return this.resistance > 0;
    }

//Representation

    @Override
    public String getIcon() {
        return "EV["+this.resistance+"]";
    }
    
	@Override
	public String getClassToString() {
		return "EV";
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

    public void move(){
        super.move();
    }
    
    public boolean GameOver() {
    	return super.GameOver();
    }

//Explode

    public void explode(){
        if(getResistance() == 0){
            for(int i = x-1; i <= x + 1; i++){
                for(int j = y-1; j <= y+1; j++){
                    IAttack other = game.getAttackableInPosition(i,j);
                    if(other != null) other.receiveExplosionDmg(EXPLOSIONDMG);
                }
            }
        }
    }


}
