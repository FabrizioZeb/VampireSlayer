package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.slayers.Slayer;
import org.ucm.tp1.logic.gameobjects.vampires.Vampire;
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
		return coins.getCoins();
	}


	public void buy(int price){
		coins.setCoins(getCoins()-price);
	}



	public void update() {
		coins.aumentar10monedas();
		gameObjectList.move();

	}

	public void attack() {
		gameObjectList.attack();
	}

	public void addSlayer(int x, int y) {
		Slayer Sl = new Slayer(x,y);
		if(coins.getCoins() > Sl.getCost() && getObjectInPos(x, y) == null) {
			coins.buyAt(Sl.getCost());
			gameObjectList.addObject(Sl);
		}
		else if(coins.getCoins() < Sl.getCost()) System.out.println("Insufficient coins");
		else if(getObjectInPos(x,y) != null) System.out.println("Position occupied");
	}


	public void addVampire() {
		if(getRandomRow() != -1){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new Vampire(row,col));
		}
	}

	public int getRandomRow(){
		int randomRow = -1;
		int i = 0;
		boolean set = false;
		int cols = game.getDim_X() - 1;
		int rows = game.getDim_Y();
		while (i < rows && !set){
			randomRow = game.getRand().nextInt(rows);
			if(getObjectInPos(randomRow,cols) == null) set = true;
			i++;
		}
		return randomRow;
	}


	public String getPositionToString(int x, int y) {
		String space = " ";
		GameObject obj = getObjectInPos(x,y);
		if(obj != null) return obj.getIcon();
		else return space;
	}

	public String getInfo() {
		String s;
		s = "Number of cycles: " + game.getCycles() + "\n";
		s += "Coins: " + coins.getCoins() + "\n";
		s += "Remaining vampires: " + Vampire.getRemainingVampires() + "\n";
		s += "Vampires on the board: " + Vampire.getVampiresOnBoard() + "\n";
		return s;
	}

	public void removeDeadObjects() {
		gameObjectList.removeDeadObjects();
	}
}

