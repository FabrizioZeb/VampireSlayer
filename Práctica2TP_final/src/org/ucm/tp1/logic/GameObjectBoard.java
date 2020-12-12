package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.list.GameObjectList;


public class GameObjectBoard {

	private Player coins;
	private GameObjectList gameObjectList;
	private Game game;


	public GameObjectBoard(Game game) {
		this.coins = new Player(game);
		this.gameObjectList = new GameObjectList();
		this.game = game;
	}

	public boolean empty(int x, int y) {
		boolean empty = false;
		if(gameObjectList.getObjectInPos(x,y) != null) empty = true;
		return empty;
	}

	public GameObject getObjectInPos(int x, int y){
		return gameObjectList.getObjectInPos(x,y);
	}

	public int getCoins(){
		return coins.setCoins();
	}


	public void buy(int price){
		coins.setCoins(getCoins()-price);
	}

}

