package com.unite;

import com.player.Joueur;

public class Lancier extends Unite{

    public Lancier(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 75; //discussion en cours
        coutUnite = 150; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,75)};
        competences[0] = new Competence("coup de lance","effectue un coup avec sa lance",event, 1,1);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,50),
                new Evenement("appliqueEtourdissement",0,0,3)
        };
        competences[1] = new Competence("maniement agile","le lancier effectue des movements précis avec sa lance pour donner un coup bien placé" +
                " et étourdir l'ennemi",event2, 1,1);
    }

    public String toString(){
        return "Lancier";
    }

    public final boolean isHero(){
        return false;
    }
}