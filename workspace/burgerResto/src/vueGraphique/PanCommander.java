package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlCommander;

public class PanCommander extends JPanel implements IUseEnregistrerCoordonneesBancaires {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Controleur du cas commander
	private ControlCommander controlCommande;
	// //controleur inclus ou etendu en relation avec l’acteur
	private PanEnregistrerCoordonneesBancaire panEnregistrerCoordonneesBancaires;
	
	// les attributs métiers: numéros du client, de l'hamburger, l'accompagnement et de la boisson
	private int numClient = 0, numHamburger = 0, numAccompagnement = 0, numBoisson = 0;
	
	// polices d'écritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);

	// Les Boutton 
	JButton validerCommande = new JButton();
	
	// Mise en page : les Box
	Box boxMiseEnPageCommande = Box.createVerticalBox();
	Box boxChoixHamburger = Box.createHorizontalBox();
	Box boxChoixAccompagnement = Box.createHorizontalBox();
	Box boxChoixBoisson = Box.createHorizontalBox();
	Box boxValiderChoix = Box.createHorizontalBox();
	Box boxMiseEnPageNumeroCommande = Box.createVerticalBox();

	JLabel numeroCommande = new JLabel();
	

	public PanCommander(ControlCommander controlCommande,
			PanEnregistrerCoordonneesBancaire panEnregistrerCoordonneesBancaires) {
		// initilaisation du contrôleur du cas + panel des cas inclus ou étendus en relationavec un acteur 
		this.controlCommande = controlCommande;
		this.panEnregistrerCoordonneesBancaires = panEnregistrerCoordonneesBancaires;
	}
	
	//Méthode d’initialisation du panel 
	public void initialisation() {
		setBackground(Color.yellow);
		
		JLabel texteCommander = new JLabel("Votre menu");
		texteCommander.setFont(policeTitre);
		JLabel texteHamburger = new JLabel("Choisissez votre hamburger");
		texteHamburger.setFont(policeParagraphe);
		JLabel texteAccompagnement = new JLabel("Choisissez votre accompagnement");
		texteAccompagnement.setFont(policeParagraphe);
		JLabel texteBoisson = new JLabel("Choisissez votre boisson");
		texteBoisson.setFont(policeParagraphe);
		JLabel texteNumeroCommandeTitre = new JLabel("Votre commande");
		texteNumeroCommandeTitre.setFont(policeTitre);
		numeroCommande.setFont(policeParagraphe);
		
		boxMiseEnPageCommande.add(texteCommander);
		boxMiseEnPageCommande.add(Box.createRigidArea(new Dimension(0, 30)));
		boxChoixHamburger.add(texteHamburger);
		boxMiseEnPageCommande.add(boxChoixHamburger);
		boxChoixAccompagnement.add(texteAccompagnement);
		boxMiseEnPageCommande.add(boxChoixAccompagnement);
		boxChoixBoisson.add(texteBoisson);
		boxMiseEnPageCommande.add(boxChoixBoisson);
		boxMiseEnPageNumeroCommande.add(numeroCommande);
		boxMiseEnPageNumeroCommande.add(texteNumeroCommandeTitre);
		boxMiseEnPageNumeroCommande.add(Box.createRigidArea(new Dimension(0, 30)));
		numeroCommande.setFont(policeParagraphe);
		boxMiseEnPageNumeroCommande.add(numeroCommande);
		this.add(boxMiseEnPageNumeroCommande);

		validerCommande.setText("Valider");
		validerCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numHamburger != 0 && numAccompagnement != 0 && numBoisson != 0) {
					validationCartePayement();
				}
			}
		});
		
		boxValiderChoix.add(validerCommande);
		boxMiseEnPageCommande.add(boxValiderChoix);
		this.add(boxMiseEnPageCommande);
	}
	
	// Méthode du cas commander
	public void commander(int numClient) {
		boxMiseEnPageCommande.setVisible(true);
		boxMiseEnPageNumeroCommande.setVisible(false);
		this.numClient = numClient;
		boolean clientConnecte = this.controlCommande.verifierIdentification(numClient);
		if (clientConnecte) {
			this.affichageMenu();
			this.selectionMenu();
		}
	}

	private void affichageMenu() {
		List<String> listeHamburger = controlCommande.getListHamburger();
		final JComboBox<String> comboBoxHamburger = new JComboBox<>();
		comboBoxHamburger.addItem("");
		for (int i = 0; i < listeHamburger.size(); i++) {
			comboBoxHamburger.addItem(listeHamburger.get(i));
		}
		comboBoxHamburger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numHamburger = comboBoxHamburger.getSelectedIndex();
			}
		});
		this.boxChoixHamburger.add(Box.createRigidArea(new Dimension(10, 0)));
		boxChoixHamburger.add(comboBoxHamburger);

		List<String> listeAccompagnement = controlCommande.getListAccompagnement();
		final JComboBox<String> comboBoxAccompagnement = new JComboBox<>();
		comboBoxAccompagnement.addItem("");
		for (int i = 0; i < listeAccompagnement.size(); i++) {
			comboBoxAccompagnement.addItem(listeAccompagnement.get(i));
		}
		comboBoxAccompagnement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numHamburger = comboBoxAccompagnement.getSelectedIndex();
			}
		});
		this.boxChoixAccompagnement.add(Box.createRigidArea(new Dimension(10, 0)));
		boxChoixAccompagnement.add(comboBoxAccompagnement);

		List<String> listeBoisson = controlCommande.getListBoisson();
		final JComboBox<String> comboBoxBoisson = new JComboBox<>();
		comboBoxBoisson.addItem("");
		for (int i = 0; i < listeBoisson.size(); i++) {
			comboBoxBoisson.addItem(listeBoisson.get(i));
		}
		comboBoxBoisson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numHamburger = comboBoxBoisson.getSelectedIndex();
			}
		});
		this.boxChoixBoisson.add(Box.createRigidArea(new Dimension(10, 0)));
		boxChoixBoisson.add(comboBoxBoisson);

	}

	private void selectionMenu() {
		boxMiseEnPageCommande.setVisible(true);
		this.setVisible(true);
		repaint();
	}

	private void validationCartePayement() {
		boolean carteRenseignee = controlCommande.verificationCB(numClient);
		if (!carteRenseignee) {
			boxMiseEnPageCommande.setVisible(false);
			panEnregistrerCoordonneesBancaires.setVisible(true);
			this.repaint();
			panEnregistrerCoordonneesBancaires.enregistrerCoordonneesBancaires(numClient, this);
		} else
			this.enregistrerCommande(carteRenseignee);
	}

	private void enregistrerCommande(boolean carteRenseignee) {
		if (carteRenseignee) {
			int numCommande = controlCommande.enregistrerCommande(numClient, numHamburger-1, numAccompagnement-1,
					numBoisson-1);
			numeroCommande.setText("Votre numero est : " + numCommande);
		}
		this.setVisible(true);
		boxMiseEnPageCommande.setVisible(false);
		boxMiseEnPageNumeroCommande.setVisible(true);
		this.repaint();
	}
	
	@Override
	public void retourEnregistrerCoordonneesBancaire(boolean carteValide) {
		this.panEnregistrerCoordonneesBancaires.setVisible(false);
		if (carteValide)
			this.enregistrerCommande(carteValide);
	}

}
