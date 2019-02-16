package vue;

import control.ControlRetirerCommandeBorne;

public class BoundaryRetirerCommandeBorne {

	Clavier clavier = new Clavier();

	// declaration du controleur du cas
	private ControlRetirerCommandeBorne controlRetirerCommandeBorne;

	// constructeur
	public BoundaryRetirerCommandeBorne(ControlRetirerCommandeBorne controlRetirerCommandeBorne2) {
		// TODO Auto-generated constructor stub
		this.controlRetirerCommandeBorne = controlRetirerCommandeBorne2;
	}

	// methode principale du cas
	public void retirerCommandeBorne() {
		System.out.println("veillez entrer le numéro de commande");
		int numeroCommande = clavier.entrerClavierInt();
		boolean commandeTrouvee = controlRetirerCommandeBorne.retirerCommandeBorne(numeroCommande);

		if (commandeTrouvee) {
			System.out.println("Vous pouvez récupérer votre commande au comptoir\n");
		} else {
			System.out.println("numéro de commande inexistant\n");
		}
	}
}
