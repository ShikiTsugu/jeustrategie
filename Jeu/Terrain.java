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

    public int getMaxUnits(){return maxUnits;}
}