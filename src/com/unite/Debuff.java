package com.unite;

import java.lang.*;

public class Debuff extends AlterationEtat {
    public Debuff(String nom, int tourRestant, Unite u){
        super(nom, tourRestant, u);
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
