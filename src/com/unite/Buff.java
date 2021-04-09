package com.unite;

public class Buff extends AlterationEtat{
    public Buff(String nom, int tourRestant, Unite u){
        super(nom, tourRestant, u);
    }

    //fonction à venir
    public void readBuff(){
        if(nom.equals("immuniteEtourdissement")){
            immuniteEtourdissement();
        }
        if(nom.equals("immunitePoison")){
            immunitePoison();
        }
        if(nom.equals("immuniteImmobilise")){
            immuniteImmobilise();
        }
        if(nom.equals("immuniteRalentissement")){
            immuniteRalentissement();
        }
        if(nom.equals("immuniteAveugle")){
            immuniteAveugle();
        }


    }

    public void immuniteEtourdissement(){
        if (tourRestant > 0) {
            tourRestant -= 1;
        }
    }

    public void immunitePoison(){
        if (tourRestant > 0) {
            tourRestant -= 1;
        }
    }

    public void immuniteImmobilise(){
        if (tourRestant > 0) {
            tourRestant -= 1;
        }
    }

    public void immuniteRalentissement(){
        if (tourRestant > 0) {
            tourRestant -= 1;
        }
    }

    public void immuniteAveugle(){
        if (tourRestant > 0) {
            tourRestant -= 1;
        }
    }

}
