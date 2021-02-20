public class Jeu {


    protected Joueur joueur1;
    protected Joueur joueur2;
    protected Terrain terrain;
    protected Joueur tourDuJoueur;
    protected String requeteCourante;



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters\Setters
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
     * getter pour le joueur auqeul c'est à qui de jouer
     * @return le joueur a qui est le tour
     */
    public Joueur getTourDuJoueur() {
        return tourDuJoueur;
    }

    /**
     * getter de la requete courante
     * @return la requete courante
     */
    public String getRequeteCourante() {
        return requeteCourante;
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


    /**
     * setter du joueur à qui est le tour de jouer
     * @param tourDuJoueur le nouveau joueur a qui est le tour
     */
    public void setTourDuJoueur(Joueur tourDuJoueur) {
        this.tourDuJoueur = tourDuJoueur;
    }

    /**
     * setter pour changer la requete courante
     * @param requeteCourante la nouvelle requete
     */
    public void setRequeteCourante(String requeteCourante) {
        this.requeteCourante = requeteCourante;
    }


    /**
     * fonction qui verifie si la partie est finie
     * @return le boolean disant si la partie est finie
     * A MODIFIER
     */
   /* public boolean gameIsOver(){
        if(joueur1.getHero().getPv() <= 0 || joueur2.getHero().getPv() <=0) 
            return true;
        
        return false;
    }*/

    /**
     * fonction d'initialisation de debut de partie
     * @param joueur1 le joueur 1
     * @param joueur2 le joueur 2
     * @param terrain le terrain choisi
     * A MODIFIER
     */
    public void startNewGame(Joueur joueur1 ,Joueur joueur2, Terrain terrain){
        setJoueur1(joueur1);
        setJoueur2(joueur2);
        setTerrain(terrain);
        setTourDuJoueur(joueur1);
    }

    
    /*
    public void playGame(){
        startNewGame();
        while(!gameIsOver()){


        }
    }
    */
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fonction de requètes joueur
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * fonction qui prend en charge les requête d'achats d'unite
     * @param joueur le joueur qui a effectué la requête
     * @param unite l'unite à place sur le terrain
     * @param x la coordonné x où place l'unite
     * @param y la coordonné y où place l'unite
     * @return le boolean indiquant la réussite de la requête
     * A MODIFIER
     */
    public boolean acheteUnite(Joueur joueur ,Unite unite, int x , int y){
        //verifie si le joueur possède assez d'argent
        if(joueur.getArgent()< unite.getCout())
            return false;
        //fait appele à la fonction d'ajout d'unite sur le terrain
        if(!terrain.ajouteUnite( unite, x,y)){
            return false;
        }
        //fait appele à la fonction d'ajout dans l'armée du joueur
        return joueur.ajouteUnite(unite);
    }


    /**
     * fonction qui prend en charge les requête de deplacement d'unite
     * @param joueur le joueur qui a effectué la requête
     * @param unite l'unite à place sur le terrain
     * @param destination la case de destination
     * @return le boolean indiquant la réussite de la requête
     */
    public boolean deplaceUnite(Joueur joueur ,Unite unite, Case destination){
        //verifie si l'unite appartient au joueur
        if(joueur == unite.getJoueur())
            return terrain.deplaceUnite(unite , destination );
        return false;
    }

    /**
     * fonction qui prend en charge les requête d'attaque d'unite
     * @param joueur le joueur qui a effectué la requête
     * @param unite l'unite à place sur le terrain
     * @param depart case de depart
     * @param cible la case cible
     * @return le boolean indiquant la réussite de la requête
     */
    public boolean attaqueUnite(Joueur joueur,Case depart,Case cible){
        if(depart.getUnite() == null){
            return false;
        }
        //verifie si l'unite appartient au joueur
        if(joueur != depart.getUnite().getJoueur())
            return false;
        //verifie si l'emplacement à une unité puis si cette dernière est du camp opposé
        if(cible.getUnite() == null || cible.getUnite().getJoueur() == joueur)
            return false;
        
        depart.getUnite().attaqueUnite(depart, cible);
        return true
    }

    







    
    public static void main(String[] args) {
        Terrain terrain = new Terrain(5,5,2);
        Joueur joueur = new Joueur(2);
        Templier templier = new Templier(joueur);
        System.out.println(terrain.ajouteUnite(templier,0,0));
        Model m = new Model("Jeu/plaine.png");
        Vue v = new Vue(m, terrain);
        v.AfficheTerrain();
    }

}