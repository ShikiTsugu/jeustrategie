package com.unite;

import com.plateau.*;

import java.util.HashMap;

//Classe contenant toutes les informations à propos d'une compétence et permet son utilisation
public class Competence {

    protected String name;
    protected String description;
    protected Evenement[] effets;
    protected int cout;
    protected int portee;
    protected int cooldown;
    protected  int cooldownActuel;
    protected Unite unite;

    public Competence(String n,String d, Evenement[] e,int p,int c, int cooldown, Unite u){//constructeur d'une compétence
        name = n;
        description = d;
        effets = e;
        cout = c;
        portee = p;
        this.cooldown = cooldown;
        unite = u;
    }

    public void setEffets(Evenement[] effets) {
        this.effets = effets;
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

    public int getPortee(){return portee;}

    //Fonction qui déroule la suite d'evenements d'une compétence et renvoi certaines information sur son utilisation(notamment le nombre d'ennemi tué ou la quantité de
    // soin effectués)
    public HashMap<String, Integer> useSkill(int xA , int yA, int xD, int yD, Terrain terrain){
        HashMap<String,Integer> resultat = new HashMap<String,Integer>();
        if(terrain.getPlateau()[yA][xA].getUnite().getPointAction()>=cout && ((Math.abs(yA - yD)+Math.abs(xA - xD)) <= portee + terrain.getPlateau()[yA][xA].getUnite().modifPortee()) &&  cooldownActuel<= 0) {
            unite.setPointAction(unite.getPointAction()-cout);
            cooldownActuel = cooldown;
            for (int i = 0; i < effets.length; i++) {
                System.out.println(effets[i].getEvent());
                HashMap<String,Integer> recap = effets[i].readEvent(xD,yD,terrain);
                //récupère les informations d'un évenement
                for(String j : recap.keySet()){
                    if(recap.get(j) >= 1) {
                        if (resultat.containsKey(j)) {
                            resultat.put(j, resultat.get(j) + recap.get(j));
                        } else {
                            resultat.put(j, recap.get(j));
                        }
                    }
                }
            }
            return resultat;
        }
        return resultat;
    }
}
