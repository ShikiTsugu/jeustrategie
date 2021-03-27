package com.player;

import com.plateau.Terrain;
import com.unite.Unite;

import java.util.Random;

public class Robot extends Joueur{
    public Robot(int a){
        super(a);
        isHuman = false;
    }

    public Unite randomUnit(){
        int range = 0;
        for (int i = 0; i<this.getUnites().length;i++){
            if(this.getUnites()[i]==null){
                break;
            }
            range++;
        }
        Random rand = new Random();
        int random = rand.nextInt(range - 1);
        return this.getUnites()[random];
    }

    public int[] unitCoord(Terrain t){
        Unite u = randomUnit();
        int[] coord = new int[2];
        for(int x = 0; x<t.getPlateau().length; x++){
            for(int y = 0; y<t.getPlateau()[x].length; y++){
                if(t.getPlateau()[x][y].getUnite()==u){
                    coord[0]=x;
                    coord[1]=y;
                    break;
                }
            }
        }
        return coord;
    }
}
