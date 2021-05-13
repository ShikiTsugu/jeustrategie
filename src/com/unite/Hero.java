package com.unite;

import com.player.Joueur;

public class Hero extends Unite{
    
    public Hero(Joueur joueur){
        super(joueur);
        santeMax = 1000;
        santeCourante = 1000;
        attaque = 250;
        attInit = 250;
        coutUnite = 0;
        porteeDeplacement = 4;
        porteeAttaque = 1;
        pointActionMax = 1;
        pointAction = 1;
        competences = new Competence[1];
        setComp();
    }
    
    public String toString(){
        return "Hero";
    }
    
    public final boolean isHero(){
        return true;
    }

    public void setComp(){//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque, pointAction, 0,this);
    }
}