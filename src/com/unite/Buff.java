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
        if(nom.equals("camouflage")){
            camouflage();
        }
        if(nom.equals("buffProphete")){
            buffProphete();
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

    public void camouflage(){
        unite.setPeutEtreAttaque(false);
        tourRestant -=1;
        if(tourRestant <= 0) unite.setPeutEtreAttaque(true);
    }

    public void buffProphete(){
        if(tourRestant > 0){
            unite.setSanteCourante(unite.getSanteCourante()*2);
            if(unite.getPorteeAttaque() > 1) unite.setPorteeAttaque(unite.getPorteeAttaque()*2);
            unite.setPorteeDeplacement(unite.getPorteeDeplacement()*2);
            unite.setAttaque(unite.getAttaque()*2);
            tourRestant-=1;
        }
    }

}
