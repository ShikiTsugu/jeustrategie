package com.unite;

import com.player.Joueur;

public class Archer extends Unite{
    
    public Archer(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 100; //discussion en cours
        coutUnite = 300; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 5; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,30)};
        competences[0] = new Competence("tir à l'arc","tire une flèche",event, 5,1);
    }
    
    public String toString(){
        return "Archer";
    }
    
    public final boolean isHero(){
        return false;
    }
}
