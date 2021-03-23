package com.plateau;

import com.launcher.Jeu;
import com.player.*;
import com.unite.Unite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlleur {
    private Vue vue;
    private Jeu jeu;

    public Controlleur(Vue v){
        vue = v;
    }

    public void placeUniteApresAchat(Unite u, ActionJoueur j, boolean J1){
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                if(vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                    j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                }else{
                    j.getJoueur().annuleAjout(u);
                    j.getJoueur().setArgent(j.getJoueur().getArgent()+u.getCoutUnite());
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Il y a déjà une unité.", "", JOptionPane.PLAIN_MESSAGE);
                }
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

    public void attaque(Joueur j, JButton attaquant){
        ActionJoueur aj = new ActionJoueur(j);
        int[]coordI = {attaquant.getX()/attaquant.getWidth(), attaquant.getY()/attaquant.getHeight()};
        int[]coordF = new int[2];
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX()/b.getWidth();
                coordF[1] = b.getY()/b.getHeight();
                aj.attaqueUnite(vue.terrain,coordI[0],coordI[1],coordF[0],coordF[1]);
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void finDeTour(){
        jeu.finDeTour();
        vue.setTourJoueur(jeu.getTourDuJoueur());
    }

    public void deplaceUnite(Joueur j, JButton posIni){
        ActionJoueur aj = new ActionJoueur(j);
        int[]coordI = {posIni.getX()/posIni.getWidth(), posIni.getY()/posIni.getHeight()};
        int[]coordF = new int[2];
        for (JButton b : vue.terrainBt) {
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX()/b.getWidth();
                coordF[1] = b.getY()/b.getHeight();
                aj.deplaceUnite(vue.terrain, coordI[0],coordI[1],coordF[0],coordF[1]);
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
