package vueGraphique;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlCommander;
import control.ControlConsulterHistorique;
import control.ControlEnregistrerCoordonneesBancaires;

public class FrameClient extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Les attributs métiers
	int numClient;
	private static final String COMMANDER = "COMMANDER";
	private static final String HISTORIQUE = "HISTORIQUE";
	private static final String MODIFIER_PROFIL = "MODIFIER_PROFIL";
	private static final String ECRAN_ACCUEIL = "ECRAN_ACCUEIL";
	
	// Les éléments graphiques (JLabel)
	JPanel panContents = new JPanel();
	private JPanel panAccueil = new JPanel();
	
	// La barre de menu (MenuBar)
	MenuBar barreMenu = new MenuBar();
	
	// Les panels 
	PanCommander panCommander;
	PanModifierProfil panModifierProfil = new PanModifierProfil();
	PanHistorique panHistorique;
	
	// Les carte (CardLayout) 
	CardLayout cartes = new CardLayout();

	// Le constructeur 
	public FrameClient(int numClient, ControlCommander controlCommande,
			ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires,ControlConsulterHistorique consulterHistorique) throws HeadlessException {
		
		// initialisation des attributs métiers 
		this.numClient = numClient;
		
		// mise en forme de la frame (titre, dimension, …) 
		this.setTitle("BurgerResto");
		this.setSize(900, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// création des différents panels 
		PanEnregistrerCoordonneesBancaire panEnregistrerCoordonneesBancaires = new PanEnregistrerCoordonneesBancaire(
				controlEnregistrerCoordonneesBancaires);
		panCommander = new PanCommander(controlCommande, panEnregistrerCoordonneesBancaires);
		panHistorique = new PanHistorique(consulterHistorique); 
		
		// initialisation des différents panels : appel à leur méthode d’initialisation
		panCommander.initialisation();
		panModifierProfil.initialisation();
		panHistorique.initialisation();
		panEnregistrerCoordonneesBancaires.initialisation();
		
		// mise en page : mises en place des cartes
		this.panContents.setLayout(cartes);
		
		// mise en place du menu 
		panCommander.add(panEnregistrerCoordonneesBancaires, "ENREGISTRER_COORDONNEES_BANCAIRE");
		panContents.add(panCommander, COMMANDER);
		panContents.add(panModifierProfil, MODIFIER_PROFIL);
		panContents.add(panHistorique, HISTORIQUE);
		this.getContentPane().add(panContents);
		
		// appel à la méthode d’initialisation du menu
		this.initialisationMenu();
		this.setMenuBar(barreMenu);
		
		// appel à la méthode d’initialisation de la page d’accueil (optionnel) 
		this.initialisationAcceuil();
		
		this.setVisible(true);
	}
	
	//initialisation de l'acceuil
	private void initialisationAcceuil() {
		this.panAccueil.setBackground(Color.ORANGE);
		JLabel texteAccueil = new JLabel("Bienvenu à Burger Resto");
		texteAccueil.setFont(new Font("Calibri", Font.BOLD, 24));
		this.panAccueil.add(texteAccueil);
		this.panAccueil.setVisible(true);
		this.panContents.add(panAccueil, ECRAN_ACCUEIL);
		cartes.show(panContents, ECRAN_ACCUEIL);
	}

	//initialisation du menu
	private void initialisationMenu() {
		// TODO Auto-generated method stub
		MenuItem commander = new MenuItem("Commander");
		commander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panCommander.commander(numClient);
				cartes.show(panContents, COMMANDER);
				panelVisible(COMMANDER);
			}
		});
		MenuItem modifierProfil = new MenuItem("ModifierProfil");
		modifierProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cartes.show(panContents, MODIFIER_PROFIL);
				panelVisible(MODIFIER_PROFIL);
			}
		});
		MenuItem historique = new MenuItem("Historique");
		historique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panHistorique.consulterHistorique(numClient);
				cartes.show(panContents, HISTORIQUE);
				panelVisible(HISTORIQUE);
			}
		});
		Menu menuMonCompte = new Menu("Mon compte ");
		menuMonCompte.add(commander);
		menuMonCompte.add(modifierProfil);
		menuMonCompte.add(historique);
		Menu menuDeconnexion = new Menu("Deconnexion");
		barreMenu.add(menuMonCompte);
		barreMenu.add(menuDeconnexion);
	}

	
 //gestion de la visibilité des pannels
	private void panelVisible(String nomPanel) {
		switch (nomPanel) {
		case COMMANDER:
			panHistorique.setVisible(false);
			panModifierProfil.setVisible(false);
			panCommander.setVisible(true);
			break;

		case HISTORIQUE:
			panCommander.setVisible(false);
			panModifierProfil.setVisible(false);
			panHistorique.setVisible(true);
			break;

		case MODIFIER_PROFIL:
			panCommander.setVisible(false);
			panHistorique.setVisible(false);
			panModifierProfil.setVisible(true);
			break;

		default:
			System.out.println("Pas de panel a ce nom");
			break;
		}
	}

}
