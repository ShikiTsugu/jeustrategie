package com.plateau;

import com.unite.Unite;

public class Terrain {
    protected Case[][] plateau;
    protected int maxUnits;
    protected int nbUnits;

    public Terrain(int largeur, int hauteur, int max){
        plateau = new Case[hauteur][largeur];
        maxUnits = max;
        nbUnits = 0;
        initialiseTerrain();
    }

    public void Print(){
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau.length;j++){
                System.out.print(plateau[j][i].toString());
            }
            System.out.println("");
        }
    }

    public Terrain(Case[][] p, int max){
        plateau = p;
        maxUnits = max;
    }

    public boolean ajouteUnite(Unite u, int x, int y){
        try{
            if(plateau[y][x].unit==null){
                plateau[y][x]=new Case(u);
                u.setPositionUnite(plateau[y][x]);
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return false;
    }


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
}