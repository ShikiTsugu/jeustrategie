package com.player;

import com.unite.Hero;
import com.unite.Unite;
import com.plateau.Terrain;

public class Joueur {
    protected int argent;
    protected boolean isHuman;
    protected Unite[] unites;

    public Joueur(int a){
        argent = a;
        isHuman = true;
    }

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
        unites = new Unite[t.getMaxUnits()];
    }

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
            return true;
        }
        //sinon parcourt le tableau et cherche une position vide pour y ajouter l'unite
        for (int i = 1; i<unites.length; i++) {
            if (unites[i]==null) {
                unites[i] = u;
                return true;
            }
        }
        //s'il n'y a plus de place, retourne faux
        return false;
    }
}
