package model;

import java.util.HashMap;
import java.util.Map;

//classe singleton
public class BDclient {

	private BDclient() {
	}

	private static class BDclientHolder {
		private static final BDclient instance = new BDclient();
	}

	public static BDclient getInstance() {
		return BDclientHolder.instance;
	}

	// liste de clients
	private Map<Integer, Client> listClient = new HashMap<Integer, Client>();

	// methode d'ajout de client à la liste
	public void ajouterClient(Client client) {
		int i = this.listClient.size();
		this.listClient.put(i, client);
	}

	// methode pour visualiser la liste des clients
	public String toString() {
		return "BDclient [listClient=" + listClient + "]";
	}

	// methode de connexion de client
	public int connecterClient(String login, String mdp) {
		int numConnexion = -1;
		for (Map.Entry<Integer, Client> entry : listClient.entrySet()) {
			Integer numClient = entry.getKey();
			Client client = entry.getValue();
			if (client.selectionnerProfil(login, mdp)) {
				client.connecterProfil();
				numConnexion = numClient;
			}
		}
		return numConnexion;
	}

	// getter sur client de la bDClient
	public Client getClient(int numeroClient) {
		Client c = listClient.get(numeroClient);
		return c;

	}

}
