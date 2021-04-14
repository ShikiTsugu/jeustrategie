package com.unite;

import com.player.Joueur;

public class Prophete extends Unite{
    public Prophete(Joueur joueur){
        super(joueur);
        santeMax = 500; //discussion en cours
        santeCourante = 500;
        attaque = 150; //discussion en cours
        coutUnite = 99999; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 4; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,150)};
        competences[0] = new Competence("tir maquique","lancer un projectile magique",event, 5,1);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,150),
                new Evenement("infligeDegats",1,0,150),
                new Evenement("infligeDegats",-1,0,150),
                new Evenement("infligeDegats",0,1,150),
                new Evenement("infligeDegats",0,-1,150),
        };
        //competences[1] = new Competence("météore","fait tomber un météore devastateur du ciel pour l'abatre sur ces ennemis",event2, 5,1);
    }

    public String toString(){
        return "Prophete";
    }

    public final boolean isHero(){
        return false;
    }
}
