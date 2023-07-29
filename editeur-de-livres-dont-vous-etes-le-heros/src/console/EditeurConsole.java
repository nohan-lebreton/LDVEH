package console;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import color.*;
import fichier.*;
import fichier_graph.*;
import graph.*;

public class EditeurConsole{
    private Color color;
    private String chemin_graphs;
    private ParcourirFichier nbFichier;

    /** 
        * Ce constructeur permet de lancer l'éditeur en mode console
    */
    public EditeurConsole() throws IOException{
        this.chemin_graphs = "./assets/sauvegarde_graph/GRAPH/";
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme
    */
    private void init() throws IOException{
        
        int choixProjet = 99;
        boolean valide = false;

        while(!(choixProjet == 0)){
            // Parcourir les fichiers existants, pour récupérer le nombre de fichier, et une liste des noms de fichier présent
            this.nbFichier = new ParcourirFichier(this.chemin_graphs);

            while(!(valide == true)){
                try{
                    //choix de la création ou du chargement d'un projet
                    System.out.println("Voulez vous créer un " + color.RED_COLOR + "nouveau projet (1)" + color.RESET_COLOR + " ou " + color.RED_COLOR + "charger un projet existant (2)" + color.RESET_COLOR + " ou " + color.RED_COLOR + "supprimer un projet existant (3)" + color.RESET_COLOR + " ? , vous avez " + color.BLUE_COLOR + " " + this.nbFichier.getNbFichier() + " projet existant." + color.RESET_COLOR);
                    Scanner scanChoix = new Scanner(System.in);
                    choixProjet = scanChoix.nextInt();
                    System.out.println("");
                    if (choixProjet > 0 && choixProjet <= 3){
                        valide = true;
                    }
                }
                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }
            
            // Créer nouveau projet
            if(choixProjet == 1){

                // on demande à l'utilisateur s'il est sûr de vouloir créer nouveau projet
                valide = false;
                int choixNouveauProjet = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la création d'un nouveau projet Oui (1) ou Non (2) ?");
                        Scanner scanNouveauProjet = new Scanner(System.in);
                        choixNouveauProjet = scanNouveauProjet.nextInt();
                        System.out.println("");

                        // Oui, on valide et on créer le nouveau projet
                        if (choixNouveauProjet == 1){

                            int choixCreation = 0;

                            while(!(valide == true)){
                                try{
                                    //choix du mode de création du graph
                                    System.out.println("Choisissez si vous voulez créer le " + color.RED_COLOR + "graphe automatiquement (1)" + color.RESET_COLOR + " ou " + color.RED_COLOR + "bien manuellement (2)" + color.RESET_COLOR);
                                    Scanner scanChoix = new Scanner(System.in);
                                    choixCreation = scanChoix.nextInt();
                                    System.out.println("");
                                    if (choixCreation == 1 || choixCreation == 2){
                                        valide = true;
                                    }
                                }
                                catch(Exception InputMismatchException){
                                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                }
                            }

                            Graph graph = null;

                            // Création auto
                            if(choixCreation == 1){
                                int nombreDeNoeudParNiveau = 0;

                                valide = false;

                                while(!(valide == true)){
                                    try{
                                        System.out.println("Bienvenu dans la création du graph automatique, " + color.RED_COLOR + "deux fichiers vont bientôt s'ouvrir et va falloir les remplir." + color.RESET_COLOR);
                                        System.out.println("Avant toutes choses, Combien d'enfants voulez-vous par noeud " + color.RED_COLOR + "(supérieur à 0)" + color.RESET_COLOR + " ?");
                                        Scanner scanNBNoeudNiveau = new Scanner(System.in);
                                        nombreDeNoeudParNiveau = scanNBNoeudNiveau.nextInt();
                                        System.out.println("");
                                        if(nombreDeNoeudParNiveau > 0){
                                            valide = true;
                                        }
                                    }
                                    catch(Exception InputMismatchException){
                                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                    }
                                }

                                graph = new Graph(nombreDeNoeudParNiveau);
                                graph.afficheGraph();
                            }

                            // Création manuel
                            else if(choixCreation == 2){
                                System.out.println("Bienvenu dans la création du graph manuel.");
                                graph = new Graph();
                                graph.afficheGraph();
                            }

                            if (graph.getNombreBonChemin() == 0){
                                System.out.println(color.RED_COLOR + "Attention votre projet en cours ne contient aucun bon chemin, veuillez y remédier en choisissant Ajouter/Supprimer un bon chemin (6)" + color.RESET_COLOR + "\n");
                            }
                            else{
                                System.out.println(color.RED_COLOR + "Vous avez " + graph.getNombreBonChemin() + " bon chemin, si vous voulez rajouter des bon chemin veuillez choisir Ajouter/Supprimer un bon chemin (6)" + color.RESET_COLOR + "\n");
                            }

                            choixProjet =  this.choixRepetitiveActions(graph);

                        }

                        // non, on fait rien
                        else if (choixNouveauProjet == 2){
                            System.out.println("La création d'un nouveau projet a été annulé !!!\n");
                            valide = true;
                            choixProjet = 99;
                        }
                    }

                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }
 
                valide = false;
            }

            // Charger un projet existant
            else if(choixProjet == 2){

                valide = false;

                // s'il n'y a pas de fichier on va au choix 1
                if (this.nbFichier.getNbFichier() == 0){
                    System.out.println("Il n'y a pas de fichier à supprimer !!!\n");
                    valide = true;
                    choixProjet = 1;
                }


                // on demande à l'utilisateur s'il est sûr de vouloir charger un projet existant
                int choixChargerProjet = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer le chargement d'un projet existant Oui (1) ou Non (2) ?");
                        Scanner scanChargerProjet = new Scanner(System.in);
                        choixChargerProjet = scanChargerProjet.nextInt();
                        System.out.println("");

                        // Oui, on valide et on charge le fichier
                        if (choixChargerProjet == 1){

                            // Si on possède plusieurs fichiers
                            if (!(this.nbFichier.getNbFichier() == 0)){
                                Scanner scanChoix = new Scanner(System.in);
                                String choix = null;


                                while (!(valide == true)){
                                    // On choisit un projet et on fait la mếme chose que les fonctions précédentes 
                                    System.out.println("Liste des fichiers existant : " + this.nbFichier.getListNomFichier());
                                    scanChoix = new Scanner(System.in);
                                    System.out.println("Veuillez choisir un projet ? ");
                                    choix = scanChoix.nextLine();
                                    System.out.println();

                                    // si c le bon nom de fichier alors on sort de la boucle
                                    if(this.nbFichier.getListNomFichier().contains(choix)){
                                        valide = true;
                                    }
                                }
                                
                                ChargerFichierGraph fichierCharge = new ChargerFichierGraph(choix, this.chemin_graphs);
                                Graph graph = fichierCharge.getGraph();

                                graph.afficheGraph();

                                if (graph.getNombreBonChemin() == 0){
                                    System.out.println(color.RED_COLOR + "Attention votre projet en cours ne contient aucun bon chemin, veuillez y remédier en choisissant Ajouter/Supprimer un bon chemin (6)" + color.RESET_COLOR + "\n");
                                }
                                else{
                                    System.out.println(color.RED_COLOR + "Vous avez " + graph.getNombreBonChemin() + " bon chemin, si vous voulez rajouter des bon chemin veuillez choisir Ajouter/Supprimer un bon chemin (6)" + color.RESET_COLOR + "\n");
                                }

                                
                                choixProjet =  this.choixRepetitiveActions(graph);

                            }
                        }

                        // non, on fait rien
                        else if (choixChargerProjet == 2){
                            System.out.println("Le chargement d'un projet existant a été annulé !!!\n");
                            valide = true;
                            choixProjet = 99;
                        }
                    }

                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }

                // Permet de faire juste le ralais
                if (!(this.nbFichier.getNbFichier() == 0)){
                    valide = false;
                }

            }

            // Supprimer un projet existant
            else if(choixProjet == 3){

                valide = false;

                // s'il n'y a pas de fichier on va au choix 1
                if (this.nbFichier.getNbFichier() == 0){
                    System.out.println("Il n'y a pas de fichier à supprimer !!!\n");
                    valide = true;
                    choixProjet = 1;
                }


                // on demande à l'utilisateur s'il est sûr de vouloir supprimer un projet existant
                int choixSupprimerProjet = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la suppression d'un projet existant Oui (1) ou Non (2) ?");
                        Scanner scanSupprimerProjet = new Scanner(System.in);
                        choixSupprimerProjet = scanSupprimerProjet.nextInt();
                        System.out.println("");

                         // Oui, on valide et on supprime le fichier
                        if (choixSupprimerProjet == 1){

                            // Si on possède plusieurs fichiers on peut les supprimer
                            if (!(this.nbFichier.getNbFichier() == 0)){
                                Scanner scanChoix = new Scanner(System.in);
                                String choix = null;

                                while (!(valide == true)){
                                    // On choisit un projet et on fait la même chose que les fonctions précédentes 
                                    System.out.println("Liste des fichiers existant : " + this.nbFichier.getListNomFichier());
                                    scanChoix = new Scanner(System.in);
                                    System.out.println("Veuillez choisir un projet ? ");
                                    choix = scanChoix.nextLine();
                                    System.out.println();

                                    // si c le bon nom de fichier alors on sort de la boucle
                                    if(this.nbFichier.getListNomFichier().contains(choix)){
                                        valide = true;
                                    }
                                }

                                SupprimerFichier fichierSupprime = new SupprimerFichier(choix, this.chemin_graphs);
                                choixProjet = 99;
                            }

                        }

                        // non, on fait rien
                        else if (choixSupprimerProjet == 2){
                            System.out.println("La suppression d'un projet existant a été annulé !!!\n");
                            valide = true;
                            choixProjet = 99;
                        }
                    }

                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }

                // Permet de faire juste le ralais
                if (!(this.nbFichier.getNbFichier() == 0)){
                    valide = false;
                }

            }
        }
        
    }

    /** 
        * Cette méthode propose à l'utilisateur plusieurs choix Modifier Noeud, Ajouter noeud, Supprimer noeud, Afficher graph, Sauvegarde graph et Quitter.
        * @param graph correspond au graph du projet en cours
        * @return un entier
    */
    private int choixRepetitiveActions(Graph graph) throws IOException{
        // Phase choix 
        boolean sauvegarde = false;
        int choixProjet = 99;

        while (!(sauvegarde == true)){
            int choixActions = 0;
            boolean valide = false;
            Scanner scanChoix = new Scanner(System.in);

            while(!(valide == true)){
                try{
                    System.out.println(color.WHITE_COLOR + "" +  color.BLACK_SCRIPTURE_COLOR + "Choisissez :" + color.RESET_COLOR);
                    System.out.println(color.RED_COLOR + "Modifier Noeud (1)" + color.RESET_COLOR + " \n" + color.RED_COLOR + "Ajouter noeud (2)" + color.RESET_COLOR + " \n" + color.RED_COLOR + "Supprimer noeud (3)" + color.RESET_COLOR + " \n" + color.RED_COLOR + "Afficher graph (4)" + color.RESET_COLOR + " \n" + color.RED_COLOR + "Sauvegarde graph (5)" + color.RESET_COLOR+ " \n" + color.RED_COLOR + "Ajouter/Supprimer un bon chemin (6)" + color.RESET_COLOR+ " \n" + color.RED_COLOR + "Quitter (7)" + color.RESET_COLOR);
                    scanChoix = new Scanner(System.in);
                    choixActions = scanChoix.nextInt();
                    System.out.println("");
                    if (choixActions > 0 && choixActions <= 7){
                        valide = true;
                    }
                }
                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }

            

            // Modifier Noeud
            if(choixActions == 1){

                // on demande à l'utilisateur s'il est sûr de vouloir modifier un noeud
                valide = false;
                int choixModif = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la modification du noeud Oui (1) ou Non (2) ?");
                        Scanner scanModif = new Scanner(System.in);
                        choixModif = scanModif.nextInt();
                        System.out.println("");

                        // Oui, on valide et on modifie le noeud
                        if (choixModif == 1){

                            int paramNumero = 0;
                            String paramSolution = null;
                            String paramParagraphe = null;

                            while(!(valide == true)){
                                try{
                                    System.out.println("Veuillez entrer le numéro du noeud que vous souhaitez modifier : ");
                                    Scanner scanParam = new Scanner(System.in);
                                    paramNumero = scanParam.nextInt();
                                    System.out.println("");
                                    // La racine à pour identifiant 1 
                                    if (paramNumero > 0 && paramNumero <= graph.getNbNoeudEnTout()){
                                        valide = true;
                                    }
                                }
                                catch(Exception InputMismatchException){
                                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                }
                            }

                            Scanner scanParamString = new Scanner(System.in);

                            // La racine n'a pas de solution
                            if (!(paramNumero == 1)){
                                System.out.println("Veuillez entrer la nouvelle solution du noeud : ");
                                paramSolution = scanParamString.nextLine();
                                System.out.println("");
                            }
                            
                            System.out.println("Veuillez entrer le nouveau paragraphe du noeud : ");
                            paramParagraphe = scanParamString.nextLine();
                            System.out.println("");

                            graph.modifierNoeud(paramNumero, paramSolution, paramParagraphe);
                            graph.afficheGraph();
                        }

                        // non, on fait rien
                        else if (choixModif == 2){
                            System.out.println("La modification du noeud a été annulé !!!\n");
                            valide = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }
            }

            // Ajouter noeud
            else if(choixActions == 2){


                // on demande à l'utilisateur s'il est sûr de vouloir ajouter un noeud
                valide = false;
                int choixAjout = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer l'ajout du noeud Oui (1) ou Non (2) ?");
                        Scanner scanAjout = new Scanner(System.in);
                        choixAjout = scanAjout.nextInt();
                        System.out.println("");

                        // Oui, on valide et on ajoute le noeud
                        if (choixAjout == 1){

                            int paramNumero = 0;
                            String paramSolution = null;
                            String paramParagraphe = null;

                            while(!(valide == true)){
                                try{
                                    System.out.println("Veuillez entrer le numéro du noeud où vous souhaitez y ajouter un noeud : ");
                                    Scanner scanParam = new Scanner(System.in);
                                    paramNumero = scanParam.nextInt();
                                    System.out.println("");
                                    // La racine à pour identifiant 1 
                                    if (paramNumero > 0 && paramNumero <= graph.getNbNoeudEnTout()){
                                        valide = true;
                                    }
                                }
                                catch(Exception InputMismatchException){
                                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                }
                            }

                            Scanner scanParamString = new Scanner(System.in);

                            System.out.println("Veuillez entrer la solution du noeud : ");
                            paramSolution = scanParamString.nextLine();
                            System.out.println("");
                            
                            System.out.println("Veuillez entrer le paragraphe du noeud : ");
                            paramParagraphe = scanParamString.nextLine();
                            System.out.println("");

                            graph.ajouterNoeud(paramNumero, paramSolution, paramParagraphe);
                            graph.afficheGraph();
                        }

                        // non, on fait rien
                        else if (choixAjout == 2){
                            System.out.println("L'ajout du noeud a été annulé !!!\n");
                            valide = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }
            }

            // Supprimer noeud
            else if(choixActions == 3){

                // on demande à l'utilisateur s'il est sûr de vouloir supprimer le noeud
                valide = false;
                int choixSuppression = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la suppression Oui (1) ou Non (2) ?");
                        Scanner scanSuppression = new Scanner(System.in);
                        choixSuppression = scanSuppression.nextInt();
                        System.out.println("");

                        // Oui, on valide et on supprime le noeud
                        if (choixSuppression == 1){

                            int paramNumero = 0;

                            while(!(valide == true)){
                                try{
                                    System.out.println("Veuillez entrer le numéro du noeud que vous souhaitez supprimer : ");
                                    Scanner scanParam = new Scanner(System.in);
                                    paramNumero = scanParam.nextInt();
                                    System.out.println("");

                                    // La racine à pour identifiant 1
                                    if (paramNumero > 0 && paramNumero <= graph.getNbNoeudEnTout()){
                                        valide = true;
                                    }
                                }
                                catch(Exception InputMismatchException){
                                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                }
                            }

                            graph.supprimerNoeud(paramNumero);
                            graph.afficheGraph();
                        }

                        // non, on fait rien
                        else if (choixSuppression == 2){
                            System.out.println("La suppression du noeud a été annulé !!!\n");
                            valide = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }
            }

            // Afficher graph
            else if(choixActions == 4){
                graph.afficheGraph();
            }

            // Sauvegarde graph
            else if(choixActions == 5){

                // on demande à l'utilisateur s'il est sûr de vouloir sauvegarder le graph
                valide = false;
                int choixSauvegarde = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la sauvegarde Oui (1) ou Non (2) ?");
                        Scanner scanSauvegarde = new Scanner(System.in);
                        choixSauvegarde = scanSauvegarde.nextInt();
                        System.out.println("");

                        // Oui, on valide et on sauvegarde le graph
                        if (choixSauvegarde == 1){

                            Scanner scanNomFichier = new Scanner(System.in);
                            this.nbFichier = new ParcourirFichier(this.chemin_graphs);

                            // Afficher les fichiers existants
                            System.out.println("Liste des fichiers existant : " + this.nbFichier.getListNomFichier());

                            System.out.println("Veuillez entrer un nom pour le fichier ? " + color.RED_COLOR + "(Attention ne mettez pas d'espace utiliser plutôt l'underscore, ne mettez pas le même nom que les fichiers existant sinon ils vont être écrassés Merci)" + color.RESET_COLOR);
                            String nomDuFichier = scanNomFichier.nextLine();

                            CreerFichierGraph creationFichierGraph = new CreerFichierGraph(nomDuFichier, graph, this.chemin_graphs);
                            valide = true;
                        }
                        
                        // non, on fait rien
                        else if (choixSauvegarde == 2){
                            System.out.println("La sauvegarde du projet a échouée !!!\n");
                            valide = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }

            }

            // Ajouter ou supprimer un bon chemin
            else if(choixActions == 6){


                

                // on demande à l'utilisateur s'il est sûr de vouloir modifier les noeuds finaux pour qu'ils soient un bon ou mauvais chemin
                valide = false;
                int choixCheminProjet = 0;
                boolean paramBool = false;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous continuer la modification des noeuds finaux pour qu'ils soient un bon ou mauvais chemin Oui (1) ou Non (2) ?");
                        Scanner scanChemin = new Scanner(System.in);
                        choixCheminProjet = scanChemin.nextInt();
                        System.out.println("");

                         // Oui
                        if (choixCheminProjet == 1){
                            int indiceNoeudCible = 0;
                            ArrayList<Noeud> listeDesBonEtMauvaiCheminPossible = graph.getListeDesBonEtMauvaiCheminPossible();

                            // On va stocker les identifiants des noeuds cible
                            ArrayList<Integer> listeID = new ArrayList<>();

                            System.out.println("Les noeuds disponibles : ");
                            for(Noeud noeud : listeDesBonEtMauvaiCheminPossible){
                                System.out.println(""+noeud + " Gagner : " + noeud.getGagneOuPerdre());
                                listeID.add(noeud.getIdentifiant());
                            }
                            System.out.println("\n");


                            int paramNumero = 0;
                            valide = false;

                            while(!(valide == true)){
                                try{
                                    System.out.println("Veuillez entrer le numéro du noeud que vous souhaitez modifier : ");
                                    Scanner scanParam = new Scanner(System.in);
                                    paramNumero = scanParam.nextInt();
                                    System.out.println("");
                                    
                                    for(int i = 0; i < listeID.size(); i++){
                                        if (listeID.get(i) == paramNumero){
                                            indiceNoeudCible = i;
                                            valide = true;
                                        }
                                    }
                                    
                                }
                                catch(Exception InputMismatchException){
                                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                                }
                            }

                            Scanner scanParamBool = new Scanner(System.in);
                            System.out.println("Veuillez entrer 'true' si c'est un noeud gagant ou 'false' un noeud perdant : ");
                            paramBool = scanParamBool.nextBoolean();
                            System.out.println("");

                            // On modifie le noeud cible pour qu'il soit gagnant ou perdant
                            listeDesBonEtMauvaiCheminPossible.get(indiceNoeudCible).setGagneOuPerdre(paramBool);

                            // Il ne faut pas oublié incrémenter le nombre de bon chemin dans le graphe s'il est gagant
                            if (listeDesBonEtMauvaiCheminPossible.get(indiceNoeudCible).getGagneOuPerdre() == true){
                                graph.setNombreBonChemin(graph.getNombreBonChemin()+1);
                            }
                            else{
                                // Si le nombre de bon chemin du graphe est égale à 0 on laisse à 0
                                if(!(graph.getNombreBonChemin() == 0)){
                                    // S'il est perdant on décrémente
                                    graph.setNombreBonChemin(graph.getNombreBonChemin()-1);
                                }
                            }
                            


                        }

                        // non, on fait rien
                        else if (choixCheminProjet == 2){
                            System.out.println("La modification des noeuds finaux pour qu'ils soient un bon ou mauvais chemin a été annulé !!!\n");
                            valide = true;
                        }
                    }

                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }


















            }



            // Quitter
            else if(choixActions == 7){
                // on demande à l'utilisateur s'il est sûr de vouloir quitter
                valide = false;
                int choixQuitter = 0;

                while(!(valide == true)){
                    try{
                        System.out.println("Voulez-vous quitter Oui (1) ou Non (2) ?");
                        Scanner scanQuitter = new Scanner(System.in);
                        choixQuitter = scanQuitter.nextInt();
                        System.out.println("");

                        // Oui, on quitte
                        if (choixQuitter == 1){
                            sauvegarde = true;
                            valide = true;
                            return 0;
                            
                        }
                        // non, on fait rien
                        else if (choixQuitter == 2){
                            System.out.println("Quitter a échouée !!!");
                            valide = true;
                        }
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }
                
            }
            
        }
        return 99;
    }
    

}