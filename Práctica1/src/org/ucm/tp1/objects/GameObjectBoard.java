package org.ucm.tp1.objects;

import org.ucm.tp1.listas.*;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.view.GamePrinter;

public class GameObjectBoard {
	
	private VampireList vampirelists;
	private SlayerList slayerlists;
	private Game game;

	
	public GameObjectBoard(Game game) {
		this.slayerlists = new SlayerList(game);
		this.vampirelists = new VampireList(game);
		this.game = game;
	}


	public boolean Vacio(int x, int y) {
		boolean noenc = true;
		int i = 0, j = 0;
		while ((i < vampirelists.getNumV() || j < slayerlists.getNumS()) && noenc) {
			if(i < vampirelists.getNumV() && vampirelists.getNumV() > 0)
			if(vampirelists.TakePosXofVampireI(i) == x && vampirelists.TakePosYofVampireI(i) == y)
				noenc = false;
			if(j < slayerlists.getNumS() && slayerlists.getNumS() > 0)
			if(slayerlists.TakePosXofSlayerI(i) == x && slayerlists.TakePosYofSlayerI(i) == y)
				noenc = false;
			i++;
			j++;
		}
		return noenc;
	}
	

		
	public void addSlayer(Slayer sl) {
		if(game.CurrentCoins() >= sl.getCoste() && game.Empty(sl.getX(), sl.getY())) {
			slayerlists.anadirS(sl);
			game.CoinsPostBuy(sl.getCoste());
			game.SetinBoard(sl.getX(), sl.getY(), sl.representarS());
		}
		else if(game.CurrentCoins() < sl.getCoste())System.out.println("Monedas insuficientes");
		else if(!game.Empty(sl.getX(), sl.getY()))System.out.println("Coordenadas Ocupadas");
	}

	//Arreglado (add vampire y random row
	
	
	public void addVampire(Vampire vm) {
		if(game.shouldAddVampire()) {
			int row = getRandomRow();
			int col = game.cols() - 1;
			vm = new Vampire(game,row,col);
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
				game.SetinBoard(vm.getX(), vm.getY(), vm.representarV());
			}
			//else System.out.println("[DEBUG] Position occuped");
		}
	}
	
	public int getRandomRow() {
		int randomRow = 0;
		int i = 0;
		boolean set = false;
		int cols = game.cols() - 1;
		int rows = game.rows();
		while(i < rows && !set) {
			randomRow = game.getRand().nextInt(rows);
			if(game.GameprinterBoardXandY(randomRow, cols) == " ") set = true;
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
				r = vampirelists.Icon(i);
				enc = true;
			}
			else if(j < slayerlists.getNumS() && slayerlists.getNumS() > 0 && (slayerlists.TakePosXofSlayerI(i) == x && slayerlists.TakePosYofSlayerI(i) == y) ){
				r = slayerlists.Icon(j);
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
		while(i < game.rows() && !game.isPerdido()) {
			if(vmpInXY(0,i) != null) {
				game.setPerdido(true);
				System.out.println("DEBUG [LOSE]");
				game.setFinjuego(true);
			}
			i++;
		}
		return game.isPerdido();
	}
	
	public Vampire vmpInXY(int col, int row) {
		Vampire v = null;
		int i = 0;
		boolean enc = false;
		while(i < vampirelists.getNumV() && !enc) {
			if(vampirelists.TakePosXofVampireI(i) == col && vampirelists.TakePosYofVampireI(i) == row) {
				v = vampirelists.getLista()[i];
				enc = true;
			}
			i++;
		}
		return v;
	}
	
	public boolean Victory() {
		boolean victoria = false;
		if(vampirelists.getRemainingV() == 0 && !GameOver()) {
			victoria = true;
		}
		return victoria;
	}
	
	public void Slayersfire() {
		slayerlists.Attack();
	}
	
	public void Vampiresbite() {
		vampirelists.Attack();
		//for(int i = 0; i < vampirelists.getNumV(); i++) {		vampirelists.getLista()[i].attack();		}
	}

	public VampireList getVampirelists() {
		return vampirelists;
	}


	public SlayerList getSlayerlists() {
		return slayerlists;
	}





	public void moveV() {
		for(int i = 0; i < vampirelists.getNumV(); i++) {
			int nextPos = vampirelists.TakePosYofVampireI(i) - 1;
			if(getObjectInPos(vampirelists.TakePosXofVampireI(i), nextPos) == " " )   {
				if(vampirelists.CiclesOfV(i) % 2 == 0)
				vampirelists.MoveVto(i,nextPos);
			}
		}
	}


	public void RemoveDeadObjs() {
		slayerlists.update(game);
		vampirelists.update(game);
		
		
	}


	public void ResetGame() {
		this.vampirelists = new VampireList(game);
		this.slayerlists = new SlayerList(game);
		game.ResetPlayer();
		game.ResetNumCiclos();
		
	}

//Revisar
	public String gameprint(){
		return game.getGameprinter().toString();
	}
	
	public String Stats() {
		String s = "Number of cycles: " + game.getnumCiclos() + "\n";
		s += "Coins: " + game.CurrentCoins() + "\n";
		s += "Remainings vampires: " + vampirelists.getRemainingV() + "\n";
		s += "Vampires on the board: " + vampirelists.getNumV() + "\n";
		return s;
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
	
	public int RemainingV() {
		return vampirelists.getRemainingV();
	}
	
	public int GetNumV() {
		return vampirelists.getNumV();
	}

	public int GetNumS() {
		return slayerlists.getNumS();
	}
	
	public void IncreaseCicles() {
		for (int i = 0; i < vampirelists.getNumV(); i++) {
			vampirelists.IncreaseCiclos(i);
		}
		for (int i = 0; i < slayerlists.getNumS(); i++) {
			slayerlists.IncreaseCiclos(i);
		}
	}


	
}

