package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Slayer;
import org.ucm.tp1.logic.Game;

public class SlayerList {
	
	private Slayer arraySlayer[];
	private int numS;
	private Game game;
	
	public SlayerList(Game game) {
		super();
		arraySlayer = new Slayer[game.MaxCant()];
		this.numS = 0;
		this.game = game;
	}
	

	public Slayer[] getLista(){
		return arraySlayer;
	}
	
	public int numSlayer(){
		return this.numS;
	}
	
	public void anadirS(Slayer s){
		if(numS < arraySlayer.length) {
		arraySlayer[numS] = s;
		numS++;
		}
	}
	
	public boolean isMuerto(int i){
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
		for(int j = i; j < numSlayer(); j++) {
			arraySlayer[j] = arraySlayer[j+1];
		}
		setNumS(getNumS()-1);
	}
	
	
	
	public void update(Game game) {
		for(int i = 0; i < numSlayer(); i++) { 
			if(isMuerto(i)) {
				arraySlayer[i] = null;
				array(i);
			}
		}
	}


	public int TakePosXofSlayerI(int i) {
		return arraySlayer[i].getX();
	}


	public int TakePosYofSlayerI(int i) {
		return arraySlayer[i].getY();
	}


	public int VidaActual(int i) {
		return arraySlayer[i].getResistencia();
	}


	public void RecibirDmg(int i, int nexthp) {
		arraySlayer[i].setResistencia(nexthp);
		
	}
	
	public String Icon(int i) {
		return arraySlayer[i].representarS();
	}


	public void IncreaseCiclos(int i) {
		arraySlayer[i].setCiclos(arraySlayer[i].getCiclos()+1);
		
	}
	
	public void Attack() {
		for(int i = 0; i < this.numS; i++) arraySlayer[i].attack(); 
		
	}
	
}
