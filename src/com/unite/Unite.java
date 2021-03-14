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
        Case test = new Case(xApres, yApres);
        if (t.getPlateau()[yPast][xPast].estUnit()) {
            Case destination = t.getPlateau()[yApres][xApres];
            if (((Math.abs(yApres - yPast)+Math.abs(xApres - xPast)) <= avant.getUnite().getPorteeDeplacement()) && destination.estVide()
            && (casesDisponibleDeplacement(t, avant.getUnite(), xPast, yPast, xApres, yApres).contains(test)) && avant.getUnite().getPointAction() > 0) {
                avant.getUnite().setPointAction(avant.getUnite().getPointAction() -1);
                Case positionInitial = avant.getUnite().getPositionUnite();
                destination.setUnite(avant.getUnite());;
                avant.getUnite().setPositionUnite(destination);
                positionInitial.supprimerUniteCase();
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
                if (defenseur.getSanteCourante() <= 0) t.getPlateau()[yD][xD].supprimerUniteCase();
                else if (t.getPlateau()[yD][xD].estObstacle() || t.getPlateau()[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        }
    }

    public Collection<Case> casesDisponibleDeplacement (Terrain t, Unite unite, int xPast, int yPast, int xApres, int yApres){
        int portee = unite.getPorteeDeplacement();
        HashSet<Case> test = new HashSet<>();
        return casesDisponiblePortee(test, t, unite, portee, xPast, yPast, xApres, yApres);
    }

    private Collection<Case> casesDisponiblePortee(HashSet<Case> test, Terrain t, Unite unite, int portee, int xPast, int yPast, int xApres, int yApres){
        if (portee <= 0) return test;
        if (yPast+1 < t.getPlateau().length && t.getPlateau()[yPast+1][xPast].estVide()){
            Case c1 = new Case(xPast, yPast+1);
            test.add(c1);
            test.addAll(casesDisponiblePortee(test, t, unite, portee-1, yPast+1, xPast, xApres, yApres));
        }
        if (xPast+1 < t.getPlateau()[0].length && t.getPlateau()[yPast][xPast+1].estVide()){
            Case c2 = new Case(xPast+1, yPast);
            test.add(c2);
            test.addAll(casesDisponiblePortee(test, t, unite,portee-1, yPast, xPast+1, xApres, yApres));
        }
        if (yPast-1 >= 0 && t.getPlateau()[yPast-1][xPast].estVide()){
            Case c3 = new Case(xPast, yPast-1);
            test.add(c3);
            test.addAll(casesDisponiblePortee(test, t, unite,portee-1, yPast-1, xPast, xApres, yApres));
        }
        if (xPast-1 >= 0 && t.getPlateau()[yPast][xPast-1].estVide()){
            Case c4 = new Case(xPast-1, yPast);
            test.add(c4);
            test.addAll(casesDisponiblePortee(test, t, unite, portee-1, yPast, xPast-1, xApres, yApres));
        }
        return calculPlusCourtChemin(test, xApres, yApres);
    }

    private Collection<Case> calculPlusCourtChemin(Collection<Case> t, int xApres, int yApres){
        HashSet<Case> newCollection = new HashSet<>();
        Iterator<Case> it = t.iterator();
        int compteur = 0;
        while(it.hasNext()){
            newCollection.add(it.next());
            compteur++;
            if ((Math.abs(yApres - it.next().getY())+Math.abs(xApres - it.next().getX())) < compteur || (it.next().getX() == xApres) && (it.next().getY() == yApres)){
                newCollection.remove(it.next());
                compteur-=1;
            }
            if ((Math.abs(yApres - it.next().getY())+Math.abs(xApres - it.next().getX())) == compteur && (it.next().getX() == xApres) && (it.next().getY() == yApres)){
                return newCollection;
            }
        }
        return newCollection;
    }
}