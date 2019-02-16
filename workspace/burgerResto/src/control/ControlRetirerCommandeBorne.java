package control;

import model.BDCommande;
import model.BDclient;
import model.CarteBancaire;
import model.Client;
import model.Commande;

public class ControlRetirerCommandeBorne {

	// declaration des attributs de la classe
	private ControlValiderPayementBancaire controlValiderPayement;
	BDCommande bdCommande = BDCommande.getInstance();
	BDclient bdClient = BDclient.getInstance();

	// constructeur
	public ControlRetirerCommandeBorne(ControlValiderPayementBancaire controlValiderPayementBancaire) {
		// TODO Auto-generated constructor stub
		this.controlValiderPayement = controlValiderPayementBancaire;
	}

	//methode de retrait de la commande à la borne
	public boolean retirerCommandeBorne(int numeroCommande) {
		boolean commandeTrouvee = true;
		Commande commande;
		CarteBancaire cartebancaire;
		commande = bdCommande.supprimerCommande(numeroCommande);

		if (commande != null) {
			int numeroClient;
			Client client;
			numeroClient = commande.getNumClient();
			client = bdClient.getClient(numeroClient);
			cartebancaire = client.getCarteBanquaire();
			int montant = commande.prixTotal();
			controlValiderPayement.validerPayementBancaire(cartebancaire, montant);
			client.ajouterCommande(commande);
		}
		return commandeTrouvee;

	}

}
