package com.unite;

import com.plateau.*;

import java.util.HashMap;

public class Competence {

    protected String name;
    protected String description;
    protected Evenement[] effets;
    protected int cout;
    protected int portee;
    protected int cooldown;
    protected  int cooldownActuel;


    public Competence(String n,String d, Evenement[] e,int p,int c, int cooldown){
        name = n;
        description = d;
        effets = e;
        cout = c;
        portee = p;
        this.cooldown = cooldown;
    }

    public int getCooldown(){return cooldown;}

    public void setCooldown(int cooldown){this.cooldown = cooldown;}

    public int getCooldownActuel() {
        return cooldownActuel;
    }

    public void setCooldownActuel(int cooldownActuel) {
        this.cooldownActuel = cooldownActuel;
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

    public HashMap<String, Integer> useSkill(int xA , int yA, int xD, int yD, Terrain terrain){
        HashMap<String,Integer> resultat = new HashMap<String,Integer>();
        if(terrain.getPlateau()[yA][xA].getUnite().getPointAction()>=cout && ((Math.abs(yA - yD)+Math.abs(xA - xD)) <= portee + terrain.getPlateau()[yA][xA].getUnite().modifPortee()) &&  cooldownActuel<= 0) {
            for (int i = 0; i < effets.length; i++) {
                HashMap<String,Boolean> recap = effets[i].readEvent(xD,yD,terrain);
                for(String j : recap.keySet()){
                    if(recap.get(j)) {
                        if (resultat.containsKey(j)) {
                            resultat.put(j, resultat.get(j) + 1);
                        } else {
                            resultat.put(j, 1);
                        }
                    }
                }
            }
            terrain.getPlateau()[yA][xA].getUnite().setPointAction(terrain.getPlateau()[yA][xA].getUnite().getPointAction()-cout);
            cooldownActuel = cooldown;
            return resultat;
        }
        return resultat;
    }


}
