package com.player;

import com.plateau.Case;
import com.plateau.Terrain;
import com.unite.Unite;

import javax.swing.*;

//Contient toutes les méthodes utiles aux actions qu'effectue le joueur
public class ActionJoueur {
    private Joueur joueur;
    private boolean bought; //permet de savoir si l'unité a bien été acheté ou non

    public ActionJoueur(Joueur j){
        joueur = j;
    }

    public Joueur getJoueur(){return joueur;}

    public void setBought(boolean b){bought = b;}

    //Action du joueur pour acheter une unité.
    public boolean acheteUnite(Unite unite, JPanel p){
        //si le joueur n'a pas assez d'argent pour acheter l'unité, on retourne false.
        if(joueur.getArgent()< unite.getCoutUnite()) {
            JOptionPane.showMessageDialog(p, "Pas assez d'argent.", "", JOptionPane.PLAIN_MESSAGE);
            return bought=false;
        }else{
            //sinon on soustrait l'argent du joueur par le cout de l'unité, et on ajoute cette unité dans sa liste d'unité.
            joueur.setArgent(joueur.achat(unite.getCoutUnite()));
            return bought=true;
        }
    }

    //Place l'unité acheté sur le terrain
    public boolean placeUnite(Terrain t, Unite unite, int x, int y,boolean isJ1){
        //Vérifie si l'unité est bien dans la zone de placement possible du joueur spécifique, sinon retourne false.
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

    //Déplace l'unité choisit en donnant sa position initiale et la position d'arrivée souhaitée.
    public boolean deplaceUnite(Terrain t, int xAvant, int yAvant, int xDestination, int yDestination){
        //verifie si l'unite appartient au joueur, si c'est le cas on la déplace.
        Unite unite = t.getPlateau()[yAvant][xAvant].getUnite();
        if(joueur == unite.getJoueur()){
            unite.deplaceUnite(t, xAvant, yAvant, xDestination, yDestination);
            return true ;
        }
        return false;
    }

    //Méthode permettant à une unité d'en attaquer une autre selon leurs coordonnées, peut aussi attaquer dans le vide.
    public boolean attaqueUnite(Terrain t, int xA, int yA, int xD, int yD){
        Case depart = t.getPlateau()[yA][xA];
        Case cible = t.getPlateau()[yD][xD];
        if(depart.getUnite() == null) return false;

        //verifie si l'unité appartient au joueur
        if(joueur != depart.getUnite().getJoueur()) return false;

        //verifie si l'emplacement a une unité puis si cette dernière est du camp opposé
        if(cible.getUnite() == null || cible.getUnite().getJoueur() == joueur) return false;

        depart.getUnite().attaqueUnite(t, xA, yA, xD, yD);
        return true;
    }
}
