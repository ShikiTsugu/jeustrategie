package com.launcher;

import com.plateau.*;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.player.Robot;
import com.unite.*;
import java.io.File;

public class Jeu {

    protected Joueur joueur1;
    protected ActionJoueur actionjoueur1;
    protected Joueur joueur2;
    protected ActionJoueur actionjoueur2;
    protected Terrain terrain;
    protected Joueur tourDuJoueur;
    protected Model m = new Model(selectGoodPath() + "/assets/titlescreen.png");
    protected Vue v;

    public Joueur getJoueur1() {
        return joueur1;
    }
    public Joueur getJoueur2() {
        return joueur2;
    }
    public Terrain getTerrain() {
        return terrain;
    }
    public Vue getV(){
        return v;
    }
    public Joueur getTourDuJoueur() {
        return tourDuJoueur;
    }
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }
    public void setActionjoueur1(ActionJoueur actionjoueur1) {this.actionjoueur1 = actionjoueur1;}
    public void setActionjoueur2(ActionJoueur actionjoueur2) {this.actionjoueur2 = actionjoueur2;}
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public static String selectGoodPath(){ //fonction permettant de retrouver le chemin pour lancer le jeu
        String path = System.getProperty("user.dir");
        File checkPath = new File(path);
        if(path.endsWith("jeu-de-strat") || hasAGoodChild(checkPath,"src")){
            path+="/src/com";
        }else if(path.endsWith("src") || hasAGoodChild(checkPath,"com")){
            path+="/com";
        }
        return path;
    }

    public static boolean hasAGoodChild(File checkPath,String wanted){
        File[] listOfChildren = checkPath.listFiles();
        if (listOfChildren==null)return false;
        for(File child:listOfChildren){
            if(child.toString().endsWith(wanted)){
                return true;
            }
        }
        return false;
    }

    public void startGraphique(){ //initialise l'interface graphique du jeu
        setJoueur1(new Joueur(0, this));
        setActionjoueur1(new ActionJoueur(joueur1));
        setJoueur2(new Joueur(0, this));
        setActionjoueur2(new ActionJoueur(joueur2));
        v = new Vue(m,terrain);
        v.getControlleur().setJeu(this);
        v.afficheIni();
        tourDuJoueur = joueur1;
        v.setTourJoueur(joueur1);
    }

    public void setStartingMoney(int i){ //initialise l'argent du début du jeu aux deux joueurs
        joueur1.setArgent(i);
        joueur2.setArgent(i);
    }

    public void setPlayer(boolean b){ //initialise les deux joueurs
        if (b){
            setJoueur1(new Joueur(0, this));
            setActionjoueur1(new ActionJoueur(joueur1));
            setJoueur2(new Joueur(0, this));
            setActionjoueur2(new ActionJoueur(joueur2));
        } else {
            setJoueur1(new Joueur(0, this));
            setActionjoueur1(new ActionJoueur(joueur1));
            setJoueur2(new Robot(0, this));
            setActionjoueur2(new ActionJoueur(joueur2));
        }
        tourDuJoueur = joueur1;
        v.setTourJoueur(joueur1);
    }

    public void AjouteHero(){ //ajoute un héro pour chaque joueur, dans leurs camps respectifs
        joueur1.initialiseListeUnites(terrain);
        joueur2.initialiseListeUnites(terrain);
        Hero h1 = new Hero(joueur1);
        Hero h2 = new Hero(joueur2);
        joueur1.ajouteUnite(h1);
        joueur2.ajouteUnite(h2);
        terrain.ajouteUnite(h1,0,terrain.getPlateau().length/2);
        terrain.ajouteUnite(h2,terrain.getPlateau()[0].length-1,terrain.getPlateau().length/2);
        setTerrain(terrain);
    }

    public void finDeTour(){
        if (tourDuJoueur == joueur1) {
            tourDuJoueur = joueur2;
        } else {
            tourDuJoueur = joueur1;
        }
    }

    public void activateAlterationEtats(){ //actualise et applique les altérations d'états pour chaque unité du joueur
        for(int i=0 ; i<joueur2.getUnites().length;i++){
            if(joueur2.getUnites()[i]!=null) {
                joueur2.getUnites()[i].readAlterationEtats();
                joueur2.getUnites()[i].checkCooldowns();
            }
        }
        for(int i=0 ; i<joueur1.getUnites().length;i++){
            if(joueur1.getUnites()[i]!=null) {
                joueur1.getUnites()[i].readAlterationEtats();
                joueur1.getUnites()[i].checkCooldowns();
            }
        }
    }

    public static void main(String[] args) { //lancer le jeu
        Jeu jeu = new Jeu();
        jeu.startGraphique();
    }
}