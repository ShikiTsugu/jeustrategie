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

    public boolean readEvent(int x,int y,Terrain t){
        if(x+this.x < 0 || x+this.x >= t.getPlateau()[0].length ||y+this.y < 0 || y+this.y >= t.getPlateau().length || t.getPlateau()[y+this.y][x+this.x].getUnite() ==null ){
            return false;
        }

        if(event.equals("infligeDegats")){
            int res = t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()-value;
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(res);
            return true;
        }
        if(event.equals("soin")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()+value);
            return true;
        }
        if(event.equals("appliqueEtourdissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addAlterationEtat("etourdissement",value);
            return true;
        }
        if(event.equals("appliquePoison")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addAlterationEtat("poison",value);
            return true;
        }
        if(event.equals("appliqueImmobilise")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addAlterationEtat("immobilise",value);
            return true;
        }
        if(event.equals("appliqueRalentissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addAlterationEtat("ralentissement",value);
            return true;
        }
        if(event.equals("appliqueAveugle")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addAlterationEtat("aveugle",value);
            return true;
        }

        return false;
    }

}
