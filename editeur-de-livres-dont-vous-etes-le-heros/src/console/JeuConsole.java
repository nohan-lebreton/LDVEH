package console;

import color.*;
import java.util.Scanner;
import fichier_graph.*;
import graph.*;

public class JeuConsole{
    private Color color;
    private String chemin;
    private ParcourirFichier nbFichier;

    /**
        * Ce constructeur permet de lancer le jeu en mode console
    **/
    public JeuConsole(){
        this.chemin = "./assets/sauvegarde_graph/GRAPH/";
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme
    */
    private void init(){
        
        int choix = 0;
        boolean valide = false;

        while (!(valide == true)){

            boolean valideChoix = false;
            while(!(valideChoix == true)){
                try{
                    System.out.println("Choisissez : \nJouer (1) \nQuitter (2)");
                    Scanner scanChoix = new Scanner(System.in);
                    choix = scanChoix.nextInt();
                    System.out.println();
                    if (choix > 0 && choix <= 2){
                        valideChoix = true;
                    }
                }

                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }

            // jouer
            if (choix == 1){
                this.jouer();

            }


            // Quitter
            else if (choix == 2){
                // on demande à l'utilisateur s'il est sûr de vouloir quitter
                valideChoix = false;
                int choixQuitter = 0;

                while(!(valideChoix == true)){
                    try{
                        System.out.println("Voulez-vous quitter Oui (1) ou Non (2) ?");
                        Scanner scanQuitter = new Scanner(System.in);
                        choixQuitter = scanQuitter.nextInt();
                        System.out.println("");

                        // Oui, on quitte
                        if (choixQuitter == 1){
                            valideChoix = true;
                            valide = true;
                            
                        }
                        // non, on fait rien
                        else if (choixQuitter == 2){
                            System.out.println("Quitter a échouée !!!");
                            valideChoix = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }

            }


        }
        
    }

    // Initialisation du jeu
    private void jouer(){
        ChargerFichierGraph fichier_charge = this.ChargementFichier();
        if (!(fichier_charge == null)){
            Graph graph = this.obtenirGraphDuFichier(fichier_charge);
            this.lancement(graph);
        }
    }

    // Demande à l'utilisateur quel fichier qu'il faut charger
    private ChargerFichierGraph ChargementFichier(){
        
        // On parcourt les fichiers existants, pour récupérer le nombre de fichier, et une liste des noms de fichier présent
        this.nbFichier = new ParcourirFichier(this.chemin);

        boolean valide = false;

        // Si on possède plusieurs fichiers
        if (!(this.nbFichier.getNbFichier() == 0)){
            Scanner scanChoix = new Scanner(System.in);
            String choix = null;


            while (!(valide == true)){
                // On choisit un projet
                scanChoix = new Scanner(System.in);
                System.out.println("Veuillez choisir une histoire pour commencer ? ");
                System.out.println("" + this.nbFichier.getListNomFichier());
                choix = scanChoix.nextLine();
                System.out.println();

                // si c le bon nom de fichier alors on sort de la boucle
                if(this.nbFichier.getListNomFichier().contains(choix)){
                    valide = true;
                }
            }
            
            valide = false;
            int choixProjet = 0;

            while(!(valide == true)){
                try{
                    System.out.println("Voulez-vous continuer Oui (1) ou Non (2) ?");
                    Scanner scanProjet = new Scanner(System.in);
                    choixProjet = scanProjet.nextInt();
                    System.out.println("");

                    // si Oui
                    if (choixProjet == 1){
                        ChargerFichierGraph fichierCharge = new ChargerFichierGraph(choix, this.chemin);
                        return fichierCharge;
                    }

                    // non, on fait rien
                    else if (choixProjet == 2){
                        System.out.println("Le chargement de l'histoire a été annulé !!!\n");
                        valide = true;
                    }
                }

                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }
        }

        else{
            System.out.println("Il n'y a pas d'histoire !!!\n");
        }

        return null;
    }

    // Comme son nom l'indique, on souhaite récupérer la structure graph qui se trouve dans un fichier
    private Graph obtenirGraphDuFichier(ChargerFichierGraph fichier_charge){
        Graph graph = fichier_charge.getGraph();
        return graph;
    }

    private void lancement(Graph graph){
        System.out.println("\n" + "³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³" + "\n\n");

        System.out.println(color.RED_COLOR + "Bienvenu, le but du livre dont vous êtes le héros c'est de le parcourir en choisissant le ou les bon chemins." + color.RESET_COLOR + "\n");
        
        System.out.println("\n" + "³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³³" + "\n\n");

        Noeud pointeur = graph.getRacine();

        // On affiche le paragraphe
        System.out.println(color.BLUE_COLOR + pointeur.getParagraphe() + color.RESET_COLOR + "\n");

        while (!(pointeur == null)){

            // On sauvegarde les solutions dans une variable pour les afficher plus tard
            String solutionsEnfants = "";
            for(int i = 0; i < pointeur.getEnfants().size(); i++){
                solutionsEnfants += pointeur.getEnfants().get(i).getSolution() + " (" + i + "), ";
            }

            solutionsEnfants += "?";

            Scanner scanChoix = new Scanner(System.in);
            boolean valide = false;
            int choixSolution = 0;

            while(!(valide == true)){
                try{
                    System.out.println(color.YELLOW_COLOR + solutionsEnfants + color.RESET_COLOR);
                    System.out.print("Choisissez une solution : ");
                    choixSolution = scanChoix.nextInt();
                    System.out.println("\n");
                    
                    if (choixSolution >= 0 && choixSolution < pointeur.getEnfants().size()){
                        valide = true;
                    }

                }

                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }
            
            pointeur = pointeur.getEnfants().get(choixSolution);
            
            // On affiche le paragraphe
            System.out.println(color.BLUE_COLOR + pointeur.getParagraphe() + color.RESET_COLOR + "\n");

            // si on a pris une direction et que le noeud ne possède pas d'enfant alors on arrête le prog
            if (pointeur.getEnfants().size() == 0){
                if (pointeur.getGagneOuPerdre() == true){
                    System.out.println("Félicitation vous avez gagné !!!");
                }
                else{
                    System.out.println("Vous êtes nul !!!");
                }
                pointeur = null;
            }
        }

        

    }

}
