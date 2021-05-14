package com.unite;

import com.player.Joueur;

public class Templier extends Unite{
    
    public Templier(Joueur joueur){//constructeur de Templier
        super(joueur);
        santeMax = 150;
        santeCourante = 150;
        attaque = 50;
        attInit = 50;
        coutUnite = 100;
        porteeDeplacement = 4;
        porteeAttaque = 1;
        pointActionMax = 2;
        pointAction = 2;
        competences = new Competence[3];
        setComp();
    }
    
    public String toString(){
        return "Templier";
    }
    
    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction-1, 0,this);
        Evenement[] event2 = {new Evenement("appliqueResistEtourdissement",0,0,5,joueur),
                new Evenement("appliqueResistImmobilise",0,0,5,joueur),
                new Evenement("appliqueResistRalentissement",0,0,5,joueur),
                new Evenement("appliqueResistAveugle",0,0,5,joueur)};
        competences[1] = new Competence("conviction innébranlable","le templier s'immunise contre tout effet de contrôle",event2, 0,pointAction-1, 9,this);
        Evenement[] event3 = {};
        competences[2] = new Competence("Richesse", "(passif) si le templier se trouve sur une case de gold, il gagnera le double", event3, -1, 999, 1,this);
    }
}