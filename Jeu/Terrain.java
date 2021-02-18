public class Terrain {
    protected Case[][] plateau;
    protected int maxUnits;
    protected int nbUnits;

    public Terrain(int largeur, int hauteur, int max){
        plateau = new Case[hauteur][largeur];
        maxUnits = max;
    }

    public Terrain(Case[][] p, int max){
        plateau = p;
        maxUnits = max;
    }

    public void PlaceUnit(int x, int y, Unite unite){
        if(maxUnits <= nbUnits){
            plateau[x][y].addUnit(unite);
            nbUnits++;
        } else {
            System.out.println("Vous avez trop d'unité");
        }
    }

    public int getMaxUnits(){return maxUnits;}
}