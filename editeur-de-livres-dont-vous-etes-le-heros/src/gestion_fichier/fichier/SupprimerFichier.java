package fichier;

import java.io.*;

public class SupprimerFichier{
    private String nomDuFichier;
    private String chemin;

    /**
        * Ce constructeur permet de supprimer un fichier
        * @param nomDuFichier correspond au nom d'un fichier cible
        * @param chemin correspond à l'emplacement du fichier
    */
    public SupprimerFichier(String nomDuFichier, String chemin) throws IOException{
        this.nomDuFichier = nomDuFichier;
        this.chemin = chemin;
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme, ici c'est la suppression d'un fichier sélectionné.
    */
    public void init() throws IOException{
        // On sélectionne le fichier
        File fichier = new File(this.chemin + this.nomDuFichier);
        
        // On regarde si c'est possible de le supprimer
        if(fichier.delete()){
            System.out.println("Le fichier " + fichier.getName() + " a été supprimé.\n");
        }

        else{
            System.out.println("La suppression du fichier n'a pas aboutie !!!\n");
        }
    }
}