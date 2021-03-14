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

    public void placeUniteApresAchat(Unite u, ActionJoueur j, boolean J1){
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public boolean acheteUnite(Joueur j, Unite u){
        if (!j.ajouteUnite(u)) {
            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le max d'unité a été atteint.", "", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Achat impossible");
            return false;
        } else {
            ActionJoueur aj = new ActionJoueur(j);
            aj.setBought(true);
            placeUniteApresAchat(u, aj,true);
            return true;
        }
    }

}
