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
		if(game.getVampirelist().getNumV() > 0) {
			for(int i = 0; i < game.getSlayerlist().getNumS();i++) {
				for(int j = 0; j < game.getVampirelist().getNumV(); j++) { //Ver si usar un for o un while
					if(game.getSlayerlist().getLista()[i].getX() == game.getVampirelist().getLista()[j].getX() && game.getSlayerlist().getLista()[i].getY() < game.getVampirelist().getLista()[j].getY()) {
						
						if(game.getSlayerlist().getLista()[i].getCiclos() > 0) {
							game.getVampirelist().getLista()[j].setResistencia(game.getVampirelist().getLista()[j].getResistencia()-this.dmg);
						}
					}
				}
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
