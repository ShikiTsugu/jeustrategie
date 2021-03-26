package com.unite;

import com.plateau.*;

public class Competence {

    protected String name;
    protected String description;
    protected Evenement[] effets;
    protected int cout;
    protected  int portee;


    public Competence(String n,String d, Evenement[] e,int p,int c){
        name = n;
        description = d;
        effets = e;
        cout = c;
        portee = p;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCout() {
        return cout;
    }

    public boolean useSkill(int xD , int yD, int x,int y, Terrain terrain){
        if(terrain.getPlateau()[yD][xD].getUnite().getPointAction()>=cout && ((Math.abs(yD - y)+Math.abs(xD - x)) <= portee)) {
            for (int i = 0; i < effets.length; i++) {
                effets[i].readEvent(x,y,terrain);
            }
            terrain.getPlateau()[yD][xD].getUnite().setPointAction(terrain.getPlateau()[yD][xD].getUnite().getPointAction()-cout);
            return true;
        }
        return false;
    }


}
