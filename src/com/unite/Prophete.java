package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

import java.util.HashMap;

public class Prophete extends Unite{

    protected Unite unite_buffed;
    public Prophete(Joueur joueur){
        super(joueur);
        santeMax = 500; //discussion en cours
        santeCourante = 500;
        attaque = 150; //discussion en cours
        attInit = 150; //discussion en cours
        coutUnite = 0; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 4; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[3];
        setComp();
    }


    public String toString(){
        return "Prophete";
    }

    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){
        HashMap<String,Integer> recap = new HashMap<String,Integer>();
        if (c >=0 && c < competences.length) {
            if(unite_buffed != null && c ==2 && unite_buffed.estDecede() == false){
                return;
            }

            recap = competences[c].useSkill(xA, yA, xD, yD, t);
            if(recap.containsKey("success")){
                unite_buffed = t.getPlateau()[yD][xD].getUnite();
            }

        }
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("tir maquique","inflige "+attaque+ " points de dégâts à l'unité ciblée",event, porteeAttaque+1,pointAction-1, 0,this);
        Evenement[] event2 = {new Evenement("donnePa",0,0,150,joueur)};
        competences[1] = new Competence("donationPA","redonne de la force à une unitée, lui confère 1 PA supplémentaire",event2, porteeAttaque,pointAction-1, 3,this);
        Evenement[] event3 = {new Evenement("appliqueBuffProphete", 0, 0, 999,joueur)};
        competences[2] = new Competence("La voie du Seigneur", "applique brièvement des buffs d'attaque, de portée de déplacement, de portée d'attaque et de santé courante", event3, porteeAttaque, pointAction, 2,this);
    }
}
