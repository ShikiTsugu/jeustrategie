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
            if (((Math.abs(yApres - yPast)+Math.abs(xApres - xPast)) <= avant.getUnite().getPorteeDeplacement()) && destination.estVide()
            && (casesDisponibleDeplacement(t, xPast, yPast).contains(t.getPlateau()[yApres][xApres])) && avant.getUnite().getPointAction() > 0) {
                avant.getUnite().setPointAction(avant.getUnite().getPointAction() -1);
                Case positionInitial = avant.getUnite().getPositionUnite();
                destination.setUnite(avant.getUnite());
                System.out.println(positionInitial);
                avant.getUnite().setPositionUnite(destination);
                System.out.println(positionInitial);
                positionInitial.supprimerUniteCase();
            }
        }
    }
    
    public void attaqueUnite(Terrain t, int xA, int yA, int xD, int yD){
        Unite attaquant = t.getPlateau()[yA][xA].getUnite();
        Unite defenseur = t.getPlateau()[yD][xD].getUnite();
        if (t.getPlateau()[yA][xA].estUnit() && attaquant.getPointAction() > 0) {
            if (t.getPlateau()[yD][xD].estUnit()){
                attaquant.setPointAction(attaquant.getPointAction() -1);
                defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque());
                if (defenseur.getSanteCourante() <= 0) t.getPlateau()[yD][xD].supprimerUniteCase();
                else if (t.getPlateau()[yD][xD].estObstacle() || t.getPlateau()[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        }
    }

    public Collection<Case> casesDisponibleDeplacement (Terrain t, int xPast, int yPast){
        Unite unite = t.getPlateau()[yPast][xPast].getUnite();
        int portee = unite.getPorteeDeplacement();
        HashSet<Case> test = new HashSet<>();
        return casesDisponiblePortee(test, t, portee, xPast, yPast);
    }

    private Collection<Case> casesDisponiblePortee(HashSet<Case> test, Terrain t, int portee, int xPast, int yPast){
        if (portee <= 0) return test;
        if (yPast+1 < t.getPlateau().length && t.getPlateau()[yPast+1][xPast].estVide()){
            test.add(t.getPlateau()[yPast+1][xPast]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast+1, xPast));
        }
        if (xPast+1 < t.getPlateau()[0].length && t.getPlateau()[yPast][xPast+1].estVide()){
            test.add(t.getPlateau()[yPast][xPast+1]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast+1));
        }
        if (yPast-1 >= 0 && t.getPlateau()[yPast-1][xPast].estVide()){
            test.add(t.getPlateau()[yPast-1][xPast]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast-1, xPast));
        }
        if (xPast-1 >= 0 && t.getPlateau()[yPast][xPast-1].estVide()){
            test.add(t.getPlateau()[yPast][xPast-1]);
            test.addAll(casesDisponiblePortee(test, t, portee-1, yPast, xPast-1));
        }
        return test;
    }
}