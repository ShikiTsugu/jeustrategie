package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

import java.util.HashMap;

public class Archer extends Unite{

    boolean passifPredateurActif;
    
    public Archer(Joueur joueur){
        super(joueur);
        santeMax = 200; //discussion en cours
        santeCourante = 200;
        attaque = 100; //discussion en cours
        coutUnite = 300; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 4; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[2];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque)};
        competences[0] = new Competence("tir à l'arc","tire une flèche",event, 5,1, 0);
        Evenement[] event2 = {};
        competences[1] = new Competence("prédateur","1 fois par tour, gagne 1 PA lorsqu'il tue un ennemi",event2, -1,999, 1);

    }
    
    public String toString(){
        return "Archer";
    }


    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){
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
}
