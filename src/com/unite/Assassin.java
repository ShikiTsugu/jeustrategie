package com.unite;

import com.player.Joueur;

public class Assassin extends Unite{

    public Assassin(Joueur joueur){//constructeur d'Assassin
        super(joueur);
        santeMax = 100;
        santeCourante = 100;
        attaque = 200;
        attInit = 200;
        coutUnite = 500;
        porteeDeplacement = 5;
        porteeAttaque = 1;
        pointActionMax = 2;
        pointAction = 2;
        competences = new Competence[4];
        setComp();
    }

    public String toString(){
        return "Assassin";
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("poignarder","inflige "+attaque+" points de dégâts à l'unité ciblée",event, porteeAttaque,pointAction-1, 0,this);
        Evenement[] event2 = {new Evenement("infligeDegats",0,0,attaque-150,joueur),
        new Evenement("appliquePoison",0,0,3,joueur)};
        competences[1] = new Competence("poignard empoisoné","applique poison à l'unité ciblée (50 points de dégâts par tour)",event2, porteeAttaque,pointAction-1, 1,this);
        Evenement[] event3 = {new Evenement("appliqueCamouflage", 0, 0, 2,joueur)};
        competences[2] = new Competence("camouflage", "applique camouflage qu'à soi-même (ne peut plus se faire attaquer par des ennemis)", event3, 0, pointAction, 3,this);
        Evenement[] event4 = {new Evenement("appliqueMort", 0, 0, 0,joueur)};
        competences[3] = new Competence("assassinat", "achève un ennemi en dessous de 100 pv, où qu'il soit", event4, 99, pointAction-1, 4,this);
    }
}