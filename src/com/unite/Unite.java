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
            if (destination.estVide() && (casesDisponibleDeplacement(t, avant.getUnite(), xPast, yPast, xApres, yApres).contains(t.getPlateau()[yApres][xApres])) && avant.getUnite().getPointAction() > 0) {
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
        if (t.getPlateau()[yA][xA].estUnit() && attaquant.getPointAction() > 0) {
            if (t.getPlateau()[yD][xD].estUnit() && ((Math.abs(yD - yA)+Math.abs(xD - xA)) <= attaquant.getPorteeAttaque())){
                attaquant.setPointAction(attaquant.getPointAction() -1);
                defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque());
                if (defenseur.getSanteCourante() <= 0) t.getPlateau()[yD][xD].supprimerUniteCase(t.getPlateau()[yD][xD]);
                else if (t.getPlateau()[yD][xD].estObstacle() || t.getPlateau()[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        }
    }

    public Collection<Case> casesDisponibleDeplacement (Terrain t, Unite unite, int xPast, int yPast, int xApres, int yApres){
        int portee = unite.getPorteeDeplacement();
        HashSet<Case> test = new HashSet<>();
        return casesDisponiblePortee(test, t, portee, xPast, yPast, xApres, yApres);
    }

    private Collection<Case> casesDisponiblePortee(HashSet<Case> test, Terrain t, int portee, int xPast, int yPast, int xApres, int yApres){
        if (portee <= 0) {
            System.out.println(test);
            return calculPlusCourtChemin(test, t, xPast, yPast, xApres, yApres);
        }
        if (yPast+1 < t.getPlateau().length && t.getPlateau()[yPast+1][xPast].estVide()){
            test.add(t.getPlateau()[yPast+1][xPast]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast+1, xPast, xApres, yApres));
        }else if (yPast+1 < t.getPlateau().length){
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast+1, xPast, xApres, yApres));
        }
        if (xPast+1 < t.getPlateau()[0].length && t.getPlateau()[yPast][xPast+1].estVide()){
            test.add(t.getPlateau()[yPast][xPast+1]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast+1, xApres, yApres));
        }else if(xPast+1 < t.getPlateau()[0].length){
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast+1, xApres, yApres));
        }
        if (yPast-1 >= 0 && t.getPlateau()[yPast-1][xPast].estVide()){
            test.add(t.getPlateau()[yPast-1][xPast]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast-1, xPast, xApres, yApres));
        }else if(yPast-1 >= 0){
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast-1, xPast, xApres, yApres));
        }
        if (xPast-1 >= 0 && t.getPlateau()[yPast][xPast-1].estVide()){
            test.add(t.getPlateau()[yPast][xPast-1]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast-1, xApres, yApres));
        }
        else if (xPast-1 >= 0){
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast-1, xApres, yApres));
        }
        return test;
    }

    private Collection<Case> calculPlusCourtChemin(HashSet<Case> t, Terrain terrain, int xPast, int yPast, int xApres, int yApres){
        HashSet<Case> newCollection = new HashSet<>();
        //System.out.println(t);
        for(Case c: t){newCollection.add(c);
            if (c.getId() == terrain.getPlateau()[yApres][xApres].getId() && c.estVide()){
            System.out.println(newCollection);
            return newCollection;
            }
            //System.out.println(c.getId());

            if ((c.getX() != xApres) && (c.getY() != yApres) && !c.estVide()){
                newCollection.remove(c);
            }
        }
        return newCollection;
    }
}