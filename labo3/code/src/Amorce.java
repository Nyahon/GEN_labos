import models.Emetteur;
import models.Pendule;
import views.VueEmetteur;
import views.VuePendule;

/****************************************************************
 * Auteur:	    Eric Lefrançois                                 *
 * Groupe:	    HES_SO  Informatique & Télécommunications       *
 * Fichier:     1er Octobre 2015-  DEPART		                *
 * Projet:	    Horloges synchronisées                          *
 ****************************************************************
 */

public class Amorce {
	private static int posX = 0;
	public static void main(String argv[]) {

		Emetteur e = new Emetteur(100);    // models.Emetteur avec une seconde de 100msec
		// Création d'une pendule, avec une seconde valant 120msec (plus lente que l'emetteur
		VueEmetteur ve = new VueEmetteur(e);
		e.addObserver(ve);
		new Thread(e).start();


		createPendule("P1-90", 90, e);
		createPendule("P2-80", 80, e);
		createPendule("P3-50", 50, e);
		createPendule("Pident-E", 100, e);





	}
	private static void createPendule(String name, int valSecondes, Emetteur e){
		Pendule p = new Pendule(valSecondes);
		VuePendule vp = new VuePendule(p, name,  posX, 200);
		p.addObserver(vp);
		e.addObserver(p);
		new Thread(p).start();
		posX+=250;
	}
}
