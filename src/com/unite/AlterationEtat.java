package com.unite;

import com.plateau.*;
import com.player.Joueur;
import java.lang.*;
import java.util.*;

public class AlterationEtat {
    protected String nom;
    protected Unite unite;
    protected int tourRestant;
    protected Debuff debuff;

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
            debuff.etourdissement();
        }
        if(nom.equals("poison")){
            debuff.poison();
        }
        if(nom.equals("immobilise")){
            debuff.immobilise();
        }
        if(nom.equals("ralentissement")){
            debuff.ralentissement();
        }
        if(nom.equals("aveugle")){
            debuff.aveugle();
        }
    }
}
