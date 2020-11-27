package org.ucm.tp1.logic.list;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.vampires.Vampires;
import org.ucm.tp1.logic.gameobjects.slayers.Slayers;

import java.util.ArrayList;

public class GameObjectList {

    private ArrayList<GameObject> list;
    private Slayers Slayer;
    private Vampires Vampire;
    private Game game;


    public GameObjectList(){
        list = new ArrayList<GameObject>();
    }

    public void addObject(GameObject object){
        list.add(object);
    }

    public boolean isDead(int i){
        return !list.get(i).isAlive();
    }

    //fix the ArrayList if an object dies.
    public void array(int i){
        while (i < list.size()){
            GameObject temp;
            temp = list.get(i+1);
            list.remove(i);
            list.set(i,temp);
            i++;
        }
    }

    public GameObject getObjectInPos(int x,int y){
        int i = 0;
        while(i < list.size()){
            if(list.get(i).getX() == x && list.get(i).getY() == y){
                if(list.get(i) == Slayer) return list.get(i);
                else if(list.get(i) == Vampire) return list.get(i);
            }
            i++;
        }
        return null;
    }

    public ArrayList<GameObject> getList() {
        return list;
    }
}
