package vue;

import java.util.ArrayList;
import java.util.List;

import control.ControlCommander;
import control.ControlVerifierIdentification;
import model.ProfilUtilisateur;

public class BoundaryCommander {
	
	Clavier clavier = new Clavier();
	
	// declaration des controleurs et boundary de cas include ou extend
	ControlCommander controlCommande;
	ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();
	BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonnees;
	
	//Listes d'aliments
	private List<String> listHamburger = new ArrayList<>();
	private List<String> listAccompagnement = new ArrayList<>();
	private List<String> listBoisson = new ArrayList<>();

	//constructeur
	public BoundaryCommander(ControlCommander controlCommande,
			BoundaryEnregistrerCoordonneesBancaires boundaryEnregistrerCoordonnees) {
		this.controlCommande = controlCommande;
		this.boundaryEnregistrerCoordonnees = boundaryEnregistrerCoordonnees;
	}

	//methodes pour demander les choix d'alients du client
	
	public int selectionnerHamburger() {
		listHamburger = controlCommande.getListHamburger();
		for (int i = 0; i < listHamburger.size(); i++) {
			System.out.println(i + 1 + ": " + listHamburger.get(i) + "\n");
		}
		System.out.println("Entrer le numero hamburger de votre choix:\n");
		int numHamburger = clavier.entrerClavierInt();
		numHamburger--;
		return numHamburger;

	}

	public int selectionnerAccompagnement() {
		listAccompagnement = controlCommande.getListAccompagnement();
		for (int i = 0; i < listAccompagnement.size(); i++) {
			System.out.println(i + 1 + ": " + listAccompagnement.get(i) + "\n");
		}
		System.out.println("Entrer le numero accompagnement de votre choix:\n");
		int numAccompagnement = clavier.entrerClavierInt();
		numAccompagnement--;
		return numAccompagnement;

	}

	public int selectionnerBoisson() {
		listBoisson = controlCommande.getListBoisson();
		for (int i = 0; i < listBoisson.size(); i++) {
			System.out.println(i + 1 + ": " + listBoisson.get(i) + "\n");
		}
		System.out.println("Entrer le numero boisson de votre choix:\n");
		int numBoisson = clavier.entrerClavierInt();
		numBoisson--;
		return numBoisson;
	}

	// methode principale du cas
	public void commander(int numClient) {

		Boolean clientConnecte = controlVerifierIdentification.verifierIdentification(ProfilUtilisateur.CLIENT,
				numClient);
		if (clientConnecte) {
			Boolean enregistrementCBOK = false;
			int numHamburger = this.selectionnerHamburger();
			int numAccompagnement = this.selectionnerAccompagnement();
			int numBoisson = this.selectionnerBoisson();

			Boolean cbExist = controlCommande.verificationCB(numClient);

			if (!cbExist) {
				enregistrementCBOK = boundaryEnregistrerCoordonnees.enregistrerCoordonneesBancaires(numClient);
			}

			if (!enregistrementCBOK) {
				System.err.println("Eureur enregistrement CB !\n");
			} else {
				/*
				 * int idCommande = controlCommande.entrerCommande(numClient, numHamburger,
				 * numAccompagnement, numBoisson);
				 */
				int idCommande = controlCommande.enregistrerCommande(numClient, numHamburger, numAccompagnement,
						numBoisson);
				System.out.println("Votre numero de commande est: " + idCommande + "\n");
			}

		}

	}

}
