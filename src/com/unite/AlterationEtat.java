package com.unite;

public class AlterationEtat {
    protected String nom;
    protected Unite unite;
    protected int tourRestant;
    protected boolean activable;

    public AlterationEtat(String nom, int tourRestant,Unite u){
        this.nom = nom;
        this.tourRestant = tourRestant;
        unite = u;
        activable = true;
    }

    public String getNom(){ return nom; }
    public int getTourRestant(){
        return tourRestant;
    }
}
