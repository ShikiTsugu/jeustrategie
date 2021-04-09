package com.unite;

import com.player.Joueur;

public class Assassin extends Unite{

    public Assassin(Joueur joueur){
        super(joueur);
        santeMax = 100; //discussion en cours
        santeCourante = 100;
        attaque = 200; //discussion en cours
        coutUnite = 500; //discussion en cours
        porteeDeplacement = 5; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,200)};
        competences[0] = new Competence("poignarder","effectue un coup de dague",event, 1,1);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,50),
        new Evenement("appliquePoison",0,0,3)};
        competences[1] = new Competence("poignard empoisoné","empoisonne un ennemi pendant 3 tours",event2, 1,1);

    }

    public String toString(){
        return "Assassin";
    }

    public final boolean isHero(){
        return false;
    }
}