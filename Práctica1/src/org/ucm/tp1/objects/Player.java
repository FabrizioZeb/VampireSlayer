package org.ucm.tp1.objects;

import java.util.Random;
import org.ucm.tp1.logic.Game;

public class Player {
	private int monedas;
	private Game game;
	
	public Player(){
		this.monedas = 50;
	}
	
	public int getMonedas() {
		return monedas;
	}
	
	public void setMonedas(int i) {
		this.monedas = i;
	}
	
	public void aumentar10monedas() {
		if(game.getRand().nextFloat() > 0.5) this.monedas += 10;	
	}
}
