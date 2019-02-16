package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlVisualiserCommandeSurPlace;
import model.BDCommande;
import vue.Fichier;

public class PanVisualiserCommandeSurPlace extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	// controleurs du cas
	private ControlVisualiserCommandeSurPlace controlVisualiserCommandeSurPlace;

	// les attributs metiers
	private static Map<String, String> mapCommandeSurPlace = new HashMap<>();

	// Les elements graphiques :
	// polices d'ecritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);

	// Mise en page : les Box
	private Box boxMiseEnpage = Box.createVerticalBox();
	private Box boxCommandeSurPlace = Box.createVerticalBox();
	private Box boxCommandes = Box.createVerticalBox();

	private FrameCuisinier frame;

	public PanVisualiserCommandeSurPlace(ControlVisualiserCommandeSurPlace controlVisualiserCommandeSurPlace) {
		this.controlVisualiserCommandeSurPlace = controlVisualiserCommandeSurPlace;
	}

	// Methode d'initialisation du panel
	public void initialisation(FrameCuisinier frame) {
		this.frame = frame;
		// mise en forme du panel (couleur, ...)
		this.setBackground(Color.WHITE);
		// creation des differents elements graphiques (JLabel, Combobox,
		// Button, TextAera ...)

		JLabel titre = new JLabel("Commandes des clients sur place");
		titre.setFont(policeTitre);

		// mise en page : placements des differents elements graphiques dans des
		// Box
		this.boxCommandeSurPlace.add(titre);
		this.boxCommandeSurPlace.add(Box.createRigidArea(new Dimension(0, 15)));
		this.boxCommandeSurPlace.add(boxCommandes);
		// mise en page : placements des differentes box dans une box principale
		this.boxMiseEnpage.add(boxCommandeSurPlace);
		// mise en page : ajout de la box principale dans le panel
		this.add(boxMiseEnpage);
	}

	// mise à jour de l'o:server
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof BDCommande) {
			if (arg instanceof String[]) {
				String[] labels = (String[]) arg;
				int i = Integer.parseInt(labels[0]);
				if (i == 3) {
					String numeroCommande = labels[1];
					String hamburger = labels[2];
					String accompagnement = labels[3];
					String boisson = labels[4];
					String texteCommandeJour = ("Commande no " + numeroCommande + " : " + hamburger + ", "
							+ accompagnement + ", " + boisson);
					JLabel label = new JLabel(texteCommandeJour);
					label.setFont(policeParagraphe);
					PanVisualiserCommandeJour.mapCommandeJour.put(numeroCommande, texteCommandeJour);
					boxCommandes.add(label);
					actualiserPanel();

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

	// methode d'actualisation du panel
	private void actualiserPanel() {
		frame.setVisible(true);
		frame.repaint();
	}

	// Méthode d’initialisation du panel
	private void initialiserPanel() {
		boxCommandes.removeAll();
		for (String texte : mapCommandeSurPlace.values()) {
			JLabel label = new JLabel(texte);
			label.setFont(policeParagraphe);
			boxCommandes.add(label);
		}
		actualiserPanel();
	}

	// methode du cas visualiser commande jour
	public void visualiserCommandeSurPlace(int numProfilCuisinier) {
		boolean identificationOK = controlVisualiserCommandeSurPlace
				.verifierIdentificationPersonnel(numProfilCuisinier);
		if (identificationOK) {
			this.controlVisualiserCommandeSurPlace.setObserver(this);
		}
	}

}
