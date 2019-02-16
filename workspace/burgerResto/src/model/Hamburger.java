package model;

public class Hamburger extends Aliment {

	public Hamburger(String nom) {
		super(nom);
	}

	@Override
	public String toString() {
		return "Hamburger [nom=" + nom + "]";
	}
	
}
