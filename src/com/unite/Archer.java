package com.unite;

import com.player.Joueur;

public class Archer extends Unite{
    
    public Archer(Joueur joueur){
        super(joueur);
        santeMax = 150; //discussion en cours
        santeCourante = 150;
        attaque = 30; //discussion en cours
        coutUnite = 300; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 5; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
    }
    
    public String toString(){
        return "Archer";
    }
    
    public final boolean isHero(){
        return false;
    }
}
