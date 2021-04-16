package com.unite;

import com.player.Joueur;

public class Pretresse extends Unite{

    public Pretresse(Joueur joueur){
        super(joueur);
        santeMax = 150; //discussion en cours
        santeCourante = 150;
        attaqueMax = 25; //discussion en cours
        attaqueCourante = 25; //discussion en cours
        coutUnite = 400; //discussion en cours
        porteeDeplacement = 5; //discussion en cours
        porteeAttaque = 3; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("soin",0,0,attaqueCourante)};
        competences[0] = new Competence("soin","soigne une unité",event, 5,1);
        Evenement[] event2 = {new Evenement("appliqueAveugle",0,0,3)};
        competences[1] = new Competence("lumière aveuglante","aveugle les ennemis , ce qui les empêchent d'attaquer",event2, 5,1);
    }

    public String toString(){
        return "Pretresse";
    }

    public final boolean isHero(){
        return false;
    }
}
