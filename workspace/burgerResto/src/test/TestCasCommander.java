package test;

import control.ControlAjouterAlimentMenu;
import control.ControlCommander;
import control.ControlCreerProfil;
import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlSIdentifier;
import control.ControlVerifierCoordonneesBancaires;
import control.ControlVerifierIdentification;
import model.BDCommande;
import model.ProfilUtilisateur;
import vue.BoundaryCommander;
import vue.BoundaryEnregistrerCoordonneesBancaires;

public class TestCasCommander {

	public static void main(String[] args) {
		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		ControlAjouterAlimentMenu controlAjouterAlimentCarte = new ControlAjouterAlimentMenu(new ControlVerifierIdentification());
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();

		controlAjouterAlimentCarte.ajouterHamburger("baconBurger");
		controlAjouterAlimentCarte.ajouterHamburger("chickenBurger");
		controlAjouterAlimentCarte.ajouterHamburger("cheeseBurger");
		controlAjouterAlimentCarte.ajouterAccompagnement("frites");
		controlAjouterAlimentCarte.ajouterAccompagnement("pommesChips");
		controlAjouterAlimentCarte.ajouterBoisson("coca");
		controlAjouterAlimentCarte.ajouterBoisson("orangeBubbles");

		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Dupond",
				"Hector", "cdh");
		int numClient = controlSIdentifier.sIdentifier(
				ProfilUtilisateur.CLIENT, "Hector.Dupond", "cdh");

		// Initialisation controleur du cas & cas Inclus/etendu
		ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();
		ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaire = new ControlVerifierCoordonneesBancaires();
		ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires = new ControlEnregistrerCoordonneesBancaires(controlVerifierCoordonneesBancaire);

		// Initialisation vue du cas & cas Inclus/etendu
		ControlCommander controlCommander = new ControlCommander(
				controlVerifierIdentification);
		BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonneesBancaires = new BoundaryEnregistrerCoordonneesBancaires(
				controlEnregistrerCoordonneesBancaires);
		BoundaryCommander boundaryCommander = new BoundaryCommander(
				controlCommander,
				boundaryEnregistrerCoordonneesBancaires);

		// Lancement du cas
		System.out.println("\nEcran client");
		boundaryCommander.commander(numClient);
		
		// Verification de la bonne realisation du cas
		//Ajouter dans le controleur "controlCommander" la méthode visualisationBDCommandes :
		// public String visualiserBDCommandes() {
		// BDCommande bdCommande = BDCommande.getInstance();
		// return (bdCommande.toString());
		// }
		System.out.println("VERIFICATION");
		System.out.println(controlCommander.visualiserBDCommandes());

		// Resultat du test
		// Ecran client
		// Veuillez selectionner le numero de votre hamburger
		// Liste des hamburgers :
		// 1 : baconBurger
		// 2 : chickenBurger
		// 3 : cheeseBurger
		//
		// 1
		// Veuillez selectionner le numero de votre accompagnement
		// Liste des accompagnement :
		// 1 : frites
		// 2 : pommesChips
		//
		// 2
		// Veuillez selectionner le numero de votre boisson
		// Liste des boissons :
		// 1 : coca
		// 2 : orangeBubbles
		//
		// 2
		// Veuillez saisir votre numero de carte bancaire
		// 123154123
		// Veuillez saisir la date d'expiration de votre carte bancaire (MMAA)
		// 1018
		// Votre numero de commande est le : 1
		// VERIFICATION
		// BDCommande [commandes={1=Commande [numClient=0, hamburger=nom=baconBurger, boisson=nom=orangeBubbles, accompagnement=nom=pommesChips]}]
	}

}
