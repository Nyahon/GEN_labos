package models; /****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO      Informatique & Télécommunications   *
 * Fichier:     models.Emetteur.java                                   *
 * Date :	      1er Octobre 2016    - Départ             		    *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
*/


import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class Emetteur extends Observable implements Runnable {


  private int dureeSeconde ;                    // Durée sec. en msec.

  private int secondes = 0;						// Compteur de secondes


// Constructeur
    public Emetteur (int dureeSeconde) {

        this.dureeSeconde = dureeSeconde;
    }

    public void run(){
        try {
            while(true) {
                heureMettreAJour();
                Thread.sleep(dureeSeconde);
            }

        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void heureMettreAJour () {
        secondes = ++secondes % 60;
        setChanged();
        notifyObservers(secondes);

        if(secondes == 0){
            notifyObservers(secondes);
        }
    }

}
