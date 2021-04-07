package com.player;

import com.plateau.Terrain;
import com.unite.Unite;

import java.util.Random;

public class Robot extends Joueur{
    private int[] coordTarget = new int[2];
    private int[] coord = new int[2];
    public Robot(int a){
        super(a);
        isHuman = false;
    }

    public int[] getCoordTarget(){return coordTarget;}

    public int[] getCoord(){return coord;}

    public void setCoordTarget(int x, int y){
        coordTarget[0]=x;
        coordTarget[1]=y;
    }

    public boolean pickUnit(Terrain t){
        for(int x = 0; x<t.getPlateau().length; x++){
            for(int y = 0; y<t.getPlateau()[x].length; y++){
                if(t.getPlateau()[x][y].estUnit()){
                    System.out.println("test1");
                    System.out.println(t.getPlateau()[x][y].getUnite().getJoueur()==this);
                    if(t.getPlateau()[x][y].getUnite().getJoueur()==this) {
                        if(t.getPlateau()[x][y].getUnite().getPointAction()>0) {
                            System.out.println("test2");
                            coord[0] = x;
                            coord[1] = y;
                            System.out.println("L'unite choisit : " + t.getPlateau()[coord[0]][coord[1]].getUnite());
                            return true;
                        }else{
                            System.out.println("Cette unite n'a plus de PA.");
                        }
                    }
                }
            }
        }
        return false;
    }
}
