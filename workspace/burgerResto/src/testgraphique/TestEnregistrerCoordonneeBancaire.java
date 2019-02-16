package testgraphique;

import javax.swing.JFrame;


import control.ControlEnregistrerCoordonneesBancaires;
import control.ControlVerifierCoordonneesBancaires;
import vueGraphique.IUseEnregistrerCoordonneesBancaires;
import vueGraphique.PanEnregistrerCoordonneesBancaire;

public class TestEnregistrerCoordonneeBancaire {

	public static void main(String[] args) {
		ControlVerifierCoordonneesBancaires controlVerifierCoordonneesBancaires = new ControlVerifierCoordonneesBancaires();
		ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires = new ControlEnregistrerCoordonneesBancaires(
				controlVerifierCoordonneesBancaires);
		// JFrame test = new JFrame();
		// test.add(panTest);
		// test.isVisible();
		// panTest.isVisible();
		// panTest.enregistrerCoordonneesBancaires(1);
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);
		fenetre.setSize(800,400);

		 PanEnregistrerCoordonneesBancaire panTest = new
		 PanEnregistrerCoordonneesBancaire(controlEnregistrerCoordonneesBancaires);
		 fenetre.setContentPane(panTest);
		 panTest.initialisation();
		 //Cette ligne passe ne passe plus à la compilation dans la partie 16.
		// panTest.enregistrerCoordonneesBancaires(1);
		 panTest.setVisible(true);
		 fenetre.repaint();
	}

}
