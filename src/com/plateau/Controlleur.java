package com.plateau;

import com.player.*;
import com.unite.Unite;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controlleur {
    private Vue vue;

    public Controlleur(Vue v){
        vue = v;
    }

    public boolean uniteAchete(Unite u, Joueur j){
        for(Unite un : j.getUnites()){
            if(un==u) return true;
        }
        return false;
    }

    public void placeUniteApresAchat(Unite u, ActionJoueur j, boolean J1){
        if(uniteAchete(u,j.getJoueur())){
            for(JButton b : vue.terrainBt){
                b.addActionListener((ActionEvent e) -> j.placeUnite(vue.terrain,u,b.getX(),b.getX(),J1));
            }
        }
    }

    public void acheteUnite(Joueur j, Unite u){
        if (!j.ajouteUnite(u)) {
            System.out.println("Achat impossible");
        };

    }

}
