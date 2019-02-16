package model;

public class Personnel extends Profil {

	//un personnel peut être gerant ou non
	public Boolean gerant=false;
	
	//constructeur
	public Personnel(String nom, String prenom, String mdp) {
		super(nom, prenom, mdp);
	}
	
	//methode de definition de gerant
	public void definirGerant()
	{
		this.gerant=true;
	}

	
	public String toString() {  return "Personnel [gerant=" + gerant +", "+ super.toString() + "]"; }

	public Boolean getGerant() {
		return gerant;
	}

	


}
