package control;

import java.util.ArrayList;
import java.util.List;
import model.Accompagnement;
import model.BDCommande;
import model.BDclient;
import model.Boisson;
import model.Client;
import model.Commande;
import model.Hamburger;
import model.Menu;
import model.ProfilUtilisateur;

public class ControlCommander {

	// declaration des attributs de la classe
	ControlVerifierIdentification controlVerifierIdentification;
	Menu menu = Menu.getInstance();
	BDclient bdClient = BDclient.getInstance();
	BDCommande bdCommande = BDCommande.getInstance();
	private String hamburger;
	private String accompagnement;
	private String boisson;
	private List<Hamburger> listHamburgers = new ArrayList<>();
	private List<Boisson> listBoissons = new ArrayList<>();
	private List<Accompagnement> listAccompagnements = new ArrayList<>();

	// constructeur
	public ControlCommander(ControlVerifierIdentification controlVerifierIdentification) {
		// TODO Auto-generated constructor stub
		this.controlVerifierIdentification = controlVerifierIdentification;
	}

	// methode permettant d'obtenir la liste des noms d'hamburgers
	public List<String> getListHamburger() {
		List<String> NomHamburgers = new ArrayList<>();
		listHamburgers = menu.getHamburgers();
		for (int i = 0; i < listHamburgers.size(); i++) {
			hamburger = listHamburgers.get(i).getNom();
			NomHamburgers.add(hamburger);
		}
		return NomHamburgers;
	}

	// methode permettant d'obtenir la liste des noms d'accompagnements
	public List<String> getListAccompagnement() {
		List<String> NomAccompagnements = new ArrayList<>();
		listAccompagnements = menu.getAccompagnements();
		for (int i = 0; i < listAccompagnements.size(); i++) {
			accompagnement = listAccompagnements.get(i).getNom();
			NomAccompagnements.add(accompagnement);
		}
		return NomAccompagnements;
	}

	// methode permettant d'obtenir la liste des noms de boissons
	public List<String> getListBoisson() {
		List<String> NomBoissons = new ArrayList<>();
		listBoissons = menu.getBoissons();
		for (int i = 0; i < listBoissons.size(); i++) {
			boisson = listBoissons.get(i).getNom();
			NomBoissons.add(boisson);
		}
		return NomBoissons;
	}

	// verification des coordonnées bancaires
	public Boolean verificationCB(int numClient) {
		Client client = bdClient.getClient(numClient);
		Boolean cbExist = client.isCBexist();
		return cbExist;
	}

	// methode de visualisation du contenu de la BDCommande
	public String visualiserBDCommandes() {
		BDCommande bdCommande = BDCommande.getInstance();
		return (bdCommande.toString());
	}

	// methode de formalisation de la visu de la BDCommande
	public String toString() {
		String contenuBDCommande = this.visualiserBDCommandes();
		return ("[commandes={" + contenuBDCommande + "}");

	}

	// methode d'enregistrement de commandes
	public int enregistrerCommande(int numClient, int numHamburger, int numAccompagnement, int numBoisson) {
		Hamburger hamburger = listHamburgers.get(numHamburger);
		Accompagnement accompagnement = listAccompagnements.get(numAccompagnement);
		Boisson boisson = listBoissons.get(numBoisson);
		int idCommande = bdCommande.enregistrerCommande(numClient, hamburger, accompagnement, boisson);
		return idCommande;
	}

	// methode de verification de l'identification du client
	public boolean verifierIdentification(int numClient) {
		return controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.CLIENT, numClient);
	}

}
