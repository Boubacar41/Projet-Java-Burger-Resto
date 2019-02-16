package control;

import java.util.Observer;

import model.BDCommande;
import model.ProfilUtilisateur;

public class ControlVisualiserCommandeJour {
	
	// declaration des attributs de la classe
	BDCommande bdCommande = BDCommande.getInstance();
	ControlVerifierIdentification controlVerifierIdentification;

	// constructeur
	public ControlVisualiserCommandeJour(ControlVerifierIdentification controlVerifierIdentification2) {
		// TODO Auto-generated constructor stub
		this.controlVerifierIdentification = controlVerifierIdentification2;
	}

	// methode pour devenir observer de BdCommande
	public void setObserver(Observer o) {
		this.bdCommande.addObserver(o);
	}

	// methode de verification d'identification de cuisinier
	public Boolean verifierIdentificationPersonnel(int numProfilCuisinier) {
		return controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.PERSONNEL, numProfilCuisinier);
	}
}
