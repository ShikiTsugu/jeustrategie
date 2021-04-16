package com.player;
import com.launcher.Jeu;
import com.plateau.Case;
import com.plateau.Terrain;
import com.unite.Unite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Robot extends Joueur{
    private int[] coordTarget = new int[2];
    private int[] coord = new int[2];
    public Robot(int a, Jeu jeu){
        super(a,jeu);
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
                    System.out.println(t.getPlateau()[x][y].getUnite().getJoueur()==this);
                    if(t.getPlateau()[x][y].getUnite().getJoueur()==this) {
                        if(t.getPlateau()[x][y].getUnite().getPointAction()>0) {
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

    public boolean allPAused(){
        for (Unite u : unites){
            if(u != null){
                if(u.getPointAction()>0){
                    return false;
                }
            }
        }
        return true;
    }

    public LinkedList availableSpace(Terrain t){
        LinkedList<int[]> coord = new LinkedList<>();
        for (int i = 0; i<t.getB2().length; i++){
            for (int j = 0; j<t.getB2()[i].length; j++){
                if(t.getB2()[i][j]==true && t.getPlateau()[i][j].estVide()){
                    int[] tmp = {i,j};
                    coord.add(tmp);
                }
            }
        }
        return coord;
    }

    public boolean targetDetected (Terrain t, int x, int y, int portee, Unite u, Joueur j){
        if(u.estDansTableau(t, x, y)
                && t.getPlateau()[y][x].estUnit()
                && t.getPlateau()[y][x].getUnite().getJoueur() != j
                && portee>=0) {
            setCoordTarget(x, y);
            System.out.println("caught");
            return true;
        }
        if (portee >= 0 && u.estDansTableau(t, x, y)){
            if (targetDetected(t, x -1, y, portee -1, u, j)
                    && u.estDansTableau(t, x -1, y)){
                return true;
            }
            if (targetDetected(t, x + 1, y, portee -1, u, j)
                    && u.estDansTableau(t, x +1, y)){
                return true;
            }
            if (targetDetected(t, x, y-1, portee -1, u, j)
                    && u.estDansTableau(t, x, y-1)){
                return true;
            }
            if (targetDetected(t, x, y +1, portee -1, u, j)
                    && u.estDansTableau(t, x, y+1)){
                return true;
            }
        }
        return false;
    }
}
