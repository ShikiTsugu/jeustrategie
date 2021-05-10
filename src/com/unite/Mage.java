package com.unite;

import com.player.Joueur;

public class Mage extends Unite{
    
    public Mage(Joueur joueur){
        super(joueur);
        santeMax = 125; //discussion en cours
        santeCourante = 125;
        attaque = 125; //discussion en cours
        attInit = 125; //discussion en cours
        coutUnite = 250; //discussion en cours
        porteeDeplacement = 4; //discussion en cours
        porteeAttaque = 4; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        competences = new Competence[3];
        setComp();
    }
    
    public String toString(){
        return "Mage";
    }
    
    public final boolean isHero(){
        return false;
    }

    public void setComp() {
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("tir maquique","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction, 0,this);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,attaque,joueur),
                new Evenement("infligeDegats",1,0,attaque,joueur),
                new Evenement("infligeDegats",-1,0,attaque,joueur),
                new Evenement("infligeDegats",0,1,attaque,joueur),
                new Evenement("infligeDegats",0,-1,attaque,joueur),
        };
        competences[1] = new Competence("météore","inflige "+attaque+" points de dégâts à l'unité ciblée et dans ses alentours",event2, porteeAttaque-1,pointAction, 4,this);
        Evenement[] event3 = {new Evenement("transformationMouton", 0, 0, 3,joueur)};
        competences[2] = new Competence("transformation en mouton", "transforme l'unité ciblée temporairement en mouton", event3, porteeAttaque, pointAction, 6,this);
    }
}
