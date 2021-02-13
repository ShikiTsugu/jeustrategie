public class Terrain {
    protected Case[][] plateau;

    public Terrain(int largeur, int hauteur){
        plateau = new Case[hauteur][largeur];
    }

    public Terrain(Case[][] p){
        plateau = p;
    }


}