package vueGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlRetirerCommandeBorne;

public class PanRetirerCommandeBorne extends JPanel {
	private static final long serialVersionUID = 1L;

	// controleurs du cas
	private ControlRetirerCommandeBorne controlRetirerCommandeBorne;

	// Les elements graphiques :
	// polices d'ecritures
	private Font policeTitre = new Font("Calibri", Font.BOLD, 24);
	private Font policeParagraphe = new Font("Calibri", Font.HANGING_BASELINE, 16);
	private Font policeChoixUtilisateur = new Font("Arial", Font.TRUETYPE_FONT, 12);

	// Elements graphique
	private TextArea textAeraNumeroCommande = new TextArea();
	JLabel reponse;

	// Mise en page : les Box
	private Box boxMiseEnpage = Box.createVerticalBox();
	private Box boxNumeroCommande = Box.createHorizontalBox();
	private Box boxReponse = Box.createHorizontalBox();
	private Box boxSuite = Box.createHorizontalBox();

	public PanRetirerCommandeBorne(ControlRetirerCommandeBorne controlRetirerCommandeBorne) {
		this.controlRetirerCommandeBorne = controlRetirerCommandeBorne;
	}

	// Methode d'initialisation du panel
	public void initialisation() {
		// mise en forme du panel (couleur, ...)
		this.setBackground(Color.RED);
		// creation des differents elements graphiques (JLabel, Combobox,
		// Button, TextAera ...)

		JLabel titre = new JLabel("Bienvenue à BurgerResto");
		titre.setFont(policeTitre);

		textAeraNumeroCommande.setMaximumSize(new Dimension(120, 20));
		textAeraNumeroCommande.setFont(policeChoixUtilisateur);

		JLabel label = new JLabel("Entrer votre numero de commande : ");
		label.setFont(policeParagraphe);
		

		reponse = new JLabel();
		reponse.setFont(policeParagraphe);

		
		JButton retourMenu = new JButton();
		JButton validationNumeroCommande = new JButton();
		
		
		retourMenu.setText("RetourMenu");
		retourMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAeraNumeroCommande.setText(null);
				reponse.setText("");
				textAeraNumeroCommande.setEditable(true);
				validationNumeroCommande.setVisible(true);
				retourMenu.setVisible(false);
			}
		});
		retourMenu.setVisible(false);
		
		
		validationNumeroCommande.setText("Valider");
		validationNumeroCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroCommande = Integer.parseInt(textAeraNumeroCommande.getText());
				retirerCommandeBorne(numeroCommande);
				retourMenu.setVisible(true);
				textAeraNumeroCommande.setEditable(false);
				validationNumeroCommande.setVisible(false);
			}
		});


		// mise en page : placements des differents elements graphiques dans des
		// Box
		boxNumeroCommande.add(label);
		boxNumeroCommande.add(Box.createRigidArea(new Dimension(10, 0)));
		boxNumeroCommande.add(textAeraNumeroCommande);
		
		boxReponse.add(reponse);
		boxSuite.add(retourMenu);
		
		// mise en page : placements des differentes box dans une box principale
		boxMiseEnpage.add(titre);
		boxMiseEnpage.add(Box.createRigidArea(new Dimension(0, 15)));
		boxMiseEnpage.add(boxNumeroCommande);
		boxMiseEnpage.add(Box.createRigidArea(new Dimension(0, 15)));
		boxMiseEnpage.add(validationNumeroCommande);
		boxMiseEnpage.add(Box.createRigidArea(new Dimension(0, 15)));
		boxMiseEnpage.add(boxReponse);
		boxMiseEnpage.add(Box.createRigidArea(new Dimension(0, 15)));
		boxMiseEnpage.add(retourMenu);
		// mise en page : ajout de la box principale dans le panel
		this.add(boxMiseEnpage);
	}

	public void retirerCommandeBorne(int numeroCommande) {
		boolean commandeTrouvee = controlRetirerCommandeBorne.retirerCommandeBorne(numeroCommande);
		if(commandeTrouvee){
		reponse.setText("Vous pouvez récupérer votre commande au comptoir");
		}
		else {
			reponse.setText("Numéro de commande inexistant");
		}
	}

}
