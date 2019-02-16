package control;

import model.*;

public class ControlCreerProfil {
	
	// declaration des attributs de la classe
	BDclient bdClient = BDclient.getInstance();
	BDpersonnel bdPersonnel = BDpersonnel.getInstance();

	// création de profil utilisateur
	public void creerProfil(ProfilUtilisateur profil, String nom, String prenom, String mdp) {
		if (profil == ProfilUtilisateur.GERANT) {
			Personnel gerant = new Personnel(nom, prenom, mdp);
			bdPersonnel.ajouterPersonnel(gerant);
			gerant.definirGerant();
		} else if (profil == ProfilUtilisateur.PERSONNEL) {
			Personnel personnel = new Personnel(nom, prenom, mdp);
			bdPersonnel.ajouterPersonnel(personnel);
		} else if (profil == ProfilUtilisateur.CLIENT) {
			Client client = new Client(nom, prenom, mdp);
			bdClient.ajouterClient(client);
		}
	}

	// methode de visualisation de la BdPersonnel
	public String visualiserBDUtilisateur() {
		return (bdPersonnel + "\n" + bdClient);
	}

}
