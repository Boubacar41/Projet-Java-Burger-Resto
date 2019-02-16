package control;

import model.BDclient;
import model.BDpersonnel;
import model.ProfilUtilisateur;

public class ControlSIdentifier {
	
	// declaration des attributs de la classe
	BDclient bdClient = BDclient.getInstance();
	BDpersonnel bdPersonnel = BDpersonnel.getInstance();

	//méthode d'identification client ou personnel
	public int sIdentifier(ProfilUtilisateur client, String login, String mdp) {
		int numProfil = -1;
		switch (client) {
		case CLIENT:
			numProfil = bdClient.connecterClient(login, mdp);
			break;
		default:
			numProfil = bdPersonnel.connecterPersonnel(login, mdp);
			break;
		}
		return numProfil;
	}

	//methode pour visualiser la bdUtilisation
	public String visualiserBDUtilisateur() {
		// TODO Auto-generated method stub
		return bdClient.toString();
	}

}
