package com.player;

import com.plateau.Case;
import com.plateau.Terrain;
import com.unite.Unite;

import javax.swing.*;

public class ActionJoueur {
    private Joueur joueur;
    private boolean bought;

    public ActionJoueur(Joueur j){
        joueur = j;
    }

    public Joueur getJoueur(){return joueur;}

    public boolean getBought(){return bought;}

    public void setBought(boolean b){bought = b;}

    //Action du joueur pour acheter une unité.
    public boolean acheteUnite(Unite unite, JPanel p){
        //si le joueur n'a pas assez d'argent pour acheter l'unité, on retourne false.
        if(joueur.getArgent()< unite.getCoutUnite()) {
            System.out.println("Pas assez d'argent");
            JOptionPane.showMessageDialog(p, "Pas assez d'argent.", "", JOptionPane.PLAIN_MESSAGE);
            return bought=false;
            //sinon on soustrait l'argent du joueur par le cout de l'unité, et on ajoute cette unité dans sa liste d'unité.
        }else{
            System.out.println(joueur.getArgent());
            joueur.setArgent(joueur.achat(unite.getCoutUnite()));
            System.out.println(joueur.getArgent());
            return bought=true;
        }
    }

    public boolean placeUnite(Terrain t, Unite unite, int x, int y,boolean isJ1){
        if(isJ1){
            if(!t.getPlateau()[y][x].J1CanBuy()){
                return false;
            }
        }else{
            if(!t.getPlateau()[y][x].J2CanBuy()){
                return false;
            }
        }
        //si l'unité est bien acheté, on place l'unité dans le terrain à la position souhaité par le joueur.
        if(bought){
            return t.ajouteUnite(unite,x,y);
        }
        return false;
    }

    public boolean deplaceUnite(Terrain t, int xAvant, int yAvant, int xDestination, int yDestination){
        //verifie si l'unite appartient au joueur
        Unite unite = t.getPlateau()[yAvant][xAvant].getUnite();
        Case destination = t.getPlateau()[yDestination][xDestination];
        if(joueur == unite.getJoueur()){
            unite.deplaceUnite(t, xAvant, yAvant, xDestination, yDestination);
            return true ;
        }
        return false;
    }

    public boolean attaqueUnite(Terrain t, int xA, int yA, int xD, int yD){
        Case depart = t.getPlateau()[yA][xA];
        Case cible = t.getPlateau()[yD][xD];
        if(depart.getUnite() == null) return false;

        //verifie si l'unite appartient au joueur
        if(joueur != depart.getUnite().getJoueur()) return false;

        //verifie si l'emplacement à une unité puis si cette dernière est du camp opposé
        if(cible.getUnite() == null || cible.getUnite().getJoueur() == joueur) return false;

        depart.getUnite().attaqueUnite(t, xA, yA, xD, yD);
        return true;
    }
}
