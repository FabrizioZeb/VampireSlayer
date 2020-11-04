package org.ucm.tp1.objects;

import org.ucm.tp1.logic.Game;

public class Slayer {

	public static final int COSTE = 50;
	
	private int resistencia;
	private int dmg;
	private int posx, posy;
	private int ciclos;
	private int coste;
//	private int frecuencia;
	private boolean vivo;
	private boolean avanza;
	private Game game;
	private boolean objetivo; 

	public Slayer(Game game,int x, int y) {
		this.resistencia = 3;
		this.dmg = 1;
		this.posx = x;
		this.posy = y;
		this.coste = COSTE;
		this.ciclos = 0;
//		this.frecuencia = 1;
		this.vivo = true;
		this.game = game;
		this.objetivo = false;
	}

	
	public void attack() {
		if(game.GameobNumVorNumS(objetivo) > 0) {
			int i = 0;
			boolean target = false;
			while(i < game.GameobNumVorNumS(objetivo) && !target) {
				if(this.posx == game.GameobGetsXorYofVorS(i, false, objetivo ) && this.posy < game.GameobGetsXorYofVorS(i, true, objetivo)) {
					if(this.ciclos > 0) {
						game.GameobVorSTakeDmg(i, dmg, objetivo); //Si boolean es false se vampire recibe dmg
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
