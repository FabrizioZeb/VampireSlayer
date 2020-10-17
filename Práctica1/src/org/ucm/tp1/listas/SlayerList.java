package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Slayer;
import org.ucm.tp1.logic.Game;

public class SlayerList {
	private Slayer arraySlayer[];
	private int numS = 0;
	
	public SlayerList() {
		arraySlayer = new Slayer[10];
	}
	
	public Slayer[] obtenerlista(){
		return arraySlayer;
	}
	
	public int numSlayer(){
		return this.numS;
	}
	
	public void anadirS(Slayer s){
		arraySlayer[numS] = s;
		numS++;
	}
	
	public boolean estaMuerto(int i){
		if(arraySlayer[i].obtenerResistencia() <= 0) return true;
		else return false;
	}
	
	public int obtenerNumS(){
		return numS;
	}
	
	public void establecerNumS(int i) {
		this.numS = i;
	}
	
	//	Si se muere un slayer sitúa todos los slayer una posición menos desde el slayer i y resta 1 a numS
	
	private void array(int i){
		for(int j = i; j < numSlayer(); j++) {
			arraySlayer[j] = arraySlayer[j+1];
		}
		establecerNumS(obtenerNumS()-1);
	}
	
	public void update(Game game) {
		for(int i = 0; i < numSlayer(); i++) {
			game.establecerCiclos(i);
			if(estaMuerto(i)) {
				array(i);
			}
		}
	}
	
}
