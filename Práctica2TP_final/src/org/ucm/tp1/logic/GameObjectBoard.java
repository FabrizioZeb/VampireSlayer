package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.slayers.Slayer;
import org.ucm.tp1.logic.gameobjects.vampires.Vampire;
import org.ucm.tp1.logic.gameobjects.vampires.ExplosiveVampire;
import org.ucm.tp1.logic.gameobjects.vampires.Dracula;
import org.ucm.tp1.logic.list.GameObjectList;
import org.ucm.tp1.logic.gameobjects.BloodBank;


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

	public boolean addSlayer(int x, int y) {
		Slayer Sl = new Slayer(x,y,game);
		if(coins.getCoins() > Sl.getCost() && getObjectInPos(x, y) == null) {
			coins.buyAt(Sl.getCost());
			gameObjectList.addObject(Sl);
			return true;
		}
		else if(coins.getCoins() < Sl.getCost()) {
			System.out.println("Insufficient coins");
			return false;
		}
		else if(getObjectInPos(x,y) != null) {
			System.out.println("Position occupied");
			return false;
		}
		return false;
	}


	public void addVampire() {
		if(getRandomRow() != -1 && game.vampireFrequency()){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new Vampire(col,row,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
		}
		if(getRandomRow() != -1 && game.vampireFrequency()){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new ExplosiveVampire(col,row,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
		}
		if(getRandomRow() != -1 && game.vampireFrequency() && !gameObjectList.checkDracula()){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new Dracula(col,row,game));
			System.out.println("Dracula is alive");
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
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
			if(getObjectInPos(cols,randomRow) == null) set = true;
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

	public void IncreaseCycles(){
		gameObjectList.increaseCycles();
	}

	public void removeDeadObjects() {
		gameObjectList.removeDeadObjects();
	}

    public boolean gameOver() {
		int i = 0;
		while(i < game.getDim_Y() && !game.isLose()){
			if(gameObjectList.vampireIn(0,i)){
				game.setLose(true);
				System.out.println("Game Over");
			}
			i++;
		}
		return game.isLose();
    }

	public boolean victory() {
		boolean victory = false;
		if(Vampire.getRemainingVampires() == 0 && Vampire.getVampiresOnBoard() == 0){
			victory = true;
			System.out.println("Victory");
		}
		return victory;
	}
	
	public void recieveBloodBankCoins(int z) {
		coins.increaseCoins(z);
	}
	
	public boolean addBloodBank(int x, int y, int z) {
		BloodBank BB = new BloodBank(x,y,z,game);
		if(coins.getCoins() > z && getObjectInPos(x, y) == null) {
			coins.buyAt(z);
			gameObjectList.addObject(BB);
			return true;
		}
		else if(coins.getCoins() < z) {
			System.out.println("Insufficient coins");
			return false;
		}
		else if(getObjectInPos(x,y) != null) {
			System.out.println("Position occupied");
			return false;
		}
		return false;
	}
	
	public boolean garlicPush(int COST) {
		if(coins.getCoins() > COST) {
			coins.buyAt(COST);
			return gameObjectList.garlicPush();
		}
		return false;
	}
	
	public boolean lightFlash(int COST) {
		if(coins.getCoins() > COST) {
			coins.buyAt(COST);
			return gameObjectList.lightFlash();
		}
		return false;
	}
	
	public boolean superCoins(int COINS) {
		coins.increaseCoins(COINS);
		return true;
	}
	
	public boolean addSelectedVampire(String type, int x, int y) {
		if(type.equals("[V]") && getObjectInPos(x,y).equals(null)) {
			gameObjectList.addObject(new Vampire(x,y,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
			return true;
		}
		else if(type.equals("[E]") && getObjectInPos(x,y).equals(null)) {
			gameObjectList.addObject(new ExplosiveVampire(x,y,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
			return true;
		}
		else if(type.equals("[D]") && getObjectInPos(x,y).equals(null)) {
			if(!gameObjectList.checkDracula()) {
				gameObjectList.addObject(new Dracula(x,y,game));
				Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
				Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
				System.out.println("Dracula is alive");
				return true;
			}
			else System.out.println("[ERROR]: Dracula is already alive");
			return false;
		}
		else {
			System.out.println("[ERROR]: invalid type");
			return false;
		}
	}
}

