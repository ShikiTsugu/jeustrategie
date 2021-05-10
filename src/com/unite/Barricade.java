package com.unite;

public class Barricade extends Obstacle{

    protected int nbTour;

    Barricade(int i){
        nom = "Barricade";
        nbTour = i;
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }
}
