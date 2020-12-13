package org.ucm.tp1.logic.list;

import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.vampires.Vampire;
import org.ucm.tp1.logic.gameobjects.vampires.Dracula;


import java.util.ArrayList;

public class GameObjectList {

    private ArrayList<GameObject> list;
    protected Game game;

    public GameObjectList(){
        list = new ArrayList<GameObject>();
    }

    public void addObject(GameObject object){
        list.add(object);
    }

    public int getCycles(int i) {
        return list.get(i).getCycles();
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
                return list.get(i);
            }
            i++;
        }
        return null;
    }

    public ArrayList<GameObject> getList() {
        return list;
    }


    public void move(){
        for(int i = 0; i < list.size(); i++){
            list.get(i).move();
        }
    }

    public void attack() {
        for(int i = 0; i < list.size(); i++){
            list.get(i).attack();
        }
    }

    public void removeDeadObjects() {
        for(int i = 0; i < list.size(); i++){
            if(isDead(i))
                array(i);
        }
    }



    public boolean vampireIn(int x, int y) {
        int i = 0;
        Vampire vm;
        boolean enc = false;
        while (i < list.size() && !enc){
            //Esto no se puede hacer hay que cambiarlo
            if(list.get(i).equals(Vampire.class) && list.get(i).getX() == x && list.get(i).getY() == y){
                enc = true;
                return true;
            }
            i++;
        }
        return false;
    }

    public void increaseCycles() {
        for(int i = 0; i < list.size(); i++){
            list.get(i).setCycles(list.get(i).getCycles()+1);
        }
    }
    
    public boolean checkDracula() {
    	int i = 0;
    	while(i<list.size()) {
    		if(list.get(i).equals(Dracula.class))
    			return true;
    	}
    	return false;
    }
    
    public boolean garlicPush() {
    	for(int i = 0; i < list.size(); i++) list.get(i).receiveGarlicPush();
    	return true;
    }
    
    public boolean lightFlash() {
    	for(int i = 0; i < list.size(); i++) list.get(i).receiveLightFlash();
    	return true;
    }
}
