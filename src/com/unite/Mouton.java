package com.unite;

import com.player.Joueur;

public class Mouton extends Unite{
    protected Unite unite;//contient l'unité transformé
    public Mouton(Joueur joueur) {//constructeur de Mouton
        super(joueur);
        santeMax = 50;
        santeCourante = 50;
        attaque = 50;
        attInit = 50;
        coutUnite = 0;
        porteeDeplacement = 4;
        porteeAttaque = 1;
        pointActionMax = 2;
        pointAction = 2;
        competences = new Competence[1];
        setComp();
    }

    public String toString(){
        return "Mouton";
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("Attaque du mouton","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction-1, 0,this);
    }
}
