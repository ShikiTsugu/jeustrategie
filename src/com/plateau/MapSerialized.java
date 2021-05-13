package com.plateau;

import java.io.Serializable;
import java.util.ArrayList;

//MapSerialized contient tout les maps, et peut être Sérailisé
public class MapSerialized implements Serializable {

    protected ArrayList<Terrain> MapList;

    public MapSerialized(){
        MapList = new ArrayList<>();
    }

    public ArrayList<Terrain> getMapList() { return MapList; }

}
