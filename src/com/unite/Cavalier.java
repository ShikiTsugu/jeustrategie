package com.unite;

import com.plateau.Terrain;
import com.player.Joueur;

public class Cavalier extends Unite{
    
    public Cavalier(Joueur joueur){
        super(joueur);
        santeMax = 300; //discussion en cours
        santeCourante = 300;
        attaque = 150; //discussion en cours
        attInit = 150; //discussion en cours
        coutUnite = 600; //discussion en cours
        porteeDeplacement = 6; //discussion en cours
        porteeAttaque = 1; //discussion en cours
        pointActionMax = 2; //discussion en cours
        pointAction = 2; //discussion en cours
        competences = new Competence[2];
        setComp();
    }
    
    public String toString(){
        return "Cavalier";
    }

    public void utiliseCompetence(int xA, int yA, int xD,int yD,int c, Terrain t){
        if (c >=0 && c < competences.length) {
            //prise en charge de compétence particulière de la charge
            if(c==1) {
                chargeCavalier(xA, yA, xD, yD, t);
            }else {
                competences[c].useSkill(xA, yA, xD, yD, t);
            }
        }
    }

    public void chargeCavalier(int xA, int yA, int xD,int yD, Terrain t){

            Evenement[] eventCharge = new Evenement[25];
            int e =0;
            //cas pour déplacement vertical
            if(xA == xD){
                //cas direction vers le bas
                if(yA<yD){
                    int i =1;

                    while( (yA +i)<=yD){
                        //condition d'arrêt
                        if( t.getPlateau()[yA+i][xA].casePos(t) ==null  || t.getPlateau()[yA+i][xA].estVide()==false || yA+i >=yD){
                            //dans le cas où le Cavalier rencontre une unite
                            if(t.getPlateau()[yA+i][xA].estUnit()){
                                Unite cible = t.getPlateau()[yA+i][xA].getUnite();
                                eventCharge[e] = new Evenement("infligeDegatsUniteSpecifique",cible,0,0,attaque);
                                e++;



                                //regarde la case derrière
                                //si elle est occupé ,on étourdit la cible
                                if((yA+i+1) >=t.getPlateau().length || t.getPlateau()[yA+i+1][xA].estVide()==false){
                                    eventCharge[e] = new Evenement("appliqueEtourdissementUniteSpecifique",cible,0,0,2);
                                    e++;

                                    //sinon on la déplace d'une case en arrière
                                }else{
                                    eventCharge[e] = new Evenement("deplacementUniteSpecifique",cible,0,1,0);
                                    e++;
                                }
                            }
                            if(t.getPlateau()[yA+i][xA].estVide()){
                                eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,0,1,0);
                                e++;
                            }
                            competences[1].setEffets(eventCharge);

                            competences[1].useSkill(xA, yA, xD, yD, t);
                            return;
                        }else{

                            eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,0,1,0);
                            System.out.println("deplacement cavalier ajouté");
                            e++;
                            i++;
                        }
                    }


                }
                //cas direction vers le haut
                if(yA>yD){
                    int i =1;

                    while( (yA -i)>=yD){
                        //condition d'arrêt
                        if( t.getPlateau()[yA-i][xA].casePos(t) ==null  || t.getPlateau()[yA-i][xA].estVide()==false || yA-i <=yD){
                            //dans le cas où le Cavalier rencontre une unite
                            if(t.getPlateau()[yA-i][xA].estUnit()){
                                Unite cible = t.getPlateau()[yA-i][xA].getUnite();
                                eventCharge[e] = new Evenement("infligeDegatsUniteSpecifique",cible,0,0,attaque);
                                e++;


                                //regarde la case derrière
                                //si elle est occupé ,on étourdit la cible
                                if((yA-i-1) < 0 || t.getPlateau()[yA-i-1][xA].estVide()==false ){
                                    eventCharge[e] = new Evenement("appliqueEtourdissementUniteSpecifique",cible,0,0,2);
                                    e++;

                                    //sinon on la déplace d'une case en arrière
                                }else{
                                    eventCharge[e] = new Evenement("deplacementUniteSpecifique",cible,0,-1,0);
                                    e++;
                                }
                            }
                            if(t.getPlateau()[yA-i][xA].estVide()){
                                eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,0,-1,0);
                                e++;
                            }
                            competences[1].setEffets(eventCharge);
                            competences[1].useSkill(xA, yA, xD, yD, t);
                            return;
                        }else{
                            eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,0,-1,0);
                            e++;
                            i++;
                        }
                    }


                }

            }

            //cas pour déplacement horizontal
            if(yA == yD){
                //cas direction vers la droite
                if(xA<xD){
                    int i =1;

                    while( (xA +i)<=xD){
                        //condition d'arrêt
                        if( t.getPlateau()[yA][xA+i].casePos(t) ==null  || t.getPlateau()[yA][xA+i].estVide()==false || xA+i >=xD){
                            //dans le cas où le Cavalier rencontre une unite
                            if(t.getPlateau()[yA][xA+i].estUnit()){
                                Unite cible =t.getPlateau()[yA][xA+i].getUnite();
                                eventCharge[e] = new Evenement("infligeDegatsUniteSpecifique",cible,0,0,attaque);
                                e++;

                                //regarde la case derrière
                                //si elle est occupé ,on étourdit la cible
                                if( (xA+i+1) >=t.getPlateau()[yA].length  ||t.getPlateau()[yA][xA+i+1].estVide()==false ){
                                    eventCharge[e] = new Evenement("appliqueEtourdissementUniteSpecifique",cible,0,0,2);
                                    e++;

                                    //sinon on la déplace d'une case en arrière
                                }else{
                                    eventCharge[e] = new Evenement("deplacementUniteSpecifique",cible,1,0,0);
                                    e++;
                                }
                            }
                            if(t.getPlateau()[yA][xA+i].estVide()){
                                eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,1,0,0);
                                e++;
                            }
                            competences[1].setEffets(eventCharge);
                            competences[1].useSkill(xA, yA, xD, yD, t);
                            return;
                        }else{
                            eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,1,0,0);
                            e++;
                            i++;
                        }
                    }


                }
                if(xA>xD){
                    int i =1;

                    while( (xA -i)>=xD){
                        //condition d'arrêt
                        if( t.getPlateau()[yA][xA-i].casePos(t) ==null  || t.getPlateau()[yA][xA-i].estVide()==false || xA-i <=xD){
                            //dans le cas où le Cavalier rencontre une unite
                            if(t.getPlateau()[yA][xA-i].estUnit()){
                                Unite cible = t.getPlateau()[yA][xA-i].getUnite();
                                eventCharge[e] = new Evenement("infligeDegatsUniteSpecifique",cible,0,0,attaque);
                                e++;

                                //regarde la case derrière
                                //si elle est occupé ,on étourdit la cible
                                if((xA-i-1) < 0 || t.getPlateau()[yA][xA-i-1].estVide()==false ){
                                    eventCharge[e] = new Evenement("appliqueEtourdissementUniteSpecifique",cible,0,0,2);
                                    e++;

                                    //sinon on la déplace d'une case en arrière
                                }else{
                                    eventCharge[e] = new Evenement("deplacementUniteSpecifique",cible,-1,0,0);
                                    e++;
                                }
                            }
                            if(t.getPlateau()[yA][xA-i].estVide()){
                                eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,-1,0,0);
                                e++;
                            }
                            competences[1].setEffets(eventCharge);
                            competences[1].useSkill(xA, yA, xD, yD, t);
                            return;
                        }else{
                            eventCharge[e] = new Evenement("deplacementUniteSpecifique",this,-1,0,0);
                            e++;
                            i++;
                        }
                    }
                }
            }
    }
    
    public final boolean isHero(){
        return false;
    }

    public void setComp(){
        Evenement[] event = {new Evenement("infligeDegats",0,0,attaque,joueur)};
        competences[0] = new Competence("coup d'épée","lancer un violent coup d'épée",event, 1,1, 0,this);
        Evenement[] event2 = {new Evenement("charge", this, 0, 0, attaque-50)};
        competences[1] = new Competence("charge", "charge en ligne droite pour infliger des dégats", event2, porteeDeplacement, 2, 4,this);
    }
}
