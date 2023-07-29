package fichier_graph;

import java.awt.Desktop;
import java.io.*;
import color.*;
import graph.*;

public class CreerFichierGraph{
    private String nomDuFichier;
    private Graph graph;
    private String chemin;
    private Desktop desktop;
    private Color color;

    public CreerFichierGraph(String nomDuFichier, Graph graph, String chemin) throws IOException{
        this.nomDuFichier = nomDuFichier;
        this.graph = graph;
        this.chemin = chemin;
        this.desktop = Desktop.getDesktop();
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme, on créé un fichier binaire et on y écrit le graph à l'intérieur.
    */
    private void init() throws IOException{
        // Si la platforme est supporté
        if (this.verifiePlatformeSupporte() == true){
            // http://www.codeurjava.com/2015/09/sauvegarder-un-arraylist-dans-un-fichier-en-java.html
            try {
                FileOutputStream fichierSortie = new FileOutputStream(this.chemin + this.nomDuFichier);
                ObjectOutputStream sortie = new ObjectOutputStream(fichierSortie);
                sortie.writeObject(this.graph);
                sortie.close();
                fichierSortie.close();
                System.out.println("\nCréation du fichier binaire terminé avec succès...\n");
            } 

            catch (FileNotFoundException e) {
                System.out.println("\n" + color.RED_COLOR + "Le fichier n'a pas était retrouvée !!!" + color.RESET_COLOR + "\n");
            }

            catch (IOException e) {
                System.out.println("\n" + color.RED_COLOR + "Une erreur est survenue !!!" + color.RESET_COLOR + "\n");
            }
        }
    }

    /** 
        * Cette méthode permet de vérifier si la plateforme est bien supporté par le programme
        * @return true si c'est compatible, false sinon.
    */
    private boolean verifiePlatformeSupporte(){
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("\n" + color.RED_COLOR + "Plateforme non supportée !!!" + color.RESET_COLOR + "\n");
            return false;
        }
        return true;
    }



}