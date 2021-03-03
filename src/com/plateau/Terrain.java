package com.plateau;

import com.unite.Unite;

public class Terrain {
    protected Case[][] plateau;
    protected int maxUnits;
    protected int nbUnits;
    protected int xH1,yH1;
    protected int xH2,yH2;

    public Terrain(int largeur, int hauteur, int max,int x1,int y1, int x2, int y2){
        plateau = new Case[hauteur][largeur];
        maxUnits = max;
        nbUnits = 0;
        xH1 = x1;
        yH1 = y1;
        xH2 = x2;
        yH2 = y2;
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

    public int getXH1() { return xH1; }

    public String getXH1AsString() {
        if(xH1 <10){
            return "0"+String.valueOf(xH1);
        }
        return String.valueOf(xH1);
    }

    public int getYH1() { return yH1; }

    public String getYH1AsString() {
        if(yH1 <10){
            return "0"+String.valueOf(yH1);
        }
        return String.valueOf(yH1);
    }

    public int getxH2() { return xH2; }

    public String getXH2AsString() {
        if(xH2 <10){
            return "0"+String.valueOf(xH2);
        }
        return String.valueOf(xH2);
    }

    public int getYH2() { return yH2; }

    public String getYH2AsString() {
        if(yH2 <10){
            return "0"+String.valueOf(yH2);
        }
        return String.valueOf(yH2);
    }

}