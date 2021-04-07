package com.unite;

import com.plateau.*;
import com.player.Joueur;
import java.lang.*;
import java.util.*;

public class AlterationEtat {
    protected String nom;
    protected Unite unite;
    protected int tourRestant;

    public AlterationEtat(String nom, int tourRestant,Unite u){
        this.nom = nom;
        this.tourRestant = tourRestant;
        unite = u;
    }

    public int getTourRestant(){
        return tourRestant;
    }

    public void readAlterationEtat(){
        if(nom.equals("etourdissement")){
            etourdissement();
        }
        if(nom.equals("poison")){
            poison();
        }
        if(nom.equals("immobilise")){
            immobilise();
        }
        if(nom.equals("ralentissement")){
            ralentissement();
        }
        if(nom.equals("aveugle")){
            aveugle();
        }
    }

    public void etourdissement(){
        if (tourRestant > 0) {
            unite.setPointAction(0);
            tourRestant -= 1;
        }
    }

    public void poison(){
        if (tourRestant > 0){
            unite.setSanteCourante(unite.getSanteCourante()-20);
            tourRestant-=1;
        }
    }

    public void immobilise(){
        if (tourRestant > 0){
            unite.setPorteeDeplacement(-1);
            tourRestant-=1;
        }
    }

    public void ralentissement(){
        if (tourRestant > 0){
            unite.setPorteeDeplacement(unite.getPorteeDeplacement()-1);
            tourRestant-=1;
        }
    }

    public void aveugle(){
        if (tourRestant > 0){
            unite.setPorteeAttaque(-1);
            tourRestant-=1;
        }
    }
}
