package control;

import model.BDclient;
import model.CarteBancaire;
import model.Client;

public class ControlEnregistrerCoordonneesBancaires {

	// declaration des attributs de la classe
	private ControlVerifierCoordonneesBancaires controlVerif = new ControlVerifierCoordonneesBancaires();
	BDclient bdClient = BDclient.getInstance();

	// constructeur
	public ControlEnregistrerCoordonneesBancaires(
			ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaires) {
		// TODO Auto-generated constructor stub
	}

	// methode d'enregistrement de coordonnées bancaires
	public Boolean enregistrerCordonneesBancaires(int numClient, int numeroCarte, int dateCarte) {
		Boolean carteValide = controlVerif.verifierCoordonneesBancaires(numeroCarte, dateCarte);
		if (carteValide) {
			Client client = bdClient.getClient(numClient);
			client.enregistrerCoordonneesBancaires(numeroCarte, dateCarte);
		}
		return carteValide;
	}
}
