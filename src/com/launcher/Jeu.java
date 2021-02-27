package com.launcher;

import com.plateau.Case;
import com.plateau.Model;
import com.plateau.Terrain;
import com.plateau.Vue;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.unite.Hero;
import com.unite.Templier;
import com.unite.Unite;

import java.io.File;

public class Jeu {


    protected Joueur joueur1;
    protected Joueur joueur2;
    protected Terrain terrain;
    protected Joueur tourDuJoueur;
    protected String requeteCourante;



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters\Setters
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * getter pour le joueur 1
     * @return le joueur 1
     */
    public Joueur getJoueur1() {
        return joueur1;
    }

    /**
     * getter pour le joueur 2
     * @return le joueur 2
     */
    public Joueur getJoueur2() {
        return joueur2;
    }

    /**
     * getter pour le com.plateau.Terrain
     * @return le com.plateau.Terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * getter pour le joueur auqeul c'est à qui de jouer
     * @return le joueur a qui est le tour
     */
    public Joueur getTourDuJoueur() {
        return tourDuJoueur;
    }

    /**
     * getter de la requete courante
     * @return la requete courante
     */
    public String getRequeteCourante() {
        return requeteCourante;
    }

    /**
     * setter pour le nouveau joueur 1
     * @param joueur1 le nouveau joueur 1
     */
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    /**
     * setter pour le nouveau joueur 2
     * @param joueur2 le nouveau joueur 2
     */
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    /**
     * setter pour le nouveau terrain
     * @param terrain le nouveau terrain
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }


    /**
     * setter du joueur à qui est le tour de jouer
     * @param tourDuJoueur le nouveau joueur a qui est le tour
     */
    public void setTourDuJoueur(Joueur tourDuJoueur) {
        this.tourDuJoueur = tourDuJoueur;
    }

    /**
     * setter pour changer la requete courante
     * @param requeteCourante la nouvelle requete
     */
    public void setRequeteCourante(String requeteCourante) {
        this.requeteCourante = requeteCourante;
    }


    /**
     * fonction qui verifie si la partie est finie
     * @return le boolean disant si la partie est finie
     * A MODIFIER
     */
   /* public boolean gameIsOver(){
        if(joueur1.getHero().getPv() <= 0 || joueur2.getHero().getPv() <=0) 
            return true;
        
        return false;
    }*/

    /**
     * fonction d'initialisation de debut de partie
     * @param joueur1 le joueur 1
     * @param joueur2 le joueur 2
     * @param terrain le terrain choisi
     * A MODIFIER
     */
    public void startNewGame(Joueur joueur1 ,Joueur joueur2, Terrain terrain){
        setJoueur1(joueur1);
        setJoueur2(joueur2);
        setTerrain(terrain);
        setTourDuJoueur(joueur1);
    }
    /*
    public void playGame(){
        startNewGame();
        while(!gameIsOver()){


        }
    }
    */
    private static String selectGoodPath(){
        String path = System.getProperty("user.dir");
        File checkPath = new File(path);
        if(path.endsWith("jeu-de-strat") || hasAGoodChild(checkPath,"src")){
            path+="/src/com";
        }else if(path.endsWith("src") || hasAGoodChild(checkPath,"com")){
            path+="/com";
        }
        return path;
    }

    private static boolean hasAGoodChild(File checkPath,String wanted){
        File[] listOfChildren = checkPath.listFiles();
        if (listOfChildren==null)return false;
        for(File child:listOfChildren){
            if(child.toString().endsWith(wanted)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Terrain terrain = new Terrain(5,5,2);
        Joueur joueur = new Joueur(200);
        joueur.initialiseListeUnites(terrain);
        Hero h = new Hero(joueur);
        ActionJoueur act = new ActionJoueur(joueur);
        Templier templier = new Templier(joueur);
        System.out.println(joueur.ajouteUnite(h));
        System.out.println(act.placeUnite(terrain,templier,0,0));
        Model m = new Model(selectGoodPath() + "/plateau/plaine.png");
        Vue v = new Vue(m, terrain);
        v.AfficheTerrain();
    }
}