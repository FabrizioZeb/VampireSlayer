package org.ucm.tp1.objects;

import org.ucm.tp1.logic.Game;

public class Slayer {

	private int resistencia;
	private int dmg;
	private int posx, posy;
	private int ciclos;
	private int coste;
	private int frecuencia;
	private boolean vivo;
	private boolean avanza;
	private Game game;

	public Slayer(int x, int y) {
		this.resistencia = 3;
		this.dmg = 1;
		this.posx = x;
		this.posy = y;
		this.coste = 50;
		this.ciclos = 0;
		this.frecuencia = 1;
		this.vivo = true;
	}

	
	public void attack() {
		if(game.getGameob().getVampirelists().getNumV() > 0) {
			int i = 0;
			boolean target = false;
			while(i < game.getGameob().getVampirelists().getNumV() && !target) {
				if(this.posx == game.getGameob().getVampirelists().getLista()[i].getX() && this.posy < game.getGameob().getVampirelists().getLista()[i].getY()) {
					if(this.ciclos > 0) {
						game.getGameob().getVampirelists().getLista()[i].setResistencia(game.getGameob().getVampirelists().getLista()[i].getResistencia()-this.dmg);
						target = true;
					}
				}
				i++;
			}
		}
	}


	
	public String representarS() {
		return "S [" + this.resistencia + "]";
	}

	public int getCiclos() {
		return this.ciclos;
	}

	public int getResistencia() {
		return this.resistencia;
	}
	
	public int getCoste() {
		return this.coste;
	}

	public boolean isVivo() {
		return this.vivo;
	}

	public boolean isAvanzar() {
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

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}
	
	public void setResistencia(int i) {
		this.resistencia = i;
	}
	



}
