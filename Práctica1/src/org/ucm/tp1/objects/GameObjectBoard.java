package org.ucm.tp1.objects;

import org.ucm.tp1.listas.*;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.view.GamePrinter;

public class GameObjectBoard {
	
	private VampireList vampirelists;
	private SlayerList slayerlists;
	private Game game;

	
	public GameObjectBoard(Game game) {
		this.slayerlists = new SlayerList();
		this.vampirelists = new VampireList(game);
		this.game = game;
	}


	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		int i = 0, j = 0;
		while ((i < vampirelists.getNumV() || j < slayerlists.getNumS()) && noenc) {
			if(vampirelists.getNumV() > 0)
			if(vampirelists.TakePosXofVampireI(i) == x && vampirelists.TakePosYofVampireI(i) == y)
				noenc = false;
			if(slayerlists.getNumS() > 0)
			if(slayerlists.TakePosXofSlayerI(i) == x && slayerlists.TakePosYofSlayerI(i) == y)
				noenc = false;
			i++;
			j++;
		}
		return noenc;
	}
	
	public Vampire vmpInXY(int x, int y) {
		Vampire v = null;
		int i = 0;
		boolean enc = false;
		while(i < vampirelists.getNumV() && !enc) {
			if(vampirelists.TakePosXofVampireI(i) == x && vampirelists.TakePosYofVampireI(i) == y) {
				v = vampirelists.getLista()[i];
				enc = true;
			}
			i++;
		}
		return v;
	}
		
	public void addSlayer(Slayer sl) {
		if(game.getPlayer().getMonedas() >= 50 && game.Empty(sl.getX(), sl.getY())) {
			slayerlists.anadirS(sl);
			game.getPlayer().setMonedas(game.getMonedas()-sl.getCoste());
			game.getGameprinter().setinBoard(sl.getX(), sl.getY(), sl.representarS());
		}
		else if(game.getPlayer().getMonedas() < 50)System.out.println("Monedas insuficientes");
		else if(!game.Empty(sl.getX(), sl.getY()))System.out.println("Coordenadas Ocupadas");
	}

	//Arreglado (add vampire y random row
	
	
	public void addVampire(Vampire vm) {
		if(game.shouldAddVampire()) {
			int row = getRandomRow();
			int col = game.getDificultad().getDim_x() - 1;
			vm = new Vampire(game,row,col);
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
				game.getGameprinter().setinBoard(vm.getX(), vm.getY(), vm.representarV());
			}
			else System.out.println("[DEBUG] Position occuped");
		}
	}
	
	public int getRandomRow() {
		int randomRow = 0;
		int i = 0;
		boolean set = false;
		int cols = game.getDificultad().getDim_x()-1;
		int rows = game.getDificultad().getDim_y();
		while(i < rows && !set) {
			randomRow = game.getRand().nextInt(rows);
			if(game.getGameprinter().getBoard()[randomRow][cols] == " ") set = true;
			i++;
		}
		return randomRow;
	}
	

	
	//Contenido en las cordenadas x,y para game que lo cede a gameprinter.
	public String getObjectInPos(int x, int y) {
		String space = " ";
		String r ="";
		boolean enc = false;
		int j = 0,i = 0;
		while (i < vampirelists.getNumV() || j < slayerlists.getNumS() && !enc) {
			if(i < vampirelists.getNumV() && vampirelists.getNumV() > 0 && vampirelists.TakePosXofVampireI(i) == x && vampirelists.TakePosYofVampireI(i) == y){
				r = vampirelists.getLista()[i].representarV();
				enc = true;
			}
			else if(j < slayerlists.getNumS() && slayerlists.getNumS() > 0 && (slayerlists.TakePosXofSlayerI(i) == x && slayerlists.TakePosYofSlayerI(i) == y) ){
				r = slayerlists.getLista()[j].representarS();
				enc = true;
			}
			i++;
			j++;
		}
		if(!enc)
		return space;
		else 
			return r;
	}
	
	public boolean GameOver() {
		int i = 0;
		while(i < game.getDificultad().getDim_y() && !game.isPerdido()) {
			if(vmpInXY(0,i) != null) {
				game.setPerdido(true);
			}
			i++;
		}
		return game.isPerdido();
	}
	
	public boolean Victory() {
		boolean victoria = false;
		if(vampirelists.getRemainingV() == 0 && !GameOver()) {
			victoria = true;
		}
		return victoria;
	}
	
	public void Slayersfire() {
		for(int i = 0; i < slayerlists.getNumS(); i++) {
			slayerlists.getLista()[i].attack();
		}
	}
	
	public void Vampiresbite() {
		for(int i = 0; i < vampirelists.getNumV(); i++) {
			vampirelists.getLista()[i].attack();
		}
	}

	public VampireList getVampirelists() {
		return vampirelists;
	}


	public SlayerList getSlayerlists() {
		return slayerlists;
	}





	public void moveV() {
		for(int i = 0; i < vampirelists.getNumV(); i++) {
			int nextPos = vampirelists.getLista()[i].getY() - 1;
			if(getObjectInPos(vampirelists.getLista()[i].getX(), nextPos) == " " )   {
				if(vampirelists.getLista()[i].getCiclos() % 2 == 0)
				vampirelists.getLista()[i].setY(nextPos);
			}
		}
	}


	public void RemoveDeadObjs() {
		slayerlists.update(game);
		vampirelists.update(game);
		
		
	}


	public void ResetGame() {
		this.vampirelists = new VampireList(game);
		this.slayerlists = new SlayerList();
		game.ResetPlayer();
		game.ResetNumCiclos();
		
	}


	public String gameprint(){
		return game.getGameprinter().toString();
	}
	

	
	
	public int TakeVPos(int i, boolean xory) {
		if(xory == false) return vampirelists.TakePosXofVampireI(i);
		else return vampirelists.TakePosYofVampireI(i);
		
	}
	
	public int TakeSPos(int i, boolean xory) {
		if(xory == false) return slayerlists.TakePosXofSlayerI(i);
		else return slayerlists.TakePosYofSlayerI(i);
	}
	
	public void SlayerTakeDmg(int i, int dmg) {
		slayerlists.RecibirDmg(i,(slayerlists.VidaActual(i)-dmg));
	}
	
	public void VampireTakeDmg(int i,int dmg) {
		vampirelists.RecibirDaño(i,(vampirelists.VidaActual(i)-dmg));
	}
	
	public int GetNumV() {
		return vampirelists.getNumV();
	}

	public int GetNumS() {
		return slayerlists.getNumS();
	}
}

