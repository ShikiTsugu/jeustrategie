package com.unite;

import com.plateau.Case;
import com.player.Joueur;

public abstract class Unite {
    protected int santeMax;
    protected int santeCourante;
    protected int attaque;
    protected int coutUnite;
    protected int porteeDeplacement;
    protected int porteeAttaque;
    protected int pointAction;
    protected Joueur joueur;
    protected Case[][] plateau;
    protected Case positionUnite;
    //rajouter plus tard protected AlterationEtat etat;
    public Unite(Joueur joueur){
        this.joueur = joueur;
    }
    
    public int getSanteMax(){
        return santeMax;
    }
    
    public int getSanteCourante(){
	    return santeCourante;
    }
    
    public int getAttaque(){
        return attaque;
    }
    
    public int getCoutUnite(){
        return coutUnite;
    }
    
    public int getPorteeDeplacement(){
        return porteeDeplacement;
    }
    
    public int getPorteeAttaque(){
        return porteeAttaque;
    }
    
    public int getPointAction(){
        return pointAction;
    }
    
    public Joueur getJoueur(){
        return joueur;
    }
    
    public Case getPositionUnite(){
        return positionUnite;
    }
    
    public void setSanteMax(int santeMax){
        this.santeMax = santeMax;
    }
    
    public void setSanteCourante(int santeCourante){
	    this.santeCourante = santeCourante;
    }
    
    public void setAttaque(int attaque){
        this.attaque = attaque;
    }
    
    public void setCoutUnite(int coutUnite){
        this.coutUnite = coutUnite;
    }
    
    public void setPorteeDeplacement(int porteeDeplacement){
        this.porteeDeplacement = porteeDeplacement;
    }
    
    public void setPorteeAttaque(int porteeAttaque){
        this.porteeAttaque = porteeAttaque;
    }
    
    public void setPointAction(int pointAction){
        this.pointAction = pointAction;
    }
    
    public void setJoueur(Joueur joueur){
	    this.joueur = joueur;
    }
    
    public void setPositionUnite(Case positionUnite){
        this.positionUnite = positionUnite;
    }
    
    public abstract String toString();
    public abstract boolean isHero();
    
    public void deplaceUnite(int xPast, int yPast, int xApres, int yApres){
        Case avant = plateau[yPast][xPast];
        if (plateau[yPast][xPast].estUnit()) {
            Case destination = plateau[yApres][xApres];
            if (destination.estVide()) {
                Case positionInitial = avant.getUnite().getPositionUnite();
                destination.setUnite(avant.getUnite());
                avant.getUnite().setPositionUnite(destination);
                positionInitial.supprimerUniteCase();
            }
        }
    }
    
    public void attaqueUnite(int xA, int yA, int xD, int yD){
        if (plateau[yA][xA].estUnit()) {
            if (plateau[yD][xD].estUnit()){
                Unite attaquant = plateau[yA][xA].getUnite();
                Unite defenseur = plateau[yD][xD].getUnite();
                attaquant.setPointAction(attaquant.getPointAction() -1);
                defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque());
                if (defenseur.getSanteCourante() <= 0) plateau[yD][xD].supprimerUniteCase();
                else if (plateau[yD][xD].estObstacle() || plateau[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        }
    }
}