package com.player;
import com.launcher.Jeu;
import com.plateau.Terrain;
import com.unite.Unite;

import java.util.LinkedList;

//Classe héritant de joueur, mais inclut en plus d'autres méthodes spécifiques à l'utilisation du robot.
//Notamment comment celui-ci réagit face au choix de l'unité à joueur, la détection de cible etc..
public class Robot extends Joueur{
    //Coordonnées de l'unité choisit
    private int[] coord = new int[2];
    public Robot(int a, Jeu jeu){
        super(a,jeu);
        isHuman = false;
    }

    public int[] getCoord(){return coord;}

    //Permet au robot de choisir la première unité qu'il rencontre qui n'a pas encore utilisé tous ses points d'action.
    public boolean pickUnit(Terrain t){
        //On parcout le plateau et on regarde si à la position il y a une unité, si c'est le cas on vérifie qu'elle appartient
        //au robot, et on vérifie qu'elle a encore des PA, si c'est le cas on prend ses coordonnées et on les stock.
        for(int x = 0; x<t.getPlateau().length; x++){
            for(int y = 0; y<t.getPlateau()[x].length; y++){
                if(t.getPlateau()[x][y].estUnit()){
                    if(t.getPlateau()[x][y].getUnite().getJoueur()==this
                            && t.getPlateau()[x][y].getUnite().getPointAction()>0) {
                        coord[0] = x;
                        coord[1] = y;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Vérifie dans la liste d'unité du robot si toutes les unités ont utilisé leurs PA.
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

    //Permet au robot de placer une unité après son achat.
    public LinkedList availableSpace(Terrain t){
        LinkedList<int[]> coord = new LinkedList<>();
        for (int i = 0; i<t.getB2().length; i++){
            for (int j = 0; j<t.getB2()[i].length; j++){
                //On regarde si à la position (i,j) il y a de la place, et si c'est une place à laquelle le robot a le droit
                //de placer son unité, si c'est le cas on ajoute à la liste de coordonnées possibles cette coordonnée.
                if(t.getB2()[i][j]==true && t.getPlateau()[i][j].estVide()){
                    int[] tmp = {i,j};
                    coord.add(tmp);
                }
            }
        }
        return coord;
    }

    //Permet de regarder autour d'une unité ciblé s'il y a de la place autour d'elle à une distance d'une case.
    //Cela permet de déplacer des unités au corps à corps vers leur cible.
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

    //Vérifie s'il y'a une unité adverse dans la portée d'attaque de l'unité choisit par le robot.
    //Si c'est le cas alors l'unité peut attaquer.
    public boolean canAttack(Terrain t, int x, int y, int portee, Unite u, Joueur j){
        if(u.estDansTableau(t, x, y)
                && t.getPlateau()[y][x].estUnit()
                && t.getPlateau()[y][x].getUnite().getJoueur() != j
                && portee>=0) {
            return true;
        }
        //Regarde tout autour de la position de l'unité tout en restant dans sa portée.
        if (portee >= 0 && u.estDansTableau(t, x, y)){
            if (u.estDansTableau(t, x -1, y)){
                return canAttack(t, x -1, y, portee -1, u, j);
            }
            if (u.estDansTableau(t, x +1, y)){
                return canAttack(t, x + 1, y, portee -1, u, j);
            }
            if (u.estDansTableau(t, x, y-1)){
                return canAttack(t, x, y-1, portee -1, u, j);
            }
            if (u.estDansTableau(t, x, y+1)){
                return canAttack(t, x, y+1, portee -1, u, j);
            }
        }
        return false;
    }

    //Cherche une unité adverse tout autour de la portée de déplacement de l'unité choisit par le robot.
    //Si une unité est trouvée alors ses coordonnées sont sauvegardées afin de pouvoir s'y déplacer vers elle ou l'attaquer.
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
        //Regarde tout autour de la position de l'unité choisit tout en restant dans sa portée de déplacement.
        if (portee >= 0 && u.estDansTableau(t, x, y)){
            if (u.estDansTableau(t, x -1, y)){
                return targetDetected(t, x -1, y, portee -1, u, j);
            }
            if (u.estDansTableau(t, x +1, y)){
                return targetDetected(t, x + 1, y, portee -1, u, j);
            }
            if (u.estDansTableau(t, x, y-1)){
                return targetDetected(t, x, y-1, portee -1, u, j);
            }
            if (u.estDansTableau(t, x, y+1)){
                return targetDetected(t, x, y +1, portee -1, u, j);
            }
        }
        return false;
    }
}
