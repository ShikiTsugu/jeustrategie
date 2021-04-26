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
        competences = new Competence[4];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque)};
        competences[0] = new Competence("poignarder","effectue un coup de dague",event, 1,1, 0);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,50),
        new Evenement("appliquePoison",0,0,3)};
        competences[1] = new Competence("poignard empoisoné","empoisonne un ennemi pendant 3 tours",event2, 1,1, 0);
        Evenement[] event3 = {new Evenement("appliqueCamouflage", 0, 0, 2)};
        competences[2] = new Competence("camouflage", "ne peut plus se faire attaquer par des ennemis", event3, 0, 1, 4);
        Evenement[] event4 = {new Evenement("appliqueMort", 0, 0, 0)};
        competences[3] = new Competence("assassinat", "achève de manière vicieuse un ennemi en dessous de 100 pv où qu'il soit", event4, 99, 1, 4);

    }

    public String toString(){
        return "Assassin";
    }

    public final boolean isHero(){
        return false;
    }
}