package com.unite;

import com.player.Joueur;

import java.util.concurrent.CompletionException;

public class Lancier extends Unite{

    public Lancier(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 75; //discussion en cours
        attInit = 75; //discussion en cours
        coutUnite = 150; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[3];
        setComp();
    }

    public String toString(){
        return "Lancier";
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup de lance","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction-1, 0,this);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,50,joueur),
                new Evenement("appliqueEtourdissement",0,0,3,joueur)
        };
        competences[1] = new Competence("maniement agile","inflige "+(attaque-25)+" points de dégâts à l'unité ciblée et étourdit l'unité ciblée",event2, porteeAttaque,pointAction-1, 0,this);
    }
}