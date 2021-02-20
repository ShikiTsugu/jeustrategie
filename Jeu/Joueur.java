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

    public boolean ajouteUnite(Unite u){
        //si la liste d'unite est vide, ajoute l'unite en première position
        if(unites[0]==null){
            unites[0]=u;
            return true;
        }
        //sinon parcourt le tableau et cherche une position vide pour y ajouter l'unite
        for (Unite un : unites) {
            if (un == null) {
                un = u;
                return true;
            }
        }
        //s'il n'y a plus de place, retourne faux
        return false;
    }
}
