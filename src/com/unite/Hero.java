package com.unite;

import com.player.Joueur;

public class Hero extends Unite{
    
    public Hero(Joueur joueur){
        super(joueur);
        santeMax = 1000; //discussion en cours
        santeCourante = 1000; //discusssion en cours
        attaque = 250; //discussion en cours
        attInit = 250; //discussion en cours
        coutUnite = 0; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[1];
        setComp();
    }
    
    public String toString(){
        return "Hero";
    }
    
    public final boolean isHero(){
        return true;
    }

    public void setComp(){
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque, pointAction, 0,this);
    }
}