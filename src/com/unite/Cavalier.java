package com.unite;

import com.player.Joueur;

public class Cavalier extends Unite{
    
    public Cavalier(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 50; //discussion en cours
        coutUnite = 500; //discussion en cours
        porteeDeplacement = 6; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 3; //discussion en cours
        pointAction = 3; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,50)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1);
    }
    
    public String toString(){
        return "Cavalier";
    }
    
    public final boolean isHero(){
        return false;
    }
}
