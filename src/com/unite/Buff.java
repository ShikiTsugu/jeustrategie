package com.unite;

//Class héritant de AlterationEtat, contenant les fonctions de buff pour les unités
public class Buff extends AlterationEtat{
    public Buff(String nom, int tourRestant, Unite u){
        super(nom, tourRestant, u);
    }

    public void readBuff(){
        if(nom.equals("immuniteEtourdissement")){ immuniteEtourdissement(); }
        if(nom.equals("immunitePoison")){ immunitePoison(); }
        if(nom.equals("immuniteImmobilise")){ immuniteImmobilise(); }
        if(nom.equals("immuniteRalentissement")){ immuniteRalentissement(); }
        if(nom.equals("immuniteAveugle")){ immuniteAveugle(); }
        if(nom.equals("camouflage")){ camouflage(); }
        if(nom.equals("buffProphete")){ buffProphete(); }
        if(nom.equals("Tower")){ Tower(); } //bonus lorsqu'une unité d'attaque à distance se trouve dans une tour
    }

    public void immuniteEtourdissement(){ tourRestant -= 1; }
    public void immunitePoison(){ tourRestant -= 1; }
    public void immuniteImmobilise(){ tourRestant -= 1; }
    public void immuniteRalentissement(){ tourRestant -= 1; }
    public void immuniteAveugle(){ tourRestant -= 1; }
    public void camouflage(){
        unite.setPeutEtreAttaque(false);
        tourRestant -=1;
        if(tourRestant <= 0) unite.setPeutEtreAttaque(true);
    }
    public void buffProphete(){
        if(tourRestant > 0){ //si le buff n'est pas terminé
            if(activable) {//si il peut être appliquer
                unite.setSanteCourante(unite.getSanteCourante() * 2); //double les points de vie actuels de l'unité choisi
                if (unite.getPorteeAttaque() > 1) unite.setPorteeAttaque(unite.getPorteeAttaque() * 2); //double la portée d'attaque des unités attaquant à distance
                unite.setPorteeDeplacement(unite.getPorteeDeplacement() * 2); //double la portée de déplacement
                unite.setAttaque(unite.getAttaque() * 2); //double l'attaque
            }
            activable = false; //le buff a été appliqué, il ne peut plus être appliqué jusqu'à la mort de l'unité
            tourRestant-=1;
        }
    }
    public void Tower(){
        if (unite instanceof Archer || unite instanceof Mage) { //si c'est une unité pouvant attaquer à distance
            unite.setAttaque(unite.getAttInit() + 100); //augmente l'attaque de l'archer ou du mage de 100, tant qu'il se trouve dans une tour
            tourRestant -=1;
        }
    }
}
