package model;

public abstract class Profil {

	// declaration des attributs
	protected String nom;
	protected String prenom;
	protected String login;
	protected String mdp;
	protected Boolean connecte = false;

	// constructeur
	public Profil(String nom, String prenom, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.login = prenom + "." + nom;
	}

	public String toString() {
		return "nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", mdp=" + mdp + ", connecte=" + connecte;
	}

	// methode de selection de profil
	public boolean selectionnerProfil(String login, String mdp) {
		boolean isClient = (this.login.equals(login) && this.mdp.equals(mdp));
		return isClient;
	}

	// methode de connexion du profil
	public void connecterProfil() {
		this.connecte = true;
	}

	public Boolean getConnecte() {
		return connecte;
	}

}
