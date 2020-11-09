package org.ucm.tp1.objects;

import org.ucm.tp1.listas.*;
import org.ucm.tp1.logic.Game;



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
		return (getObjectInPos(x, y) == " ");
	}
	

		
	public void addSlayer(Slayer sl) {
		if(game.Buyable(sl) && game.Empty(sl.getX(), sl.getY())) {
			game.Bought(sl);
			slayerlists.anadirS(sl);
			game.SetinBoard(sl.getX(), sl.getY(), sl.representarS());
		}
		else if(!game.Buyable(sl))System.out.println("Monedas insuficientes");
		else if(!game.Empty(sl.getX(), sl.getY()))System.out.println("Coordenadas Ocupadas");
	}
	
	public void addVampire(Vampire vm) {
		if(vampirelists.shouldAddVampire()) {
			int row = getRandomRow();
			int col = game.ColsandRows(false) - 1;
			vm = new Vampire(game,row,col);
			if(game.Empty(vm.getX(), vm.getY())) {
				vampirelists.anadirV(vm);
				game.SetinBoard(vm.getX(), vm.getY(), vm.representarV());
			}
		}
	}
	
	public int getRandomRow() {
		int randomRow = 0;
		int i = 0;
		boolean set = false;
		int cols = game.ColsandRows(false) - 1;
		int rows = game.ColsandRows(true);
		while(i < rows && !set) {
			randomRow = game.getRand().nextInt(rows);
			if(game.GameprinterBoardXandY(randomRow, cols) == " ") set = true;
			i++;
		}
		return randomRow;
	}
	

	public String getObjectInPos(int x, int y) {
		String space = " ";
		String V = vampirelists.getVampirein(x, y), S = slayerlists.getSlayerin(x, y);
		if(V == space && S != space) return S;
		else if (S == space && V != space) return V;
		else return space;
	}
	
	public void attack() {
		slayerlists.Attack();
		vampirelists.Attack();
	}
	
	public void moveV() {
		vampirelists.moveV();
	}
	
	public void RemoveDeadObjs() {
		slayerlists.update(game);
		vampirelists.update(game);

	}
	
	public void IncreaseCicles() {
		vampirelists.IncreaseCiclos();
		slayerlists.IncreaseCiclos();
		
	}
	
	public boolean GameOver() {
		int i = 0;
		while(i < game.ColsandRows(true)  && !game.isPerdido()) {
			if(vampirelists.getVampirein(i, 0) != " " ) {
				game.setPerdido(true);
				System.out.println("Derrota");
			}
			i++;
		}
		return game.isPerdido();
	}
	

	public boolean Victory() {
		boolean victoria = false;
		if(vampirelists.getRemainingV() == 0 && vampirelists.getNumV() == 0) {
			victoria = true;
			System.out.println("Victoria");
		}
		return victoria;
	}
	

	
	public String StatsofVampires() {
		String s = "Remainings vampires: " + vampirelists.getRemainingV() + "\n";
		s += "Vampires on the board: " + vampirelists.getNumV() + "\n";
		return s;
	}
	
	
	public void attackV(int row, int col) {
		Slayer sl = slayerlists.slInXY(row, col);
		int x = sl.getX(), y = sl.getY();
		if(vampirelists.getNumV() > 0) {
			int i = 0;
			boolean target = false;
			while(i < vampirelists.getNumV() && !target) {
				if(vampirelists.vmVisible(x, y, i))
					if(sl.getCiclos() > 0) {
					vampirelists.RecibirDaño(i, sl.getDmg());
					target = true;
					}
				i++;
			}
		}
		
	}
	
	
	public void attackS(int row, int col) {
		Vampire vm = vampirelists.vmpInXY(row, col);
		int x = vm.getX(), y = vm.getY();
		if(slayerlists.getNumS() > 0) {
				int i = 0;
				boolean target = false;
				while(i < slayerlists.getNumS() && !target) {
					if(slayerlists.slReachable(x, y, i)) {
						if(vm.getCiclos() > 0) {
							slayerlists.RecibirDmg(i, vm.getDmg());
							target = true;
						}						
					}
					i++;
				}
		}
	}






	


	
}

