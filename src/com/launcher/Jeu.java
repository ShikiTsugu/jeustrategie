package com.launcher;

import com.plateau.*;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.player.Robot;
import com.unite.*;
import com.unite.Templier;

import java.io.File;
import java.util.Scanner;

public class Jeu {


    protected Joueur joueur1;
    protected ActionJoueur actionjoueur1;
    protected Joueur joueur2;
    protected ActionJoueur actionjoueur2;
    protected Terrain terrain;
    protected Joueur tourDuJoueur;
    protected String requeteCourante;
    protected Model m = new Model(selectGoodPath() + "/assets/titlescreen.png");
    protected Vue v;



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
     * getter pour le com.plateau.Terrain
     * @return le com.plateau.Terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    public Vue getV(){
        return v;
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

    public void setActionjoueur1(ActionJoueur actionjoueur1) {this.actionjoueur1 = actionjoueur1;}

    public void setActionjoueur2(ActionJoueur actionjoueur2) {this.actionjoueur2 = actionjoueur2;}

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
    public boolean gameIsOver(){
        if(joueur1.getHero().getSanteCourante() <= 0 || joueur2.getHero().getSanteCourante() <=0)
            return true;

        return false;
    }

    /**
     * fonction d'initialisation de debut de partie
     * @param terrain le terrain choisit
     * A MODIFIER
     */
    public void startNewGame(Terrain terrain){
        setJoueur1(new Joueur(1000, this));
        setActionjoueur1(new ActionJoueur(joueur1));
        joueur1.initialiseListeUnites(terrain);
        setJoueur2(new Joueur(1000, this));
        setActionjoueur2(new ActionJoueur(joueur2));
        joueur2.initialiseListeUnites(terrain);
        setTerrain(terrain);
        setTourDuJoueur(joueur1);
        setRequeteCourante("B"+ terrain.getXH1AsString() + terrain.getYH1AsString() + "H");
        requestReader();
        setTourDuJoueur(joueur2);
        setRequeteCourante("B"+ terrain.getXH2AsString() + terrain.getYH2AsString() + "H");
        requestReader();
        setTourDuJoueur(joueur1);
        setRequeteCourante("XXXXXXX");
    }

    public boolean RequestFinished(String requete){
        for(int i=0;i<requete.length();i++){
            if(requete.substring(i).equals("X")){
                return false;
            }
        }
        return true;
    }

    public void requestReader() {
        try {
            if (requeteCourante.substring(0, 1).equals("B")) {
                Unite unite;
                unite = new Templier(tourDuJoueur);
                if (requeteCourante.substring(5, 6).equals("A")) {
                    unite = new Archer(tourDuJoueur);
                }
                if (requeteCourante.substring(5, 6).equals("C")) {
                    unite = new Cavalier(tourDuJoueur);
                }
                if (requeteCourante.substring(5, 6).equals("M")) {
                    unite = new Mage(tourDuJoueur);
                }
                if (requeteCourante.substring(5, 6).equals("H")) {
                    unite = new Hero(tourDuJoueur);
                }


                if (tourDuJoueur == joueur1) {
                    actionjoueur1.placeUnite(terrain, unite, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)), true);


                } else {
                    actionjoueur2.placeUnite(terrain, unite, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)), false);
                }


            }
            if (requeteCourante.substring(0, 1).equals("D")) {


                if (tourDuJoueur == joueur1) {
                    actionjoueur1.deplaceUnite(terrain, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)),
                            Integer.parseInt(requeteCourante.substring(5, 7)), Integer.parseInt(requeteCourante.substring(7, 9)));

                } else {
                    actionjoueur2.deplaceUnite(terrain, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)),
                            Integer.parseInt(requeteCourante.substring(5, 7)), Integer.parseInt(requeteCourante.substring(7, 9)));


                }
            }
            if (requeteCourante.substring(0, 1).equals("A")) {
                if (tourDuJoueur == joueur1) {
                    actionjoueur1.attaqueUnite(terrain, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)),
                            Integer.parseInt(requeteCourante.substring(5, 7)), Integer.parseInt(requeteCourante.substring(7, 9)));
                } else {
                    actionjoueur2.attaqueUnite(terrain, Integer.parseInt(requeteCourante.substring(1, 3)), Integer.parseInt(requeteCourante.substring(3, 5)),
                            Integer.parseInt(requeteCourante.substring(5, 7)), Integer.parseInt(requeteCourante.substring(7, 9)));
                }
            }
            if (requeteCourante.equals("finDuTour")) {
                if (tourDuJoueur == joueur1) {
                    tourDuJoueur = joueur2;
                } else {
                    tourDuJoueur = joueur1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Requete non reconnue");
        }


    }

    public void playGame(Terrain terrain){
        startNewGame(terrain);
        Scanner scanner = new Scanner(System.in);
        String playerRequest;
        while(!gameIsOver()){
            terrain.Print();
            playerRequest = scanner.nextLine();
            requeteCourante = playerRequest;
            requestReader();
            System.out.println(joueur1.getUnites()[0].getSanteCourante());
            System.out.println(joueur2.getUnites()[0].getSanteCourante());

        }
        scanner.close();
    }

    public static String selectGoodPath(){
        String path = System.getProperty("user.dir");
        File checkPath = new File(path);
        if(path.endsWith("jeu-de-strat") || hasAGoodChild(checkPath,"src")){
            path+="/src/com";
        }else if(path.endsWith("src") || hasAGoodChild(checkPath,"com")){
            path+="/com";
        }
        return path;
    }

    public static boolean hasAGoodChild(File checkPath,String wanted){
        File[] listOfChildren = checkPath.listFiles();
        if (listOfChildren==null)return false;
        for(File child:listOfChildren){
            if(child.toString().endsWith(wanted)){
                return true;
            }
        }
        return false;
    }

    public void startGraphique(){
        setJoueur1(new Joueur(1000, this));
        setActionjoueur1(new ActionJoueur(joueur1));
        //setJoueur2(new Robot(1000, this));
        setJoueur2(new Joueur(1000, this));
        setActionjoueur2(new ActionJoueur(joueur2));
        tourDuJoueur = joueur1;
        v = new Vue(m,terrain,joueur1);
        v.getControlleur().setJeu(this);
        v.afficheIni();
    }

    public void getMapTerrain(){
        terrain = v.getControlleur().getMap().getTerrain();
    }

    public void AjouteHero(){
        joueur1.initialiseListeUnites(terrain);
        joueur2.initialiseListeUnites(terrain);
        Hero h1 = new Hero(joueur1);
        Hero h2 = new Hero(joueur2);
        joueur1.ajouteUnite(h1);
        joueur2.ajouteUnite(h2);
        terrain.ajouteUnite(h1,0,terrain.getPlateau().length/2);
        terrain.ajouteUnite(h2,terrain.getPlateau()[0].length-1,terrain.getPlateau().length/2);
        terrain.setEffectCase(terrain.getPlateau()[0].length/2,terrain.getPlateau().length/2, new CaseGold());
        setTerrain(terrain);
    }


    public void finDeTour(){
        if (tourDuJoueur == joueur1) {
            tourDuJoueur = joueur2;
        } else {
            tourDuJoueur = joueur1;
        }
    }

    public void activateAlterationEtats(){
        for(int i=0 ; i<joueur2.getUnites().length;i++){
            if(joueur2.getUnites()[i]!=null) {
                joueur2.getUnites()[i].readAlterationEtats();
                joueur2.getUnites()[i].checkCooldowns();
            }
        }
        for(int i=0 ; i<joueur1.getUnites().length;i++){
            if(joueur1.getUnites()[i]!=null) {
                joueur1.getUnites()[i].readAlterationEtats();
                joueur1.getUnites()[i].checkCooldowns();
            }
        }
    }


    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        //jeu.playGame(terrain);
        //System.out.println(act.placeUnite(terrain,joueur.getUnites()[1],1,1, true));
        jeu.startGraphique();
        //System.out.println(joueur2.getArgent());
    }
}