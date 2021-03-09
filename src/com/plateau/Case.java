package com.plateau;

import com.unite.*;

public class Case {
    protected boolean obstacle;
    protected Unite unit;

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