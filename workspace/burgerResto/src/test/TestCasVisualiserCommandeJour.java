package test;

import control.ControlCreerProfil;
import control.ControlSIdentifier;
import control.ControlVerifierIdentification;
import control.ControlVisualiserCommandeJour;
import model.Accompagnement;
import model.BDCommande;
import model.Boisson;
import model.Hamburger;
import model.ProfilUtilisateur;
import vue.BoundaryVisualiserCommandeJour;

public class TestCasVisualiserCommandeJour {

	public static void main(String[] args) {
		// Mise en place de l'environnement pour le test
		Hamburger cheeseBurger = new Hamburger("CheeseBurger");
		Hamburger maxiBurger = new Hamburger("MaxiBurger");
		Accompagnement accompagnement = new Accompagnement("Frites");
		Boisson boissonCoca = new Boisson("Coca");
		Boisson boissonOrange = new Boisson("Orange");

		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		controlCreerProfil.creerProfil(ProfilUtilisateur.PERSONNEL, "Dupond", "Jacques", "jdu");

		// Connexion du cuisinier
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();
		int numCuisinier = controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL, "Jacques.Dupond", "jdu");

		// Initialisation controleur du cas & cas Inclus/etendu
		ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();
		controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.PERSONNEL, numCuisinier);

		// cas visualiser commande du jour
		ControlVisualiserCommandeJour controlVisualiserCommandeJour = new ControlVisualiserCommandeJour(
				controlVerifierIdentification);
		BoundaryVisualiserCommandeJour boundaryVisualiserCommandeJour = new BoundaryVisualiserCommandeJour(
				controlVisualiserCommandeJour);
		boundaryVisualiserCommandeJour.visualiserCommandeJour(numCuisinier);

		try {
			Thread.sleep(1500);
			System.out.println("Ecriture des commandes dans le fichier");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// extrait cas commander
		BDCommande bdCommande = BDCommande.getInstance();
		bdCommande.enregistrerCommande(1, cheeseBurger, accompagnement, boissonCoca);
		bdCommande.enregistrerCommande(1, maxiBurger, accompagnement, boissonOrange);

		// Ecriture des commandes dans le fichier
		// Commande n°1 : CheeseBurger, Frites, Coca
		// Commande n°2 : MaxiBurger, Frites, Orange
	}

}
