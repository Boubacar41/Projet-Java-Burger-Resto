package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Profil {
	// declaration des attributs
	private String nom;
	private String prenom;
	CarteBancaire carteBanquaire = new CarteBancaire();
	
	private List<Commande> commandes = new ArrayList<>(); // declaration d'un tableau de commande

	// constructeur
	public Client(String nom, String prenom, String mdp) {
		super(nom, prenom, mdp);
	}

	//methode d'ajout à la liste de commandes du client
	public void ajouterCommande(Commande commande) {
		commandes.add(commande);
	}

	// methode pour visualiser la liste des commandes
	public void afficheCommandes() {
		for (Commande commande : commandes) {
			System.out.println(commande);
		}
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String toString() {
		return "Client [" + super.toString() + "]";
	}

	//methode pour enregistrer les attributs coordonnées banquaire
	public void enregistrerCoordonneesBancaires(int numeroCarte, int dateExpCarte) {
		CarteBancaire carteBanquaire = new CarteBancaire(numeroCarte, dateExpCarte);
		this.carteBanquaire = carteBanquaire;
	}

	//verification de l'existance des coordonnées banquaires
	public boolean isCBexist() {
		return false;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public CarteBancaire getCarteBanquaire() {
		return carteBanquaire;
	}

}
