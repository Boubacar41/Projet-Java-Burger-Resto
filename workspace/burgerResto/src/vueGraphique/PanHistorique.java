package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlConsulterHistorique;

public class PanHistorique extends JPanel {
	private static final long serialVersionUID = 1L;

	// controleurs du cas
	private ControlConsulterHistorique controlConsulterHistorique;

	// Les elements graphiques :
	// polices d'ecritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);

	// Mise en page : les Box
	private Box boxMiseEnpage = Box.createVerticalBox();
	private Box boxCommandeHistorique = Box.createVerticalBox();
	private Box boxCommandes = Box.createVerticalBox();

	public PanHistorique(ControlConsulterHistorique controlConsulterHistorique) {
		this.controlConsulterHistorique = controlConsulterHistorique;
	}

	// Methode d'initialisation du panel
	public void initialisation() {
		// mise en forme du panel (couleur, ...)
		this.setBackground(Color.CYAN);
		// creation des differents elements graphiques (JLabel, Combobox,
		// Button, TextAera ...)

		JLabel titre = new JLabel("Historique de vos commandes");
		titre.setFont(policeTitre);

		// mise en page : placements des differents elements graphiques dans des
		// Box
		this.boxCommandeHistorique.add(titre);
		this.boxCommandeHistorique.add(Box.createRigidArea(new Dimension(0, 15)));
		this.boxCommandeHistorique.add(boxCommandes);
		// mise en page : placements des differentes box dans une box principale
		this.boxMiseEnpage.add(boxCommandeHistorique);
		// mise en page : ajout de la box principale dans le panel
		this.add(boxMiseEnpage);
	}

	// methode du cas consulter historique
	public void consulterHistorique(int numProfil) {
		Boolean connexionOK;
		List<String> historique = new ArrayList<>();
		connexionOK = controlConsulterHistorique.verifierIdentificationClient(numProfil);
		if (connexionOK) {
			historique = controlConsulterHistorique.consulterHistorique(numProfil);
			System.out.println("Historique du client no " + (numProfil + 1) + ":\n");
			for (String commande : historique) {
				JLabel label = new JLabel(commande);
				label.setFont(policeParagraphe);
				boxCommandes.add(label);
			}
			;
		}
	}

}
