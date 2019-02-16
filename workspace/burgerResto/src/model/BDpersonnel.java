package model;

import java.util.HashMap;
import java.util.Map;

//classe singleton
public class BDpersonnel {
	private BDpersonnel() {}
	
	private static class BDpersonnelHolder{
		private static final BDpersonnel instance = new BDpersonnel();
	}
	
	public static BDpersonnel getInstance() {
		return BDpersonnelHolder.instance;
	}

	// liste de personnels
	private Map<Integer, Personnel> listPersonnel = new HashMap<Integer, Personnel>();

	// methode d'ajout de personnel à la liste
	public void ajouterPersonnel(Personnel personnel) {
		int i = this.listPersonnel.size();
		this.listPersonnel.put(i, personnel);}

	// methode pour visualiser la liste des personnels
	public String toString() {
		return "BDpersonnel [listPersonnel=" + listPersonnel + "]";
	}
	
	// methode de connexion de personnel
	public int connecterPersonnel(String login, String mdp) {
		int numConnexion = -1;
		for (Map.Entry<Integer,Personnel> entry : listPersonnel.entrySet()) {
		Integer numPersonnel = entry.getKey();
		Personnel personnel = entry.getValue();
		if (personnel.selectionnerProfil(login, mdp)) {
		personnel.connecterProfil();
		numConnexion = numPersonnel;
		}
		}
		return numConnexion;
		}
	
	//getter sur personnel
	public Personnel getPersonnel(int numeroPersonnel) {
		Personnel p=listPersonnel.get(numeroPersonnel);
		return p;
		
	}
	
}
