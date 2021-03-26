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

    public void readEvent(int x,int y,Terrain t){
        if(event.equals("infligeDegats")){
            int res = t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()-value;
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(res);
        }
        if(event.equals("soin")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()+value);
        }
    }

}
