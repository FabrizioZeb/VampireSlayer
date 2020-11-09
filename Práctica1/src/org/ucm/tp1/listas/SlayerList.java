package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Slayer;
import org.ucm.tp1.logic.Game;

public class SlayerList {
	
	private Slayer arraySlayer[];
	private int numS;

	
	public SlayerList(Game game) {
		super();
		arraySlayer = new Slayer[game.MaxCant()];
		this.numS = 0;
	}
	

	public Slayer[] getLista(){
		return arraySlayer;
	}
	
	public void anadirS(Slayer s){
		if(numS < arraySlayer.length) {
		arraySlayer[numS] = s;
		numS++;
		}
	}
	
	private boolean isMuerto(int i){
		if(arraySlayer[i].getResistencia() < 0) return true;
		else return false;
	}
	
	public int getNumS(){
		return numS;
	}
	
	public void setNumS(int i) {
		this.numS = i;
	}
	

	//	Si se muere un slayer sitúa todos los slayer una posición menos desde el slayer i y resta 1 a numS
	
	private void array(int i){
		for(int j = i; j < this.numS; j++) {
			arraySlayer[j] = arraySlayer[j+1];
		}
		setNumS(getNumS()-1);
	}
	
	
	
	public void update(Game game) {
		for(int i = 0; i < this.numS; i++) { 
			if(isMuerto(i)) {
				arraySlayer[i] = null;
				array(i);
			}
		}
	}



	public void RecibirDmg(int i, int nexthp) {
		arraySlayer[i].setResistencia(arraySlayer[i].getResistencia()-nexthp);
		
	}


	public void IncreaseCiclos() {
		for(int i = 0; i < this.numS; i++)
		arraySlayer[i].setCiclos(arraySlayer[i].getCiclos()+1);
		
	}
	
	public void Attack() {
		for(int i = 0; i < this.numS; i++) arraySlayer[i].attack(); 
		
	}

	public Slayer slInXY(int col, int row) {
		Slayer s = null;
		int i = 0;
		boolean enc = false;
		while(i < this.numS && !enc) {
			if(arraySlayer[i].getX() == col && arraySlayer[i].getY()  == row) {
				s = arraySlayer[i];
				enc = true;
			}
			i++;
		}
		return s;
	}
	
	 public boolean slReachable(int x, int y, int i) {
		 return (x == arraySlayer[i].getX() && (y - 1 == arraySlayer[i].getY()));
	 }
	
	 
	public String getSlayerin(int x,int y) {
		int i = 0;
		boolean enc = false;
		while(i < this.numS && !enc) {
			if(arraySlayer[i].getX() == x && arraySlayer[i].getY() == y) {
				enc = true;
				return arraySlayer[i].representarS();				
			}
			i++;
		}
		return " ";
	}



}
