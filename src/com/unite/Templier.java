package com.unite;

import com.player.Joueur;

public class Templier extends Unite{
    
    public Templier(Joueur joueur){
        super(joueur);
        santeMax = 150; //discussion en cours
        santeCourante = 150;
        attaque = 50; //discussion en cours
        coutUnite = 100; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[3];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1, 0,this);
        Evenement[] event2 = {new Evenement("appliqueResistEtourdissement",0,0,5,joueur),
                new Evenement("appliqueResistImmobilise",0,0,5,joueur),
                new Evenement("appliqueResistRalentissement",0,0,5,joueur),
                new Evenement("appliqueResistAveugle",0,0,5,joueur)};
        competences[1] = new Competence("conviction innébranlable","le templier,armé de son courage et d'une volonté à toute épreuve " +
                "ne laissera rien le ralentir dans dans sa mission. L'imuunise contre tout effet de contrôle",event2, 0,1, 0,this);
        Evenement[] event3 = {};
        competences[2] = new Competence("Richesse", "Si le templier se trouve sur une case de gold, il gagnera le double", event3, -1, 999, 1,this);
    }
    
    public String toString(){
        return "Templier";
    }
    
    public final boolean isHero(){
        return false;
    }
}