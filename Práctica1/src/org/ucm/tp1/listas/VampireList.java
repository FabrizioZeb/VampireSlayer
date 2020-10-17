package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Vampire;
import org.ucm.tp1.logic.Game;

public class VampireList {
	
	private Vampire arrayVampiros[];
	private int numV = 0;
	
	public VampireList(){
		arrayVampiros = new Vampire[10];
	}
	
	public Vampire[] obtenerlista(){
		return arrayVampiros;
	}
	
	public int numVampiros() {
		return this.numV;
	}
	
	public void anadirV(Vampire v){
		arrayVampiros[numV] = v;
		numV++;
	}
	
	public boolean estaMuerto(int i) {
		if(arrayVampiros[i].obtenerResistencia() <= 0) return true;
		else return false;
	}
	
	public int obtenerNumV(){
		return numV;
	}
	
	public void establecerNumV(int i) {
		this.numV = i;
	}
	
	//	Si se muere un vampiro sitúa todos los vampiros una posición menos desde el vampiro i y resta 1 a numV
	private void array(int i) {
		for(int j = i; j < numVampiros(); j++) {
			arrayVampiros[j] = arrayVampiros[j+1];
		}
		establecerNumV(obtenerNumV()-1);
	}
	
	public void update() {
		for(int i = 0; i < numVampiros(); i++) {
			
		}
	}

}
