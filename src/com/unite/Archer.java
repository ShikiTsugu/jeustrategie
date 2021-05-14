package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

import java.util.HashMap;

public class Archer extends Unite{

    public Archer(Joueur joueur){
        super(joueur);
        santeMax = 200;
        santeCourante = 200;
        attaque = 100;
        attInit = 100;
        coutUnite = 300;
        porteeDeplacement = 4;
        porteeAttaque = 4;
        pointActionMax = 2;
        pointAction = 2;
        competences = new Competence[2];
        setComp();
    }
    
    public String toString(){
        return "Archer";
    }


    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){//Modification de la fonction pour prendre en charge l'effet du passif predateur
        HashMap<String,Integer> recap= new HashMap<String,Integer>();
        if (c >=0 && c < competences.length) {
            recap = competences[c].useSkill(xA, yA, xD, yD, t);
            if (recap.containsKey("cible tué") && recap.get("cible tué") >= 1 && competences[1].getCooldownActuel() <= 0) {
                pointAction += 1;
                competences[1].setCooldownActuel(1);
            }
        }
    }
    
    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("tir à l'arc","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque+1,pointAction-1, 0,this);
        Evenement[] event2 = {};
        competences[1] = new Competence("prédateur","(passif) 1 fois par tour, gagne 1 PA lorsqu'il tue un ennemi",event2, -1,999, 1,this);
    }
}
