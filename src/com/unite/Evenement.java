package com.unite;

import com.plateau.*;

import java.util.HashMap;

public class Evenement {
    protected String event;
    protected int value;
    protected int x;
    protected int y;

    public Evenement(String e, int x,int y,int v){
        event = e;
        this.x=x;
        this.y=y;
        value = v;
    }

    public int getValue(){ return value;}

    public void setValue(int value){this.value = value;}

    public HashMap<String,Boolean> readEvent(int x, int y, Terrain t){
        HashMap<String, Boolean> resultat = new HashMap<String,Boolean>();
        if(x+this.x < 0 || x+this.x >= t.getPlateau()[0].length ||y+this.y < 0 || y+this.y >= t.getPlateau().length || t.getPlateau()[y+this.y][x+this.x].getUnite() ==null ){
            return resultat;
        }

        if(event.equals("infligeDegats") && t.getPlateau()[y+this.y][x+this.x].getUnite().getPeutEtreAttaque()){
            int res = t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()-value;
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(res);
            boolean b = t.getPlateau()[y+this.y][x+this.x].getUnite().estMort(t, x+this.x, y+this.y);
            resultat.put("cible tué",b);
            return resultat;
        }
        if(event.equals("soin")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()+value);
            return resultat;
        }
        if(event.equals("appliqueEtourdissement")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteEtourdissement")==false) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("etourdissement", value);
            }
            return resultat;
        }
        if (event.equals("appliqueCamouflage")) {
            t.getPlateau()[y + this.y][x + this.x].getUnite().addBuff("camouflage", value);
            return resultat;
        }
        if(event.equals("appliqueMort") && t.getPlateau()[y+this.y][x+this.x].getUnite().isHero() == false){
            if (t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante() <= 100){
                t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(0);
                t.getPlateau()[y+this.y][x+this.x].getUnite().estMort(t, x+this.x, y+this.y);
            }
            return resultat;
        }
        if(event.equals("appliqueBuffProphete") && t.getPlateau()[y+this.y][x+this.x].getUnite().isHero() == false){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("buffProphete", value);
            return resultat;
        }
        if(event.equals("donnePa")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setPointAction(t.getPlateau()[y+this.y][x+this.x].getUnite().getPointAction()+1);
            return resultat;
        }
        if(event.equals("mettreBarricade")){
            if (t.getPlateau()[y+this.y][x+this.x].estVide() && value > 0){
                t.getPlateau()[y+this.y][x+this.x].setObstacle(true);
                value-=1;
                return resultat;
            }
            else if(value == 0) t.getPlateau()[y+this.y][x+this.x].setObstacle(false);
            return resultat;
        }
        if(event.equals("appliqueResistEtourdissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteEtourdissement",value);
            return resultat;
        }
        if(event.equals("appliquePoison")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immunitePoison")==false) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("poison", value);
            }
            return resultat;
        }
        if(event.equals("appliqueResistPoison")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immunitePoison",value);
            return resultat;
        }
        if(event.equals("appliqueImmobilise")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteImmobilise")==false) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("immobilise", value);
            }
            return resultat;
        }
        if(event.equals("appliqueResistImmobilise")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteImmobilise",value);
            return resultat;
        }
        if(event.equals("appliqueRalentissement")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteRalentissement")==false) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("ralentissement", value);
            }
            return resultat;
        }
        if(event.equals("appliqueResistRalentissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteRalentissement",value);
            return resultat;
        }
        if(event.equals("appliqueAveugle")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteAveugle")==false) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("aveugle", value);
            }
            return resultat;
        }
        if(event.equals("appliqueResistAveugle")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteAveugle",value);
            return resultat;
        }

        return resultat;
    }

}
