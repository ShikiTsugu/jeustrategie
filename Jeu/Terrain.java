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

    public boolean ajouteUnite(Unite u, int x, int y){
        try{
            if(plateau[y][x]==null){
                plateau[y][x]=new Case(u);
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public int getMaxUnits(){return maxUnits;}
}