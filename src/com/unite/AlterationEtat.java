package com.unite;

import com.plateau.*;
import com.player.Joueur;
import java.lang.*;
import java.util.*;

public class AlterationEtat {
    protected String nom;
    protected int tourRestant;

    public AlterationEtat(String nom, int tourRestant){
        this.nom = nom;
        this.tourRestant = tourRestant;
    }

    public void etourdissement(Terrain t, int x, int y){
        if (tourRestant > 0) {
            t.getPlateau()[y][x].getUnite().setPointAction(0);
            tourRestant -= 1;
        }
    }

    public void poison(Terrain t, int x, int y){
        if (tourRestant > 0){
            t.getPlateau()[y][x].getUnite().setSanteCourante(t.getPlateau()[y][x].getUnite().getSanteCourante()-20);
            tourRestant-=1;
        }
    }

    public void immobilise(Terrain t, int x, int y){
        if (tourRestant > 0){
            t.getPlateau()[y][x].getUnite().setPorteeDeplacement(-1);
            tourRestant-=1;
        }
    }

    public void ralentissement(Terrain t, int x, int y){
        if (tourRestant > 0){
            t.getPlateau()[y][x].getUnite().setPorteeDeplacement(t.getPlateau()[y][x].getUnite().getPorteeDeplacement()-1);
            tourRestant-=1;
        }
    }

    public void aveugle(Terrain t, int x, int y){
        if (tourRestant > 0){
            t.getPlateau()[y][x].getUnite().setPorteeAttaque(-1);
            tourRestant-=1;
        }
    }
}
