package org.ucm.tp1.objects;


import org.ucm.tp1.logic.Game;

public class Player {
	private int monedas;
	private Game game;
	
	public Player(Game game){
		this.monedas = 50;
		this.game = game;
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
	
	public void ResetMonedas() {
		monedas = 50;
	}
}
