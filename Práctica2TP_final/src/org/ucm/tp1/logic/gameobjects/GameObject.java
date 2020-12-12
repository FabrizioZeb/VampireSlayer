package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.list.GameObjectList;

public abstract class GameObject implements IAttack{

    protected int x;
    protected int y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract int getX();

    public abstract int getY();

    public abstract void setY(int y);

    public abstract void setX(int x);

    public abstract void setResistance(int hp);

    public abstract int getResistance();

    public abstract boolean isAlive();

    public abstract int getCycles();

    public abstract void update(Game game);

    //IAttack interface:
    public abstract boolean receiveSlayerAttack(int damage);
    public abstract boolean receiveVampireAttack(int damage);
    public abstract boolean receiveLightFlash();
    public abstract  boolean receiveGarlicPush();
    public abstract boolean receiveDraculaAttack();
    public abstract boolean receiveExplosionDmg(int damage);
    //IAttack interface end;


}
