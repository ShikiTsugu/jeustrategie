package com.unite;

import com.player.Joueur;

import java.util.concurrent.CompletionException;

public class Lancier extends Unite{

    public Lancier(Joueur joueur){
        super(joueur);
        santeMax = 200;
        santeCourante = 200;
        attaque = 75;
        attInit = 75;
        coutUnite = 150;
        porteeDeplacement = 3;
        porteeAttaque = 1;
        pointActionMax = 2;
        pointAction = 2;
        competences = new Competence[2];
        setComp();
    }

    public String toString(){
        return "Lancier";
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup de lance","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction-1, 0,this);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,50,joueur),
                new Evenement("appliqueEtourdissement",0,0,4,joueur)
        };
        competences[1] = new Competence("maniement agile","inflige "+(attaque-25)+" points de dégâts à l'unité ciblée et étourdit l'unité ciblée",event2, porteeAttaque,pointAction-1, 0,this);
    }
}