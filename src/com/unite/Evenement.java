package com.unite;

import com.plateau.*;

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

    public boolean readEvent(int x, int y, Terrain t){
        if(x+this.x < 0 || x+this.x >= t.getPlateau()[0].length ||y+this.y < 0 || y+this.y >= t.getPlateau().length || t.getPlateau()[y+this.y][x+this.x].getUnite() ==null ){
            return false;
        }

        if(event.equals("infligeDegats")){
            int res = t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()-value;
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(res);
            t.getPlateau()[y+this.y][x+this.x].getUnite().estMort(t, x+this.x, y+this.y);
            return true;
        }
        if(event.equals("soin")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()+value);
            return true;
        }
        if(event.equals("appliqueEtourdissement")){
            if(!t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteEtourdissement")) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("etourdissement", value);
            }
            return true;
        }
        if(event.equals("appliqueResistEtourdissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addDebuff("immuniteEtourdissement",value);
            return true;
        }
        if(event.equals("appliquePoison")){
            if(!t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immunitePoison")) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("poison", value);
            }
            return true;
        }
        if(event.equals("appliqueResistPoison")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addDebuff("immunitePoison",value);
            return true;
        }
        if(event.equals("appliqueImmobilise")){
            if(!t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteImmobilise")) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("immobilise", value);
            }
            return true;
        }
        if(event.equals("appliqueResistImmobilise")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addDebuff("immuniteImmobilise",value);
            return true;
        }
        if(event.equals("appliqueRalentissement")){
            if(!t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteRalentissement")) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("ralentissement", value);
            }
            return true;
        }
        if(event.equals("appliqueResistRalentissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addDebuff("immuniteRalentissement",value);
            return true;
        }
        if(event.equals("appliqueAveugle")){
            if(!t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteAveugle")) {
                t.getPlateau()[y + this.y][x + this.x].getUnite().addDebuff("aveugle", value);
            }
            return true;
        }
        if(event.equals("appliqueResistAveugle")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteAveugle",value);
            return true;
        }

        return false;
    }

}
