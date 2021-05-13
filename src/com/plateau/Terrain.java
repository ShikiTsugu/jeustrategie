package com.plateau;

import com.unite.Unite;

import java.io.Serializable;

// Terrain contient tout le plateau de jeu (les cases, les obstacles et les cases spéciaux) ainsi que tout les unité qui sont sur le plateau
public class Terrain implements Serializable {
    protected Case[][] plateau;
    protected int maxUnits;
    protected int nbUnits;
    protected int xH1,yH1;
    protected int xH2,yH2;
    protected boolean[][] b1,b2;


    public Terrain(int largeur, int hauteur, int max,int x1,int y1, int x2, int y2, boolean[][] b1, boolean[][] b2){
        plateau = new Case[hauteur][largeur];
        maxUnits = max;
        nbUnits = 0;
        xH1 = x1;
        yH1 = y1;
        xH2 = x2;
        yH2 = y2;
        this.b1=b1;
        this.b2=b2;
        initialiseTerrain();
        setBuyableArea(b1,b2);
    }

    public boolean[][] getB1() {
        return b1;
    }

    public boolean[][] getB2() {
        return b2;
    }

    //Délimite la zone où le joueur peut acheter des unités
    public void setBuyableArea(boolean[][] b1, boolean[][] b2){
        for(int i=0;i<b1.length;i++){
            for(int j=0;j<b1[i].length;j++){
                plateau[i][j].setJ1Buyable(b1[i][j]);
                plateau[i][j].setJ2Buyable(b2[i][j]);
            }
        }
    }

    public Terrain(Case[][] p, int max){
        plateau = p;
        maxUnits = max;
    }

    //Ajoute l'unité sur le plateau
    public boolean ajouteUnite(Unite u, int x, int y){
        try{
            if(plateau[y][x].unit==null){
                plateau[y][x].setUnite(u);
                u.setPositionUnite(plateau[y][x]);
                u.setCurrentX(x);
                u.setCurrentY(y);
                u.setTerrain(this);
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    //initialise chaque case du plateau
    public void initialiseTerrain(){
        for (int x = 0; x < plateau.length; x++){
            for (int y = 0; y < plateau[x].length; y++){
                plateau[x][y] = new Case();
            }
        }
    }

    public int getMaxUnits(){return maxUnits;}

    public Case[][] getPlateau(){
        return plateau;
    }

    //Ajoute une case spécial sur le plateau
    public void setEffectCase(int x, int y, CaseEffect ce){
        plateau[y][x] = ce;
    }

}