package com.unite;

import com.player.Joueur;

public class Mage extends Unite{
    
    public Mage(Joueur joueur){
        super(joueur);
        santeMax = 125; //discussion en cours
        santeCourante = 125;
        attaque = 125; //discussion en cours
        coutUnite = 250; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 4; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,125,joueur)};
        competences[0] = new Competence("tir maquique","lancer un projectile magique",event, 5,1, 0);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,125,joueur),
                new Evenement("infligeDegats",1,0,125,joueur),
                new Evenement("infligeDegats",-1,0,125,joueur),
                new Evenement("infligeDegats",0,1,125,joueur),
                new Evenement("infligeDegats",0,-1,125,joueur),
        };
        competences[1] = new Competence("météore","fait tomber un météore devastateur du ciel pour l'abatre sur ces ennemis",event2, 5,1, 1);
    }
    
    public String toString(){
        return "Mage";
    }
    
    public final boolean isHero(){
        return false;
    }
}
