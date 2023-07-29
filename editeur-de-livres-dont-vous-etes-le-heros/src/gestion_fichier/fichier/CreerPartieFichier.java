package fichier;

import java.awt.Desktop;
import java.io.*;
import color.*;

public class CreerPartieFichier {
    private File file;
    private String name;
    private Desktop desktop;
    private Color color;

    /**
        * Ce constructeur permet d'instancier un objet CreerPartieFichier
        * @param name correspond au nom du fichier
    */
    public CreerPartieFichier(String name) throws IOException {
        this.name = name;
        this.desktop = Desktop.getDesktop();
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme
    */
    private void init() throws IOException{
        // Si la platforme est supporté
        if (this.verifiePlatformeSupporte() == true){
            // On crée le fichier
            this.file = new File(this.name);
            if (this.file.createNewFile()){
                System.out.println("Le fichier est créé.");
            }
            else{
                System.out.println(color.ORANGE_COLOR + "Le fichier existe déjà, remplacement en cours." + color.RESET_COLOR);
            }
            System.out.println("");
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

    /** 
		* Cet accesseur permet d'obtenir le fichier
		* @return le fichier
	*/
    public File getFile(){
        return this.file;
    }

}
