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
    protected int attInit;
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
    protected ArrayList<Unite> listUniteTransforme; //pour la transformation en mouton
    protected int direction; //direction du déplacement (nord/sud/est/ouest)
    private LinkedList<int[]> coordTarget = new LinkedList<>();

    public Unite(Joueur joueur){ //constructeur d'unité
        this.joueur = joueur;
        buffs = new ArrayList<Buff>();
        debuffs = new ArrayList<Debuff>();
    }

    public LinkedList<int[]> getCoordTarget(){return coordTarget;}
    
    public int getSanteMax(){ return santeMax; }

    public int getSanteCourante(){ return santeCourante; }

    public int getAttaque(){ return attaque; }

    public int getAttInit() { return attInit; }
    
    public int getCoutUnite(){ return coutUnite; }
    
    public int getPorteeDeplacement(){ return porteeDeplacement; }
    
    public int getPorteeAttaque(){ return porteeAttaque; }

    public int getPointActionMax() { return pointActionMax; }
    
    public int getPointAction(){ return pointAction; }
    
    public Joueur getJoueur(){ return joueur; }

    public Terrain getTerrain() { return terrain;  }

    public Case getPositionUnite(){ return positionUnite; }
    
    public ArrayList<Case> getDeplacementDisponible(){return deplacementDisponible;}

    public int getCurrentX() { return currentX; }

    public int getCurrentY() { return currentY; }

    public Competence[] getCompetences() { return competences; }

    public boolean getPeutEtreAttaque(){return peutEtreAttaque;}

    public ArrayList<Unite> getListUniteTransforme(){return listUniteTransforme;}

    public ArrayList<Buff> getBuffs() { return buffs; }

    public ArrayList<Debuff> getDebuffs() { return debuffs; }

    public void setSanteMax(int santeMax){ this.santeMax = santeMax; }
    
    public void setSanteCourante(int santeCourante){ this.santeCourante = santeCourante; }
    
    public void setAttaque(int attaque){ this.attaque = attaque; }
    
    public void setCoutUnite(int coutUnite){ this.coutUnite = coutUnite; }
    
    public void setPorteeDeplacement(int porteeDeplacement){ this.porteeDeplacement = porteeDeplacement; }
    
    public void setPorteeAttaque(int porteeAttaque){ this.porteeAttaque = porteeAttaque; }

    public void setPointActionMax(int pointActionMax){ this.pointActionMax = pointActionMax; }
    
    public void setPointAction(int pointAction){ this.pointAction = pointAction; }
    
    public void setJoueur(Joueur joueur){ this.joueur = joueur; }

    public void setTerrain(Terrain terrain) { this.terrain = terrain;  }

    public void setPositionUnite(Case positionUnite){ this.positionUnite = positionUnite; }

    public void setCurrentX(int currentX) { this.currentX = currentX; }

    public void setCurrentY(int currentY) { this.currentY = currentY; }

    public void setCompetences(Competence[] competences) { this.competences = competences; }

    public void setDeplacementDisponible(ArrayList<Case> deplacementDisponible){this.deplacementDisponible = deplacementDisponible;}

    public void addBuff(String a,int n){ buffs.add(new Buff(a,n,this)); }

    public void addDebuff(String a,int n){ debuffs.add(new Debuff(a,n,this)); }

    public void setPeutEtreAttaque(boolean peutEtreAttaque){this.peutEtreAttaque = peutEtreAttaque;}

    public void setDirection(int direction){this.direction = direction;}

    public void readAlterationEtats(){ //permet d'actualiser et appliquer la liste d'altération d'état pour chaque unité
        updateAlterationEtats();
        activeAlterationEtats();
    }

    public void checkCooldowns(){ //actulise le cooldown de chaque compétence de l'unité
        for(int i =0 ; i<competences.length ;i++){ //pour chaque compétence de l'unité
            if(competences[i].getCooldownActuel() > 0 ){ //si le cooldown d'une compétence est supérieur à 0
                competences[i].setCooldownActuel(competences[i].getCooldownActuel()-1); //on baisse le cooldown de 1
            }
        }
    }

    public void updateAlterationEtats(){ //actualise les altérations d'états de l'unité
        for(int i =0; i< buffs.size();i++){ //pour chaque buff possible
            if(buffs.get(i).getTourRestant() <= 0){ //si le buff est terminé
                buffs.remove(i); //retire le buff
            }
        }
        for(int i =0; i< debuffs.size();i++){ //pour chaque débuff possible
            if(debuffs.get(i).getTourRestant() <= 0){ //si le débuff est terminé
                debuffs.remove(i); //retire le débuff
            }
        }
    }

    public void activeAlterationEtats(){ //applique les altérations d'états de l'unité
        for(int i =0; i< buffs.size();i++){ //pour chaque buff possible
            if(buffs.get(i).getTourRestant() > 0){ //si le buff n'est pas fini
                buffs.get(i).readBuff(); //applique le buff
            }
        }
        for(int i =0; i< debuffs.size();i++){ //pour chaque débuff possible
            if(debuffs.get(i).getTourRestant() > 0){ //si le débuff n'est pas fini
                debuffs.get(i).readDebuff(); //applique le débuff
            }
        }
    }

    public boolean possedeBuff(String s){ //retourne un boolean permettant de savoir si l'unité possède un buff
        for(int i =0 ; i< buffs.size();i++){ //pour chaque buff possible
            if(buffs.get(i).getNom().equals(s)){ //si le nom du buff correspond au buff donné à la fonction
                return true; //renvoie vrai
            }
        }
        return false; //renvoie faux sinon
    }

    public int modifPortee(){ //renvoie la nouvelle portée de déplacement et d'attaque en cas du débuff "aveugle"
        int modif = 0;
        for(int i =0; i< debuffs.size();i++){ //pour chaque débuff possible
            if(debuffs.get(i).getNom().equals("aveugle")){ //si le nom du débuff est "aveugle"
                return -99;
            }
        }
        return modif;
    }
    
    public void deplaceUnite(Terrain t, int xPast, int yPast, int xApres, int yApres){ //déplace l'unité aux coordonnées d'arrivées
        Case initiale= t.getPlateau()[yPast][xPast]; //case actuelle de l'unité
        if (t.getPlateau()[yPast][xPast].estUnit()) { //vérification qu'on a une unité dans cette case
            Case destination = t.getPlateau()[yApres][xApres]; //case destination à laquelle on veut déplacer l'unité
            if (destination.estVide() && (casesDisponibleDeplacement(t, initiale.getUnite(), xPast, yPast, xApres, yApres)) && initiale.getUnite().getPointAction() > 0) {
                //si la destination est vide, si il existe un chemin permettant d'y aller et que l'unité a assez de point d'action pour se déplacer
                initiale.getUnite().setPointAction(initiale.getUnite().getPointAction() -1); //l'unité perd 1 PA pour se déplacer
                Case positionInitial = initiale.getUnite().getPositionUnite(); //récupère la case de l'unité qu'on veut déplacer
                destination.setUnite(initiale.getUnite()); //défini l'unité dans la case destination
                initiale.getUnite().setPositionUnite(destination); //défini la nouvelle position de l'unité
                positionInitial.supprimerUniteCase(positionInitial); //la case initiale ne possède plus d'unité
                currentX = xApres;
                currentY = yApres;
            }
        }
    }
    
    public void attaqueUnite(Terrain t, int xA, int yA, int xD, int yD){ //pour le robot, permet à une unité d'attaquer
        Unite attaquant = t.getPlateau()[yA][xA].getUnite();
        Unite defenseur = t.getPlateau()[yD][xD].getUnite();
        if (t.getPlateau()[yA][xA].estUnit() && attaquant.getPointAction() > 0) { //si l'attaquant est une unité existante et qu'elle a assez de point d'action
            if (t.getPlateau()[yD][xD].estUnit() && ((Math.abs(yD - yA)+Math.abs(xD - xA)) <= attaquant.getPorteeAttaque())){
                //si le défenseur est une unité existante et que la distance entre l'attaquant et le défenseur correspond à la portée d'attaque de l'attaquant
                attaquant.setPointAction(attaquant.getPointAction() -1); //l'unité perd 1PA pour attaquer
                defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque()); //le défenseur perd des points de vie par rapport à l'attaque de l'attaquant
                if (defenseur.getSanteCourante() <= 0){ //si le défenseur n'a plus de point de vie
                    gagnerArgentApresMort(defenseur); //l'attaquant gagne de l'argent
                    defenseur.getJoueur().annuleAjout(t.getPlateau()[yD][xD].getUnite()); //retire le défenseur de la liste des unités du joueur possédant le défenseur
                    t.getPlateau()[yD][xD].supprimerUniteCase(t.getPlateau()[yD][xD]); //retire l'unité du terrain
                }
            }
        }
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

    public boolean estMort(Terrain t, Unite u){
        Unite defenseur = u;
        if (defenseur.getSanteCourante() <= 0) {
            if(defenseur.toString().equals("Mouton")){
                defenseur.getListUniteTransforme().remove(0);
            }
            gagnerArgentApresMort(defenseur);
            joueur.annuleAjout(defenseur);
            t.getPlateau()[defenseur.getCurrentY()][defenseur.getCurrentX()].supprimerUniteCase(t.getPlateau()[defenseur.getCurrentY()][defenseur.getCurrentX()]);
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
        if ((xA == xD && yA == yD && portee >= 0)){
            test.add(t.getPlateau()[yA][xA]);
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
                if (cheminTrouverCavalier(test, t, xA, yA-1, xD, yD, portee -1) && t.getPlateau()[yA-1][xA].estVide() && estDansTableau(t, xA, yA-1)){
                test.add(t.getPlateau()[yA][xA]);
                setDirection(1);
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
        return false;
    }
    public void resetPointAction(){pointAction = pointActionMax; }
    public abstract String toString();
    public abstract boolean isHero();
    public abstract void setComp();

}