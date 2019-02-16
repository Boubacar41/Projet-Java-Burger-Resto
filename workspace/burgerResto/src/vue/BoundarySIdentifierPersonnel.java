package vue;

import control.ControlSIdentifier;
import model.ProfilUtilisateur;

public class BoundarySIdentifierPersonnel {

	// controleur du cas
	ControlSIdentifier controlSIdentifier=new ControlSIdentifier();
	//clavier
	Clavier clavier = new Clavier();
	
	// methode principale du cas
	public int identifierPersonnel() {
		System.out.println("Veuillez entrer votre login");
		String login = clavier.entrerClavierString();
		System.out.println("Veuillez entrer votre mot de passe");
		String mdp = clavier.entrerClavierString();
		return controlSIdentifier.sIdentifier(ProfilUtilisateur.PERSONNEL,
		login, mdp);
		}
	
	
}
