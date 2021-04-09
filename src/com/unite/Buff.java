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
            tourRestant -= 1;

    }

    public void immunitePoison(){
            tourRestant -= 1;

    }

    public void immuniteImmobilise(){
            tourRestant -= 1;

    }

    public void immuniteRalentissement(){
            tourRestant -= 1;

    }

    public void immuniteAveugle(){
            tourRestant -= 1;

    }

}
