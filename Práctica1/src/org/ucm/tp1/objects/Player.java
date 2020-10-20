package org.ucm.tp1.objects;

import java.util.Random;

public class Player {
	private int monedas;
	
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
		if(Math.random() > 0.5) this.monedas += 10;	
	}
}
