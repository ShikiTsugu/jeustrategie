package com.unite;

import com.player.Joueur;

public class Mage extends Unite{
    
    public Mage(Joueur joueur){
        super(joueur);
        santeMax = 125; //discussion en cours
        santeCourante = 125;
        attaque = 50; //discussion en cours
        coutUnite = 400; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 5; //discussion en cours
        pointAction = 1; //discussion en cours
    }
    
    public String toString(){
        return "mage";
    }
    
    public final boolean isHero(){
        return false;
    }
}
