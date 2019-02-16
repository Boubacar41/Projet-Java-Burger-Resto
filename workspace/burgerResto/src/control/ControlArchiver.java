package control;

import model.BDCommande;
import model.ProfilUtilisateur;

public class ControlArchiver {
	// declaration des attributs de la classe
	ControlVerifierIdentification controlVerifierIdentification;
	BDCommande bdCommande = BDCommande.getInstance();

	// constructeur
	public ControlArchiver(ControlVerifierIdentification controlVerifierIdentification2) {
		this.controlVerifierIdentification = controlVerifierIdentification2;
	}

	// methode pour archiver les commandes passées
	public void archiver(int numProfil) {

		Boolean identificationOK = controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.GERANT,
				numProfil);

		if (identificationOK) {
			bdCommande.viderCommandes();
		}

	}
}
