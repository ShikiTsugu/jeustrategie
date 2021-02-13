public class Case {
    protected boolean obstacle;
    protected Unite unit;

    public Case(){
        unit = null;
        obstacle = false;
    }

    public Case(Unite u){
        unit = u;
        obstacle = false;
    }

    public Case(boolean o){
        obstacle = o;
    }

    public boolean estVide(){
        return obstacle==false&&unit==null;
    }

    public String toString(){
        if(estVide()) return "vide";
        return obstacle==false?unit.toString():"obstacle";
    }
}