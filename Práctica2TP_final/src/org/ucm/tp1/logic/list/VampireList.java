package org.ucm.tp1.logic.list;

import org.ucm.tp1.logic.gameobjects.GameObject;
import org.ucm.tp1.logic.gameobjects.slayers.Slayers;
import org.ucm.tp1.logic.gameobjects.vampires.Vampires;
import org.ucm.tp1.logic.Game;

import java.util.ArrayList;

public class VampireList extends GameObjectList {

	private Vampires vampires;
	private Game game;
	
	public VampireList(Game game){
		super();
		//this.remainingV = game.getDificultad().getNumberOfVampires();
	}
	
	public ArrayList<GameObject> getList(){
		return super.getList();
	}

	public void add(Vampires v){
		if(Vampires.getRemainingVampires() > 0) {
			super.addObject(v);
			Vampires.setRemainingVampires(getRemainingV()-1);
		}
	}
	

	//	Si se muere un vampiro sitúa todos los vampiros una posición menos desde el vampiro i y resta 1 a numV


	public void updateList(){
		for(int i = 0; i < getList().size(); i++) {
			if(isDead(i)) array(i);
			else getList().get(i).update(game);
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
		return ( this.remainingV > 0 && game.CorrectVmFreq());
	}
		
	
	public void moveV() {
		for(int i = 0; i < this.numV; i++) {
			int nextpos = arrayVampiros[i].getY() - 1;
			if(game.position(arrayVampiros[i].getX(),nextpos))
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

	public void attackV(int x, int y, Slayers sl){
		if(this.numV > 0){
			int i = 0;
			boolean target = false;
			while (i < this.numV && !target){
				if(vmVisible(x,y,i))
					if(sl.getCycles() > 0){
						RecibirDaño(i,sl.getDmg());
						target = true;
					}
				i++;
			}
		}
	}

}


