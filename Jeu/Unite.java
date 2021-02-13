public abstract class Unite {
    protected int santeMax;
    protected int attaque;
    protected int coutUnite;
    protected int porteeDeplacement;
    protected int porteeAttaque;
    protected int pointAction;
    protected Joueur joueur;
    //rajouter plus tard protected Case positionUnite;
    //rajouter plus tard protected AlterationEtat etat;
    public Unite(Joueur joueur){
        this.joueur = joueur;
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
    
    public Joueur getJoueur(){
        return joueur;
    }
    
    public void setSanteMax(int santeMax){
        this.santeMax = santeMax;
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

}