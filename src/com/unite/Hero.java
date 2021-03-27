package com.unite;

import com.player.Joueur;

public class Hero extends Unite{
    
    public Hero(Joueur joueur){
        super(joueur);
        santeMax = 300; //discussion en cours
        santeCourante = 300; //discusssion en cours
        attaque = 50; //discussion en cours
        coutUnite = 0; //discussion en cours
        porteeDeplacement = 5; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 3; //discussion en cours
        pointAction = 3; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,50)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1);
    }
    
    public String toString(){
        return "Hero";
    }
    
    public final boolean isHero(){
        return true;
    }
}