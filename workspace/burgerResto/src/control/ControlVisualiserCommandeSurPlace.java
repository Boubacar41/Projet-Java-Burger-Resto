package control;

import java.util.Observer;
import model.BDCommande;
import model.ProfilUtilisateur;

public class ControlVisualiserCommandeSurPlace {

	// declaration des attributs de la classe
	BDCommande bdCommande = BDCommande.getInstance();
	ControlVerifierIdentification controlVerifierIdentification;

	// constructeur
	public ControlVisualiserCommandeSurPlace(ControlVerifierIdentification controlVerifierProfil) {
		this.controlVerifierIdentification = controlVerifierProfil;
	}

	// methode pour devenir observer de bdCommande
	public void setObserver(Observer o) {
		this.bdCommande.addObserver(o);
	}

	// methode de verification d'identification de personnel
	public Boolean verifierIdentificationPersonnel(int numProfilCuisinier) {
		return controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.PERSONNEL, numProfilCuisinier);
	}

}
