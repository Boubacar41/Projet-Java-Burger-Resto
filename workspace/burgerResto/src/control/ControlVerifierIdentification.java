package control;

import model.BDclient;
import model.BDpersonnel;
import model.Client;
import model.Personnel;
import model.ProfilUtilisateur;

public class ControlVerifierIdentification {
	
	// declaration des attributs de la classe
	BDclient bdClient = BDclient.getInstance();
	BDpersonnel bdPesonnel = BDpersonnel.getInstance();
	
	//verifier identification de client ou de personnel
	public Boolean verifierIdentification(ProfilUtilisateur profil, int numProfil) {
		Boolean connectOK = false;
		Boolean identificationOK=false;
		switch (profil) {
		case CLIENT:
			Client client = bdClient.getClient(numProfil);
			if (client != null) {
				connectOK = client.getConnecte();
			}
			break;
		case PERSONNEL:
			Personnel personnel = bdPesonnel.getPersonnel(numProfil);
			if (personnel != null) {
				connectOK = personnel.getConnecte();
			}

			break;
		default:
			Personnel personnel1 = bdPesonnel.getPersonnel(numProfil);
			if (personnel1 != null) {
				identificationOK = personnel1.getGerant();
				if(identificationOK)
				{
					connectOK = personnel1.getConnecte();
				}
			}
			break;
		}

		return connectOK;
	}

}
