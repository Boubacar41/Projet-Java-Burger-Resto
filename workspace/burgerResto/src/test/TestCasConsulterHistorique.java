package test;

import control.ControlAjouterAlimentMenu;

import control.ControlArchiver;
import control.ControlCommander;
import control.ControlCreerProfil;
import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlRetirerCommandeBorne;
import control.ControlSIdentifier;
import control.ControlValiderPayementBancaire;
import control.ControlVerifierCoordonneesBancaires;
import control.ControlVerifierIdentification;
import control.ControlVisualiserCommandeJour;
import control.ControlVisualiserCommandeSurPlace;
import control.ControlConsulterHistorique;
import model.ProfilUtilisateur;
import vue.BoundaryArchiver;
import vue.BoundaryCommander;
import vue.BoundaryEnregistrerCoordonneesBancaires;
import vue.BoundaryRetirerCommandeBorne;
import vue.BoundaryVisualiserCommandeJour;
import vue.BoundaryVisualiserCommandeSurPlace;
import vue.BoundaryConsulterHistorique;

public class TestCasConsulterHistorique {

	public static void main(String[] args) {
		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		ControlAjouterAlimentMenu controlAjouterAlimentCarte = new ControlAjouterAlimentMenu(
				new ControlVerifierIdentification());
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();

		controlAjouterAlimentCarte.ajouterHamburger("baconBurger");
		controlAjouterAlimentCarte.ajouterHamburger("chickenBurger");
		controlAjouterAlimentCarte.ajouterHamburger("cheeseBurger");
		controlAjouterAlimentCarte.ajouterAccompagnement("frites");
		controlAjouterAlimentCarte.ajouterAccompagnement("pommesChips");
		controlAjouterAlimentCarte.ajouterBoisson("coca");
		controlAjouterAlimentCarte.ajouterBoisson("orangeBubbles");

		// Création et connexion du profil gérant
		controlCreerProfil.creerProfil(ProfilUtilisateur.GERANT, "Martin", "Victor", "gmv");
		int numGerant = controlSIdentifier.sIdentifier(ProfilUtilisateur.GERANT, "Victor.Martin", "gmv");

		// Création et connexion des profils clients
		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Dupond", "Hector", "cdh");
		int numClient = controlSIdentifier.sIdentifier(ProfilUtilisateur.CLIENT, "Hector.Dupond", "cdh");

		controlCreerProfil.creerProfil(ProfilUtilisateur.CLIENT, "Durand", "Jacques", "cdj");
		int numClient2 = controlSIdentifier.sIdentifier(ProfilUtilisateur.CLIENT, "Jacques.Durand", "cdj");
		System.out.println("Client n°" + numClient + "et client n°" + numClient2);
		// Création et connexion des profils cuisiniers
		controlCreerProfil.creerProfil(ProfilUtilisateur.PERSONNEL, "Martin", "Stephane", "pms");
		int numCuisinier = controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL, "Stephane.Martin", "pms");

		controlCreerProfil.creerProfil(ProfilUtilisateur.PERSONNEL, "Bernard", "Christophe", "pbc");
		int numCuisinier2 = controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL, "Christophe.Bernard", "pbc");

		// Initialisation controleur du cas & cas Inclus/etendu
		ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();
		ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaire = new ControlVerifierCoordonneesBancaires();
		ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires = new ControlEnregistrerCoordonneesBancaires(
				controlVerifierCoordonneesBancaire);

		// Initialisation vue du cas & cas Inclus/etendu
		BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonneesBancaires = new BoundaryEnregistrerCoordonneesBancaires(
				controlEnregistrerCoordonneesBancaires);
		BoundaryCommander boundaryCommander = new BoundaryCommander(new ControlCommander(controlVerifierIdentification),
				boundaryEnregistrerCoordonneesBancaires);

		// creation pour la visualisation de la commande du jour
		ControlVisualiserCommandeJour controlVisualiserCommandeJour = new ControlVisualiserCommandeJour(
				controlVerifierIdentification);
		BoundaryVisualiserCommandeJour boundaryVisualiserCommandeJour = new BoundaryVisualiserCommandeJour(
				controlVisualiserCommandeJour);
		boundaryVisualiserCommandeJour.visualiserCommandeJour(numCuisinier);
		boundaryVisualiserCommandeJour.visualiserCommandeJour(numCuisinier2);

		// creation pour la visualisation de la commande sur place
		ControlVisualiserCommandeSurPlace controlVisualiserCommandeSurPlace = new ControlVisualiserCommandeSurPlace(
				controlVerifierIdentification);
		BoundaryVisualiserCommandeSurPlace boundaryVisualiserCommandeSurPlace = new BoundaryVisualiserCommandeSurPlace(
				controlVisualiserCommandeSurPlace);
		boundaryVisualiserCommandeSurPlace.visualiserCommandeSurPlace(numCuisinier);
		boundaryVisualiserCommandeSurPlace.visualiserCommandeSurPlace(numCuisinier2);

		// Lancement du test
		System.out.println("\nCommande client 1");
		boundaryCommander.commander(numClient);
		System.out.println("\nCommande client 2");
		boundaryCommander.commander(numClient2);

		// attendre 60s pour la visualisation du fichier avec les commandes
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Arrivee d'un client sur place
		ControlValiderPayementBancaire controlValiderPayementBancaire = new ControlValiderPayementBancaire();
		ControlRetirerCommandeBorne controlRetirerCommandeBorne = new ControlRetirerCommandeBorne(
				controlValiderPayementBancaire);
		BoundaryRetirerCommandeBorne boundaryRetirerCommandeBorne = new BoundaryRetirerCommandeBorne(
				controlRetirerCommandeBorne);
		boundaryRetirerCommandeBorne.retirerCommandeBorne();

		// Consultation de l'histrorique
		ControlConsulterHistorique controlConsulterHistorique = new ControlConsulterHistorique(
				controlVerifierIdentification);
		BoundaryConsulterHistorique boundaryConsulterHistorique = new BoundaryConsulterHistorique(
				controlConsulterHistorique);
		boundaryConsulterHistorique.consulterHistorique(numClient);

		// attendre 60s pour la visualisation du fichier avec les commandes
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Cas Archiver
		System.out.println("COMMANDES ARCHIVER");
		ControlArchiver controlArchiver = new ControlArchiver(controlVerifierIdentification);
		BoundaryArchiver boundaryArchiver = new BoundaryArchiver(controlArchiver);
		boundaryArchiver.archiver(numGerant);

		// Verification de la bonne realisation du cas
		// Resultat du test
		//
		// Commande client 1
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
		// 1019
		// Votre numero de commande est le : 1
		//
		// Commande client 2
		// Veuillez selectionner le numero de votre hamburger
		// Liste des hamburgers :
		// 1 : baconBurger
		// 2 : chickenBurger
		// 3 : cheeseBurger
		//
		// 3
		// Veuillez selectionner le numero de votre accompagnement
		// Liste des accompagnement :
		// 1 : frites
		// 2 : pommesChips
		//
		// 1
		// Veuillez selectionner le numero de votre boisson
		// Liste des boissons :
		// 1 : coca
		// 2 : orangeBubbles
		//
		// 1
		// Veuillez saisir votre numero de carte bancaire
		// 369258147
		// Veuillez saisir la date d'expiration de votre carte bancaire (MMAA)
		// 1219
		// Votre numero de commande est le : 2
		//
		// Dans le fichier ecranCuisinierCommandeJour.txt (Rafraichir le projet)
		// :
		// Commande n°1 : baconBurger, pommesChips, orangeBubbles
		// Commande n°2 : cheeseBurger, frites, coca
		// Fermer le fichier
		// Attendre 30 secondes (Thread.sleep(30000);) l'écriture dans la
		// console
		//
		// Veuillez entrer le numéro de commande
		// 1
		// Compte bancaire n°123154123 a été débité de 8€
		// Historique du client n°1 :
		// Commande no 1 : baconBurger, pommesChips, orangeBubbles
		//
		// Dans le fichier ecranCuisinierCommandeJour.txt (Rafraichir le projet)
		// :
		// Commande n°2 : cheeseBurger, frites, coca
		// Dans le fichier ecranCuisinierCommandeSurPlace.txt
		// Commande n°1 : baconBurger, pommesChips, orangeBubbles
		// Fermer le fichier ecranCuisinierCommandeSurPlace.txt
		// Attendre 30 secondes (Thread.sleep(30000);) l'écriture dans la
		// console de "COMMANDES ARCHIVER"
		// Ouvrir le fichier ecranCuisinierCommandeJour.txt : il doit être vide

	}

}
