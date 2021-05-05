package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

import java.util.HashMap;

public class Pretresse extends Unite{
    int suiviSoins ;

    public Pretresse(Joueur joueur){
        super(joueur);
        santeMax = 150; //discussion en cours
        santeCourante = 150;
        attaque = 25; //discussion en cours
        coutUnite = 400; //discussion en cours
        porteeDeplacement = 5; //discussion en cours
        porteeAttaque = 3; //discussion en cours
        pointActionMax = 1; //discussion en cours
        pointAction = 1; //discussion en cours
        suiviSoins = 0 ;
        competences = new Competence[3];
        Evenement[] event = {new Evenement("soin",0,0,50,joueur)};
        competences[0] = new Competence("soin","soigne une unité",event, 5,1, 0);
        Evenement[] event2 = {new Evenement("appliqueAveugle",0,0,3,joueur)};
        competences[1] = new Competence("lumière aveuglante","aveugle les ennemis , ce qui les empêchent d'attaquer",event2, 5,1, 0);
        Evenement[] event3 = {new Evenement("invocation Prophete",0,0,0,joueur)};
        competences[2] = new Competence("implorer Le Seigneur","prie les cieux pour qu'ils offrent leur assitsance",event3,5 ,1,99);

    }

    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){
        HashMap<String,Integer> recap= new HashMap<String,Integer>();
        if (c >=0 && c < competences.length){
            if(c==2 && (/*suiviSoins <500 ||*/joueur.getPropheteInvoc())) {
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
}
