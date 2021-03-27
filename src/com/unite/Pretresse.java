package com.unite;

import com.player.Joueur;

public class Pretresse extends Unite{

    public Pretresse(Joueur joueur){
        super(joueur);
        santeMax = 125; //discussion en cours
        santeCourante = 125;
        attaque = 50; //discussion en cours
        coutUnite = 400; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 5; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("soin",0,0,50)};
        competences[0] = new Competence("soin","soigne une unité",event, 5,1);
    }

    public String toString(){
        return "Pretresse";
    }

    public final boolean isHero(){
        return false;
    }
}
