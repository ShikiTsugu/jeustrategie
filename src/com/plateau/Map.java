package com.plateau;

import java.io.*;

public class Map{

    protected Terrain map;
    protected boolean b1[][];
    protected boolean b2[][];

    public Map(){}

    public void createTerrain(int x, int y, int z){
        generateSpawnable(y, x, z);
        map = new Terrain(x,y,12,0,3,13,3,b1,b2);
    }

    public void generateSpawnable(int x, int y, int s){
        b1 = new boolean[x][y];
        b2 = new boolean[x][y];

        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                b1[i][j] = j < s;
                b2[i][j] = j >= y - s;
            }
        }
    }

    public void CheckSpecialSpawnable(){
        for (int i = 0; i < b1.length; i++){
            for (int j = 0; j < b1[i].length; j++){
                if (map.getPlateau()[i][j] instanceof CaseEffect){
                    b1[i][j] = false;
                    b2[i][j] = false;
                }
            }
        }
    }

    public void Map5x5(){
        createTerrain(5, 5, 2);
        map.getPlateau()[0][1].setObstacle(true);
        map.getPlateau()[0][map.getPlateau()[0].length-2].setObstacle(true);
        map.getPlateau()[0][1].setObstacle(true);
        map.getPlateau()[map.getPlateau()[0].length-1][1].setObstacle(true);
        map.getPlateau()[map.getPlateau()[0].length-1][map.getPlateau()[0].length-2].setObstacle(true);
    }

    public void Map14x6(){
        createTerrain(14, 6, 4);
        map.getPlateau()[0][4].setObstacle(true);
        map.getPlateau()[1][4].setObstacle(true);
        map.getPlateau()[1][5].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-1][0].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-1][(map.getPlateau()[0].length-1)-4].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-2][(map.getPlateau()[0].length-1)-4].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-2][(map.getPlateau()[0].length-1)-5].setObstacle(true);
        map.getPlateau()[0][map.getPlateau()[0].length-1].setObstacle(true);
        map.setEffectCase(map.getPlateau()[0].length/2,map.getPlateau().length/2, new CaseGold());
        map.setEffectCase(map.getPlateau()[0].length/2-1,map.getPlateau().length/2-1, new Tower());
        CheckSpecialSpawnable();
    }

    public void Map14x6Gold(){
        createTerrain(14, 6, 4);
        map.setEffectCase(0,0, new CaseGold());
        map.setEffectCase(0,map.getPlateau().length-1, new CaseGold());
        map.setEffectCase(map.getPlateau()[0].length-1,0, new CaseGold());
        map.setEffectCase(map.getPlateau()[0].length-1,map.getPlateau().length-1, new CaseGold());
        map.getPlateau()[0][4].setObstacle(true);
        map.getPlateau()[1][4].setObstacle(true);
        map.getPlateau()[1][5].setObstacle(true);
        map.getPlateau()[2][5].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-1][(map.getPlateau()[0].length-1)-4].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-2][(map.getPlateau()[0].length-1)-4].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-2][(map.getPlateau()[0].length-1)-5].setObstacle(true);
        map.getPlateau()[map.getPlateau().length-3][(map.getPlateau()[0].length-1)-5].setObstacle(true);
        CheckSpecialSpawnable();
    }

    public Terrain getTerrain(){ return map; }

    public void setMap(Terrain map) { this.map = map; }
}
