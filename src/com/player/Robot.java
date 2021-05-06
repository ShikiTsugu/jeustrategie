package com.player;
import com.launcher.Jeu;
import com.plateau.Case;
import com.plateau.Terrain;
import com.unite.Unite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Robot extends Joueur{
    private int[] coord = new int[2];
    public Robot(int a, Jeu jeu){
        super(a,jeu);
        isHuman = false;
    }

    public int[] getCoord(){return coord;}

    public boolean pickUnit(Terrain t){
        for(int x = 0; x<t.getPlateau().length; x++){
            for(int y = 0; y<t.getPlateau()[x].length; y++){
                if(t.getPlateau()[x][y].estUnit()){
                    if(t.getPlateau()[x][y].getUnite().getJoueur()==this
                            && t.getPlateau()[x][y].getUnite().getPointAction()>0) {
                        coord[0] = x;
                        coord[1] = y;
                        System.out.println("L'unite choisit : " + t.getPlateau()[coord[0]][coord[1]].getUnite() +
                                ", PA : " + t.getPlateau()[coord[0]][coord[1]].getUnite().getPointAction());
                        return true;
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

    public LinkedList availableSpaceAroundTarget(Terrain t, Unite u){
        LinkedList<int[]> coord = new LinkedList<>();
        //coordonnées linéaires :
        if(u.estDansTableau(t,u.getCurrentX(),u.getCurrentY()+1)
                && t.getPlateau()[u.getCurrentY()+1][u.getCurrentX()].estVide()){
            int[] tmp = {u.getCurrentX(),u.getCurrentY()+1};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX(),u.getCurrentY()-1)
                && t.getPlateau()[u.getCurrentY()-1][u.getCurrentX()].estVide()){
            int[] tmp = {u.getCurrentX(),u.getCurrentY()-1};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX()+1,u.getCurrentY())
                && t.getPlateau()[u.getCurrentY()][u.getCurrentX()+1].estVide()){
            int[] tmp = {u.getCurrentX()+1,u.getCurrentY()};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX()-1,u.getCurrentY())
                && t.getPlateau()[u.getCurrentY()][u.getCurrentX()-1].estVide()){
            int[] tmp = {u.getCurrentX()-1,u.getCurrentY()};
            coord.add(tmp);
        }
        //coordonnées diagonales :
        if(u.estDansTableau(t,u.getCurrentX()+1,u.getCurrentY()+1)
                && t.getPlateau()[u.getCurrentY()+1][u.getCurrentX()+1].estVide()){
            int[] tmp = {u.getCurrentX()+1,u.getCurrentY()+1};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX()+1,u.getCurrentY()-1)
                && t.getPlateau()[u.getCurrentY()-1][u.getCurrentX()+1].estVide()){
            int[] tmp = {u.getCurrentX()+1,u.getCurrentY()-1};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX()-1,u.getCurrentY()-1)
                && t.getPlateau()[u.getCurrentY()-1][u.getCurrentX()-1].estVide()){
            int[] tmp = {u.getCurrentX()-1,u.getCurrentY()-1};
            coord.add(tmp);
        }
        if(u.estDansTableau(t,u.getCurrentX()-1,u.getCurrentY()+1)
                && t.getPlateau()[u.getCurrentY()+1][u.getCurrentX()-1].estVide()){
            int[] tmp = {u.getCurrentX()-1,u.getCurrentY()+1};
            coord.add(tmp);
        }
        return coord;
    }

    public boolean canAttack(Terrain t, int x, int y, int portee, Unite u, Joueur j){
        if(u.estDansTableau(t, x, y)
                && t.getPlateau()[y][x].estUnit()
                && t.getPlateau()[y][x].getUnite().getJoueur() != j
                && portee>=0) {
            System.out.println("Cible à portée");
            return true;
        }
        if (portee >= 0 && u.estDansTableau(t, x, y)){
            if (canAttack(t, x -1, y, portee -1, u, j)
                    && u.estDansTableau(t, x -1, y)){
                return true;
            }
            if (canAttack(t, x + 1, y, portee -1, u, j)
                    && u.estDansTableau(t, x +1, y)){
                return true;
            }
            if (canAttack(t, x, y-1, portee -1, u, j)
                    && u.estDansTableau(t, x, y-1)){
                return true;
            }
            if (canAttack(t, x, y +1, portee -1, u, j)
                    && u.estDansTableau(t, x, y+1)){
                return true;
            }
        }
        return false;
    }

    public boolean targetDetected (Terrain t, int x, int y, int portee, Unite u, Joueur j){
        if(u.estDansTableau(t, x, y)
                && t.getPlateau()[y][x].estUnit()
                && t.getPlateau()[y][x].getUnite().getJoueur() != j
                && portee>=0) {
            int[] tmp = {x,y};
            u.getCoordTarget().add(tmp);
            System.out.println("caught "+t.getPlateau()[y][x].getUnite());
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
