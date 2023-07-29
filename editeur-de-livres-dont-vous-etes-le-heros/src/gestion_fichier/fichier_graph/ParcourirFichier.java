package fichier_graph;

import java.util.ArrayList;
import java.io.*;

public class ParcourirFichier{
    private String chemin;
    private int nbFichier;
    private ArrayList<String> listNomFichier;

    /**
        * Ce constructeur permet d'instancier un objet ParcourirFichier
        * @param chemin correspond au nom du chemin
    */
    public ParcourirFichier(String chemin){
        this.chemin = chemin;
        this.nbFichier = 0;
        this.listNomFichier = new ArrayList<>();
        this.init();
    }


    /**
        * Cette méthode permet de lancer le programme, on parcourt les fichier présent dans le chemin. 
    */
    private void init(){

		File folder = new File(this.chemin);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
				this.nbFichier++;
                this.listNomFichier.add(file.getName());
		    }
		}
    }


    /** 
		* Cet accesseur permet d'obtenir le nombre de fichier
		* @return le nombre de fichier
	*/
    public int getNbFichier(){
        return this.nbFichier;
    }

    /** 
		* Cet accesseur permet d'obtenir la liste des noms de fichier
		* @return la liste des nom de fichier
	*/
    public ArrayList<String> getListNomFichier(){
        return this.listNomFichier;
    }

}