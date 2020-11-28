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


	public void addVampire(Vampires vampire,int x, int y){
		if(Vampires.getRemainingVampires() > 0) {
			super.addObject(vampire,x,y);
			Vampires.reduceRemainingVampires();
			Vampires.addVampiresOnBoard();
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
	
	

	public boolean vmVisible(int x, int y, int i) {
		return (x == this.arrayVampiros[i].getX() && y < this.arrayVampiros[i].getY());
	}
	
	
	public boolean shouldAddVampire() {
		return ( this.remainingV > 0 && game.CorrectVmFreq());
	}
		
	
	public void moveV() {
		for(int i = 0; i < getList().size(); i++) {
			int nextpos = getList().get(i).getY() - 1;
			if (game.position(getList().get(i).getX(), getList().get(i).getY()) == Vampire && game.position(getList().get(i).getX(), nextpos) == null && getList().get(i).getCycles() % 2 == 0)
				getList().get(i).setY(nextpos);
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


