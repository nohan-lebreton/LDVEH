package fichier;

import java.io.*;
import java.util.ArrayList;

public class LireAfficherFichier{
	private BufferedReader buffered;
	private Integer numero;
	private String chemin;
	private String name_fichier;

	/**
        * Ce constructeur permet d'instancier un objet LireAfficherFichier
        * @param chemin correspond au chemin du fichier
        * @param numero correspond au numéro du fichier
    */
	public LireAfficherFichier(String chemin, Integer numero) throws IOException{
		this.numero = numero;
		this.chemin = chemin;
		// On crée une chaîne de caractères contenant le chamin du fichier et le nom
		this.name_fichier = String.format(this.chemin + "#%2d.txt", this.numero);
    }
	
	/** 
		* Cette méthode permet d'afficher le contenu du fichier dans la console
	*/
	public void afficheContenuFichier() throws IOException{
		// On lit le fichier
		this.buffered = new BufferedReader(new FileReader(this.name_fichier));

		String line;
		// On parcourt chaque lignes
		while ((line = this.buffered.readLine()) != null){
			// Afficher le contenu du fichier
			System.out.println(line);
		}
		this.buffered.close();
	}

	/** 
		* Cette méthode permet de prendre chaque ligne d'un fichier et les mets dans une liste
		* @return une liste de string
	*/
	public ArrayList<String> creerArrayListString() throws IOException{
		ArrayList<String> listeString = new ArrayList<>();

		// On lit le fichier
		this.buffered = new BufferedReader(new FileReader(this.name_fichier));

		String line;
		// On parcourt chaque lignes
		while ((line = this.buffered.readLine()) != null){
			// On ajoute chaque ligne dans la liste
			listeString.add(line);
		}
		this.buffered.close();
		return listeString;
	}
}
