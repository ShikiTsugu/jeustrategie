package com.plateau;

import com.unite.*;

import java.io.Serializable;

public class Case implements Serializable {
    protected boolean obstacle;
    protected Unite unit;
    protected boolean J1Buyable ;
    protected boolean J2Buyable ;
    protected int id;
    protected static int incremente;
    protected int hauteur;

    public Case(){
        unit = null;
        obstacle = false;
        id=incremente++;
    }

    public Case(Unite u){
        unit = u;
        obstacle = false;
        id=incremente++;
    }

    public Case(boolean o){
        obstacle = o;
        id=incremente++;
    }

    public int getId(){return id;}


    public boolean estVide(){
        return !estObstacle() && !estUnit();
    }
    
    public Unite getUnite(){
        return unit;
    }
    
    public void setUnite(Unite unit){
        this.unit = unit;
    }

    public boolean getObstacle(){return obstacle;}
    public void setObstacle(boolean obstacle){this.obstacle = obstacle;}

    public void setJ1Buyable(boolean j1Buyable) {
        J1Buyable = j1Buyable;
    }

    public void setJ2Buyable(boolean j2Buyable) {
        J2Buyable = j2Buyable;
    }

    public boolean J1CanBuy(){
        return J1Buyable;
    }

    public boolean J2CanBuy(){
        return J2Buyable;
    }
    
    public boolean estObstacle(){
        return obstacle;
    }
    
    public boolean estUnit(){
        return unit != null;
    }
    
    public void supprimerUniteCase(Case c){
        c.setUnite(null);
    }

    public int getHauteur(){ return hauteur; }

    public void setHauteur(int h){ hauteur = h; }

    public String afficheContenu(){
        if(unit instanceof Hero){
            return "H";
        }
        if(unit instanceof Archer){
            return "A";
        }
        if(unit instanceof Cavalier){
            return "C";
        }
        if(unit instanceof Mage){
            return "M";
        }
        if(unit instanceof Templier){
            return "T";
        }
        return Integer.toString(id);
    }

    public int[] casePos(Terrain t){
        int[] coord = new int[2];
        for (int i = 0; i<t.getPlateau().length; i++){
            for (int j = 0; j<t.getPlateau()[i].length; j++){
                if (t.getPlateau()[i][j].id==this.id){
                    coord[1]=i;
                    coord[0]=j;
                    return coord;
                }
            }
        }
        return null;
    }

    public String toString(){
        if(estVide()) return Integer.toString(id);
        if(estUnit()) return  afficheContenu();
        return !estObstacle()?unit.toString():"obstacle";
    }

}