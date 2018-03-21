package views;

import models.Pendule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class VuePendule  extends JFrame implements Observer {

    private int minutes = 0;       	// Compteurs de la pendule
    private int secondes = 0;
    private int heures = 0;

    private static int TAILLE = 100; // Taille de la demi-fenétre
    private ToileGraphique toile;
    private JButton plus;

    //------------------------------------------------------------------------
    class ToileGraphique extends JPanel {

        public ToileGraphique() {
            setBackground(Color.white);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            dessinerAiguilles(g);

        }


        public Dimension getPreferredSize() {
            return new Dimension(2 * TAILLE, 2 * TAILLE);
        }


        public void dessinerAiguilles(Graphics g) {
            // calculer les coordonnées des aiguilles
            int cosxm = (int) (TAILLE + (TAILLE / 2) *
                    Math.cos(2 * ((double) minutes / 60 * Math.PI - Math.PI / 4)));
            int sinym = (int) (TAILLE + (TAILLE / 2) *
                    Math.sin(2 * ((double) minutes / 60 * Math.PI - Math.PI / 4)));
            int cosxh = (int) (TAILLE + (TAILLE / 4) *
                    Math.cos(2 * ((double) heures / 12 * Math.PI - Math.PI / 4)));
            int sinyh = (int) (TAILLE + (TAILLE / 4) *
                    Math.sin(2 * ((double) heures / 12 * Math.PI - Math.PI / 4)));

            g.setColor(Color.red);
            g.drawLine(TAILLE, TAILLE,
                    (int) (TAILLE + (TAILLE - 20.0) *
                            Math.cos(2 * ((double) secondes / 60 * Math.PI - Math.PI / 4))),
                    (int) (TAILLE + (TAILLE - 20) *
                            Math.sin(2 * ((double) secondes / 60 * Math.PI - Math.PI / 4))));

            g.setColor(Color.blue);
            g.drawLine(TAILLE, TAILLE, cosxm, sinym);
            g.drawLine(TAILLE, TAILLE, cosxh, sinyh);
        }
    }
    public VuePendule(Pendule p, String nom, int posX, int posY){

        toile = new ToileGraphique();



        setTitle(nom);
        getContentPane().add (toile, BorderLayout.CENTER);
        dessinerTemps();
        dessinerBouton(p);

        pack();
        setResizable(false);
        setLocation (posX, posY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void dessinerBouton(Pendule p){
        plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                p.incrementerMinutes();
            }
        });
        getContentPane().add(plus, BorderLayout.NORTH);
    }
    public void dessinerTemps(){
        JLabel jlabel = new JLabel(secondes+":"+minutes+":"+heures){
            @Override
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
            }
        };
        jlabel.setFont(new Font("Verdana",1,20));
        getContentPane().add(jlabel, BorderLayout.SOUTH);
    }

    public void update(Observable o, Object p){
        secondes = ( (int[]) p)[0];
        minutes = ( (int[]) p)[1];
        heures = ( (int[]) p)[2];
        //toile.repaint();
        getContentPane().repaint();



    }
}
