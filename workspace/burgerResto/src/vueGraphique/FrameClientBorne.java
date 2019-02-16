package vueGraphique;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlRetirerCommandeBorne;

public class FrameClientBorne extends JFrame {
	private static final String RETRAIT_COMMANDE = "RETRAIT COMMANDE";
	private static final long serialVersionUID = 1L;

	// Les cartes
	private CardLayout cartes = new CardLayout();

	// Les panels
	private JPanel panContents = new JPanel(cartes);
	private PanRetirerCommandeBorne panRetirerCommandeBorne;

	public FrameClientBorne(ControlRetirerCommandeBorne controlRetirerCommandeBorne) {

		// mise en forme de la Frame
		setTitle("Borne de retrait des commandes");
		setSize(900, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// creation des differents panels
		panRetirerCommandeBorne = new PanRetirerCommandeBorne(controlRetirerCommandeBorne);

		// initialisation des differents pannels
		panRetirerCommandeBorne.initialisation();

		// mise en page : mise en place des cartes
		panContents.setLayout(cartes);
		panContents.add(panRetirerCommandeBorne, RETRAIT_COMMANDE);
		this.getContentPane().add(panContents);
		panRetirerCommandeBorne.setVisible(true);
		cartes.show(panContents, RETRAIT_COMMANDE);

		this.setVisible(true);
	}

}
