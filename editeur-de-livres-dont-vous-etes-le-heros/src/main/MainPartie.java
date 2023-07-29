package main;

import java.util.Scanner;
import console.*;
import editeur.*;
import java.io.*;
import color.*;

public class MainPartie{
    private Color color;

    /** 
        * Ce constructeur permet d'instancier un objet MainPartie
    */
    public MainPartie() throws IOException{
        this.init();
    }

    /** 
        * Cette méthode permet de lancer le programme, l'utilisateur aura un choix à faire lancer l'éditeur en mode console ou graphique, lancer le jeu en mode console ou graphique et quitter le programme.
    */
    private void init() throws IOException{
        int choix = 0;

        boolean valide = false;
        boolean valideChoix = false;
        while(!(valide == true)){
            while(!(valideChoix == true)){
                try{
                    System.out.println(color.WHITE_COLOR + "" +  color.BLACK_SCRIPTURE_COLOR + "Choisissez :" + color.RESET_COLOR);
                    System.out.println(color.GREEN_COLOR + "Editeur en console (1)" + color.RESET_COLOR + " \n"  + color.GREEN_COLOR + "Editeur en interface graphique (2)" + color.RESET_COLOR + " \n" + color.GREEN_COLOR + "Jeu en console (3)" + color.RESET_COLOR + " \n" + color.GREEN_COLOR + "Jeu en interface graphique (4)" + color.RESET_COLOR + " \n" + color.GREEN_COLOR + "Quitter (5)" + color.RESET_COLOR);
                    Scanner scanChoix = new Scanner(System.in);
                    choix = scanChoix.nextInt();
                    System.out.println();
                    if (choix > 0 && choix <= 5){
                        valideChoix = true;
                    }
                }

                catch(Exception InputMismatchException){
                    System.out.println("\n" + Color.RED_COLOR +"La saisie est incorrecte !!!" + Color.RESET_COLOR + "\n");
                }
            }

            // Editeur en console
            if (choix == 1){
                EditeurConsole EC = new EditeurConsole();
            }

            // Editeur en interface graphique
            else if (choix == 2){
                Menu tmp = new Menu();
                valide = true;
            }

            // Jeu en console
            else if (choix == 3){
                JeuConsole JC = new JeuConsole();
            }

            // Jeu en interface graphique
            else if (choix == 4){
                
            }

            // Quitter
            else if (choix == 5){
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

            valideChoix = false;
            
        }
 
    }
}
