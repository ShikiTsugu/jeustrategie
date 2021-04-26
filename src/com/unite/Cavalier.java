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
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1, 0);
        Evenement[] event2 = {new Evenement("charge", 0, 0, attaque-50)};
        competences[1] = new Competence("charge", "charge en ligne droite pour infliger des dégats", event2, porteeDeplacement, 2, 4);
    }
    
    public String toString(){
        return "Cavalier";
    }
    
    public final boolean isHero(){
        return false;
    }
}
