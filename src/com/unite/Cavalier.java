package com.unite;

import com.player.Joueur;

public class Cavalier extends Unite{
    
    public Templier(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 50; //discussion en cours
        coutUnite = 500; //discussion en cours
        porteeDeplacement = 6; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointAction = 3; //discussion en cours
    }
    
    public String toString(){
        return "cavalier";
    }
    
    public final boolean isHero(){
        return false;
    }
}
