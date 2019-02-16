package test;

import control.ControlCreerProfil;
import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlSIdentifier;
import control.ControlVerifierCoordonneesBancaires;
import model.ProfilUtilisateur;
import vue.BoundaryEnregistrerCoordonneesBancaires;
public class TestCasEnregistrerCoordonneesBancaires {

	public static void main(String[] args) {
		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Dupond", "Hector", "cdh");
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();
		int numClient = controlSIdentifier.sIdentifier(ProfilUtilisateur.CLIENT, "Hector.Dupond", "cdh");

		// Initialisation controleur du cas
		ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaires = new ControlVerifierCoordonneesBancaires();
		ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires = new ControlEnregistrerCoordonneesBancaires(
				controlVerifierCoordonneesBancaires);
		// Initialisation vue du cas

		BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonneesBancaires = new BoundaryEnregistrerCoordonneesBancaires(
				controlEnregistrerCoordonneesBancaires);
		// Lancement du cas
		boundaryEnregistrerCoordonneesBancaires.enregistrerCoordonneesBancaires(numClient);
		// Verification de la bonne realisation du cas
		// Completer la methode toString de la classe Client avec l'affichage de la carte bancaire :
		// public String toString() {
		// return "Client [" + super.toString() + ", carteBancaire = " +
		// carteBancaire + "]";
		// }
		System.out.println("VERIFICATION");
		System.out.println(controlSIdentifier.visualiserBDUtilisateur());

		// Resultat du test
		// VERIFICATION
		// Veuillez saisir votre numero de carte bancaire
		// 12315415
		// Veuillez saisir la date d'expiration de votre carte bancaire (MMAA)
		// 1210
		// VERIFICATION
		// BDPersonnel [listePersonnel={}]
		// BDClient [listeClient={0=Client [nom=Dupond, prenom=Hector,
		// login=Hector.Dupond, mdp=cdh, carteBancaire = CarteBancaire
		// [numeroCarte=12315415, dateCarte=1210]]}]

	}
}
