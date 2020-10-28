package org.ucm.tp1.objects;

import org.ucm.tp1.listas.*;
import org.ucm.tp1.logic.Game;

public class GameObjectBoard {
	
	private VampireList vampirelists;
	private SlayerList slayerlists;
	private Game game;

	
	public GameObjectBoard() {
		this.slayerlists = new SlayerList();
		this.vampirelists = new VampireList();
	}


	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		int i = 0, j = 0;
		while ((i < vampirelists.getNumV() || j < slayerlists.getNumS()) && noenc) {
			
			if(vampirelists.getLista()[i].getX() == x && vampirelists.getLista()[i].getY() == y)
				noenc = false;
			if(slayerlists.getLista()[i].getX() == x && slayerlists.getLista()[i].getY() == y)
				noenc = false;
		}
		return noenc;
	}
	
	public Vampire vmpInXY(int x, int y) {
		Vampire v = null;
		for(int i = 0; i <vampirelists.getNumV(); i++) {
			if(v.getX() == vampirelists.getLista()[i].getX() && v.getY() == vampirelists.getLista()[i].getY()) {
				v = vampirelists.getLista()[i];
			}
		}
		return v;
	}
		
	public void addSlayer(Slayer sl) {
		if(game.getPlayer().getMonedas() >= 50 && game.Empty(sl.getX(), sl.getY())) {
			game.getSlayerlist().anadirS(sl);
			game.getPlayer().setMonedas(game.getMonedas()-sl.getCoste());
			
		}
	}


	public void addVampire(Vampire vm) {
		if(game.shouldAddVampire()) {
			int x = game.getRandomRow();
			int y = game.getDificultad().getDim_y();
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
			}
			else System.out.println("[DEBUG] Position occuped");
		}
		
	}


	public VampireList getVampirelists() {
		return vampirelists;
	}


	public SlayerList getSlayerlists() {
		return slayerlists;
	}

	
	

	
	

}

