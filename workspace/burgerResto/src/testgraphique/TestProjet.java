package testgraphique;

import control.ControlAjouterAlimentMenu;
import control.ControlArchiver;
import control.ControlCommander;
import control.ControlConsulterHistorique;
import control.ControlCreerProfil;
import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlRetirerCommandeBorne;
import control.ControlSIdentifier;
import control.ControlValiderPayementBancaire;
import control.ControlVerifierCoordonneesBancaires;
import control.ControlVerifierIdentification;
import control.ControlVisualiserCommandeJour;
import control.ControlVisualiserCommandeSurPlace;
import model.ProfilUtilisateur;
import vue.BoundaryArchiver;
import vueGraphique.FrameClientBorne;
import vueGraphique.FrameClient;
import vueGraphique.FrameCuisinier;

public class TestProjet {
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
		ControlConsulterHistorique controlConsulterHistorique = new ControlConsulterHistorique(controlVerifierIdentification);

		// cas commander
		ControlCommander controlCommande = new ControlCommander(controlVerifierIdentification);
		new FrameClient(numClient, controlCommande, controlEnregistrerCoordonneesBancaires, controlConsulterHistorique);
		new FrameClient(numClient2, controlCommande, controlEnregistrerCoordonneesBancaires, controlConsulterHistorique);

		// cas visualisation commande du jour
		ControlVisualiserCommandeJour controlVisualiserCommandeJour = new ControlVisualiserCommandeJour(
				controlVerifierIdentification);
		ControlVisualiserCommandeSurPlace controlVisualiserCommandeSurPlace = new ControlVisualiserCommandeSurPlace(
				controlVerifierIdentification);
		new FrameCuisinier(numCuisinier, controlVisualiserCommandeJour, controlVisualiserCommandeSurPlace);
		new FrameCuisinier(numCuisinier2, controlVisualiserCommandeJour, controlVisualiserCommandeSurPlace);

		// Arrivee d'un client sur place
		ControlValiderPayementBancaire controlValiderPayementBancaire = new ControlValiderPayementBancaire();
		ControlRetirerCommandeBorne controlRetirerCommandeBorne = new ControlRetirerCommandeBorne(
				controlValiderPayementBancaire);
		new FrameClientBorne(controlRetirerCommandeBorne);

		// attendre 90s pour la visualisation du fichier avec les commandes
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Cas Archiver
		System.out.println("COMMANDES ARCHIVER");
		ControlArchiver controlArchiver = new ControlArchiver(controlVerifierIdentification);
		BoundaryArchiver boundaryArchiver = new BoundaryArchiver(controlArchiver);
		boundaryArchiver.archiver(numGerant);
	}

}
