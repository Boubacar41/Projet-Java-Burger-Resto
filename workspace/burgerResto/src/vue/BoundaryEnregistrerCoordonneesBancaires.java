package vue;

import control.ControlEnregistrerCoordonneesBancaires;

public class BoundaryEnregistrerCoordonneesBancaires {

	//clavier
	Clavier clavier = new Clavier();
	
	// declaration du controleur du cas
	ControlEnregistrerCoordonneesBancaires control;

	// constructeur
	public BoundaryEnregistrerCoordonneesBancaires(
			ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires) {
		this.control=controlEnregistrerCoordonneesBancaires;
	}

	// methode principale du cas
	public Boolean enregistrerCoordonneesBancaires(int numClient) {
		System.out.println("Veillez entrer votre numéro de carte bancaire: \n");
		int numeroCarte = clavier.entrerClavierInt();
		System.out.println("Veuillez entrer la date d'expiration de votre carte (MMAA):\n");
		int dateExpCarte = clavier.entrerClavierInt();
		Boolean carteValide = control.enregistrerCordonneesBancaires(numClient, numeroCarte, dateExpCarte);
		if (!carteValide) {
			System.out.println("Votre carte n'est pas valide, votre commande ne peut aboutir !\n");
		}
		
		return carteValide;
	}
}
