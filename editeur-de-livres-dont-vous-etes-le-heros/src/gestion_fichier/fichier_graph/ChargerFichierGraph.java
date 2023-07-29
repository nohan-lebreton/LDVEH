package fichier_graph;

import java.io.*;
import color.*;
import graph.*;

public class ChargerFichierGraph{
    private String nomDuFichier;
    private String chemin;
    private Graph graph;
    private Color color;

    /**
        * Ce constructeur permet d'instancier un objet ChargerFichierGraph
        * @param nomDuFichier correspond au nom du fichier
        * @param chemin correspond au nom du chemin
    */
    public ChargerFichierGraph(String nomDuFichier, String chemin) throws IOException{
        this.nomDuFichier = nomDuFichier;
        this.chemin = chemin;
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme, on charge le fichier binaire et à l'intérieur ce trouve la sauvegarde d'un projet précédent. On la met dans une varible de type Graph.
    */
    private void init() throws IOException{
        try {
            FileInputStream fichierEntree = new FileInputStream(this.chemin + this.nomDuFichier);
            ObjectInputStream entree = new ObjectInputStream(fichierEntree);
            this.graph = (Graph) entree.readObject();
            entree.close();
            fichierEntree.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("\n" + color.RED_COLOR + "Le fichier n'a pas était retrouvé !!!" + color.RESET_COLOR + "\n");
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("ici");
            System.out.println("\n" + color.RED_COLOR + "Une erreur est survenue !!!" + color.RESET_COLOR + "\n");
        }

        catch (ClassNotFoundException e) {
            System.out.println("\n" + color.RED_COLOR + "La classe n'a pas était retrouvée !!!" + color.RESET_COLOR + "\n");
        }
    }

    /** 
        * Cet accesseur permet d'obtenir le graph chargé
        * @return le graph
    */
    public Graph getGraph(){
        return this.graph;
    }

}