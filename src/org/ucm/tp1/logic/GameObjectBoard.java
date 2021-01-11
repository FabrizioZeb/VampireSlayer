package org.ucm.tp1.logic;

import org.ucm.tp1.exceptions.DraculaIsAliveException;
import org.ucm.tp1.exceptions.NoMoreVampiresException;
import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.slayers.Slayer;
import org.ucm.tp1.logic.gameobjects.vampires.Vampire;
import org.ucm.tp1.logic.gameobjects.vampires.ExplosiveVampire;
import org.ucm.tp1.logic.gameobjects.vampires.Dracula;
import org.ucm.tp1.logic.list.GameObjectList;
import org.ucm.tp1.logic.gameobjects.BloodBank;


public class GameObjectBoard {


	private GameObjectList gameObjectList;
	private Game game;


	public GameObjectBoard(Game game) {
		this.gameObjectList = new GameObjectList(game);
		this.game = game;
	}

	public boolean empty(int x, int y) {
		boolean empty = false;
		if(gameObjectList.getObjectInPos(x,y) == null) empty = true;
		return empty;
	}

	public GameObject getObjectInPos(int x, int y){
		return gameObjectList.getObjectInPos(x,y);
	}


	public void move() {
		gameObjectList.move();

	}

	public void attack() {
		gameObjectList.attack();
	}

	public void addSlayer(int x, int y) {
		gameObjectList.addObject(new Slayer(x,y,game));
	}
	


	public void addVampire() {
		if(getRandomRow() != -1 && game.vampireFrequency() && Vampire.getRemainingVampires() > 0){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new Vampire(col,row,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
		}
		if(getRandomRow() != -1 && game.vampireFrequency() && Vampire.getRemainingVampires() > 0){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new ExplosiveVampire(col,row,game));
			Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard()+1);
			Vampire.setRemainingVampires(Vampire.getRemainingVampires()-1);
		}
		if(getRandomRow() != -1 && game.vampireFrequency() && !Dracula.Alive() && Vampire.getRemainingVampires() > 0){
			int row = getRandomRow();
			int col = game.getDim_X()-1;
			gameObjectList.addObject(new Dracula(col,row,game));
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



	public void IncreaseCycles(){
		gameObjectList.increaseCycles();
	}

	public void removeDeadObjects() {
		gameObjectList.removeDeadObjects();
	}

    public boolean gameOver() {
		if(gameObjectList.GameOver()){
			game.setLose(true);
			System.out.println("Game Over");
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
	
	
	public void addBloodBank(int x, int y, int z) {
		BloodBank BB = new BloodBank(x,y,z,game);
		gameObjectList.addObject(BB);
	}
	
	public boolean garlicPush(int COST) {
		return gameObjectList.garlicPush();
	}
	
	public boolean lightFlash(int COST) {
		return gameObjectList.lightFlash();
	}
	

	
	public boolean addSelectedVampire(String type, int x, int y) throws DraculaIsAliveException, NoMoreVampiresException {
		if (Vampire.getRemainingVampires() > 0) {
			if (type.equalsIgnoreCase("v")) {
				gameObjectList.addObject(new Vampire(x, y, game));
				Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() + 1);
				Vampire.setRemainingVampires(Vampire.getRemainingVampires() - 1);
				return true;
			} else if (type.equalsIgnoreCase("e")) {
				gameObjectList.addObject(new ExplosiveVampire(x, y, game));
				Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() + 1);
				Vampire.setRemainingVampires(Vampire.getRemainingVampires() - 1);
				return true;
			} else if (type.equalsIgnoreCase("d")) {
				if (!Dracula.Alive()) {
					gameObjectList.addObject(new Dracula(x, y, game));
					Vampire.setVampiresOnBoard(Vampire.getVampiresOnBoard() + 1);
					Vampire.setRemainingVampires(Vampire.getRemainingVampires() - 1);
					return true;
				} else throw new DraculaIsAliveException("[ERROR]: Dracula is already on board");
			}
		}
		else throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
		return false;
	}

	public void update() {
		gameObjectList.update();
	}
	
	public int getListSize() {
		return gameObjectList.getListSize();
	}
	
	public String getClassToString(int i) {
		return gameObjectList.getClassToString(i);
	}
	
	public int getObjectX(int i) {
		return gameObjectList.getObjectX(i);
	}
	
	public int getObjectY(int i) {
		return gameObjectList.getObjectY(i);
	}
	
	public int getObjectLife(int i) {
		return gameObjectList.getObjectLife(i);
	}
	
	public int getObjectCost(int i) {
		return gameObjectList.getObjectCost(i);
	}
	public int getObjectStep(int i) {
		return gameObjectList.getObjectStep(i);
	}
}

