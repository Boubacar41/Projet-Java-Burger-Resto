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

import control.ControlVisualiserCommandeJour;
import model.BDCommande;
import vue.Fichier;

public class PanVisualiserCommandeJour extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	// controleurs du cas
	private ControlVisualiserCommandeJour controlVisualiserCommandeJour;

	// les attributs metiers
	public static Map<String, String> mapCommandeJour = new HashMap<>();

	// Les elements graphiques :
	// polices d'ecritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);

	// Mise en page : les Box
	private Box boxMiseEnpage = Box.createVerticalBox();
	private Box boxCommandeJour = Box.createVerticalBox();
	private Box boxCommandes = Box.createVerticalBox();

	private FrameCuisinier frame;

	public PanVisualiserCommandeJour(ControlVisualiserCommandeJour controlVisualiserCommandeJour) {
		this.controlVisualiserCommandeJour = controlVisualiserCommandeJour;
	}

	// Methode d'initialisation du panel
	public void initialisation(FrameCuisinier frame) {
		this.frame = frame;
		// mise en forme du panel (couleur, ...)
		this.setBackground(Color.WHITE);
		// creation des differents elements graphiques (JLabel, Combobox,
		// Button, TextAera ...)

		JLabel titre = new JLabel("Commande du Jour");
		titre.setFont(policeTitre);

		// mise en page : placements des differents elements graphiques dans des
		// Box
		this.boxCommandeJour.add(titre);
		this.boxCommandeJour.add(Box.createRigidArea(new Dimension(0, 15)));
		this.boxCommandeJour.add(boxCommandes);
		// mise en page : placements des differentes box dans une box principale
		this.boxMiseEnpage.add(boxCommandeJour);
		// mise en page : ajout de la box principale dans le panel
		this.add(boxMiseEnpage);
	}

	// methode de mise a jour de l'observer
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof BDCommande) {
			if (arg instanceof String[]) {
				String[] labels = (String[]) arg;
				int i = Integer.parseInt(labels[0]);
				if (i == 1) {
					// System.out.println("Commande n°"+labels[1]+": "+labels[2]+" "+labels[3]+"
					// "+labels[4]+"\n");
					// Fichier.ecrire("Commande n°" + labels[1] + " : " + labels[2] + " , " +
					// labels[3] + " , " + labels[4]);
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
				} else if (i == 2) {
					PanVisualiserCommandeJour.mapCommandeJour.clear();
					boxCommandes.removeAll();
					actualiserPanel();
				} else if (i == 3) {
					// Fichier.effacerCommande(Integer.parseInt(labels[1]));
					PanVisualiserCommandeJour.mapCommandeJour.remove(labels[1]);
					initialiserPanel();
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

	// methode d'actualisation de panel
	private void actualiserPanel() {
		frame.setVisible(true);
		frame.repaint();
	}

	// Méthode d’initialisation du panel
	private void initialiserPanel() {
		boxCommandes.removeAll();
		for (String texte : mapCommandeJour.values()) {
			JLabel label = new JLabel(texte);
			label.setFont(policeParagraphe);
			boxCommandes.add(label);
		}
		actualiserPanel();
	}

	// methode du cas visualiser commande jour
	public void visualiserCommandeJour(int numProfilCuisinier) {
		if (controlVisualiserCommandeJour.verifierIdentificationPersonnel(numProfilCuisinier)) {
			this.controlVisualiserCommandeJour.setObserver(this);
		}

	}

}
