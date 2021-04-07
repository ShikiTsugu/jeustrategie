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


}
