package com.unite;

public class Obstacle {
    protected String nom;
    protected boolean franchissable;

    public Obstacle(String nom, boolean b){
        this.nom = nom;
        franchissable = b;
    }

    public String getNom() {
        return nom;
    }

    public boolean franchissable(){
        return franchissable;
    }
}
