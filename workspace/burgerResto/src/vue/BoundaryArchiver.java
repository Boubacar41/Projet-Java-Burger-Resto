package vue;

import control.ControlArchiver;

public class BoundaryArchiver {
	// declaration du controleur du cas
	ControlArchiver controlArchiver;

	// constructeur
	public BoundaryArchiver(ControlArchiver controlArchiver2) {
		this.controlArchiver = controlArchiver2;
	}

	// methode principale du cas
	public void archiver(int numProfil) {
		controlArchiver.archiver(numProfil);
	}
}
