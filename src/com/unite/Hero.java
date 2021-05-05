package com.unite;

import com.player.Joueur;

public class Hero extends Unite{
    
    public Hero(Joueur joueur){
        super(joueur);
        santeMax = 1000; //discussion en cours
        santeCourante = 1000; //discusssion en cours
        attaque = 250; //discussion en cours
        coutUnite = 0; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1, 0);
    }
    
    public String toString(){
        return "Hero";
    }
    
    public final boolean isHero(){
        return true;
    }
}