package com.player;

import com.launcher.Jeu;
import com.unite.Hero;
import com.unite.Unite;
import com.plateau.Terrain;

public class Joueur {
    protected int argent;
    protected Jeu jeu ;
    protected boolean isHuman;
    protected Unite[] unites;
    private boolean added=false;

    public Joueur(int a, Jeu jeu){
        this.jeu = jeu;
        argent = a;
        isHuman = true;
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

    public void initialiseListeUnites(Terrain t){
        unites = new Unite[t.getMaxUnits()/2];
    }

    public boolean getAdded(){return added;}

    public int achat(int cout){
        if(argent==0 || argent<cout){
            return argent;
        }else{
            return argent-=cout;
        }
    }

    public boolean ajouteUnite(Unite u){
        //si la liste d'unite est vide, ajoute l'unite en première position
        if(unites[0]==null&&(u instanceof Hero)){
            unites[0]=u;
            u.setPointAction(0);
            System.out.println("Hero ajouté");
            return true;
        }
        //sinon parcourt le tableau et cherche une position vide pour y ajouter l'unite
        for (int i = 1; i<unites.length; i++) {
            if (unites[i]==null) {
                u.setPointAction(0);
                unites[i] = u;
                System.out.println(u+" Ajouté");
                added = true;
                return true;
            }
        }
        //s'il n'y a plus de place, retourne faux
        System.out.println("Plus de place");
        added = false;
        return false;
    }

    public void annuleAjout(Unite u){
        for (int i = 1; i<unites.length; i++) {
            if (unites[i] == u) {
                unites[i] = null;
            }
        }
    }

    public void displayList(){
        for (int i = 0; i<unites.length;i++){
            System.out.println(unites[i]);
        }
    }

    public void resetPointAction(){
        for (Unite u : unites) {
            try {
                u.resetPointAction();
            } catch (NullPointerException e){}
        }
    }
}
