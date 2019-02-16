package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

//classe singleton et observable
public class BDCommande extends Observable {
	private BDCommande() {
	}
	private static class BDCommandeHolder {
		private static final BDCommande instance = new BDCommande();
	}
	public static BDCommande getInstance() {
		return BDCommandeHolder.instance;
	}

	// liste de commandes
	private Map<Integer, Commande> commandes = new HashMap<>();
	
	// recuperation de l'instance BDClient
	BDclient bdClient = BDclient.getInstance();

	// methode d'ajout de commande à la liste
	public void ajouterCommande(Commande commande) {
		int numeroCommande = commandes.size() + 1;
		commandes.put(numeroCommande, commande);
	}

	// methode pour visualiser la liste des commandes
	public void afficherCommande() {
		for (Commande commande : commandes.values()) {
			System.out.println(commande.toString());
		}
	}

	//methode de suppression de commande de liste de commandes
	public Commande supprimerCommande(Integer numeroCommande) {
		this.setChanged();
		Commande commande = commandes.get(numeroCommande);
		Hamburger hamburger = commande.getHamburger();
		Accompagnement accompagnement = commande.getAccompagnement();
		Boisson boisson = commande.getBoisson();
		String[] label = { String.valueOf(3), Integer.toString(numeroCommande), hamburger.nom, accompagnement.nom,
				boisson.nom };
		this.notifyObservers(label);
		commandes.remove(numeroCommande);
		return commande;
	}

	// methode pour visualiser la liste des commandes
	public void afficherCommandes() {
		Set<Integer> ensembleNumCommande = commandes.keySet();
		for (Integer numeroCommande : ensembleNumCommande) {
			System.out.println(commandes.get(numeroCommande));
		}
	}

	// methode pour visualiser la liste des commandes
	public String toString() {
		return "BDCommande [commandes=" + commandes + "]";
	}

	//methode d'enrégistrement de commandes et envoie de notification aux classes observers
	public int enregistrerCommande(int numClient, Hamburger hamburger, Accompagnement accompagnement, Boisson boisson) {
		this.setChanged();
		Commande commande = new Commande(hamburger, accompagnement, boisson);
		this.ajouterCommande(commande);
		Client client= bdClient.getClient(numClient);
		client.ajouterCommande(commande);
		int numeroCommande = commande.getNumeroCommandeAttribuee();
		String[] label = { String.valueOf(1), Integer.toString(numeroCommande), hamburger.nom, accompagnement.nom,
				boisson.nom };
		this.notifyObservers(label);
		return numeroCommande;
	}

	//methode de notification pour archiver les commandes
	public void viderCommandes() {
		this.setChanged();
		String[] label = { String.valueOf(2) };
		this.notifyObservers(label);
	}

}
