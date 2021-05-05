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
        competences = new Competence[3];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque)};
        competences[0] = new Competence("tir maquique","lancer un projectile magique",event, 5,1, 0);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,attaque),
                new Evenement("infligeDegats",1,0,attaque),
                new Evenement("infligeDegats",-1,0,attaque),
                new Evenement("infligeDegats",0,1,attaque),
                new Evenement("infligeDegats",0,-1,attaque),
        };
        competences[1] = new Competence("météore","fait tomber un météore devastateur du ciel pour l'abatre sur ces ennemis",event2, 4,2, 4);
        Evenement[] event3 = {new Evenement("transformationMouton", 0, 0, 2)};
        competences[2] = new Competence("transformation en mouton", "transforme son adversaire en mouton temporairement", event3, 4, 2, 6);
    }
    
    public String toString(){
        return "Mage";
    }
    
    public final boolean isHero(){
        return false;
    }
}
