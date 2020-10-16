package org.ucm.tp1.objects;

import org.ucm.tp1.logic.Game;

public class Slayer {

	private int resistencia;
	private int dmg;
	private int posx, posy;
	private int ciclos;
	private int coste;
	private boolean vivo;
	private boolean avanza;
	private Game game;
	
	private Slayer(int x, int y) {
		this.resistencia = 3;
		this.dmg = 1;
		this.posx = x;
		this.posy = y;
		this.coste = 50;
		this.ciclos = 0;
		this.vivo = true;
	}
	
	public String representarS(){
		return "S [" + this.resistencia + "]";
	}
	
	public int obtenerCiclos(){
		return this.ciclos;
	}
	
	public int obtenerResistencia(){
		return this.resistencia;
	}
	
	public boolean estaVivo(){
		return this.vivo;
	}
	
	public boolean puedeAvanzar(){
		return this.avanza;
	}
	
	public int obtenerCordX(){
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
