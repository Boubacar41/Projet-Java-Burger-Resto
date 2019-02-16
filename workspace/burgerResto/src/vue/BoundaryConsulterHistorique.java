package vue;

import java.util.ArrayList;
import java.util.List;

import control.ControlConsulterHistorique;

public class BoundaryConsulterHistorique {
	// declaration du controleur du cas
	private ControlConsulterHistorique controlConsulterHistorique;

	// constructeur
	public BoundaryConsulterHistorique(ControlConsulterHistorique controlConsulterHistorique2) {
		// TODO Auto-generated constructor stub
		this.controlConsulterHistorique = controlConsulterHistorique2;
	}

	// methode principale du cas
	public void consulterHistorique(int numProfil) {
		Boolean connexionOK;
		List<String> historique = new ArrayList<>();
		connexionOK = controlConsulterHistorique.verifierIdentificationClient(numProfil);
		if (connexionOK) {
			historique = controlConsulterHistorique.consulterHistorique(numProfil);
			System.out.println("Historique du client no " + (numProfil + 1) + ":\n");
			for (int i = 0; i < historique.size(); i++) {
				System.out.println(historique.get(i) + "\n");
			}
		}
	}

}
