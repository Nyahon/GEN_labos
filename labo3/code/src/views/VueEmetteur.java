package views;

import models.Emetteur;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueEmetteur extends JFrame implements Observer {

    private final static int LARGEUR = 100;		// largeur fenêtre de l'emetteur
    private final static int HAUTEUR = 100;		// hauteur fenêtre de l'emetteur

    private JLabel champAffichage = new JLabel("00");
    private Font fonte = new Font ("TimeRoman",  Font.BOLD, 80);

    public VueEmetteur (Emetteur e) {

        getContentPane().add("North", champAffichage);
        champAffichage.setSize(LARGEUR, HAUTEUR);
        champAffichage.setFont (fonte);
        setTitle("models.Emetteur");

        pack();
        setLocation(200, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void update(Observable o, Object p){
        champAffichage.setText ( String.valueOf( (int)p ) );

    }
}
