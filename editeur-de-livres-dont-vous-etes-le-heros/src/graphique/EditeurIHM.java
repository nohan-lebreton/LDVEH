package graphique;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import color.*;
import editeur.*;
import fichier.*;
import fichier_graph.*;
import graph.*;

public class EditeurIHM{
    private Color color;
    private String chemin_graphs;
    private ParcourirFichier nbFichier;

    /**
        * Ce constructeur permet de lancer l'éditeur en mode console
    */
    
    public EditeurIHM(){
        this.chemin_graphs = "./assets/sauvegarde_graph/GRAPH/";
        this.init();
    }

    /**
        * Cette méthode permet de lancer le programme
    */
    private void init() {
	
	Menu MIHM = new Menu();
	
        int choixProjet = 99;
        boolean valide = false;

        while(!(choixProjet == 0)){
            // Parcourir les fichiers existants, pour récupérer le nombre de fichier, et une liste des noms de fichier présent
            this.nbFichier = new ParcourirFichier(this.chemin_graphs);
	}
   }
 
   
}
