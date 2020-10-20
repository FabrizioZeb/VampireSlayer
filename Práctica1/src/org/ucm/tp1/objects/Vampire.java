package org.ucm.tp1.objects;

import org.ucm.tp1.logic.Game;


public class Vampire {
	
	private int resistencia;
	private int dmg;
	private int posx, posy;
	private int velocidad;
	private int ciclos;
	private boolean vivo;
	private boolean avanza;
	private Game game;
	
	//Construye un nuevo zombie, por lo que ciclos = 0 y 
	public Vampire (int x, int y) {
		this.resistencia = 5;
		this.dmg = 1;
		this.posx = x;
		this.posy = y;
		this.velocidad = 1;
		this.ciclos = 0;
		this.vivo = true;
	}
	
	public String representarV() {
		return "V [" + this.resistencia + "]";
	}
	
	public int getCiclos() {
		return this.ciclos;
	}
	
	public int getResistencia() {
		return this.resistencia;
	}
	
	public boolean isVivo(){
		return this.vivo;
	}
	
	
	public boolean isAvanzar(){
		return this.avanza;
	}
	
	public int getX() {
		return this.posx;
	}
	
	public int getY() {
		return this.posy;
	}
	
	public void setX(int x) {
		this.posx = x;
	}
	
	public void setY(int y) {
		this.posy = y;
	}
	
	public void setCiclos(int ciclos){
		this.ciclos = ciclos;
	}
	
	
}
