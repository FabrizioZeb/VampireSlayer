package org.ucm.tp1.logic.gameobjects;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.list.GameObjectList;

public abstract class GameObject {

    protected int x;
    protected int y;
    private GameObjectList lists;


    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        this.lists = new GameObjectList();
    }

    public abstract int getX();

    public abstract int getY();

    public abstract void setY(int y);

    public abstract void setX(int x);

    public abstract boolean isAlive();

    public abstract int getCycles();

    public GameObjectList getLists() {
        return lists;
    }

    public void setLists(GameObjectList lists) {
        this.lists = lists;
    }

    public abstract void update(Game game);
}
