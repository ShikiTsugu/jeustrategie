package com.unite;

import com.player.Joueur;

public class Templier extends Unite{
    
    public Templier(Joueur joueur){
        super(joueur);
        santeMax = 150; //discussion en cours
        santeCourante = 150;
        attaque = 50; //discussion en cours
        coutUnite = 100; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,50)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1);

    }
    
    public String toString(){
        return "Templier";
    }
    
    public final boolean isHero(){
        return false;
    }
}