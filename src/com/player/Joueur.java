package com.player;

import com.launcher.Jeu;
import com.unite.Hero;
import com.unite.Unite;
import com.unite.Mouton;
import com.plateau.Terrain;

//Classe regroupant toutes les méthodes liés au joueur, remplissage de sa liste d'unité, système d'achat lié à son argent etc..
public class Joueur {
    protected int argent;
    protected Jeu jeu ;
    protected boolean isHuman;
    protected Unite[] unites;
    private boolean added=false;
    protected boolean propheteInvoc;

    public Joueur(int a, Jeu jeu){
        this.jeu = jeu;
        argent = a;
        isHuman = true;
        propheteInvoc = false ;
    }

    public Jeu getJeu() {return jeu;}

    public Unite getHero(){
        return unites[0];
    }

    public Unite[] getUnites() {
        return unites;
    }

    public int getArgent(){return argent;}

    public void setArgent(int a){argent = a;}

    public boolean getIsHuman(){return isHuman;}

    //Initialise la liste d'unité du joueur selon le nombre fixé d'unité possible sur un terrain spécifique.
    //Chaque joueur a le droit à la moitié du nombre possible d'unité.
    public void initialiseListeUnites(Terrain t){
        unites = new Unite[t.getMaxUnits()/2];
    }

    public boolean getPropheteInvoc(){
        return propheteInvoc;
    }

    //Permet d'actualiser l'argent du joueur selon si le joueur possède suffisamment d'argent par rapport au cout d'une unité.
    public int achat(int cout){
        if(argent==0 || argent<cout){
            return argent;
        }else{
            return argent-=cout;
        }
    }

    //Ajoute ou non une unité à la liste du joueur après qu'elle ait été achetée.
    public boolean ajouteUnite(Unite u){
        //si la liste d'unite est vide, ajoute l'unite en première position
        if(unites[0]==null&&(u instanceof Hero)){
            unites[0]=u;
            u.setPointAction(0);
            return true;
        }
        //sinon parcourt le tableau et cherche une position vide pour y ajouter l'unite
        for (int i = 1; i<unites.length; i++) {
            if (unites[i]==null) {
                u.setPointAction(0);
                unites[i] = u;
                added = true;
                return true;
            }
        }
        //s'il n'y a plus de place, retourne faux
        added = false;
        return false;
    }

    //Compte le nombre d'unité dans la liste d'unité du joueur (différent de la taille du tableau).
    public int countUnits(){
        int units=0;
        for(int i = 0; i<unites.length; i++){
            if(unites[i]!=null){
                units++;
            }
        }
        return units;
    }

    //Enlève une unité de la liste d'unité du joueur.
    public void annuleAjout(Unite u){
        for (int i = 1; i<unites.length; i++) {
            if (unites[i] == u) {
                unites[i] = null;
            }
        }
    }

    //Reset les points d'actions de TOUTES les unités que le joueur possède.
    public void resetPointAction(){
        for (Unite u : unites) {
            try {
                u.resetPointAction();
            } catch (NullPointerException e){}
        }
    }

    //Regarde si le joueur possède ou non le nombre max d'unité possible.
    public boolean maxUnit(){
        for (Unite u : unites){
            //Si une place dans la liste est null, c'est qu'il reste de la place, donc le max n'est pas atteint.
            if(u == null){
                return false;
            }
        }
        return true;
    }

    //Cherche dans les unités du joueur si une unité est un mouton ou non.
    public void detransformationPremierMouton(){
        for(Unite un : unites){
            Mouton m ;
            //Si c'est le cas, on lui redonne son apparence normale
            if(un instanceof Mouton){
                m = (Mouton)un;
                m.getUnite().setCurrentX(m.getCurrentX());
                m.getUnite().setCurrentY(m.getCurrentY());
                Unite un1 = m.getUnite();
                jeu.getTerrain().getPlateau()[un1.getCurrentY()][un1.getCurrentX()].setUnite(un1);
                un1.setPositionUnite(m.getPositionUnite());
                annuleAjout(m);
                ajouteUnite(un1);
                return;
            }
        }
    }
}
