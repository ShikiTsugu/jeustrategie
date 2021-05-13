package com.plateau;

import com.unite.Unite;

import java.io.Serializable;

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

    public void setB1(boolean[][] b1) { this.b1 = b1; }

    public void setB2(boolean[][] b2) { this.b2 = b2; }

    public void setBuyableArea(boolean[][] b1, boolean[][] b2){
        for(int i=0;i<b1.length;i++){
            for(int j=0;j<b1[i].length;j++){
                plateau[i][j].setJ1Buyable(b1[i][j]);
                plateau[i][j].setJ2Buyable(b2[i][j]);
            }
        }
    }

    public void Print(){
        System.out.print("  ");
        for(int i=0;i< plateau[0].length;i++){
            System.out.print("y" + i+" ");
        }
        System.out.println("");

        for(int i=0;i<plateau.length;i++){
            System.out.print("x"+ i+" ");
            for(int j=0;j<plateau[i].length;j++){
                System.out.print(plateau[i][j].toString() +" ");
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
                plateau[y][x].setUnite(u);
                u.setPositionUnite(plateau[y][x]);
                u.setCurrentX(x);
                u.setCurrentY(y);
                u.setTerrain(this);
                System.out.println("unite placé");
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        System.out.println("impossible");
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

    public void setEffectCase(int x, int y, CaseEffect ce){
        plateau[y][x] = ce;
    }

}