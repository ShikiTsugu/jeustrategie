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

    public boolean useSkill(int xA , int yA, int xD,int yD, Terrain terrain){
        if(terrain.getPlateau()[yA][xA].getUnite().getPointAction()>=cout && ((Math.abs(yA - yD)+Math.abs(xA - xD)) <= portee)) {
            for (int i = 0; i < effets.length; i++) {
                effets[i].readEvent(xD,yD,terrain);
            }
            terrain.getPlateau()[yA][xA].getUnite().setPointAction(terrain.getPlateau()[yA][xA].getUnite().getPointAction()-cout);
            return true;
        }
        return false;
    }


}
