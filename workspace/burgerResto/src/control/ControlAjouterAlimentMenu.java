package control;

import model.Accompagnement;
import model.Boisson;
import model.Hamburger;
import model.Menu;
import model.ProfilUtilisateur;

public class ControlAjouterAlimentMenu {
	// declaration des attributs de la classe
	public Menu menu = Menu.getInstance();
	ControlVerifierIdentification controlVerifierIdentification;

	// constructeur
	public ControlAjouterAlimentMenu(ControlVerifierIdentification controlVerifierIdentification2) {
		// TODO Auto-generated constructor stub
		this.controlVerifierIdentification = controlVerifierIdentification2;
	}

	// methode d'ajout d'hamburger au menu
	public void ajouterHamburger(String nom) {
		Hamburger h1 = new Hamburger(nom);
		menu.ajouterHamburger(h1);
	}

	// methode d'ajout d'accompagnement au menu
	public void ajouterAccompagnement(String nom) {
		Accompagnement a1 = new Accompagnement(nom);
		menu.ajouterAccompagnement(a1);
	}

	// methode d'ajout de boisson au menu
	public void ajouterBoisson(String nom) {
		Boisson b1 = new Boisson(nom);
		menu.ajouterBoisson(b1);
	}

	// methode de verification d'identification de gerant
	public Boolean verifierIdentification(int numProfil) {
		return controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.GERANT, numProfil);
	}

	// methode de visualisation du menu
	public String visualiserMenu() {
		return menu.toString();
	}
}
