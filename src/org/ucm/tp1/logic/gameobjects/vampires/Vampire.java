package org.ucm.tp1.logic.gameobjects.vampires;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.IAttack;


public class Vampire extends GameObject {

    private static int vampiresOnBoard;
    private static int remainingVampires;

    private static final int DMG = 1;
    protected int dmg;
    protected int resistance;
    protected int cycles;
    protected boolean alive;
    protected boolean move;

    public Vampire(int x, int y, Game game){
        super(x,y, game);
        this.dmg = DMG;
        this.resistance = 5;
        this.cycles = 0;
        this.alive = true;
        this.move = false;
    }

    public static int getRemainingVampires() {
        return remainingVampires;
    }

    public static int getVampiresOnBoard(){
        return vampiresOnBoard;
    }


    //Number of Vampires Static methods:
    public static void InitializeRemainingVampires(){
        remainingVampires = game.getNumberOfVampires();
    }

    public static void setRemainingVampires(int i){
        remainingVampires = i;
    }

    public static void InitializeVampiresOnBoard(){
        vampiresOnBoard = 0;
    }

    public static void setVampiresOnBoard(int i){
        vampiresOnBoard = i;
    }


    @Override
    public int getCost() {
        return 0;
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
            setResistance(0);
        return true;
    }


    public boolean receiveGarlicPush() {
	    if (getX() == game.getDim_X()-1) setResistance(0);
         else if (getX() < game.getDim_X()-1 && !game.getObject(this.x + 1,this.y)){
			setCycles(0);
            setX(getX() + 1);
        }
        return true;
    }


    public boolean receiveDraculaAttack() {
        return false;
    }

    @Override
    public boolean receiveExplosionDmg(int damage) {
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
    	if(this.resistance <= 0){
    	    if(vampiresOnBoard > 0)
            setVampiresOnBoard(getVampiresOnBoard() - 1);
        }
        return this.resistance > 0;
    }

//Representation

    public String getIcon() {
        return "V["+this.resistance+"]";
    }
    
	public String getClassToString() {
		return "V";
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
        if(getCycles() > 0 && game.Empty(getX()-1,getY()) && getCycles() % 2 == 0) {
            this.move = true;
            setX(getX()-1);
        }
        else this.move = false;

    }
    
    public boolean GameOver() {
    	if(this.x == 0) return true;
    	else return false;
    }
}
