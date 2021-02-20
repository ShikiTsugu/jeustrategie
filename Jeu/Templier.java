public class Templier extends Unite{
    
    public Templier(Joueur joueur){
        super(joueur);
        santeMax = 100; //discussion en cours
        santeCourante = 100;
        attaque = 10; //discussion en cours
        coutUnite = 200; //discussion en cours
        porteeDeplacement = 3; //discussion en cours
        porteeAttaque = 5; //discussion en cours
        pointAction = 2; //discussion en cours
    }
    
    public String toString(){
        return "templier";
    }
    
    public final boolean isHero(){
        return false;
    }
}