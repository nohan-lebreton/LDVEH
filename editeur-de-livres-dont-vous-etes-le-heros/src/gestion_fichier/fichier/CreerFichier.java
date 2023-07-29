package fichier;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.awt.Desktop;
import color.*;

public class CreerFichier {
	private String numeroFichier;
	private String chemin;
	private String contenu;
	private Integer numeroFichierInt;
	private Desktop desktop;
	private File file;
	private Color color;

	/**
        * Ce constructeur permet de créer un fichier
        * @param chemin correspond à l'emplacement de sauvegarde du fichier
        * @param contenu correspond au contenu du fichier
        * @param numeroFichierInt correspond au numéro du fichier
    */
	public CreerFichier(String chemin, String contenu, Integer numeroFichierInt) throws IOException{
		this.chemin = chemin;
		this.contenu = contenu;
		this.numeroFichierInt = numeroFichierInt;
		this.init();
	}

	/** 
        * Cette méthode permet de lancer le programme
    */
	private void init() throws IOException{
		int numFile;
		
		if (!(this.numeroFichierInt == null)){
			numFile = this.numeroFichierInt;
		}
		else{
			// Nom du fichier
			numFile = this.creerNumeroFichierOrdonne();
			this.numeroFichier += numFile;
			//System.out.println("Creation d'un fichier ");
		}

		// On crée une chaîne de caractères contenant le chamin du fichier et le nom
		String name_fichier = String.format(this.chemin + "/#%2d.txt", numFile);
		
		// Ici on crée le fichier s'il n'existe pas, et on écrit à l'intérieur
		CreerPartieFichier partie_fichier = new CreerPartieFichier(name_fichier);
		this.ecrireDansLeFichier(partie_fichier);
		this.file = partie_fichier.getFile();
	}

	/** 
		* Cette méthode a pour but que les nouveaux fichiers ont des noms qui se suivent
		* @return le numéro suivant
	*/
	private int creerNumeroFichierOrdonne(){
		int x = 1;
		File folder = new File(this.chemin);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
				x++;
		    }
		}
		return x;
	}

	/** 
		* Cette méthode a pour but d'écrire dans le fichier
		* @param file correspond à un fichier contenu dans la classe CreerPartieFichier
	*/
	private void ecrireDansLeFichier(CreerPartieFichier file) throws IOException{
		try{
            // On défini l'encodage
            Charset utf8 = StandardCharsets.UTF_8;
            
            // Ecrire dans le fichier
            FileWriter fw = new FileWriter(file.getFile().getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.contenu);
            bw.close();
        }

        catch (Exception IOException){
			System.out.println("\n" + Color.RED_COLOR +"Une erreur est survenue." + Color.RESET_COLOR + "\n");
        }
	}

	/** 
		* Cette méthode a pour but d'ouvrir un fichier
	*/
	public void ouvrirFichier() throws IOException{
		this.desktop = Desktop.getDesktop();
		if(this.file.exists()){
          desktop.open(this.file);
        }
	}

	/** 
		* Cet accesseur permet d'obtenir le chemin du fichier
		* @return le chemin
	*/
	public String getChemin(){
		return this.chemin;
	}

	/** 
		* Cet accesseur permet d'obtenir le numéro de fichier
		* @return le numéro
	*/
	public Integer getNumeroFichierInt(){
		return this.numeroFichierInt;
	}

	/** 
		* Cet accesseur permet d'obtenir le fichier
		* @return le fichier
	*/
	public File getFile(){
		return this.file;
	}
}
