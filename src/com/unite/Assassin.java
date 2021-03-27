package com.unite;

import com.player.Joueur;

public class Assassin extends Unite{

    public Assassin(Joueur joueur){
        super(joueur);
        santeMax = 100; //discussion en cours
        santeCourante = 100;
        attaque = 60; //discussion en cours
        coutUnite = 300; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,60)};
        competences[0] = new Competence("poignarder","effectue un coup de dague",event, 1,1);
    }

    public String toString(){
        return "Assassin";
    }

    public final boolean isHero(){
        return false;
    }
}