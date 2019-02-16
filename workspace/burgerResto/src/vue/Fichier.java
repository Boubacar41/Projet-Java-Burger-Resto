package vue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fichier {

	// methode d'ecriture dans le fichier ecranCuisinier.txt
	public static void ecrire(String texte) {
		try {
			File f = new File("./ecranCuisinier.txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f, true);
			fw.write(texte + "\n");
			fw.flush();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// methode d'ecriture dans le fichier ecranCuisinierCommandeSurPlace.txt
	public static void ecrireSurPlace(String texte) {
		try {
			File f = new File("./ecranCuisinierCommandeSurPlace.txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f, true);
			fw.write(texte + "\n");
			fw.flush();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// methode pour vider le fichier ecranCuisinier.txt
	public static void effacer() {
		try {
			File f = new File("./ecranCuisinier.txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f, false);
			fw.write("");
			fw.flush();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// methode de suppression d'une ligne dans un fichier texte
	public static boolean deleteLine(String fileName, int lineNumber) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

			StringBuffer sb = new StringBuffer();
			String line;
			int nbLinesRead = 0;
			while ((line = reader.readLine()) != null) {
				if (nbLinesRead != lineNumber) {
					sb.append(line + "\n");
				}
				nbLinesRead++;
			}
			reader.close();
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(sb.toString());
			out.close();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * public static boolean deleteLines(String fileName, int lineNumber) { try {
	 * BufferedReader reader = new BufferedReader(new InputStreamReader(new
	 * FileInputStream(fileName)));
	 * 
	 * StringBuffer sb = new StringBuffer(); String line; int nbLinesRead = 0; while
	 * ((line = reader.readLine()) != null) { if (nbLinesRead != lineNumber) {
	 * sb.append(line + "\n"); } nbLinesRead++; } reader.close(); BufferedWriter out
	 * = new BufferedWriter(new FileWriter(fileName)); out.write(sb.toString());
	 * out.close();
	 * 
	 * } catch (Exception e) { return false; } return true; }
	 */

	//methode pour effacer une commande du fichier ecranCuisinier.txt
	public static void effacerCommande(int numeroCommande) {
		boolean delete = deleteLine("ecranCuisinier.txt", numeroCommande - 1);
	}

}
