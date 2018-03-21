package models; /********************************************************************
 * Auteur:	    Eric Lefrançois                                     *
 * Groupe:	    HES_SO/EIG  Informatique & Télécommunication        *
 * Fichier:     models.Pendule.java                                        *
 * Date :	    1er Octobre 2015-  DEPART                 		    *
 * Projet:	    Horloges synchronisées                              *
 ********************************************************************
*/


import java.awt.*;
import java.lang.Math;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;




public class Pendule extends Observable implements Runnable, Observer {
//Classe qui décrit une montre avec un affichage des aiguilles
	
	private int dureeSeconde;       // Durée de la seconde en msec.
    private int minutes = 0;       	// Compteurs de la pendule
    private int secondes = 0;
    private int heures = 0;








	public void run() {
		try {
			while(true) {
				incrementerSecondes();
				Thread.sleep(dureeSeconde);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



    //------------------------------------------------------------------------
    public Pendule (int valSeconde) {

	    dureeSeconde = valSeconde;
   }

    public void incrementerSecondes(){
    	secondes ++;
        if (secondes == 60) {   
        	secondes = 0;
        	incrementerMinutes();
        }
        setChanged();
        notifyObservers( new int[]{secondes,minutes,heures} );
    }

    public void incrementerMinutes() {
      minutes = ++minutes % 60 ;
      if (minutes == 0) {
          heures = ++heures % 24;
      }
    }

    public void update(Observable o, Object p){
        secondes = (int)p ;
        setChanged();
        notifyObservers( new int[]{secondes,minutes,heures} );

    }

}
