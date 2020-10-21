package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Slayer;
import org.ucm.tp1.logic.Game;

public class SlayerList {
	
	private Slayer arraySlayer[];
	private int numS;
	
	public SlayerList() {
		arraySlayer = new Slayer[50];
		this.numS = 0;
	}
	
	public Slayer[] getLista(){
		return arraySlayer;
	}
	
	public int numSlayer(){
		return this.numS;
	}
	
	public void anadirS(Slayer s){
		arraySlayer[numS] = s;
		numS++;
	}
	
	public boolean isMuerto(int i){
		if(arraySlayer[i].getResistencia() <= 0) return true;
		else return false;
	}
	
	public int getNumS(){
		return numS;
	}
	
	public void setNumS(int i) {
		this.numS = i;
	}
	
	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		for(int i = 0; i < numSlayer(); i++) {
			if(arraySlayer[i].getX() == x && arraySlayer[i].getY() == y) 
				return noenc = false;
		}
		return noenc;
	}
	
	//	Si se muere un slayer sitúa todos los slayer una posición menos desde el slayer i y resta 1 a numS
	
	private void array(int i){
		for(int j = i; j < numSlayer(); j++) {
			arraySlayer[j] = arraySlayer[j+1];
		}
		setNumS(getNumS()-1);
	}
	
	
	
	public void update(Game game) {
		boolean x;
		for(int i = 0; i < numSlayer(); i++) {
			game.establecerCiclos(i);
 
			if(isMuerto(i)) {
				array(i);
			}
		}
	}
	
}
