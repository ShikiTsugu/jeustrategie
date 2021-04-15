package com.unite;

import com.player.Joueur;

public class Cavalier extends Unite{
    
    public Cavalier(Joueur joueur){
        super(joueur);
        santeMax = 300; //discussion en cours
        santeCourante = 300;
        attaque = 150; //discussion en cours
        coutUnite = 600; //discussion en cours
        porteeDeplacement = 6; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,150)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1, 0);
    }
    
    public String toString(){
        return "Cavalier";
    }
    
    public final boolean isHero(){
        return false;
    }
}
