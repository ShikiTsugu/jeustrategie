package com.unite;

import com.plateau.Case;
import com.plateau.Terrain;
import com.player.Joueur;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Unite {
    protected int santeMax;
    protected int santeCourante;
    protected int attaque;
    protected int coutUnite;
    protected int porteeDeplacement;
    protected int porteeAttaque;
    protected int pointActionMax;
    protected int pointAction;
    protected Joueur joueur;
    protected Terrain terrain;
    protected Case positionUnite;
    protected int currentX;
    protected int currentY;
    protected Competence[] competences;
    protected ArrayList<Case> deplacementDisponible;
    protected ArrayList<Buff> buffs;
    protected ArrayList<Debuff> debuffs;
    protected boolean peutEtreAttaque= true;
    protected ArrayList<Unite> listUniteTransforme;
    protected int direction;
    private LinkedList<int[]> coordTarget = new LinkedList<>();

    public Unite(Joueur joueur){
        this.joueur = joueur;
        buffs = new ArrayList<Buff>();
        debuffs = new ArrayList<Debuff>();
    }

    public LinkedList<int[]> getCoordTarget(){return coordTarget;}
    
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

    public int getPointActionMax() { return pointActionMax; }
    
    public int getPointAction(){
        return pointAction;
    }
    
    public Joueur getJoueur(){
        return joueur;
    }

    public Terrain getTerrain() { return terrain;  }

    public Case getPositionUnite(){
        return positionUnite;
    }
    
    public ArrayList<Case> getDeplacementDisponible(){return deplacementDisponible;}

    public int getCurrentX() { return currentX; }

    public int getCurrentY() { return currentY; }

    public Competence[] getCompetences() { return competences; }

    public boolean getPeutEtreAttaque(){return peutEtreAttaque;}

    public ArrayList<Unite> getListUniteTransforme(){return listUniteTransforme;}

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public ArrayList<Debuff> getDebuffs() {
        return debuffs;
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

    public void setPointActionMax(int pointActionMax){ this.pointActionMax = pointActionMax; }
    
    public void setPointAction(int pointAction){
        this.pointAction = pointAction;
    }
    
    public void setJoueur(Joueur joueur){
	    this.joueur = joueur;
    }

    public void setTerrain(Terrain terrain) { this.terrain = terrain;  }

    public void setPositionUnite(Case positionUnite){
        this.positionUnite = positionUnite;
    }

    public void setCurrentX(int currentX) { this.currentX = currentX; }

    public void setCurrentY(int currentY) { this.currentY = currentY; }

    public void setCompetences(Competence[] competences) { this.competences = competences; }

    public void setDeplacementDisponible(ArrayList<Case> deplacementDisponible){this.deplacementDisponible = deplacementDisponible;}

    public void addBuff(String a,int n){
        buffs.add(new Buff(a,n,this));
    }

    public void addDebuff(String a,int n){
        debuffs.add(new Debuff(a,n,this));
    }

    public void setPeutEtreAttaque(boolean peutEtreAttaque){this.peutEtreAttaque = peutEtreAttaque;}

    public void setListUniteTransforme(ArrayList<Unite> listUniteTransforme){this.listUniteTransforme = listUniteTransforme;}

    public void setDirection(int direction){this.direction = direction;}

    public void readAlterationEtats(){
        updateAlterationEtats();
        activeAlterationEtats();
    }

    public void checkCooldowns(){
        for(int i =0 ; i<competences.length ;i++){
            if(competences[i].getCooldownActuel() > 0 ){
                competences[i].setCooldownActuel(competences[i].getCooldownActuel()-1);
            }
        }
    }

    public void updateAlterationEtats(){
        for(int i =0; i< buffs.size();i++){
            if(buffs.get(i).getTourRestant() <= 0){
                buffs.remove(i);
            }
        }
        for(int i =0; i< debuffs.size();i++){
            if(debuffs.get(i).getTourRestant() <= 0){
                debuffs.remove(i);
            }
        }
    }

    public void activeAlterationEtats(){
        for(int i =0; i< buffs.size();i++){
            if(buffs.get(i).getTourRestant() > 0){
                buffs.get(i).readBuff();
            }
        }
        for(int i =0; i< debuffs.size();i++){
            if(debuffs.get(i).getTourRestant() > 0){
                debuffs.get(i).readDebuff();
            }
        }

    }

    public boolean possedeBuff(String s){
        for(int i =0 ; i< buffs.size();i++){
            if(buffs.get(i).getNom().equals(s)){
                System.out.println("true");
                return true;
            }
        }
        System.out.println("false");

        return false;
    }

    public int modifPortee(){
        int modif = 0;
        for(int i =0; i< debuffs.size();i++){
            if(debuffs.get(i).getNom().equals("aveugle")){
                return -99;

            }
        }
        return modif ;

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
                currentX = xApres;
                currentY = yApres;
                System.out.println(deplacementDisponible);
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
                if (defenseur.getSanteCourante() <= 0){
                    gagnerArgentApresMort(defenseur);
                    defenseur.getJoueur().annuleAjout(t.getPlateau()[yD][xD].getUnite());
                    t.getPlateau()[yD][xD].supprimerUniteCase(t.getPlateau()[yD][xD]);
                }
                else if (t.getPlateau()[yD][xD].estObstacle() || t.getPlateau()[yD][xD].estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
            }
        } System.out.println("def pv avant : "+defenseur.santeCourante);
    }

    public boolean estMort(Terrain t, int xD, int yD){
        Unite defenseur = t.getPlateau()[yD][xD].getUnite();
        if (defenseur.getSanteCourante() <= 0) {
            if(defenseur.toString().equals("Mouton")){
                defenseur.getListUniteTransforme().remove(0);
            }
            gagnerArgentApresMort(defenseur);
            joueur.annuleAjout(t.getPlateau()[yD][xD].getUnite());
            t.getPlateau()[yD][xD].supprimerUniteCase(t.getPlateau()[yD][xD]);
            return true;
        }
        return false;
    }

    public boolean estDecede(){
        return santeCourante <= 0;
    }



    public void gagnerArgentApresMort(Unite uniteD){
        Joueur j;
        if(uniteD.getJoueur() == uniteD.getJoueur().getJeu().getJoueur1()) {
            j = uniteD.getJoueur().getJeu().getJoueur2();
        }else{
            j = uniteD.getJoueur().getJeu().getJoueur1();
        }
        j.setArgent(j.getArgent() + (uniteD.getCoutUnite()/3));
    }

    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){
        if (c >=0 && c < competences.length)
            competences[c].useSkill(xA,yA,xD,yD,t);
    }

    public boolean casesDisponibleDeplacement (Terrain t, Unite unite, int xPast, int yPast, int xApres, int yApres){
        int portee = unite.getPorteeDeplacement();
        ArrayList<Case> test = new ArrayList<>();
        return cheminTrouver(test, t, xPast, yPast, xApres, yApres, portee);
    }

    public boolean cheminTrouver (ArrayList<Case> test, Terrain t, int xPast, int yPast, int xApres, int yApres, int portee){
        if (xPast == xApres && yPast == yApres && portee >= 0 && estDansTableau(t, xPast, yPast)){
            test.add(t.getPlateau()[yPast][xPast]);
            setDeplacementDisponible(test);
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
        return (yPast < t.getPlateau().length && xPast < t.getPlateau()[0].length && yPast >= 0 && xPast >= 0);
    }

    public void chargeCavalier(Terrain t, int xPast, int yPast, int xApres, int yApres){
        Case avant = t.getPlateau()[yPast][xPast];
        if (t.getPlateau()[yPast][xPast].estUnit()) {
            if ((casesDisponibleDeplacementCavalier(t, avant.getUnite(), xPast, yPast, xApres, yApres)) && avant.getUnite().getPointAction() > 0) {
                System.out.println("yolo");
                Case positionInitial = avant.getUnite().getPositionUnite();
                Case destination = positionInitial.getUnite().getDeplacementDisponible().get(positionInitial.getUnite().getDeplacementDisponible().size()-3);
                destination.setUnite(avant.getUnite());
                avant.getUnite().setPositionUnite(destination);
                positionInitial.supprimerUniteCase(positionInitial);
                currentX = xApres;
                currentY = yApres;
                System.out.println(deplacementDisponible);
            }
        }
    }

    public boolean casesDisponibleDeplacementCavalier (Terrain t, Unite unite, int xA, int yA, int xD, int yD){
        int portee = unite.getPorteeDeplacement();
        ArrayList<Case> test = new ArrayList<>();
        return cheminTrouverCavalier(test, t, xA, yA, xD, yD, portee);
    }

    public boolean cheminTrouverCavalier (ArrayList<Case> test, Terrain t, int xA, int yA, int xD, int yD, int portee){
        System.out.println("pop");
        if ((xA == xD && yA == yD && portee >= 0)){
            System.out.println("WTF");
            test.add(t.getPlateau()[yA][xA]);
            System.out.println(direction);
            if(direction ==1) test.add(t.getPlateau()[yA-1][xA]);
            if(direction ==2) test.add(t.getPlateau()[yA+1][xA]);
            if(direction ==3) test.add(t.getPlateau()[yA][xA-1]);
            if(direction ==4) test.add(t.getPlateau()[yA][xA+1]);
            setDeplacementDisponible(test);
            System.out.println(test);
            return true;
        }
        if (xA == xD && portee >= 0){
            if (yA > yD){
                setDirection(1);
                if (cheminTrouverCavalier(test, t, xA, yA-1, xD, yD, portee -1) && estDansTableau(t, xA, yA-1)){
                test.add(t.getPlateau()[yA][xA]);

                    System.out.print("POP1");
                return true;
                }
            }
            else if (yA < yD){
                if (cheminTrouverCavalier(test, t, xA, yA+1, xD, yD, portee -1) && t.getPlateau()[yA+1][xA].estVide() && estDansTableau(t, xA, yA+1)){
                test.add(t.getPlateau()[yA][xA]);
                setDirection(2);

                return true;
                }
            }
        }
        else if (yA == yD && portee >= 0){
            if (xA > xD){
                if (cheminTrouverCavalier(test, t, xA-1, yA, xD, yD, portee -1) && t.getPlateau()[yA][xA-1].estVide() && estDansTableau(t, xA-1, yA)){
                test.add(t.getPlateau()[yA][xA]);
                setDirection(3);
                return true;
                }
            }
            else if (xA < xD){
                if (cheminTrouverCavalier(test, t, xA+1, yA, xD, yD, portee -1) && t.getPlateau()[yA][xA+1].estVide() && estDansTableau(t, xA+1, yA)){
                test.add(t.getPlateau()[yA][xA]);
                setDirection(4);
                return true;
                }
            }
        }
        System.out.println("false");
        return false;
    }

    public void resetPointAction(){pointAction = pointActionMax; }
}