package vue;

import control.ControlAjouterAlimentMenu;

public class BoundaryAjouterAlimentMenu {
	
	// declaration des attributs

	Clavier clavier = new Clavier();
	// declaration du controleur du cas
	ControlAjouterAlimentMenu controlAjout;

	//constructeur
	public BoundaryAjouterAlimentMenu(ControlAjouterAlimentMenu controlAjout) {
		super();
		this.controlAjout = controlAjout;
	}

	//methode principale du cas
	public void ajouterAlimentMenu(int numProfil) {
		boolean entreeCorrect = false;
		int choix = 0;
		if (controlAjout.verifierIdentification(numProfil)) {
			do {
				System.out.println("1. Ajouter un hamburger\n");
				System.out.println("2. Ajouter un accompagnement\n");
				System.out.println("3. Ajouter une boisson\n");
				choix = clavier.entrerClavierInt();
				entreeCorrect = ((choix != 1) || (choix != 2) || (choix != 3));

				System.out.println("Veuillez entrer le nom de l'aliment:\n");
				String nom = clavier.entrerClavierString();
				switch (choix) {
				case 1:
					controlAjout.ajouterHamburger(nom);
					break;
				case 2:
					controlAjout.ajouterAccompagnement(nom);
					break;
				case 3:
					controlAjout.ajouterBoisson(nom);
					break;
				default:
					System.out.println("Aliment non reconnu !");
					break;
				}
			} while (!entreeCorrect);
		}
	}
}
