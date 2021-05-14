package com.unite;

import com.plateau.*;
import com.player.ActionJoueur;
import com.player.Joueur;

import java.util.HashMap;

//Permet d'effectuer un changement sur le plateau, plus particulièrement sur les unités qui s'y trouvent.
public class Evenement {
    protected String event;
    protected Joueur j;
    protected int value;
    protected int x;
    protected int y;
    protected Unite unite;


    public Evenement(String e, int x,int y,int v,Joueur j){//constructeur Evenement (utilisé généralement lorsqu'une position est donné)
        event = e;
        this.x=x;
        this.y=y;
        value = v;
        this.j = j;
    }

    public Evenement(String e, Unite unite, int x,int y,int v){//constructeur utilisé généralement lorsqu'une unité est directement spécifiée
        event = e;
        this.unite = unite;
        this.x=x;
        this.y=y;
        value = v;
    }

    public int getValue(){ return value;}

    public String getEvent() {
        return event;
    }

    public void setValue(int value){this.value = value;}

    //Fonction qui effectue l'action de l'evenement
    public HashMap<String,Integer> readEvent(int x, int y, Terrain t){
        HashMap<String, Integer> resultat = new HashMap<String,Integer>();

        if(event.equals("invocation Prophete")){
            ActionJoueur a = new ActionJoueur(j);
            Prophete p = new Prophete(j);
            if(a.acheteUnite(p,j.getJeu().getV().getTerrainPanel()) && j.ajouteUnite(p)){
                boolean b = j.getJeu().getTourDuJoueur() == j.getJeu().getJoueur1();
                a.placeUnite(t,p,x,y,b);

            }

            return resultat;
        }
        if(event.equals("deplacementUniteSpecifique")){
            if(unite.estDecede() || unite.currentY+this.y < 0 || unite.getCurrentY()+this.y >= t.getPlateau().length ||
                    unite.currentX+this.x < 0 || unite.getCurrentX()+this.x >= t.getPlateau()[unite.currentY+this.y].length||!t.getPlateau()[unite.currentY+this.y][unite.currentX+this.x].estVide()){

                return resultat;
            }else{

                t.getPlateau()[unite.currentY][unite.currentX].setUnite(null);
                t.getPlateau()[unite.currentY+this.y][unite.currentX+this.x].setUnite(unite);
                unite.setPositionUnite(t.getPlateau()[unite.currentY+this.y][unite.currentX+this.x]);
                unite.setCurrentY(unite.currentY+this.y);
                unite.setCurrentX(unite.currentX+this.x);

            }
        }

        if(x+this.x < 0 || x+this.x >= t.getPlateau()[0].length ||y+this.y < 0 || y+this.y >= t.getPlateau().length || t.getPlateau()[y+this.y][x+this.x].getUnite() ==null ){
            return resultat;
        }



        if(event.equals("infligeDegats") && t.getPlateau()[y+this.y][x+this.x].getUnite().getPeutEtreAttaque()){
            int res = t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()-value;
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(res);
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().estMort(t, x+this.x, y+this.y))
            resultat.put("cible tué",1);
            return resultat;
        }
        if(event.equals("infligeDegatsUniteSpecifique") && unite.getPeutEtreAttaque()){
            int res = unite.getSanteCourante()-value;
            unite.setSanteCourante(res);
            if(unite.estMort(t, unite))
                resultat.put("cible tué",1);
            return resultat;
        }
        if(event.equals("soin")){
            int pvAvant= t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante();
            t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante()+value);
            resultat.put("PV soigné",t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante() - pvAvant);
            return resultat;
        }
        if(event.equals("appliqueEtourdissement")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteEtourdissement")==false) {
                Debuff debuff = new Debuff("etourdissement",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
                t.getPlateau()[y+this.y][x+this.x].getUnite().getDebuffs().add(debuff);
                debuff.readDebuff();
            }
            return resultat;
        }
        if(event.equals("appliqueEtourdissementUniteSpecifique")){
            if(unite.possedeBuff("immuniteEtourdissement")==false) {
                Debuff debuff = new Debuff("etourdissement",value,unite);
                unite.getDebuffs().add(debuff);
                debuff.readDebuff();
            }
            return resultat;
        }
        if (event.equals("appliqueCamouflage")) {
            Buff buff = new Buff("camouflage",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
            t.getPlateau()[y+this.y][x+this.x].getUnite().getBuffs().add(buff);
            buff.readBuff();
            return resultat;
        }
        if(event.equals("appliqueMort") && t.getPlateau()[y+this.y][x+this.x].getUnite().isHero() == false){
            if (t.getPlateau()[y+this.y][x+this.x].getUnite().getSanteCourante() < 100){
                t.getPlateau()[y+this.y][x+this.x].getUnite().setSanteCourante(0);
                t.getPlateau()[y+this.y][x+this.x].getUnite().estMort(t, x+this.x, y+this.y);
            }
            return resultat;
        }


        if(event.equals("appliqueBuffProphete") && t.getPlateau()[y+this.y][x+this.x].getUnite().isHero() == false){
            Buff buff = new Buff("buffProphete",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
            t.getPlateau()[y+this.y][x+this.x].getUnite().getBuffs().add(buff);
            buff.readBuff();
            resultat.put("success",1);
            return resultat;
        }
        if(event.equals("donnePa")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().setPointAction(t.getPlateau()[y+this.y][x+this.x].getUnite().getPointAction()+1);
            return resultat;
        }
        if(event.equals("transformationMouton")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite() instanceof Hero == false) {
                Unite u = t.getPlateau()[y + this.y][x + this.x].getUnite();
                Mouton m = new Mouton(u.getJoueur());
                m.setTerrain(t);
                m.setPositionUnite(t.getPlateau()[y + this.y][x + this.x]);
                Joueur j = u.getJoueur();
                m.setUnite(u);
                m.addDebuff("mouton", value);
                m.setCurrentY(m.getUnite().getCurrentY());
                m.setCurrentX(m.getUnite().getCurrentX());
                j.annuleAjout(u);
                j.ajouteUnite(m);
                t.getPlateau()[y + this.y][x + this.x].setUnite(m);
            }
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
                Debuff debuff = new Debuff("immobilise",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
                t.getPlateau()[y+this.y][x+this.x].getUnite().getDebuffs().add(debuff);
                debuff.readDebuff();
            }

            return resultat;
        }
        if(event.equals("appliqueResistImmobilise")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteImmobilise",value);
            return resultat;
        }
        if(event.equals("appliqueRalentissement")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteRalentissement")==false) {
                Debuff debuff = new Debuff("ralentissement",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
                t.getPlateau()[y+this.y][x+this.x].getUnite().getDebuffs().add(debuff);
                debuff.readDebuff();
            }
            return resultat;
        }
        if(event.equals("appliqueResistRalentissement")){
            t.getPlateau()[y+this.y][x+this.x].getUnite().addBuff("immuniteRalentissement",value);
            return resultat;
        }
        if(event.equals("appliqueAveugle")){
            if(t.getPlateau()[y+this.y][x+this.x].getUnite().possedeBuff("immuniteAveugle")==false) {
                Debuff debuff = new Debuff("aveugle",value,t.getPlateau()[y+this.y][x+this.x].getUnite());
                t.getPlateau()[y+this.y][x+this.x].getUnite().getDebuffs().add(debuff);
                debuff.readDebuff();
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
