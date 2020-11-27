package org.ucm.tp1.logic.list;

import org.ucm.tp1.logic.gameobjects.slayers.Slayers;
import org.ucm.tp1.objects.Vampire;
import org.ucm.tp1.logic.Game;

public class SlayerList extends GameObjectList {

	public SlayerList() {
		super();
	}
	

	public void addSlayer(Slayers sl){
		super.addObject(sl);
	}

	public boolean isDead(int i){
		if(super.) return true;
		else return false;
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

	public Slayers slInXY(int col, int row) {
		Slayers s = null;
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

	public void attackS(int x, int y, Vampire vm){
		if(this.numS > 0){
			int i = 0;
			boolean target = false;
			while(i < this.numS && !target){
				if(slReachable(x,y,i))
					if(vm.getCiclos() > 0){
						RecibirDmg(i,vm.getDmg());
						target = true;
					}
				i++;
			}
		}
	}


}
