package vue;

import java.util.Observable;
import java.util.Observer;
import control.ControlVisualiserCommandeSurPlace;
import model.BDCommande;

public class BoundaryVisualiserCommandeSurPlace implements Observer {
	// controleur du cas
	ControlVisualiserCommandeSurPlace controlsurplace;

	// constructeur
	public BoundaryVisualiserCommandeSurPlace(ControlVisualiserCommandeSurPlace controlsurplace) {
		super();
		this.controlsurplace = controlsurplace;
	}

	// methode principale du cas
	public void visualiserCommandeSurPlace(int numProfilCuisinier) {
		boolean identificationOK = controlsurplace.verifierIdentificationPersonnel(numProfilCuisinier);
		if (identificationOK) {
			this.controlsurplace.setObserver(this);
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
				if (i == 3) {
					// System.out.println("Commande n°"+labels[1]+": "+labels[2]+" "+labels[3]+"
					// "+labels[4]+"\n");
					Fichier.ecrireSurPlace("Commande n°" + labels[1] + " : " + labels[2] + " , " + labels[3] + " , "
							+ labels[4] + "\n");
				} else {
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
