public class Jeu {


    protected Joueur joueur1;
    protected Joueur joueur2;
    protected Terrain terrain;


    /**
     * getter pour le joueur 1
     * @return le joueur 1
     */
    public Joueur getJoueur1() {
        return joueur1;
    }

    /**
     * getter pour le joueur 2
     * @return le joueur 2
     */
    public Joueur getJoueur2() {
        return joueur2;
    }

    /**
     * getter pour le Terrain
     * @return le Terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * setter pour le nouveau joueur 1
     * @param joueur1 le nouveau joueur 1
     */
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    /**
     * setter pour le nouveau joueur 2
     * @param joueur2 le nouveau joueur 2
     */
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    /**
     * setter pour le nouveau terrain
     * @param terrain le nouveau terrain
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public boolean gameIsOver(){
        if(joueur1.getHero().getPv() >= 0 || joueur2.getHero().getPv()) 
            return true;
        
        return false;
    }

    public void startNewGame(Joueur joueur1 ,Joueur joueur2, Terrain terrain){
        setJoueur1(joueur1);
        setJoueur2(joueur2);
        setTerrain(terrain);
    }

    public void joueur1Play(){

    }



    
    public static void main(String[] args) {

    }

}