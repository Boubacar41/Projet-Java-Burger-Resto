package vue;

import java.util.Scanner;

//classe pour les methodes de saisi au clavier
public class Clavier {
	private Scanner scanner = new Scanner(System.in);
	
	//saisi d'entier
	public int entrerClavierInt() {
		return this.scanner.nextInt();
	}
	//saisi de chaine de caractères
	public String entrerClavierString() {
		return this.scanner.next();
	}

}
