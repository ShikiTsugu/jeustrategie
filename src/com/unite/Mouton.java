package com.unite;

import com.player.Joueur;

public class Mouton extends Unite{
    protected Unite unite;
    public Mouton(Joueur joueur) {
        super(joueur);
        santeMax = 50; //discussion en cours
        santeCourante = 50; //discusssion en cours
        attaque = 50; //discussion en cours
        coutUnite = 0; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[1];
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("Attaque du mouton","donne un coup de tête à son adversaire",event, porteeAttaque,pointAction-1, 0,this);
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
}
