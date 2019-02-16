package model;

public class CarteBancaire {
	// declaration des attributs
	private int numeroCarte;
	private int dateCarte;

	//constructeur vide
	public CarteBancaire() {
		super();
	}

	//constructeur surchargé
	public CarteBancaire(int numeroCarte, int dateCarte) {
		this.numeroCarte = numeroCarte;
		this.dateCarte = dateCarte;
	}
}
