public class Joueur {
    protected int argent;
    protected boolean isHuman;
    protected Unite[] unites;

    public Joueur(int a){
        argent = a;
        isHuman = true;
    }

    public int getArgent(){return argent;}

    public boolean getIsHuman(){return isHuman;}

    public void initialiseListeUnites(Terrain t){
        unites = new Unite[t.getMaxUnits()];
    }
}
