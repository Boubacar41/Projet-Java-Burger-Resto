package model;

public abstract class Aliment {
	// attribut nom
	protected String nom;

	// constructeur
	public Aliment(String nom) {
		this.nom = nom;
	}
 
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Aliment [nom=" + nom + "]";
	}

}
