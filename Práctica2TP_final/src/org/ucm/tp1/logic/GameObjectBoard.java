package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.list.GameObjectList;
import org.ucm.tp1.logic.Game;

import java.awt.font.GlyphMetrics;


public class GameObjectBoard {

	private GameObjectList gameObjectList;
	private Game game;

	
	public GameObjectBoard(Game game) {
		this.gameObjectList = new GameObjectList();
		this.game = game;
	}

	public GameObject getObjectInPos(int x, int y){
		return gameObjectList.getObjectInPos(x,y);
	}



	public boolean Vacio(int x, int y) {
		return (gameObjectList.getObjectInPos(x,y) != null);
	}

		
	public void addSlayer(Slayer sl) {
		if(game.Buyable(sl) && game.Empty(sl.getX(), sl.getY())) {
			game.Bought(sl);
			slayerlists.anadirS(sl);
			game.SetinBoard(sl.getX(), sl.getY(), sl.representarS());
		}
		else if(!game.Buyable(sl))System.out.println("Monedas insuficientes");
		else if(!game.Empty(sl.getX(), sl.getY()))System.out.println("Coordenadas Ocupadas");
	}
	
	public void addVampire(Vampire vm) {
		if(vampirelists.shouldAddVampire()) {
			int row = getRandomRow();
			int col = game.ColsandRows(false) - 1;
			vm = new Vampire(game,row,col);
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
				game.SetinBoard(vm.getX(), vm.getY(), vm.representarV());
			}
		}
	}
	
	public int getRandomRow() {
		int randomRow = 0;
		int i = 0;
		boolean set = false;
		int cols = game.ColsandRows(false) - 1;
		int rows = game.ColsandRows(true);
		while(i < rows && !set) {
			randomRow = game.getRand().nextInt(rows);
			if(gameObjectList.getObjectInPos(randomRow, cols) == null) set = true;
			i++;
		}
		return randomRow;
	}

	public void attack() {
		slayerlists.Attack();
		vampirelists.Attack();
	}
	
	public void moveV() {
		vampirelists.moveV();
	}
	
	public void RemoveDeadObjs() {
		gameObjectList.getList().update(Game game);

	}
	
	public void IncreaseCicles() {
		vampirelists.IncreaseCiclos();
		slayerlists.IncreaseCiclos();
		
	}
	
	public boolean GameOver() {
		int i = 0;
		while(i < game.ColsandRows(true)  && !game.isPerdido()) {
			if(vampirelists.getVampirein(i, 0) != " " ) {
				game.setPerdido(true);
				System.out.println("Derrota");
			}
			i++;
		}
		return game.isPerdido();
	}
	

	public boolean Victory() {
		boolean victoria = false;
		if(vampirelists.getRemainingV() == 0 && vampirelists.getNumV() == 0) {
			victoria = true;
			System.out.println("Victoria");
		}
		return victoria;
	}
	

	
	public String StatsofVampires() {
		String s = "Remaining vampires: " + vampirelists.getRemainingV() + "\n";
		s += "Vampires on the board: " + vampirelists.getNumV() + "\n";
		return s;
	}
	
	
	public void attackV(int row, int col) {
		Slayer sl = slayerlists.slInXY(row, col);
		int x = sl.getX(), y = sl.getY();
		vampirelists.attackV(x,y,sl);
	}
	
	
	public void attackS(int row, int col) {
		Vampire vm = vampirelists.vmpInXY(row, col);
		int x = vm.getX(), y = vm.getY();
		slayerlists.attackS(x,y,vm);
	}






	


	
}

