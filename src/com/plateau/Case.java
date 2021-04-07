package com.plateau;

import com.unite.*;

public class Case {
    protected boolean obstacle;
    protected Unite unit;
    protected boolean J1Buyable ;
    protected boolean J2Buyable ;
    protected int id;
    protected static int incremente;

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

    public int getId(){return id;}

    public Case(boolean o){
        obstacle = o;
    }

    public boolean estVide(){
        return !estObstacle() && !estUnit();
    }
    
    public Unite getUnite(){
        return unit;
    }
    
    public void setUnite(Unite unit){
        this.unit = unit;
    }

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
        return coord;
    }

    public String toString(){
        if(estVide()) return Integer.toString(id);
        if(estUnit()) return  afficheContenu();
        return !obstacle?unit.toString():"obstacle";
    }
}