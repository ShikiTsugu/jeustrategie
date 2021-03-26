package com.unite;

import com.player.Joueur;

public class Lancier extends Unite{

    public Lancier(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 30; //discussion en cours
        coutUnite = 300; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
    }

    public String toString(){
        return "Lancier";
    }

    public final boolean isHero(){
        return false;
    }
}