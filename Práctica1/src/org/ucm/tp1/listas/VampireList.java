package org.ucm.tp1.listas;

import org.ucm.tp1.objects.Vampire;
import org.ucm.tp1.logic.Game;

public class VampireList {
	
	private Vampire arrayVampiros[];
	private int numV;
	private int remainingV;
	private Game game;
	
	public VampireList(Game game){
		super();
		this.game = game;
		arrayVampiros = new Vampire[game.getDificultad().getNumberOfVampires()];
		this.numV = 0;
		this.remainingV = game.getDificultad().getNumberOfVampires();
	}
	
	public Vampire[] getLista(){
		return arrayVampiros;
	}
	
	public int getNumV(){
		return numV;
	}
	
	public void setNumV(int i) {
		this.numV = i;
	}
	
		
	public void anadirV(Vampire v){
		if(numV < arrayVampiros.length) {
		arrayVampiros[numV] = v;
		numV++;
		remainingV--;
		}
	}
	
	public boolean isMuerto(int i) {
		if(arrayVampiros[i].getResistencia() < 0) return true;
		else return false;
	}
	

	public int getRemainingV() {
		return remainingV;
	}

	public void setRemainingV(int remainingV) {
		this.remainingV = remainingV;
	}

	
	//	Si se muere un vampiro sitúa todos los vampiros una posición menos desde el vampiro i y resta 1 a numV
	private void array(int i) {
		for(int j = i; j < getNumV(); j++) {
			arrayVampiros[j] = arrayVampiros[j+1];
		}
		setNumV(getNumV()-1);
	}
	
	public void update(Game game) {
		for(int i = 0; i < getNumV(); i++) {
			if(isMuerto(i)) {
				arrayVampiros[i] = null;
				array(i);
				
			}
		}
	}
	
	public int TakePosXofVampireI(int i) {
		return arrayVampiros[i].getX();
	}
	
	public int TakePosYofVampireI(int i) {
		return arrayVampiros[i].getY();
	}
	
	public void MoveVto(int i, int nextpos) {
		arrayVampiros[i].setY(nextpos);
	}
	
	public void RecibirDaño(int i,int nexthp) {
		arrayVampiros[i].setResistencia(nexthp);
	}
	
	public int VidaActual(int i) {
		return arrayVampiros[i].getResistencia();
	}

	public String Icon(int i) {
		return arrayVampiros[i].representarV();
	}
	
	public int CiclesOfV(int i) {
		return arrayVampiros[i].getCiclos();
	}

	public void IncreaseCiclos(int i) {
		arrayVampiros[i].setCiclos(arrayVampiros[i].getCiclos()+1);
		
	}
	
	public void Attack() {
		for(int i = 0; i < this.numV; i++) game.attackSlayer(arrayVampiros[i].getX(),arrayVampiros[i].getY());
	}
	
	
	public Vampire vmpInXY(int col, int row) {
		Vampire v = null;
		int i = 0;
		boolean enc = false;
		while(i < this.numV && !enc) {
			if(arrayVampiros[i].getX() == col && arrayVampiros[i].getY()  == row) {
				v = arrayVampiros[i];
				enc = true;
			}
			i++;
		}
		return v;
	}
	
	
	
	
	
	
	
}


