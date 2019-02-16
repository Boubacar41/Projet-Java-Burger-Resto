package model;

public class Commande {
	// declaration des attributs
	private static int numeroCommande = 0;
	private int numClient;
	private int numeroCommandeAttribuee;
	private Hamburger hamburger;
	private Accompagnement accompagnement;
	private Boisson boisson;
	Menu menu = Menu.getInstance();

	// constructeur
	public Commande(Hamburger hamburger, Accompagnement accompagnement, Boisson boisson) {
		this.hamburger = hamburger;
		this.accompagnement = accompagnement;
		this.boisson = boisson;
		numeroCommande++;
		numeroCommandeAttribuee = numeroCommande;
	}

	// constructeur surchargé
	public Commande(int numClient, Hamburger hamburger, Accompagnement accompagnement, Boisson boisson) {
		super();
		this.numClient = numClient;
		this.hamburger = hamburger;
		this.accompagnement = accompagnement;
		this.boisson = boisson;
		numeroCommande++;
		numeroCommandeAttribuee = numeroCommande;
	}

	//methode d'initialisation des numeros de commande
	public static void initialiseNumeroCommande() {
		numeroCommande = 0;
	}

	public String toString() {
		return "Commande [numClient=" + numClient + ", hamburger=" + hamburger + ", boisson=" + boisson
				+ ", accompagnement=" + accompagnement + "]\n";
	}
	
	//getters de certains attributs 
	public int getNumeroCommandeAttribuee() {
		return numeroCommandeAttribuee;
	}

	public Hamburger getHamburger() {
		return hamburger;
	}

	public Accompagnement getAccompagnement() {
		return accompagnement;
	}

	public Boisson getBoisson() {
		return boisson;
	}

	public int getNumClient() {
		return numClient;
	}

	public int prixTotal() {
		int montant = menu.getPRIX_MENU();
		return montant;
	}

}
