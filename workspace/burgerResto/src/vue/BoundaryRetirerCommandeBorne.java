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
		System.out.println("veillez entrer le num�ro de commande");
		int numeroCommande = clavier.entrerClavierInt();
		boolean commandeTrouvee = controlRetirerCommandeBorne.retirerCommandeBorne(numeroCommande);

		if (commandeTrouvee) {
			System.out.println("Vous pouvez r�cup�rer votre commande au comptoir\n");
		} else {
			System.out.println("num�ro de commande inexistant\n");
		}
	}
}
