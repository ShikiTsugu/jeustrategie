package com.player;

import com.plateau.Case;
import com.plateau.Terrain;
import com.unite.Unite;

public class ActionJoueur {
    private Joueur joueur;
    protected Case[][] plateau;

    public ActionJoueur(Joueur j){
        joueur = j;
    }

    public Joueur getJoueur(){return joueur;}

    //Action du joueur pour acheter une unité.
    public boolean acheteUnite(Unite unite){
        //si le joueur n'a pas assez d'argent pour acheter l'unité, on retourne false.
        if(joueur.getArgent()< unite.getCoutUnite()) return false;
        //sinon on soustrait l'argent du joueur par le cout de l'unité, et on ajoute cette unité dans sa liste d'unité.
        else{
            joueur.setArgent(joueur.achat(unite.getCoutUnite()));
            return joueur.ajouteUnite(unite);
        }
    }

    public boolean placeUnite(Terrain t, Unite unite, int x, int y){
        //si l'unité est bien acheté, on place l'unité dans le terrain à la position souhaité par le joueur.
        if(acheteUnite(unite)){
            return t.ajouteUnite(unite,x,y);
        }
        return false;
    }

    public boolean deplaceUnite(int xAvant, int yAvant, int xDestination, int yDestination){
        //verifie si l'unite appartient au joueur
        Unite unite = plateau[yAvant][xAvant].getUnite();
        Case destination = plateau[yDestination][xDestination];
        if(joueur == unite.getJoueur()){
            unite.deplaceUnite(xAvant, yAvant, xDestination, yDestination);
            return true ;
        }
        return false;
    }

    public boolean attaqueUnite(int xA, int yA, int xD, int yD){
        Case depart = plateau[yA][xA];
        Case cible = plateau[yD][xD];
        if(depart.getUnite() == null) return false;

        //verifie si l'unite appartient au joueur
        if(joueur != depart.getUnite().getJoueur()) return false;

        //verifie si l'emplacement à une unité puis si cette dernière est du camp opposé
        if(cible.getUnite() == null || cible.getUnite().getJoueur() == joueur) return false;

        depart.getUnite().attaqueUnite(xA, yA, xD, yD);
        return true;
    }
}
