// Information :
//////////////////////////////////////////////////////////////////////////
/* Les étages se décomposent ainsi :
    - Racine = étage 1
    - Ses enfants = étage 2
    - Ainsi de suite ...

   Les noeuds :
    noeud 1 = racine
    noeud 2 = enfant 1 de la racine
    ...


   Code couleurs :
    - VERT = Etage 
    - VIOLET = Noeud
    - BLEU = PARAGRAPHE des noeuds / fichier 
    - JAUNE = SOLUTION des noeuds
    - ROUGE = Erreur / Choix / paragraphe important
    - ORANFE = Remplacement de fichier
*/

package graph;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;
import fichier.*;
import java.io.*;
import color.*;
import  java.util.Random;

public class Graph implements Serializable{
    private int nombreDeNoeudEnTout;
    private int nombreDeNoeudParNiveau;
    private int nombreDeNiveau;

    private int numeroParagraphe;
    private int numeroSolution;

    private int numeroEtage;
    private Noeud graph;

    private CreerFichier fichier;
    private String chemin_paragraphe = "./assets/sauvegarde_graph/TMP/Paragraphes/";
    private String chemin_solutions = "./assets/sauvegarde_graph/TMP/Solutions/";

    private Color color;

    private int nombreDeBonChemin = 0;


    /**
        * Le constructeur permet d'instancier un graph de manière automatique
        * @param nombreDeNoeudParNiveau correspond au nombre de noeud que l'utilisateur souhaite par niveau 
    */
    public Graph(int nombreDeNoeudParNiveau) throws IOException{
        this.nombreDeNoeudEnTout = 0;
        this.nombreDeNoeudParNiveau = nombreDeNoeudParNiveau;
        this.numeroEtage = 0;
        this.numeroParagraphe = 0;
        this.numeroSolution = 0;
        this.graph = this.creationGraphAutomatique();
    }

    /**
        * Le constructeur permet d'instancier un graph de manière manuelle
    */
    public Graph() throws IOException{
        this.nombreDeNoeudEnTout = 0;
        this.nombreDeNoeudParNiveau = 0;
        this.numeroEtage = 0;
        this.graph = this.creationGraphManuelle();
    }

    /**
        * Cette Méthode permet de créer la racine
        * @return racine
    **/
    private Noeud creationRacine(){
        Noeud racine = new Noeud("", "", 1);
        return racine;
    }

    /* Objectif :
        - demander le nb d'étage qu'il souhaite en comptant la racine
        - demander à chaque création de niveau le nb de noeud
        - + le paragraphe à rentrer
    */
    /**
        * Cette méthode permet de créer manuellement le graph en entrant les valeurs
        * @return racine
    **/
    private Noeud creationGraphManuelle() throws IOException{
        int nbNiveau = 0;
        boolean valide = false;
        int cptIdentifiant = 2;

        // On veut que l'utilisateur entre un entier, on demande le nombre d'étage au total
        while(!(valide == true)){
            try{
                // Nombre de niveau
                System.out.println("Veuillez entrer le nombre d'étage souhaité en comptant l'étage 1 :");
                Scanner scanNbNiveau = new Scanner(System.in);
                nbNiveau = scanNbNiveau.nextInt();

                // On veut garder en mémoire le nombre d'étage
                this.nombreDeNiveau = nbNiveau;

                System.out.println();
                if (nbNiveau > 0){
                    valide = true;
                }
            }
            catch(Exception InputMismatchException){
                System.out.println( "\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
            }
        }

        //////////////////////////////////////////////////////////////////////////
        //              Création Racine + 2ème étage
        //////////////////////////////////////////////////////////////////////////
        
        // On incrémente le numéro de l'étage avant car on a fixé que la racine se trouve à l'étage 1
        this.numeroEtage += 1;

        // Création de la racine
        Noeud racine = this.creationRacine();
        System.out.println(color.GREEN_COLOR + "Etage " + this.numeroEtage + color.RESET_COLOR + " : " + color.PURPLE_COLOR + "Noeud " + racine.getIdentifiant() + " : Racine" + color.RESET_COLOR);
        

        // Paragraphe pour le noeud en cours
        System.out.println("Veuillez entrer le paragraphe :");
        Scanner scanParagraphe = new Scanner(System.in);
        racine.setParagraphe(scanParagraphe.nextLine());
        System.out.println("");

        // si on a 1 niveau on s'arrête ici et on retourne la racine
        if (nbNiveau == this.numeroEtage){
            this.nombreDeNoeudParNiveau = 1;
            return racine;
        }

        valide = false;
        Scanner scanNbNoeud = null;
        int nbNoeud = 0;

        // On veut que l'utilisateur rentre un entier, on demmande ici le nombre de noeud pour l'étage
        while(!(valide == true)){
            try{
                System.out.println(color.PURPLE_COLOR + "Noeud " + racine.getIdentifiant() + " : Racine" + color.RESET_COLOR + " Donnez lui un nombre de noeud ? ");
                scanNbNoeud = new Scanner(System.in);
                nbNoeud = scanNbNoeud.nextInt();

                // On souhaite garder en mémoire le nombre de noeud par niveau maximal
                if (nbNoeud > this.nombreDeNoeudParNiveau){
                    this.nombreDeNoeudParNiveau = nbNoeud;
                }

                System.out.println("");
                valide = true;
            }
            catch(Exception InputMismatchException){
                System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
            }
        }

        this.numeroEtage += 1;
        
        for (int i = 0; i < nbNoeud; i++){
            System.out.println(color.GREEN_COLOR + "Etage " + this.numeroEtage + color.RESET_COLOR + " : " + color.PURPLE_COLOR + "Noeud "  + cptIdentifiant + color.RESET_COLOR);
            Noeud noeud = new Noeud("", "", cptIdentifiant);

            // Solution
            System.out.println(color.BLUE_COLOR + "'" + racine.getParagraphe() + "'" + color.RESET_COLOR +" :" + "Donnez la solution ?");
            noeud.setSolution(scanParagraphe.nextLine());

            // Paragraphe pour le noeud en cours
            System.out.println("Veuillez entrer un paragraphe :");
            noeud.setParagraphe(scanParagraphe.nextLine());

            System.out.println("");
            racine.ajouteEnfants(noeud);

            cptIdentifiant++;
        }
        this.nombreDeNoeudEnTout = nbNoeud + 1;

        // si on a 2 niveau on s'arrête ici et on retourne la racine accompagné de ses enfants
        if (nbNiveau == this.numeroEtage){
            return racine;
        }

        //////////////////////////////////////////////////////////////////////////







        Noeud pointeur = racine;

        LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
        listePointeurEnfant.add(pointeur.getEnfants());
        
        int k = 0;
        boolean stop = false;
        int nombreNoeud = pointeur.getEnfants().size();

        ArrayList<Integer> listeNombreNoeud = new ArrayList<Integer>();
        listeNombreNoeud.add(1);
        listeNombreNoeud.add(nombreNoeud);

        int cpt = 1;
    
        ArrayList<Integer> listeNombreNoeudCourant = new ArrayList<Integer>();
        int cpt2 = 0;

        while(!(stop == true)){    

            /* 
               - listePointeurEnfant contient plusieurs listes de noeud, chaque liste représnete les enfants d'un noeud parent.
               - stop comme son nom l'indique permet d'arrêter le prog
               - ici on parcourt chaque élément de la liste pour y associer le nombre de noeud souhaité par l'utilisateur. 
               - le this.nombreDeNoeudEnTout sert juste pour la méthode afficheGraph

               - listeNombreNoeud sert à stocker le nombre de noeud par niveau.
               - listeNombreNoeudCourant permet de fusionner tous les noeuds d'un etage, par exemple si un etage est composé de [p1,p2,p3] et [p4,p5] donc 3 + 2 = 5;
               - listeNoued stocke les enfants des noeuds parents de chaque étage, lorsque this.numeroEtage s'increment alors liste est automatiquement vidée. 
            */
            
            LinkedList<LinkedList<Noeud>> listeNoued = new LinkedList<LinkedList<Noeud>> ();
    
            nombreNoeud = 0;

            for (int j = 0; j < listePointeurEnfant.get(k).size(); j++){


                /////////////////////////////////////////////////////////////////////////////
                pointeur = listePointeurEnfant.get(k).get(j);
                /////////////////////////////////////////////////////////////////////////////

                
                valide = false;
                // Nombre de noeud par niveau
                while(!(valide == true)){
                    try{
                        System.out.println(color.PURPLE_COLOR + "Noeud " + pointeur.getIdentifiant() + color.RESET_COLOR + " Donnez lui un nombre de noeud ? ");
                        scanNbNoeud = new Scanner(System.in);
                        nbNoeud = scanNbNoeud.nextInt();

                        // On souhaite garder en mémoire le nombre de noeud par niveau maximal
                        if (nbNoeud > this.nombreDeNoeudParNiveau){
                            this.nombreDeNoeudParNiveau = nbNoeud;
                        }

                        System.out.println("");
                        valide = true;
                    }
                    catch(Exception InputMismatchException){
                        System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                    }
                }

                // Création des noeuds selon le nombre de noeud souhaité
                for (int i = 0; i < nbNoeud; i++){

                    System.out.println(color.GREEN_COLOR + "Etage " + (this.numeroEtage + 1) + color.RESET_COLOR + " : " + color.PURPLE_COLOR + "Noeud "  + cptIdentifiant + color.RESET_COLOR);
                    Noeud noeud = new Noeud("", "", cptIdentifiant);
                    scanParagraphe = new Scanner(System.in);

                    // Solution
                    System.out.println(color.BLUE_COLOR + "'" + pointeur.getParagraphe() + "'" + color.RESET_COLOR +" :" + "Donnez la solution ?");
                    noeud.setSolution(scanParagraphe.nextLine());

                    // Paragraphe pour le noeud en cours
                    System.out.println("Veuillez entrer le paragraphe :");
                    noeud.setParagraphe(scanParagraphe.nextLine());
                   
                    System.out.println("");
                    pointeur.ajouteEnfants(noeud);
                    this.nombreDeNoeudEnTout+=1;
                    nombreNoeud+=1;

                    cptIdentifiant++;
                }
                
                listePointeurEnfant.add(pointeur.getEnfants());
                listeNoued.add(pointeur.getEnfants());
            }

            listeNombreNoeudCourant.add(nombreNoeud);

            if (listeNombreNoeudCourant.size() == listeNombreNoeud.get(cpt2)){
                cpt2++;
                this.numeroEtage+=1;
                int nombreNoeudCourant = 0;
                for(int i=0;i<listeNombreNoeudCourant.size();i++){
                    nombreNoeudCourant+=listeNombreNoeudCourant.get(i);
                }
                listeNombreNoeud.add(nombreNoeudCourant);
                listeNombreNoeudCourant.clear();
            }

            k++;
            cpt++;

            if (nbNiveau == this.numeroEtage){
                this.numeroEtage = nbNiveau;
                stop = true;
            }
        }
        return racine;
    } 

    /* L'objectif :
        - Automatiser la création du graph
        - Demander à l'utilisateur le nombre de noeud en tout
    */
    /*  Cette methode permet de créer un graph automatiquement, lors de la création de l'objet Graph on doit entrer comme parametre le nombre de noeuds, le nombre de noeuds par niveau qu'on souhaite et la liste qui contient les paragraphes.
        S'il y a pas assez de paragraphes par rapport au nombre de noeud demandés alors on va créer ces noeuds avec pour paragraphe _VIDE_. Comme ça plus tard l'utilisateur va pouvoir les supprimer manuellement ou les éditer.
        S'il y a trop de paragraphe par rapport au nombre de noeud alors on ne les prends pas en compte.
        Si l'utilisateur demande 1 noeud alors retourner la racine
        Si l'utilisateur demande 2 noeud et que le nombreDeNoeudParNiveau > 2 alors redemander à l'utilisateur de réentrer un nombre noeud correcte.
        Si le noeud de noeud est égale à nombreDeNoeudParNiveau + racine alors il y a aucun problème.
    */

    /**
        * Cette méthode permet de créer automatiquement le graph
        * @return racine
    **/
    private Noeud creationGraphAutomatique() throws IOException{

        // On créer un fichier où chaque ligne sera un paragraphe
        CreerFichier fichier_paragraphe = new CreerFichier(this.chemin_paragraphe,"Fichier paragraphe : veuillez remplacer cette ligne par votre paragraphe, chaque ligne réprésente un paragraphe. NE LAISSEZ PAS DE LIGNE VIDE À LA FIN !!!",0);

        // On créer un fichier où chaque ligne sera une solution
        CreerFichier fichier_solution = new CreerFichier(this.chemin_solutions,"Fichier solutions : veuillez remplacer cette ligne par votre solution, chaque ligne réprésente une solution. NE LAISSEZ PAS DE LIGNE VIDE À LA FIN !!!",0);

        // L'utilisateur devra remplir les fichiers à la mains, un paragraphe par ligne, pareil pour les solutions.
        // On ouvre les fichiers 

        fichier_paragraphe.ouvrirFichier();
        fichier_solution.ouvrirFichier();

        int choix = 0;

        while(!(choix == 1 || choix == 2)){
            try{
                System.out.println("Est-ce que vous avez fini ? Vous avez bien enregistré et fermé les fichiers ? " + color.RED_COLOR + "OUI (1)" + color.RESET_COLOR + " ou " + color.RED_COLOR + "NON (2)" + color.RESET_COLOR);
                Scanner scanChoix = new Scanner(System.in);
                choix = scanChoix.nextInt();
                System.out.println("");
                
                if (choix == 1){

                    // On va mettre les données des fichiers dans des listes.
                    LireAfficherFichier lire_fichier_paragraphe = new LireAfficherFichier(fichier_paragraphe.getChemin(), fichier_paragraphe.getNumeroFichierInt());
                    ArrayList<String> listeParagraphes = lire_fichier_paragraphe.creerArrayListString();
                    
                    LireAfficherFichier lire_fichier_solution = new LireAfficherFichier(fichier_solution.getChemin(), fichier_solution.getNumeroFichierInt());
                    ArrayList<String> listeSolutions = lire_fichier_solution.creerArrayListString();

                    this.nombreDeNoeudEnTout = listeParagraphes.size();


                    //////////////////////////////////////////////////////////////////////////
                    //              Création Racine + 2ème étage
                    //////////////////////////////////////////////////////////////////////////
                    
                    Noeud racine = this.creationRacine();
                    racine.setParagraphe(listeParagraphes.get(this.numeroParagraphe));

                    // La racine à pour numéro 1 et la précompte le deuxième noeud
                    int numeroNoeud = 2;

                    if (this.nombreDeNoeudEnTout == 1){
                        this.nombreDeNiveau = 1;
                        this.nombreDeNoeudParNiveau = 1;
                        return racine;
                    }

                    for(int i = 1; i <= this.nombreDeNoeudParNiveau; i++){
                        this.numeroParagraphe += 1;

                        Noeud enfant = new Noeud(listeSolutions.get(this.numeroSolution),listeParagraphes.get(this.numeroParagraphe),numeroNoeud);
                        racine.ajouteEnfants(enfant);
                        this.numeroSolution += 1;
                        
                        this.nombreDeNiveau = 2;

                        if (this.nombreDeNoeudEnTout == i+1){
                            this.nombreDeNoeudParNiveau = i;
                            return racine;
                        }

                        numeroNoeud++;
                    }

                    //////////////////////////////////////////////////////////////////////////

                    Noeud pointeur = racine;

                    LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
                    listePointeurEnfant.add(pointeur.getEnfants());
                    
                    int k = 0;
                    // On compte aussi la racine
                    int cpt = pointeur.getEnfants().size() + 1;
                    this.numeroParagraphe += 1;

                    while(!(cpt == this.nombreDeNoeudEnTout)){    

                        for (int j = 0; j < listePointeurEnfant.get(k).size(); j++){
                            pointeur = listePointeurEnfant.get(k).get(j);

                            // Création des noeuds selon le nombre de noeud total
                            for (int i = 0; i < this.nombreDeNoeudParNiveau; i++){
                                if (!(cpt ==  this.nombreDeNoeudEnTout)){
                                    // Si on n'a pas assez de paragraphe ou de solution on ajoute des noeuds pour combler le manque
                                    Noeud noeud = new Noeud("_VIDE_","_VIDE_",numeroNoeud);
                                    // On créé les noeuds à partir des listes
                                    if (this.numeroParagraphe < listeParagraphes.size() && this.numeroSolution < listeSolutions.size()){
                                        noeud = new Noeud(listeSolutions.get(this.numeroSolution), listeParagraphes.get(this.numeroParagraphe),numeroNoeud);
                                        this.numeroParagraphe++;
                                        this.numeroSolution += 1;

                                    }
                                    // Si on a la paragraphe mais pas le solution on remplace l'élément dans le noeud par _VIDE_
                                    else if(this.numeroParagraphe < listeParagraphes.size()){
                                        noeud = new Noeud("_VIDE_", listeParagraphes.get(this.numeroParagraphe),numeroNoeud);
                                        this.numeroParagraphe++;
                                    }
                                    // Si on a la solution mais pas le paragraphe on remplace l'élément dans le noeud par _VIDE_
                                    else if(this.numeroSolution < listeSolutions.size()){
                                        noeud = new Noeud(listeSolutions.get(this.numeroSolution), "_VIDE_",numeroNoeud);
                                        this.numeroSolution += 1;
                                    }

                                    pointeur.ajouteEnfants(noeud);
                                    numeroNoeud++;
                                    cpt++;
                                }
                            }
                            listePointeurEnfant.add(pointeur.getEnfants());
                        }
                        k++;
                        this.nombreDeNiveau++;
                    }
                    return racine;
                }

                else if (choix == 2){
                    fichier_paragraphe.ouvrirFichier();
                    fichier_solution.ouvrirFichier();
                    choix = 0;
                }
            }

            catch(Exception InputMismatchException){
                System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
            }
        }
        return null;
    } 
    
    /**
        * Cette méthode permet d'afficher le graph
    **/
    public void afficheGraph(){
        Noeud pointeur = this.getRacine();
        if (!(pointeur == null)){
            LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

            LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
            listePointeurEnfant.add(pointeur.getEnfants());

            System.out.println("Noeud Paragraphe : " + color.BLUE_COLOR + "'" + pointeur.getParagraphe() + "'" + color.RESET_COLOR + " : Identifiant : " + color.PURPLE_COLOR + " " + pointeur.getIdentifiant() + " " + color.RESET_COLOR);
            System.out.println("Enfants " + pointeur.getEnfants());
            System.out.println("");
            
            int k = 0;
            while(!(k == this.nombreDeNoeudEnTout)){
                for (int i = 0; i < pointeur2.size(); i++){
                    if (!(listePointeurEnfant.get(k).isEmpty())){
                        pointeur = listePointeurEnfant.get(k).get(i);
                        System.out.println("Noeud Paragraphe : " + color.BLUE_COLOR + "'" + pointeur.getParagraphe() + "'" + color.RESET_COLOR  + " : Identifiant : " + color.PURPLE_COLOR + " " + pointeur.getIdentifiant() + " " + color.RESET_COLOR);
                        System.out.println("Enfants " + pointeur.getEnfants());
                        System.out.println("");

                        listePointeurEnfant.add(pointeur.getEnfants());
                        pointeur2 = listePointeurEnfant.get(k);
                    }
                }
                k++;
            }
            System.out.println("");    
        }
        
    }

    /**
        * Cette méthode retourne la racine
        * @return la racine
    **/
    public Noeud getRacine(){
        return this.graph;
    }

    /**
        * Cette méthode permet de modifier le paragraphe du noeud
        * @param noeudCible correspond au noeud à modifier
        * @param newParagraphe correspond au nouveau paragraphe qu'on souhaite donner au noeud
    **/
    public void setParagraphGraph(Noeud noeudCible, String newParagraphe){
      if (!(noeudCible == null)){
        noeudCible.setParagraphe(newParagraphe);
      }
    }

    /**
        * Cette méthode permet de modifier la solution du noeud
        * @param noeudCible correspond au noeud à modifier
        * @param newSolution correspond à la nouvelle solution qu'on souhaite donner au noeud
    **/
    public void setSolutionGraph(Noeud noeudCible, String newSolution){
      if (!(noeudCible == null)){
        noeudCible.setSolution(newSolution);
      }
    }


    /**
        * Cette méthode permet de récuperer un noeud du graph, Si on souhaite récupérer le premier Noeud (la racine) le numéro attendu est le 1.
        * @param  numero correspond au numéro du noeud que l'on veut récupérer
        * @return le noeud, si on le trouve pas on retroune null
    **/
    public Noeud getNoeud(int numero){
        Noeud pointeur = this.getRacine();
        
        if (pointeur == null){
            return null;
        }
        LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

        LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
        listePointeurEnfant.add(pointeur.getEnfants());

        int k = 0;

        // Si c'est la racine
        if (pointeur.getIdentifiant() == numero){
            return pointeur;
        }

        while((!(k == this.nombreDeNoeudEnTout))){
            for (int i = 0; i < pointeur2.size(); i++){

                if (!(listePointeurEnfant.get(k).isEmpty())){
                    pointeur = listePointeurEnfant.get(k).get(i);
                    listePointeurEnfant.add(pointeur.getEnfants());
                    pointeur2 = listePointeurEnfant.get(k);
                }

                if (pointeur.getIdentifiant() == numero){
                    return pointeur;
                }

            }
            k++;
        }
        return null;
    }

    /**
        * Cette méthode permet de modifier le paragraphe et la solution du noeud cible, Si on souhaite modifier le premier Noeud (la racine) le numéro attendu est le 1.
        * @param numero correspond au numéro du noeud que l'on veut récupérer
        * @param newSolution correspond à la nouvelle solution qu'on souhaite mettre
        * @param newParagraphe correspond au nouveau paragraphe qu'on souhaite mettre
    **/
    public void modifierNoeud(int numero, String newSolution, String newParagraphe){
        Noeud noeudCible = this.getNoeud(numero);
        // Si c'est la racine on peut modifier que le paragraphe.
        if (!(numero == 1)){
            this.setSolutionGraph(noeudCible, newSolution);
        }
        this.setParagraphGraph(noeudCible, newParagraphe);
    }

    /**
        * Cette méthode permet d'ajouter un noeud à un noeud cible, Si on souhaite ajouter un noeud à la la racine le numéro attendu est le 1.
        * @param  numero correspond au numéro du noeud que l'on veut récupérer
        * @param solution correspond à la solution qu'on souhaite mettre
        * @param paragraphe correspond au paragraphe qu'on souhaite mettre
    **/
    public void ajouterNoeud(int numero, String solution, String paragraphe){
        // Je choisi un noeud
        Noeud noeudCible = this.getNoeud(numero);

        // Il ne faut pas oublier de d'incrémenter this.nombreDeNoeudEnTout
        this.nombreDeNoeudEnTout += 1;

        Noeud newNoeud = new Noeud(solution, paragraphe, this.nombreDeNoeudEnTout);

        // J'ajoute le nouveau noeud au noeud de départ
        noeudCible.ajouteEnfants(newNoeud);

        this.ordonneIdentifiantNoeud(1);
    }
    
    /**
        * Cette méthode permet de supprimer un noeud, on ne peut pas supprimer la racine. La méthode retourne true si la suppression est possible false sinon.
        * @param  numero correspond au numéro du noeud que l'on veut récupérer
    **/
    public boolean supprimerNoeud(int numero){

        // Si le neoud ciblé n'est pas la racine
        if (!(numero == 1)){

            // on pointe sur la racine
            Noeud pointeur = this.getRacine();


            // On cible le noeud à supprimer
            Noeud noeudCible = this.getNoeud(numero);


            
            if (this.supprimeNoeudPartie(pointeur, noeudCible) == true){
                this.ordonneIdentifiantNoeud(2);
                return true;
            }
            
            // On créé une liste de Noeud et à l'intérieur on place les enfants de la racine
            LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

            // On créé une matrice de Noeud qui va contenir tous les enfants par noeud
            LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
            listePointeurEnfant.add(pointeur.getEnfants());

            int k = 0;

            while((!(k == this.nombreDeNoeudEnTout))){
                for (int i = 0; i < pointeur2.size(); i++){

                    // On fait la même chose que précédemment
                    if (this.supprimeNoeudPartie(pointeur, noeudCible) == true){
                        this.ordonneIdentifiantNoeud(2);
                        return true;
                    }

                    // s'il y a des noeud, alors on parcourt le graph
                    if (!(listePointeurEnfant.get(k).isEmpty())){
                        pointeur = listePointeurEnfant.get(k).get(i);
                        listePointeurEnfant.add(pointeur.getEnfants());
                        pointeur2 = listePointeurEnfant.get(k);
                    }
                }
                k++;
            }
            return false;

        }
        // Si c'est la racine
        else{
            System.out.println(color.RED_COLOR + "Vous ne pouvez pas supprimer la racine !!!" + color.RESET_COLOR + "\n");
            return false;
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////


        // A terminé



    ///////////////////////////////////////////////////////////////////////////////////////

    /** 
        * Méthode permet de parcourir le graph et ranger les identifiants dans l'ordre croissant.

        * En bonus cette méthode permet de mettre à jour le nombre de niveau et le nombre de noeud par niveau maximal
        * @param option correspond à l'option ajouter (1) ou supprimer (2)
    */
    private void ordonneIdentifiantNoeud(int option){

        // on pointe sur la racine
        Noeud pointeur = this.getRacine();
        // On créé une liste de Noeud et à l'intérieur on place les enfants de la racine
        LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

        // On créé une matrice de Noeud qui va contenir tous les enfants par noeud
        LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
        listePointeurEnfant.add(pointeur.getEnfants());

        int k = 0;
        int cpt = 2;

        while((!(k == this.nombreDeNoeudEnTout))){
            int cptNoeudParNiveau = 0;
            // On parcourt les enfants de la racine au départ, puis plus tard les enfants des autres noeuds
            for (int i = 0; i < pointeur2.size(); i++){

                // s'il y a des noeuds, alors on parcourt le graph
                if (!(listePointeurEnfant.get(k).isEmpty())){
                    pointeur = listePointeurEnfant.get(k).get(i);

                    pointeur.setIdentifiant(cpt);

                    listePointeurEnfant.add(pointeur.getEnfants());
                    pointeur2 = listePointeurEnfant.get(k);
                    cpt++;
                    cptNoeudParNiveau++;
                    
                    // option ajouté
                    if (option == 1 && cptNoeudParNiveau > this.nombreDeNoeudParNiveau){
                        this.nombreDeNoeudParNiveau = cptNoeudParNiveau;
                    }

                    // option supprimé
                    else if (option == 2 && cptNoeudParNiveau < this.nombreDeNoeudParNiveau){
                        this.nombreDeNoeudParNiveau = cptNoeudParNiveau;
                    }
                }
            }
            k++;
        }

        System.out.println("************ Nombre d'étage : " + this.nombreDeNiveau);
        System.out.println("************ Nombre de noeud par étage : " + this.nombreDeNoeudParNiveau);

    }


    /* - Vérifie si un noeud est égale à un autre
       - Permet de supprimer un noeud 
    */

    /**
        * Cette méthode privée permet de supprimer un noeud, vérifie si un noeud est égale à un autre, elle est utilisé dans la méthode supprimerNoeud.
        * @param pointeur correspond à un noeud la racine.
        * @param noeudCible correspond au noeud cible.
        * @return un booleen true si on peut supprimer et false sinon.
    **/
    private boolean supprimeNoeudPartie(Noeud pointeur, Noeud noeudCible){
        
        // Tout d'abord on va parcourir la liste des enfants de la racine, si le noeud qu'on recherche est présent on le vire sinon on passe à la suite
        for(Noeud noeudEnfant : pointeur.getEnfants()){
            if (noeudEnfant.equals(noeudCible)){
                // On supprime le noeud de la liste 
                pointeur.supprimerEnfants(noeudCible);

                // On ajoute les enfants de la cible dans le noeud parent
                for(Noeud noeudEnfantCible : noeudCible.getEnfants()){
                    pointeur.ajouteEnfants(noeudEnfantCible);
                }
                
                // On supprimer définitivement le noeud cible
                noeudCible = null;

                // Il ne faut pas oublier de décrémenter this.nombreDeNoeudEnTout
                this.nombreDeNoeudEnTout = this.nombreDeNoeudEnTout - 1;
                System.out.println("Suppression du noeud terminé avec succès...\n");

                return true;
            }
        }
        return false;
    }


    /** 
        * Cet accesseur permet d'obtenir le nombre de noeud en tout
        * @return le nombre de noeud en tout
    */
    public int getNbNoeudEnTout(){
        return this.nombreDeNoeudEnTout;
    }

    /** 
        * Cette méthode permet de réorganiser le placement des noeuds au sein du graph
    */
    private void reorganiseLivreAleatoirement(){        

        // on pointe sur la racine
        Noeud pointeur = this.getRacine();

        ////////////////////////////////////////////////////////

        // On sauvegarde la racine vierge
        Noeud sauv_pointeur = new Noeud(pointeur.getSolution(),pointeur.getParagraphe(),pointeur.getIdentifiant());

         // On créé une liste qui va accueillir les noeuds qui vont devenir parent 
        ArrayList<Noeud> listeNoeudsParent = new ArrayList<>();

        // On créé une liste qui va accueillir tous les noeuds plaçable
        ArrayList<Noeud> listeNoeuds = new ArrayList<>();

        ////////////////////////////////////////////////////////



        // On créé une liste de Noeud et à l'intérieur on place les enfants de la racine
        LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

        // On créé une matrice de Noeud qui va contenir tous les enfants par noeud
        LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
        listePointeurEnfant.add(pointeur.getEnfants());

        int k = 0;
        int cpt = 2;

        while((!(k == this.nombreDeNoeudEnTout))){
            // On parcourt les enfants de la racine au départ, puis plus tard les enfants des autres noeuds
            for (int i = 0; i < pointeur2.size(); i++){

                // s'il y a des noeuds, alors on parcourt le graph
                if (!(listePointeurEnfant.get(k).isEmpty())){
                    pointeur = listePointeurEnfant.get(k).get(i);
                    listeNoeuds.add(pointeur);


                    listePointeurEnfant.add(pointeur.getEnfants());
                    pointeur2 = listePointeurEnfant.get(k);
                    cpt++;
                }
            }
            k++;
        }

        // Au départ nous avons qu'un seul choix c'est de donner un enfant à la racine

        /* 
            Après nous avons deux choix :
                -> donner un enfant à la racine
                -> donner un enfant au noeud préalablement créer 

        */

        // ...


        // Le problème c'est que chaque noeud à toujours leurs enfants donc il faut les supprimer

        for (int i = 0; i < listeNoeuds.size(); i++){
            listeNoeuds.get(i).getEnfants().clear();
        }

        // Initialisation du random
        Random seed = new Random();

        // On pointe sur la racine
        Noeud pointeurNoeud = sauv_pointeur;

        // On ajoute la racine dans la liste des choix possibles
        listeNoeudsParent.add(pointeurNoeud);

        while (!(listeNoeuds.size() == 0)){
            
            // On récupère le noeud qui va servir de parent
            Noeud noeudRandomParent = listeNoeudsParent.get(seed.nextInt(listeNoeudsParent.size()));

            // On récupère le noeud
            Noeud noeudRandom = listeNoeuds.get(seed.nextInt(listeNoeuds.size()));

            // On ajoute le noeud 
            noeudRandomParent.ajouteEnfants(noeudRandom);

            // On supprimer le noeud de la liste
            listeNoeuds.remove(noeudRandom);

            // On pointe sur le nouveau noeud et à la prochaine itération, ce noeud deviendra un choix potentiel
            pointeurNoeud = noeudRandom;

            listeNoeudsParent.add(pointeurNoeud);
        }


        this.graph = sauv_pointeur;
        this.ordonneIdentifiantNoeud(0);
    }

    /** 
        * Cet accesseur permet d'obtenir le nombre de bon chemin en tout
        * @return le nombre de bon chemin
    */
    public int getNombreBonChemin(){
        return this.nombreDeBonChemin;
    }

    /** 
        * Cet accesseur permet de modifier le nombre de bon chemin en tout
        * @param newNombreDeBonChemin correspond au nouveau nombre de bon chemin
    */
    public void setNombreBonChemin(int newNombreDeBonChemin){
        this.nombreDeBonChemin = newNombreDeBonChemin;
    }

    /** 
        * Cette méthode permet d'obtenir une liste contenant la listes des bons et mauvais chemins potentiels'
    */
    public ArrayList<Noeud> getListeDesBonEtMauvaiCheminPossible(){

        // on pointe sur la racine
        Noeud pointeur = this.getRacine();
        // On créé une liste de Noeud et à l'intérieur on place les enfants de la racine
        LinkedList<Noeud> pointeur2 = pointeur.getEnfants();

        // On créé une matrice de Noeud qui va contenir tous les enfants par noeud
        LinkedList<LinkedList<Noeud>> listePointeurEnfant = new LinkedList<LinkedList<Noeud>>();
        listePointeurEnfant.add(pointeur.getEnfants());

        // On créé la liste des bons et mauvais chemins
        ArrayList<Noeud> listeBonMauvaisChemin = new ArrayList<>();

        // Si la racine est une feuille alors on le rajoute dans la liste
        if (pointeur.getEnfants().size() == 0){
            listeBonMauvaisChemin.add(pointeur);
            return listeBonMauvaisChemin;
        }


        int k = 0;
        int cpt = 2;

        while((!(k == this.nombreDeNoeudEnTout))){
            int cptNoeudParNiveau = 0;
            // On parcourt les enfants de la racine au départ, puis plus tard les enfants des autres noeuds
            for (int i = 0; i < pointeur2.size(); i++){

                // s'il y a des noeuds, alors on parcourt le graph
                if (!(listePointeurEnfant.get(k).isEmpty())){
                    

                    pointeur = listePointeurEnfant.get(k).get(i);
                    listePointeurEnfant.add(pointeur.getEnfants());
                    pointeur2 = listePointeurEnfant.get(k);
                    cpt++;
                    cptNoeudParNiveau++;

                    // Si le noeud est une feuille alors on le rajoute dans la liste
                    if (pointeur.getEnfants().size() == 0){
                        listeBonMauvaisChemin.add(pointeur);
                    }
                }
                
            }
            k++;
        }
        return listeBonMauvaisChemin;
    }

}

