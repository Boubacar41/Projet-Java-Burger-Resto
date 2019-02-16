package model;

public class Boisson extends Aliment {

	//constructeur
	public Boisson(String nom) {
		super(nom);
	}

	@Override
	public String toString() {
		return "Boisson [nom=" + nom + "]";
	}

}
