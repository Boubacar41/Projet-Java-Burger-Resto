package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import control.ControlEnregistrerCoordonneesBancaires;

public class PanEnregistrerCoordonneesBancaire extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// contrôleurs du cas
	protected ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires;

	// les attributs métiers
	private int numClient;

	// polices d'écritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);
	private Font policeAremplacer = new Font("Arial", Font.ITALIC, 12);
	private Font policeChoixUtilisateur = new Font("Arial", Font.TRUETYPE_FONT, 12);

	// Mise en page : les Box
	private Box boxMiseEnPageCoordonneesBancaires = Box.createVerticalBox();
	private Box boxNumeroCarte = Box.createHorizontalBox();
	private Box boxValiditeCarte = Box.createHorizontalBox();
	private Box boxValiderCarte = Box.createHorizontalBox();

	// Les zones de texte
	private TextArea textAeraNumeroCarte = new TextArea();
	private TextArea textAreaDateExpiration = new TextArea();

	private IUseEnregistrerCoordonneesBancaires panAppelant;

	// constructeur
	public PanEnregistrerCoordonneesBancaire(
			ControlEnregistrerCoordonneesBancaires controlEnregistrerCoordonneesBancaires) {
		this.controlEnregistrerCoordonneesBancaires = controlEnregistrerCoordonneesBancaires;
	}
	
	//Méthode d’initialisation du panel
	public void initialisation() {
		// mise en forme du panel
		setBackground(Color.yellow);
		
		// création des différents éléments graphiques
		JLabel texteCoordonneesBancaires = new JLabel("Entrer vos coordonnées bancaires");
		texteCoordonneesBancaires.setFont(policeTitre);
		boxMiseEnPageCoordonneesBancaires.add(texteCoordonneesBancaires);
		JLabel texteNumeroCarteBancaire = new JLabel("Entrer le numéro de votre carte bancaire");
		texteNumeroCarteBancaire.setFont(policeParagraphe);
		boxMiseEnPageCoordonneesBancaires.add(texteNumeroCarteBancaire);
		JLabel texteValiditeCarte = new JLabel("Entrer la date d’expiration de votre carte bancaire");
		texteValiditeCarte.setFont(policeParagraphe);
		boxMiseEnPageCoordonneesBancaires.add(texteValiditeCarte);

		JButton validationCoordonneeBancaire = new JButton();
		validationCoordonneeBancaire.setText("Valider");
		validationCoordonneeBancaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroCarte = Integer.valueOf(textAeraNumeroCarte.getText());
				int dateCarte = Integer.valueOf(textAreaDateExpiration.getText());
				traitementCoordonneesBancaires(numeroCarte, dateCarte);
			}
		});
		boxValiditeCarte.add(validationCoordonneeBancaire);
		boxMiseEnPageCoordonneesBancaires.add(boxNumeroCarte);
		boxMiseEnPageCoordonneesBancaires.add(boxValiditeCarte);
		boxMiseEnPageCoordonneesBancaires.add(boxValiderCarte);
		this.add(boxMiseEnPageCoordonneesBancaires);
		boxMiseEnPageCoordonneesBancaires.setVisible(true);
		this.setVisible(false);

		textAeraNumeroCarte.setMaximumSize(new Dimension(120, 20));
		boxNumeroCarte.add(textAeraNumeroCarte);
		textAreaDateExpiration.setForeground(Color.gray);
		textAreaDateExpiration.setMaximumSize(new Dimension(60, 20));
		textAreaDateExpiration.setFont(policeAremplacer);

		textAreaDateExpiration.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
				textAreaDateExpiration.setText(null);
				textAreaDateExpiration.setFont(policeChoixUtilisateur);
				textAreaDateExpiration.setForeground(Color.black);
			}
		});

		boxValiditeCarte.add(textAreaDateExpiration);

	}
	
//methode du cas enregistrerCoordonnées bancaires
	public void enregistrerCoordonneesBancaires(int numClient, IUseEnregistrerCoordonneesBancaires panAppelant) {
		this.numClient = numClient;
		this.panAppelant = panAppelant;
		textAeraNumeroCarte.setText("");
		textAreaDateExpiration.setText("MMAA");
		this.setVisible(true);
		this.repaint();
	}

	// Méthodes privées traitement coordonnees bancaires
	private void traitementCoordonneesBancaires(int numeroCarte, int dateCarte) {
		boolean carteValide = controlEnregistrerCoordonneesBancaires.enregistrerCordonneesBancaires(numClient,
				numeroCarte, dateCarte);
		panAppelant.retourEnregistrerCoordonneesBancaire(carteValide);
	}
}
