package com.plateau;

import java.io.Serializable;
import java.util.ArrayList;

//MapSerialized contient toutes les maps, et peut être sérialisé
public class MapSerialized implements Serializable {

    protected ArrayList<Terrain> MapList;

    public MapSerialized(){
        MapList = new ArrayList<>();
    }

    public ArrayList<Terrain> getMapList() { return MapList; }

}
