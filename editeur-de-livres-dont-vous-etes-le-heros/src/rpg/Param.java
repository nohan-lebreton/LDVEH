// Cette classe correspond aux différents paramètres nécessaires à la construction du livre

package rpg;
import java.util.Scanner;

public class Param{
  private String name;
  private int difficult;
  private int nombreDeNoeudParNiveau;
  private int nombreDeBonChemin;
  private int nombreEtage;

  /**
      * Le constructeur permet d'instancier les paramêtres du programme
      * @param nombreDeNoeudParNiveau correspond aux nombres de noeud max présent sur les niveau
      * @param nombreEtage correspond aux nombres d'étages présent dans le jeu
      * @param nombreDeBonChemin correspond aux nombres de bons chemins sur un étage du jeu
  */
  public Param(int nombreDeNoeudParNiveau, int nombreEtage, int nombreDeBonChemin){
    this.nombreDeNoeudParNiveau = nombreDeNoeudParNiveau;
    this.nombreEtage = nombreEtage;
    this.nombreDeBonChemin = nombreDeBonChemin;
  }

  /**
      * Cette méthode permet de parametrer la difficulté en fonction de la structure du graphe
  */
  public void setDifficult(){

    this.difficult = (nombreDeNoeudParNiveau*getNombreEtage())/nombreDeBonChemin;
  }

  /**
      * Cet assesseur permet de récupérer la valeur de difficulté
      * @return difficult
  */
  public int getDifficult(){
    return this.difficult;
  }

  /**
      * Cet assesseur permet de récupérer le nombre d'étage
      * @return le nombre d'etage
  */
  public int getNombreEtage(){
    return this.nombreEtage;
  }

  /**
      * Cette méthode permet de parametrer le nombre de bon chemin
      * @require nombreDeBonChemin > 0
      * @ensure nombreDeBonchemin <= 5
  */
  public void setnombreDeBonChemin(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Entrez le nombre de chemin correct");
    this.nombreDeBonChemin = sc.nextInt();
    System.out.println("Le nombre de bon chemin est " + nombreDeBonChemin);
  }

  /**
      * Cet assesseur récupère le nombre de bon chemin
      * @return le nombre de bon chemin
  */
  public int getNbBonChemin(){
    return this.nombreDeBonChemin;
  }
}
//////////////////////////////////////////////////////////////////
