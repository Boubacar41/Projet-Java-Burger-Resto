package model;

import java.util.ArrayList;
import java.util.List;

//singleton 
public class Menu {
	private Menu() {
	}
	private static class MenuHolder {
		private final static Menu instance = new Menu();
	}
	public static Menu getInstance() {
		return MenuHolder.instance;
	}

	//declaration des listes d'aliments
	private List<Hamburger> hamburgers = new ArrayList<>();
	private List<Boisson> boissons = new ArrayList<>();
	private List<Accompagnement> accompagnements = new ArrayList<>();
	
	//prix d'une commande
	private final int PRIX_MENU = 8;

	//methode d'ajout d'hamburger
	public void ajouterHamburger(Hamburger h1) {
		this.hamburgers.add(h1);
	}

	//methode d'ajout de boisson
	public void ajouterBoisson(Boisson b1) {
		this.boissons.add(b1);
	}
	//methode d'ajout d'accompagnement
	public void ajouterAccompagnement(Accompagnement a1) {
		this.accompagnements.add(a1);
	}

	public List<Hamburger> getHamburgers() {
		return hamburgers;
	}

	public List<Boisson> getBoissons() {
		return boissons;
	}

	public List<Accompagnement> getAccompagnements() {
		return accompagnements;
	}

	//methode pour visualiser le menu
	public String toString() {
		return "Menu [hamburgers=" + hamburgers + ", boissons=" + boissons + ", accompagnements=" + accompagnements
				+ "]";
	}

	public int getPRIX_MENU() {
		return PRIX_MENU;
	}

}
