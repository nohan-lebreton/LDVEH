package rpg;

public class Item{

  // type de l'item ex: Epee, bouclier ...
  private String nomItem;
  // etage à partir du quel l'item peu apparaitre
  private int etageSpawn;
  // taux d'apparition en %
  private int spawnRate;
  // Propabilité de passage à l'etage superieur ajouter en %
  private int puissance;

  /**
      * Ce constructeur a pour but d'instancier un item
      * @param nomItem correspond au nom que l'on veut donner à l'Item
      * @param etageSpawn correspond à l'étage à laquelle l'objet peut apparaître
      * @param spawnRate correspond au pourcentage d'apparition de l'objet
      * @param puissance correspond à la puissance de l'objet
  */
  public Item(String nomItem, int etageSpawn, int spawnRate, int puissance){
    this.nomItem = nomItem;
    this.etageSpawn = etageSpawn;
    this.spawnRate = spawnRate;
    this.puissance = puissance;
  }

  /**
      * Cet accesseur a pour but de retouner le nom de l'Item
      * @return le nom de l'item
  */
  public String getNomItem(){
    return this.nomItem;
  }

  /**
      * Cet accesseur a pour but de récuperer getEtageSpawn
      * @return etageSpawn
  **/
  public int getEtageSpawn(){
    return this.etageSpawn;
  }

  /**
      * Cet accesseur a pour but de récupérer getSpawnRate
      * @return spawnRate
  **/
  public int getSpawnRate(){
    return this.spawnRate;
  }

/*
  Cette fonction a pour but de retourner puissance
  @return int puissance
*/
  /**
      * Cet accesseur a pour but d'obtenir la puissance
      * @return la puissance
  */
  public int getPuissance(){
    return this.puissance;
  }

  @Override
  public String toString(){
    return this.nomItem;
  }

}
