public abstract class Unite {
    protected int santeMax;
    protected int attaque;
    protected int coutUnite;
    protected int porteeDeplacement;
    protected int porteeAttaque;
    protected int pointAction;
    //rajouter plus tard protected Joueur joueur;
    //rajouter plus tard protected Case positionUnite;
    //rajouter plus tard protected AlterationEtat etat;
    public Unite(){//prend en argument Joueur joueur plus tard
        //this.joueur = joueur;
    }
    
    public int getSanteMax(){
        return santeMax;
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

}