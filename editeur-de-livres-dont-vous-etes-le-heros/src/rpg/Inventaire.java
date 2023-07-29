package rpg;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventaire{
  private ArrayList<Item> inventaire;

  /**
      * Ce constructeur permet d'instancier un objet inventaire
  */
  public Inventaire(){
    this.inventaire = new ArrayList<Item>();
  }

  /**
      * Cet accesseur a pour but de retourner la liste correspondant a l'inventaire
      * @return l'inventaire
  */
  public ArrayList<Item> getInventaire(){
    return this.inventaire;
  }

  /**
      * Cet accesseur a pour but de retourner un objet présant dans la liste
      * @param nomItem correspond à l'item que l'on souhaite retrouver en particulier
      * @return un item
  */

  public Item getItem(String nomItem){
    for(Item objet : this.inventaire){
      if (nomItem == objet.getNomItem()){
        return objet;
      }
    }
    return null;
  }


  /**
      * Cette méthode a pour but de rajouter un item dans la liste Inventaire
      * @param objet correspond à l'objet que l'on soufaite ajouter dans la liste
  */
  public void ajouterItem(Item objet) {
    this.inventaire.add(objet);
  }

}
