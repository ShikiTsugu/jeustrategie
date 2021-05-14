package com.unite;

import java.lang.*;

//Class héritant de AlterationEtat, contenant les fonctions de debuff pour les unités
public class Debuff extends AlterationEtat {
    public Debuff(String nom, int tourRestant, Unite u){
        super(nom, tourRestant, u);
    }

    public void readDebuff(){
        if(nom.equals("etourdissement")){ etourdissement(); }
        if(nom.equals("poison")){ poison(); }
        if(nom.equals("immobilise")){ immobilise(); }
        if(nom.equals("ralentissement")){ ralentissement(); }
        if(nom.equals("aveugle")){ aveugle(); }
        if(nom.equals("mouton")){ mouton(); }
    }

    public void etourdissement(){ //ne possède plus de PA
        if (tourRestant > 0) {
            unite.setPointAction(-10);
            tourRestant -= 1;
        }
        if(tourRestant <= 0){
            unite.addBuff("immuniteEtourdissement",3); //suite à la fin d'un étourdissement, l'unité devient immunisé contre cet effet temporairement
        }
    }

    public void poison(){
        if (tourRestant > 0){
            unite.setSanteCourante(unite.getSanteCourante()-50); //l'unité perd 50 points de vie par tour
            unite.estMort(unite.getTerrain(), unite.getCurrentX(), unite.getCurrentY()); //l'unité peut mourir du poison
            tourRestant-=1;
        }
    }

    public void immobilise(){
        if (tourRestant > 0){
            unite.setPorteeDeplacement(-10); //ne peut plus se déplacer
            tourRestant-=1;
        }
    }

    public void ralentissement(){
        if (tourRestant > 0){
            unite.setPorteeDeplacement(unite.getPorteeDeplacement()-1); //réduit la portée de déplacement
            tourRestant-=1;
        }
    }

    public void aveugle(){
        if (tourRestant > 0){
            unite.setPorteeAttaque(-10); //ne peut plus attaquer
            tourRestant-=1;
        }
    }

    public void mouton(){
        if (tourRestant <= 1 && unite.toString().equals("Mouton")){ //si l'effet de la transformation du mouton se termine
            unite.getJoueur().detransformationPremierMouton(); //retransforme le mouton, en unité d'origine
        }
        tourRestant-=1;
    }

}
