package vueGraphique;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import control.ControlVisualiserCommandeJour;
import control.ControlVisualiserCommandeSurPlace;

public class FrameCuisinier extends JFrame {
	private static final String COMMANDE_JOUR = "COMMANDE JOUR";
	private static final String COMMANDE_SUR_PLACE = "COMMANDE SUR PLACE";
	private static final String ECRAN_ACCUEIL = "ECRAN_ACCUEIL";
	private static final long serialVersionUID = 1L;

	// Les attributs metiers
	private int numPersonnel;

	// La barre de menu
	MenuBar barreMenu = new MenuBar();

	// Les cartes
	private CardLayout cartes = new CardLayout();

	// Les panels
	private JPanel panContents = new JPanel(cartes);
	private JPanel panAccueil = new JPanel();
	private PanVisualiserCommandeJour panVisualisationCommandeJour;
	private PanVisualiserCommandeSurPlace panVisualiserCommandeSurPlace;

	public FrameCuisinier(int numPersonnel, ControlVisualiserCommandeJour controlVisualiserCommandeJour,
			ControlVisualiserCommandeSurPlace controlVisualiserCommandeSurPlace) {
		// intitialisation des attributs metiers
		this.numPersonnel = numPersonnel;

		// mise en forme de la Frame
		this.setTitle("RestoBurger Interne");
		this.setSize(900, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// creation des differents panels
		this.panVisualisationCommandeJour = new PanVisualiserCommandeJour(controlVisualiserCommandeJour);
		this.panVisualiserCommandeSurPlace = new PanVisualiserCommandeSurPlace(controlVisualiserCommandeSurPlace);

		// initialisation des differents pannels
		this.panVisualisationCommandeJour.initialisation(this);
		this.panVisualiserCommandeSurPlace.initialisation(this);

		// mise en page : mise en place des cartes
		this.panContents.setLayout(cartes);
		this.panContents.add(panVisualisationCommandeJour, COMMANDE_JOUR);
		this.panContents.add(panVisualiserCommandeSurPlace, COMMANDE_SUR_PLACE);
		this.getContentPane().add(panContents);

		// appel a la methode d'initialisation du menu
		this.initialisationMenu();

		// mise en place du menu
		this.setMenuBar(barreMenu);

		// appel a la methode d'initialisation de la page d'accueil
		this.initialisationAcceuil();

		this.setVisible(true);
	}

	// TODO JLabel
	private void initialisationAcceuil() {
		this.panAccueil.setBackground(Color.WHITE);
		JLabel texteAccueil = new JLabel("Bonjour");
		texteAccueil.setFont(new Font("Calibri", Font.BOLD, 24));
		this.panAccueil.add(texteAccueil);
		this.panAccueil.setVisible(true);
		this.panContents.add(panAccueil, ECRAN_ACCUEIL);
		cartes.show(panContents, ECRAN_ACCUEIL);
	}

	// TODO MenuBar
	public void initialisationMenu() {
		MenuItem visualiserCommandeJour = new MenuItem("Commandes de la journée");
		visualiserCommandeJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panVisualisationCommandeJour.visualiserCommandeJour(numPersonnel);
				cartes.show(panContents, COMMANDE_JOUR);
			}
		});
		MenuItem visualiserCommandeSurPlace = new MenuItem("Commandes sur place");
		visualiserCommandeSurPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panVisualiserCommandeSurPlace.visualiserCommandeSurPlace(numPersonnel);
				cartes.show(panContents, COMMANDE_SUR_PLACE);
			}
		});
		Menu menuMonCompte = new Menu("Mon compte ");
		menuMonCompte.add(visualiserCommandeJour);
		menuMonCompte.add(visualiserCommandeSurPlace); 

		Menu menuDeconnexion = new Menu("Deconnexion");

		barreMenu.add(menuMonCompte);
		barreMenu.add(menuDeconnexion);

	}

}
