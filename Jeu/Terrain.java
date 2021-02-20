public class Terrain {
    protected Case[][] plateau;
    protected int maxUnits;

    public Terrain(int largeur, int hauteur, int max){
        plateau = new Case[hauteur][largeur];
        maxUnits = max;
    }

    public Terrain(Case[][] p, int max){
        plateau = p;
        maxUnits = max;
    }
    
    public void deplaceUnite(Unite unit, Case destination){
        if (destination.estVide()){
            Case positionInitial = unit.getPositionUnite();
            destination.setUnite(unit);
            unit.setPositionUnite(destination);
            positionInitial.supprimerUniteCase();
        } 
    }

    public int getMaxUnits(){return maxUnits;}
}