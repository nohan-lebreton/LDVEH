package graph;

import java.util.LinkedList;
import java.io.*;
import color.*;

public class Noeud implements Serializable{
    private String solution;
    private String paragraphe;
    private int identifiant;
    private LinkedList<Noeud> enfants;
    private Color color;
    private boolean gagneOuPerdre;
    
    /**
        * Ce constructeur permet d'instancier un objet noeuds, un noeud possède une solution, un paragraphe, un identifiant, un ou des enfants et une variable gagné ou perdre.
        * @param solution correspond à la solution du noeud
        * @param paragraphe correspond au paragraphe du noeud
        * @param identifiant correspond à l'identifiant du noeud
    */
    public Noeud(String solution, String paragraphe, int identifiant){
        this.solution = solution;
        this.paragraphe = paragraphe;
        this.identifiant = identifiant;
        this.enfants = new LinkedList<Noeud>(); 
        this.gagneOuPerdre = false; 
    }

    /** 
        * Cet accesseur permet de récupérer le paragraphe
        * @return le paragraphe du noeud
    */
    public String getParagraphe(){
        return this.paragraphe;
    }

    /** 
        * Cet accesseur permet de récupérer la solution
        * @return la solution du noeud
    */
    public String getSolution(){
        return this.solution;
    }


    /**
        * Cet accesseur permet de modifier le paragraphe du noeud
        * @param newParagraphe correspond au nouveau paragraphe
    **/
    public void setParagraphe(String newParagraphe){
        this.paragraphe = newParagraphe;
    }

    /**
        * Cet accesseur permet de modifier la solution du noeud
        * @param newSolution correspond à la nouvelle solution
    **/
    public void setSolution(String newSolution){
        this.solution = newSolution;
    }

    /**
        * Cet accesseur permet d'obtenir l'identifiant du noeud
        * @return l'identifiant
    **/
    public int getIdentifiant(){
        return this.identifiant;
    }

    /**
        * Cet accesseur permet de modifier l'identifiant du noeud
        * @param newIdentifiant correspond au nouvel identifiant.
    **/
    public void setIdentifiant(int newIdentifiant){
        this.identifiant = newIdentifiant;
    }

    /**
        * Cet accesseur permet d'obtenir les enfants du noeud
        * Les enfants se sont des noeuds
        * @return une liste d'enfants
    **/
    public LinkedList<Noeud> getEnfants(){
        return this.enfants;
    }

    /**
        * Cette méthode permet d'ajouter des enfants au noeud
        * @param enfant correspond aux noeuds que l'on souhaite rajouter en tant qu'enfant
    **/
    public void ajouteEnfants(Noeud enfant){
        this.enfants.add(enfant);
    }

    /**
        * Cette méthode permet de supprimer un enfant de la liste
        * @param enfant correspond aux noeuds que l'on souhaite supprimer
    **/
    public void supprimerEnfants(Noeud enfant){
        this.enfants.remove(enfant);
    }

    /**
        * Cette accesseur permet d'obtenir la victoire ou la défaite
        * @return le booleen gagné (true) ou perdre (false)
    **/
    public boolean getGagneOuPerdre(){
        return this.gagneOuPerdre;
    }

    /**
        * Cet accesseur permet de modifier la variable gagneOuPerdre
        * @param newGagneOuPerdre correspond au nouvel booleen gagneOuPerdre.
    **/
    public void setGagneOuPerdre(boolean newGagneOuPerdre){
        this.gagneOuPerdre = newGagneOuPerdre;
    }


    @Override
    public String toString(){
        return "(" + color.YELLOW_COLOR + this.solution + color.RESET_COLOR + " : " + color.BLUE_COLOR + this.paragraphe + color.RESET_COLOR + " : " + color.PURPLE_COLOR + " " + this.identifiant + " " + color.RESET_COLOR + ")";
    }
}
