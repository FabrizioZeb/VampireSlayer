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
	
	private boolean isMuerto(int i) {
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


	
	public void RecibirDaño(int i,int nexthp) {
		arrayVampiros[i].setResistencia(arrayVampiros[i].getResistencia() - nexthp);
	}
	


	public void IncreaseCiclos() {
		for(int i = 0; i < this.numV; i++) arrayVampiros[i].setCiclos(arrayVampiros[i].getCiclos()+1);
		
	}
	
	public void Attack() {
		for(int i = 0; i < this.numV; i++) arrayVampiros[i].attack();
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
	
	
	public boolean vmVisible(int x, int y, int i) {
		return (x == this.arrayVampiros[i].getX() && y < this.arrayVampiros[i].getY());
	}
	
	
	public boolean shouldAddVampire() {
		return ( this.remainingV > 0 && game.CorrectVmFrec());
	}
		
	
	public void moveV() {
		for(int i = 0; i < this.numV; i++) {
			int nextpos = arrayVampiros[i].getY() - 1;
			if(game.position(arrayVampiros[i].getX(),nextpos) == " ")
				if(arrayVampiros[i].getCiclos() % 2 == 0)
					arrayVampiros[i].setY(nextpos);
		}
	}
	
	public String getVampirein(int x,int y) {
		int i = 0;
		boolean enc = false;
		while(i < this.numV && !enc) {
			if(arrayVampiros[i].getX() == x && arrayVampiros[i].getY() == y) {
				enc = true;
				return arrayVampiros[i].representarV();				
			}
			i++;
		}
		return " ";
	}
	
}


