package vue;

import java.util.Observable;
import java.util.Observer;

import control.ControlVisualiserCommandeJour;
import model.BDCommande;

public class BoundaryVisualiserCommandeJour implements Observer {
	// controleur du cas
	ControlVisualiserCommandeJour control;

	//constructeur
	public BoundaryVisualiserCommandeJour(ControlVisualiserCommandeJour control) {
		super();
		this.control = control;

	}

	//methode principale du cas
	public void visualiserCommandeJour(int numProfilCuisinier) {
		if (control.verifierIdentificationPersonnel(numProfilCuisinier)) {
			this.control.setObserver(this);
		}

	}

	// methode de mise à jour de l'observer pour traiter les notifications de
	// l'observable
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof BDCommande) {
			if (arg instanceof String[]) {
				String[] labels = (String[]) arg;
				int i = Integer.parseInt(labels[0]);
				if (i == 1) {
					// System.out.println("Commande n°"+labels[1]+": "+labels[2]+" "+labels[3]+"
					// "+labels[4]+"\n");
					Fichier.ecrire(
							"Commande n°" + labels[1] + " : " + labels[2] + " , " + labels[3] + " , " + labels[4]);
				} else if (i == 2) {
					Fichier.effacer();
				} else if (i == 3) {
					Fichier.effacerCommande(Integer.parseInt(labels[1]));
				}

				else {
					System.out.println("Numéro d'affichage non réconnu !\n");
				}
			} else {
				System.out.println("type de message inconnu !\n");
			}
		} else {
			System.out.println("Objet emetteur inconnu !\n");
		}

	}

}
