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
        competences = new Competence[3];
        Evenement[] event = {new Evenement("infligeDegats",0,0,150)};
        competences[0] = new Competence("tir maquique","lancer un projectile magique",event, 5,1, 0);
        Evenement[] event2 = {new Evenement("donnePa",0,0,150)};
        competences[1] = new Competence("donationPA","redonne de la force à une unitée, lui confère 1 PA supplémentaire",event2, 4,1, 3);
        Evenement[] event3 = {new Evenement("appliqueBuffProphete", 0, 0, 2)};
        competences[2] = new Competence("La voie du Seigneur", "applique brièvement la puissance du Seigneur, attaque, portée de déplacement, portée d'attaque et santé courante multipliées par 2!", event3, 4, 2, 2);
    }

    public String toString(){
        return "Prophete";
    }

    public final boolean isHero(){
        return false;
    }
}
