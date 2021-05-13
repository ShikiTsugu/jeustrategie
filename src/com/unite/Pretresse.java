package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

import java.util.HashMap;

public class Pretresse extends Unite{
    int suiviSoins ;//le suivi du nombre de pv restauré. 500 pv doivent être restauré pour pouvoir invoquer le Prophète

    public Pretresse(Joueur joueur){//constructeur de Pretresse
        super(joueur);
        santeMax = 150;
        santeCourante = 150;
        attaque = 50;
        attInit = 50;
        coutUnite = 400;
        porteeDeplacement = 5;
        porteeAttaque = 5;
        pointActionMax = 1;
        pointAction = 1;
        suiviSoins = 0 ;
        competences = new Competence[3];
        setComp();
    }

    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){//modification de la fonction pour quelle récupère le nombre de pv restauré
        HashMap<String,Integer> recap= new HashMap<String,Integer>();
        if (c >=0 && c < competences.length){
            if(c==2 && (suiviSoins <500 ||joueur.getPropheteInvoc())) {
                return;
            }
            recap =competences[c].useSkill(xA,yA,xD,yD,t);
            if ( c == 0 && recap.containsKey("PV soigné") && recap.get("PV soigné") >= 1){
                suiviSoins += recap.get("PV soigné");
            }

        }


    }

    public String toString(){
        return "Pretresse";
    }

    public final boolean isHero(){
        return false;
    }

    public void setComp() {//fonction qui initialise les compétences
        Evenement[] event = {new Evenement("soin",0,0,attaque,joueur)};
        competences[0] = new Competence("soin","soigne "+attaque+" points à une unité",event, porteeAttaque,pointAction, 0,this);
        Evenement[] event2 = {new Evenement("appliqueAveugle",0,0,3,joueur)};
        competences[1] = new Competence("lumière aveuglante","aveugle les ennemis , ce qui les empêchent d'attaquer temporairement",event2, porteeAttaque,pointAction, 0,this);
        Evenement[] event3 = {new Evenement("invocation Prophete",0,0,0,joueur)};
        competences[2] = new Competence("implorer Le Seigneur","invoque l'unité \"Le prophète\", dans votre zone, à l'endroit choisi",event3,porteeAttaque ,pointAction,99,this);
    }
}
