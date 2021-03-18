package com.plateau;

import com.launcher.Jeu;
import com.player.*;
import com.unite.Unite;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controlleur {
    private Vue vue;
    private Jeu jeu;

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
            boolean b = jeu.getTourDuJoueur() == jeu.getJoueur1();
            placeUniteApresAchat(u, aj,b);
            return true;
        }
    }

    public void attaque(JButton attaquant){
        Unite atq = vue.getTerrain().getPlateau()[attaquant.getY()/attaquant.getHeight()][attaquant.getX()/attaquant.getWidth()].getUnite();
        int xAtq = attaquant.getX()/attaquant.getWidth();
        int yAtq = attaquant.getY()/attaquant.getHeight();
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                atq.attaqueUnite(vue.getTerrain(),xAtq,yAtq,b.getX()/b.getWidth(),b.getY()/b.getHeight());
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void finDeTour(){
        jeu.finDeTour();
        vue.setTourJoueur(jeu.getTourDuJoueur());
    }

    public void deplaceUnite(JButton posAct){
        Unite act = vue.getTerrain().getPlateau()[posAct.getY()/posAct.getHeight()][posAct.getX()/posAct.getWidth()].getUnite();
        System.out.println("l'unité : "+act);
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                act.deplaceUnite(vue.getTerrain(),posAct.getX()/posAct.getWidth(),posAct.getY()/posAct.getHeight(),b.getX()/b.getWidth(),b.getY()/b.getHeight());
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void setJeu(Jeu j){
        jeu = j;
    }

    public Jeu getJeu(){
        return jeu;
    }

}
