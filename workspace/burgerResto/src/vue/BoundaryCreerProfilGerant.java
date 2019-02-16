package vue;

import control.ControlCreerProfil;
import vue.Clavier;
import model.ProfilUtilisateur;

public class BoundaryCreerProfilGerant {
	// declaration du controleur du cas
	ControlCreerProfil controlCreerProfil = new ControlCreerProfil();

	// clavier
	Clavier clavier = new Clavier();

	// constructeur
	public BoundaryCreerProfilGerant(ControlCreerProfil controlCreerProfil) {
		this.controlCreerProfil = controlCreerProfil;
	}

	// methode principale du cas
	public void creerProfilGerant() {
		// TODO Auto-generated method stub
		System.out.println("Veuillez entrer votre nom:\n");
		String nom = clavier.entrerClavierString();

		System.out.println("Veuillez entrer votre prenom:\n");
		String prenom = clavier.entrerClavierString();

		System.out.println("Veuillez entrer votre mot de passe:\n");
		String mdp = clavier.entrerClavierString();

		controlCreerProfil.creerProfil(ProfilUtilisateur.GERANT, nom, prenom, mdp);

	}
}
