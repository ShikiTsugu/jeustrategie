package com.plateau;

import com.unite.*;

public class Case {
    protected boolean obstacle;
    protected Unite unit;
    protected boolean J1Buyable ;
    protected boolean J2Buyable ;

    public Case(){
        unit = null;
        obstacle = false;
    }

    public Case(Unite u){
        unit = u;
        obstacle = false;
    }

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
        return obstacle == true;
    }
    
    public boolean estUnit(){
        return unit != null;
    }
    
    public Unite supprimerUniteCase(){
        Unite newUnit = unit;
        unit = null;
        return newUnit;
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
        return ".";
    }

    public String toString(){
        if(estVide()) return ".";
        if(estUnit()) return  afficheContenu();
        return obstacle==false?unit.toString():"obstacle";
    }
}