package org.ucm.tp1.objects;

import org.ucm.tp1.listas.*;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.view.GamePrinter;

public class GameObjectBoard {
	
	private VampireList vampirelists;
	private SlayerList slayerlists;
	private Game game;

	
	public GameObjectBoard(Game game) {
		this.slayerlists = new SlayerList();
		this.vampirelists = new VampireList(game);
		this.game = game;
	}


	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		int i = 0, j = 0;
		while ((i < vampirelists.getNumV() || j < slayerlists.getNumS()) && noenc) {
			if(vampirelists.getNumV() > 0)
			if(vampirelists.getLista()[i].getX() == x && vampirelists.getLista()[i].getY() == y)
				noenc = false;
			if(slayerlists.getNumS() > 0)
			if(slayerlists.getLista()[i].getX() == x && slayerlists.getLista()[i].getY() == y)
				noenc = false;
			i++;
			j++;
		}
		return noenc;
	}
	
	public Vampire vmpInXY(int x, int y) {
		Vampire v = null;
		int i = 0;
		boolean enc = false;
		while(i < vampirelists.getNumV() && !enc) {
			if(x == vampirelists.getLista()[i].getX() && y == vampirelists.getLista()[i].getY()) {
				v = vampirelists.getLista()[i];
				enc = true;
			}
			i++;
		}
		return v;
	}
		
	public void addSlayer(Slayer sl) {
		if(game.getPlayer().getMonedas() >= 50 && game.Empty(sl.getX(), sl.getY())) {
			slayerlists.anadirS(sl);
			game.getPlayer().setMonedas(game.getMonedas()-sl.getCoste());
			game.getGameprinter().setinBoard(sl.getX(), sl.getY(), sl.representarS());
		}
		else System.out.println("Las coordenadas estan ocupadas o monedas insuficientes");
	}


	public void addVampire(Vampire vm) {
		if(game.shouldAddVampire()) {
			int y = getRandomRow();
			int x = game.getDificultad().getDim_y() - 1;
			vm = new Vampire(game,x,y);
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
				game.getGameprinter().setinBoard(vm.getX(), vm.getY(), vm.representarV());
			}
			else System.out.println("[DEBUG] Position occuped");
		}
		
	}

	//Contenido en las cordenadas x,y para game que lo cede a gameprinter.
	public String getObjectInPos(int x, int y) {
		String space = " ";
		String r ="";
		boolean enc = false;
		int j = 0,i = 0;
		while (i < vampirelists.getNumV() || j < slayerlists.getNumS() && !enc) {
			if(vampirelists.getNumV() > 0 && (vampirelists.getLista()[i].getX() == x && vampirelists.getLista()[i].getY() == y)) {
				r = vampirelists.getLista()[i].representarV();
				enc = true;
			}
			else if(slayerlists.getNumS() > 0 && (slayerlists.getLista()[j].getX() == x && slayerlists.getLista()[j].getY() == y)) {
				r = slayerlists.getLista()[i].representarS();
				enc = true;
			}
			i++;
			j++;
		}
		if(!enc)
		return space;
		else 
			return r;
	}
	
	public boolean GameOver() {
		int i = 0;
		while(i < game.getDificultad().getDim_y() && !game.isPerdido()) {
			if(vmpInXY(0,i) != null) {
				game.setPerdido(true);
			}
			i++;
		}
		return game.isPerdido();
	}
	
	public boolean Victory() {
		boolean victoria = false;
		if(vampirelists.getRemainingV() == 0 && !GameOver()) {
			victoria = true;
		}
		return victoria;
	}
	
	public void Slayersfire() {
		for(int i = 0; i < slayerlists.getNumS(); i++) {
			slayerlists.getLista()[i].attack();
		}
	}
	
	public void Vampiresbite() {
		for(int i = 0; i < vampirelists.getNumV(); i++) {
			vampirelists.getLista()[i].attack();
		}
	}

	public VampireList getVampirelists() {
		return vampirelists;
	}


	public SlayerList getSlayerlists() {
		return slayerlists;
	}





	public void moveV() {
		for(int i = 0; i < vampirelists.getNumV(); i++) {
			int nextPos = vampirelists.getLista()[i].getY() - 1;
			if(getObjectInPos(vampirelists.getLista()[i].getX(), nextPos) == " " )   {
				if(vampirelists.getLista()[i].getCiclos() % 2 == 0)
				vampirelists.getLista()[i].setY(nextPos);
			}
		}
	}


	public void RemoveDeadObjs() {
		vampirelists.update(game);
		vampirelists.update(game);
		
		
	}


	public void ResetGame() {
		vampirelists = new VampireList(game);
		slayerlists = new SlayerList();
		game.getPlayer().setMonedas(50);
		game.setNumciclos(0);
		
		
		
	}


	public String gameprint(){
		return game.getGameprinter().toString();
	}
	
	
	public int getRandomRow() {
		int randomX =0 ;
		int i = 0;
		boolean set = false;
		int x = game.getDificultad().getDim_x();
		int y = game.getDificultad().getDim_y()-1;
		
		while(i < x && !set) {
			randomX = game.getRand().nextInt(x);
			if(game.getGameprinter().getBoard()[y][randomX] == " ") set = true;
		}
		return randomX;
	}

}

