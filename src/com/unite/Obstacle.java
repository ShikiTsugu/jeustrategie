package com.unite;

import java.io.Serializable;

public class Obstacle implements Serializable {
    protected String nom;
    protected boolean franchissable;

    public Obstacle(String nom){
        this.nom = nom;

    }

    public Obstacle(){
        this.nom = "Rocher";

    }



    public String getNom() {
        return nom;
    }

}
