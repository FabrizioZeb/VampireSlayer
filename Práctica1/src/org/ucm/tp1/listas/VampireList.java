package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Vampire;
import org.ucm.tp1.logic.Game;

public class VampireList {
	
	private Vampire arrayVampiros[];
	private int numV = 0;
	
	public VampireList(){
		arrayVampiros = new Vampire[10];
	}
	
	public Vampire[] getLista(){
		return arrayVampiros;
	}
	
	public int numVampiros() {
		return this.numV;
	}
	
	public void anadirV(Vampire v){
		arrayVampiros[numV] = v;
		numV++;
	}
	
	public boolean isMuerto(int i) {
		if(arrayVampiros[i].getResistencia() <= 0) return true;
		else return false;
	}
	
	public int getNumV(){
		return numV;
	}
	
	public void setNumV(int i) {
		this.numV = i;
	}
	
	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		for(int i = 0; i < numVampiros(); i++) {
			if(arrayVampiros[i].getX() == x && arrayVampiros[i].getY() == y) 
				return noenc = false;
		}
		return noenc;
	}
	
	
	
	//	Si se muere un vampiro sitúa todos los vampiros una posición menos desde el vampiro i y resta 1 a numV
	private void array(int i) {
		for(int j = i; j < numVampiros(); j++) {
			arrayVampiros[j] = arrayVampiros[j+1];
		}
		setNumV(getNumV()-1);
	}
	
	public void update(Game game) {
		for(int i = 0; i < numVampiros(); i++) {
			if(isMuerto(i)) {
				getLista()[i] = null;
				array(i);
				
			}
		}
	}

}
