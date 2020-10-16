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
	
	public int obtenerCiclos() {
		return this.ciclos;
	}
	
	public int obtenerResistencia() {
		return this.resistencia;
	}
	
	public boolean estaVivo(){
		return this.vivo;
	}
	
	
	public boolean puedeAvanzar(){
		return this.avanza;
	}
	
	public int obtenercordX() {
		return this.posx;
	}
	
	public int obtenercordY() {
		return this.posy;
	}
	
	public void moverX(int x) {
		this.posx = x;
	}
	
	public void moverY(int y) {
		this.posy = y;
	}
	
	public void establecerCiclos(int ciclos){
		this.ciclos = ciclos;
	}
	
	
}
