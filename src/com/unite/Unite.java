package com.unite;

import com.plateau.*;
import com.player.Joueur;
import java.lang.*;
import java.util.*;

public abstract class Unite {
    protected int santeMax;
    protected int santeCourante;
    protected int attaque;
    protected int coutUnite;
    protected int porteeDeplacement;
    protected int porteeAttaque;
    protected int pointAction;
    protected Joueur joueur;
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
    
    public void deplaceUnite(Terrain t, int xPast, int yPast, int xApres, int yApres){
        Case avant = t.getPlateau()[yPast][xPast];
        if (t.getPlateau()[yPast][xPast].estUnit()) {
            Case destination = t.getPlateau()[yApres][xApres];
            if (destination.estVide() && (casesDisponibleDeplacement(t, avant.getUnite(), xPast, yPast, xApres, yApres)) && avant.getUnite().getPointAction() > 0) {
                avant.getUnite().setPointAction(avant.getUnite().getPointAction() -1);
                Case positionInitial = avant.getUnite().getPositionUnite();
                destination.setUnite(avant.getUnite());
                avant.getUnite().setPositionUnite(destination);
                positionInitial.supprimerUniteCase(positionInitial);
            }
        }
    }
    
    public void attaqueUnite(Terrain t, int xA, int yA, int xD, int yD){
        Unite attaquant = t.getPlateau()[yA][xA].getUnite();
        Unite defenseur = t.getPlateau()[yD][xD].getUnite();
        System.out.println("def pv avant : "+defenseur.santeCourante);
        if (t.getPlateau()[yA][xA].estUnit() && attaquant.getPointAction() > 0) {
            if (t.getPlateau()[yD][xD].estUnit() && ((Math.abs(yD - yA)+Math.abs(xD - xA)) <= attaquant.getPorteeAttaque())){
                attaquant.setPointAction(attaquant.getPointAction() -1);
                defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque());
                if (defenseur.getSanteCourante() <= 0) t.getPlateau()[yD][xD].supprimerUniteCase(t.getPlateau()[yD][xD]);
                else if (t.getPlateau()[yD][xD].estObstacle() || t.getPlateau()[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        } System.out.println("def pv avant : "+defenseur.santeCourante);
    }

    public boolean casesDisponibleDeplacement (Terrain t, Unite unite, int xPast, int yPast, int xApres, int yApres){
        int portee = unite.getPorteeDeplacement();
        HashSet<Case> test = new HashSet<>();
        return cheminTrouver(test, t, xPast, yPast, xApres, yApres, portee);
    }

    public boolean cheminTrouver (HashSet<Case> test, Terrain t, int xPast, int yPast, int xApres, int yApres, int portee){
        if (xPast == xApres && yPast == yApres && portee >= 0 && estDansTableau(t, xPast, yPast)){
            test.add(t.getPlateau()[yPast][xPast]);
            return true;
        }
        if (portee >= 0 && estDansTableau(t, xPast, yPast)){
            if (cheminTrouver(test, t, xPast -1, yPast, xApres, yApres, portee -1) && estDansTableau(t, xPast -1, yPast)
            && t.getPlateau()[yPast][xPast-1].estVide()){
                test.add(t.getPlateau()[yPast][xPast]);
                return true;
            }
            if (cheminTrouver(test, t, xPast + 1, yPast, xApres, yApres, portee -1) && estDansTableau(t, xPast +1, yPast)
            && t.getPlateau()[yPast][xPast+1].estVide()){
                test.add(t.getPlateau()[yPast][xPast]);
                return true;
            }
            if (cheminTrouver(test, t, xPast, yPast-1, xApres, yApres, portee -1) && estDansTableau(t, xPast, yPast-1)
            && t.getPlateau()[yPast-1][xPast].estVide()){
                test.add(t.getPlateau()[yPast][xPast]);
                return true;
            }
            if (cheminTrouver(test, t, xPast, yPast +1, xApres, yApres, portee -1) && estDansTableau(t, xPast, yPast+1)
            && t.getPlateau()[yPast+1][xPast].estVide()){
                test.add(t.getPlateau()[yPast][xPast]);
                return true;
            }
        }
        return false;
    }

    public boolean estDansTableau(Terrain t, int xPast, int yPast) {
        return (yPast < t.getPlateau().length && xPast < t.getPlateau().length && yPast >= 0 && xPast >= 0);
    }
}