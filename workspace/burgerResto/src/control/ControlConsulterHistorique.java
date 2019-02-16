package control;

import java.util.ArrayList;
import java.util.List;

import model.Accompagnement;
import model.BDclient;
import model.Boisson;
import model.Client;
import model.Commande;
import model.Hamburger;
import model.ProfilUtilisateur;

public class ControlConsulterHistorique {
	
	// declaration des attributs de la classe
	BDclient bdClient = BDclient.getInstance();
	ControlVerifierIdentification controlVerifierIdentification;
	List<String> historique = new ArrayList<>();
	
	// constructeur
	public ControlConsulterHistorique(ControlVerifierIdentification controlVerifierIdentification2) {
		this.controlVerifierIdentification=controlVerifierIdentification2;
	}

	// methode de verification de l'identification de client
	public Boolean verifierIdentificationClient(int numProfil) {
		Boolean connexionOK;
		connexionOK = controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.CLIENT, numProfil);
		return connexionOK;
	}

	//méthode de construction de la liste des commandes de client
	public List<String> consulterHistorique(int numProfil) {
		Client client = bdClient.getClient(numProfil);

		List<Commande> commandes = new ArrayList<>();

		commandes = client.getCommandes();
		for (int i = 0; i < commandes.size(); i++) {
			int numeroCommande=commandes.get(i).getNumeroCommandeAttribuee();
			
			Hamburger hamburger=commandes.get(i).getHamburger();
			
			Accompagnement accompagnement=commandes.get(i).getAccompagnement();
			
			Boisson boisson=commandes.get(i).getBoisson();
			
			String nomHamburger=hamburger.getNom();
			String nomAccompagnement=accompagnement.getNom();
			String nomBoisson=boisson.getNom();
			
			String commande="Commande no"+String.valueOf(numeroCommande)+": "+nomHamburger+", "+nomAccompagnement+", "+nomBoisson;
			historique.add(commande);
           
		}
		return historique;

	}

}
