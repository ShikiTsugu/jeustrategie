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
    
    public Case positionUnite(){
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
    
    public void deplaceUnite(Unite unit, Case destination){
        if (destination.estVide()){
            Case positionInitial = unit.getPositionUnite();
            destination.setUnite(unit);
            unit.setPositionUnite(destination);
            positionInitial.supprimerUniteCase();
        } 
    }
    
    public void attaqueUnite(Case positionA, Case positionD){
        Unite attaquant = positionA.getUnit();
        Unite defenseur = positionD.getUnit();
        if (positionD.estUnit() && !positionD.estObstable()){
            attaquant.setPointAction(attaquant.getPointAction() -1);
            defenseur.setSanteCourante(defenseur.getSanteCourante()- attaquant.getAttaque());
        }
        else if (positionD.estObstacle() || positionD.estVide()) attaquant.setPointAction(attaquant.getPointAction() -1);
    }

}