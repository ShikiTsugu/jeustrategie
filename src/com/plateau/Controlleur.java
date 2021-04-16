package com.plateau;

import com.launcher.Jeu;
import com.player.*;
import com.player.Robot;
import com.unite.Unite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Controlleur {
    private Vue vue;
    private Jeu jeu;
    private int nbTour;
    private Map map;

    public Controlleur(Vue v){
        jeu = new Jeu();
        vue = v;
        nbTour = 1;
        map = new Map();
    }

    public Map getMap() {
        return map;
    }

    public Jeu getJeu(){
        return jeu;
    }

    public void setJeu(Jeu j){
        jeu = j;
    }

    public boolean acheteUnite(Joueur j, Unite u){
        if (!j.ajouteUnite(u)) {
            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le max d'unité a été atteint.", "", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Achat impossible");
            return false;
        } else {
            ActionJoueur aj = new ActionJoueur(j);
            aj.setBought(true);
            boolean b = jeu.getTourDuJoueur() == jeu.getJoueur1();
            placeUniteApresAchat(u, aj,b);
            return true;
        }
    }

    public void placeUniteApresAchat(Unite u, ActionJoueur j, boolean J1){
        for (JButton b : vue.terrainBt) {
            if(j.getJoueur()==jeu.getJoueur1()){
                if(vue.terrain.getB1()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]==true) {
                    if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                        b.setContentAreaFilled(true);
                        b.setBackground(new Color(0, 150, 0));
                    }else{
                        b.setContentAreaFilled(true);
                        b.setBackground(new Color(150, 0, 0));
                    }
                }else{
                    b.setContentAreaFilled(true);
                    b.setBackground(new Color(150, 0, 0));
                }
            }
            if(j.getJoueur()==jeu.getJoueur2()){
                if(vue.terrain.getB2()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]==true) {
                    if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                        b.setContentAreaFilled(true);
                        b.setBackground(new Color(0, 150, 0));
                    }else{
                        b.setContentAreaFilled(true);
                        b.setBackground(new Color(150, 0, 0));
                    }
                }else{
                    b.setContentAreaFilled(true);
                    b.setBackground(new Color(150, 0, 0));
                }
            }

            b.addActionListener((ActionEvent e) -> {
                if (j.getJoueur()==jeu.getJoueur1()) {
                    if (vue.terrain.getB1()[b.getY() / b.getHeight()][b.getX() / b.getWidth()] == true) {
                        if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                            j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                        } else {
                            j.getJoueur().annuleAjout(u);
                            j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Il y a déjà une unité.", "", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        j.getJoueur().annuleAjout(u);
                        j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                        JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Impossible, en dehors de la zone d'achat.", "", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                if (j.getJoueur()==jeu.getJoueur2()) {
                    if (vue.terrain.getB2()[b.getY() / b.getHeight()][b.getX() / b.getWidth()] == true) {
                        if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                            j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                        } else {
                            j.getJoueur().annuleAjout(u);
                            j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Il y a déjà une unité.", "", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        j.getJoueur().annuleAjout(u);
                        j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                        JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Impossible, en dehors de la zone d'achat.", "", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void attaque(Joueur j, JButton attaquant){
        ActionJoueur aj = new ActionJoueur(j);
        int[]coordI = {attaquant.getX()/attaquant.getWidth(), attaquant.getY()/attaquant.getHeight()};
        int[]coordF = new int[2];
        Unite atq = vue.terrain.getPlateau()[coordI[1]][coordI[0]].unit;
        for (JButton b : vue.terrainBt) {
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setContentAreaFilled(true);
                    if(vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].estUnit() && ((Math.abs(b.getY()/b.getHeight() - coordI[1])+Math.abs(b.getX()/b.getWidth() - coordI[0])) <= atq.getPorteeAttaque())
                            && vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit!=atq && vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getJoueur()!=j) {
                        b.setBackground(new Color(0, 150, 0));
                    }else{
                        b.setBackground(new Color(150, 0, 0));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    b.setContentAreaFilled(false);
                }
            });
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX()/b.getWidth();
                coordF[1] = b.getY()/b.getHeight();
                try {
                    int hpMax = vue.terrain.getPlateau()[coordF[1]][coordF[0]].unit.getSanteCourante();
                    if (aj.attaqueUnite(vue.terrain, coordI[0], coordI[1], coordF[0], coordF[1])) {
                        int hpLost = hpMax - vue.terrain.getPlateau()[coordF[1]][coordF[0]].unit.getSanteCourante();
                        JOptionPane.showMessageDialog(vue.getTerrainPanel(), "PV perdus : " + hpLost,
                                vue.terrain.getPlateau()[coordF[1]][coordF[0]].unit.toString() +
                                        (vue.terrain.getPlateau()[coordI[1]][coordI[0]].unit.getJoueur() == j ? " Adverse" : " Allié"),
                                JOptionPane.PLAIN_MESSAGE,
                                vue.generateImage(vue.terrain.getPlateau()[coordF[1]][coordF[0]].unit.toString()));
                    }
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Vous attaquez dans le vide.", "", JOptionPane.PLAIN_MESSAGE);
                }
                vue.generateTerrain();
                vue.generateTaskBar();
                if (jeu.getJoueur1().getHero().getSanteCourante() <= 0){
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 2 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
                }   else if (jeu.getJoueur2().getHero().getSanteCourante() <= 0){
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 1 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
                }
            });
        }
    }

    public void useSkill(Joueur j, JButton cast,int c) {
        ActionJoueur aj = new ActionJoueur(j);
        int[] coordI = {cast.getX() / cast.getWidth(), cast.getY() / cast.getHeight()};
        int[] coordF = new int[2];
        Unite atq = vue.terrain.getPlateau()[coordI[1]][coordI[0]].unit;
        for (JButton b : vue.terrainBt) {
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setContentAreaFilled(true);
                    if (vue.terrain.getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estUnit() && ((Math.abs(b.getY() / b.getHeight() - coordI[1]) + Math.abs(b.getX() / b.getWidth() - coordI[0])) <= atq.getPorteeAttaque())
                            && vue.terrain.getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].unit != atq && vue.terrain.getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].unit.getJoueur() != j) {
                        b.setBackground(new Color(0, 150, 0));
                    } else {
                        b.setBackground(new Color(150, 0, 0));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    b.setContentAreaFilled(false);
                }
            });
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX() / b.getWidth();
                coordF[1] = b.getY() / b.getHeight();
                try {
                    vue.terrain.getPlateau()[coordI[1]][coordI[0]].unit.utiliseCompetence(coordI[0], coordI[1], coordF[0], coordF[1], c, vue.terrain);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Vous attaquez dans le vide.", "", JOptionPane.PLAIN_MESSAGE);
                }
                vue.generateTerrain();
                vue.generateTaskBar();
                if (jeu.getJoueur1().getHero().getSanteCourante() <= 0) {
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 2 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
                } else if (jeu.getJoueur2().getHero().getSanteCourante() <= 0) {
                    JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 1 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
                }
            });

        }
    }

    public void deplaceUnite(Joueur j, JButton posIni){
        ActionJoueur aj = new ActionJoueur(j);
        int[]coordI = {posIni.getX()/posIni.getWidth(), posIni.getY()/posIni.getHeight()};
        int[]coordF = new int[2];
        Unite u = vue.terrain.plateau[coordI[1]][coordI[0]].unit;
        for (JButton b : vue.terrainBt) {
            if(u.casesDisponibleDeplacement(vue.terrain, u, coordI[0], coordI[1], b.getX()/b.getWidth(), b.getY()/b.getHeight())) {
                b.setContentAreaFilled(true);
                b.setBackground(new Color(0, 150, 0));
            }
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX()/b.getWidth();
                coordF[1] = b.getY()/b.getHeight();
                aj.deplaceUnite(vue.terrain, coordI[0],coordI[1],coordF[0],coordF[1]);
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void viewStats(JButton b){
        Unite u = vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit;
        JFrame stats = new JFrame(vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.toString());
        stats.setVisible(true);
        stats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        stats.setLocationRelativeTo(vue.getContentPane());

        JPanel statsPanel = new JPanel(new BorderLayout());
        JLabel unite = new JLabel(vue.generateImage(vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.toString()));
        statsPanel.add(unite,BorderLayout.WEST);

        JPanel allStats = new JPanel();
        allStats.setPreferredSize(new Dimension(300,300));
        allStats.setLayout(new BoxLayout(allStats,BoxLayout.Y_AXIS));
        allStats.add(new JLabel("PV : "+u.getSanteCourante()+"/"+u.getSanteMax()));
        allStats.add(new JLabel("ATQ : "+u.getAttaque()));
        allStats.add(new JLabel("PorteeATQ : "+u.getPorteeAttaque()));
        allStats.add(new JLabel("PorteeDEP : "+u.getPorteeDeplacement()));
        allStats.add(new JLabel("PA : "+u.getPointAction()+"/"+u.getPointActionMax()));
        allStats.add(new JLabel("Buff : "));
        allStats.add(new JLabel("Debuff : "));

        JScrollPane scrollStats = new JScrollPane(allStats);
        scrollStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statsPanel.add(scrollStats,BorderLayout.CENTER);

        stats.setSize(250,175);
        stats.setResizable(false);
        stats.add(statsPanel);
    }

    public void TerrrainEffect(){
        try {
            for (int x = 0; x < vue.terrain.plateau.length; x++) {
                for (int y = 0; y < vue.terrain.plateau[x].length; y++) {
                    if (vue.terrain.plateau[x][y] instanceof CaseEffect){
                        ((CaseEffect) vue.terrain.plateau[x][y]).Effect();
                    }
                }
            }
        } catch (NullPointerException e) {}
    }

    public void finDeTour(){
        jeu.finDeTour();
        nbTour++;
        if(jeu.getJoueur2().getIsHuman()) {
            JFrame findeTour = new JFrame();
            findeTour.setVisible(true);
            findeTour.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            findeTour.setLocationRelativeTo(vue.getContentPane());
            findeTour.setSize(new Dimension(300, 150));

            JPanel panelFindeTour = new JPanel();
            BoxLayout box = new BoxLayout(panelFindeTour, BoxLayout.Y_AXIS);
            panelFindeTour.setLayout(box);
            panelFindeTour.setPreferredSize(new Dimension(200, 100));
            JLabel nTour = new JLabel("Tour " + nbTour);
            JLabel jTour = new JLabel("Tour du joueur");
            if (jeu.getTourDuJoueur() == jeu.getJoueur1()) {
                jeu.getJoueur1().resetPointAction();
                jTour = new JLabel("Tour du joueur 1");
            } else if (jeu.getTourDuJoueur() == jeu.getJoueur2()) {
                if (nbTour == 2) jeu.getJoueur2().getHero().setPointAction(0);
                else jeu.getJoueur2().resetPointAction();
                jTour = new JLabel("Tour du joueur 2");
            }
            jeu.activateAlterationEtats();
            nTour.setPreferredSize(new Dimension(50, 25));
            jTour.setPreferredSize(new Dimension(50, 25));
            nTour.setAlignmentX(Component.CENTER_ALIGNMENT);
            jTour.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelFindeTour.add(Box.createRigidArea(new Dimension(10, 10)));
            panelFindeTour.add(nTour);
            panelFindeTour.add(Box.createRigidArea(new Dimension(25, 25)));
            panelFindeTour.add(jTour);
            findeTour.add(panelFindeTour);
            findeTour.pack();
        }else{
            if (jeu.getTourDuJoueur() == jeu.getJoueur1()) {
                jeu.getJoueur1().resetPointAction();
                JOptionPane.showMessageDialog(vue.getTerrainPanel(), "A votre tour, le robot a joué.", "Tour "+nbTour, JOptionPane.PLAIN_MESSAGE);
            } else if (jeu.getTourDuJoueur() == jeu.getJoueur2()) {
                if (nbTour == 2) jeu.getJoueur2().getHero().setPointAction(0);
                else jeu.getJoueur2().resetPointAction();
            }
            jeu.activateAlterationEtats();
        }
        vue.setTourJoueur(jeu.getTourDuJoueur());
        TerrrainEffect();
        vue.generateTerrain();
        vue.generateTaskBar();
    }


    //Méthodes concernant le robot

    public void deplaceUniteRob(Joueur j){
        ActionJoueur aj = new ActionJoueur(j);
        if(((Robot) j).pickUnit(vue.terrain)) {
            int[] coordI = ((Robot) j).getCoord();
            Unite u = vue.terrain.plateau[coordI[0]][coordI[1]].unit;
            u.casesDisponibleDeplacement(vue.terrain, u, coordI[1], coordI[0], coordI[1], coordI[0]);
            int[] coordF = {coordI[0], coordI[1]};
            if(((Robot)j).targetDetected(vue.terrain, u.getCurrentX(), u.getCurrentY(), u.getPorteeDeplacement(), u, j)){
                u.setPointAction(0); //temporaire => pour éviter boucle infinie
                return;
            }
            for (Case c : u.getDeplacementDisponible()) {
                if (u.getPointAction() > 0) {
                    /*if (coordI[1] > 0) {
                        if(c.casePos(vue.terrain)[0]>coordI[1] && c.estVide()) {
                            coordF[1]=c.casePos(vue.terrain)[0];
                            aj.deplaceUnite(vue.terrain, coordI[1], coordI[0], coordF[1], coordF[0]);
                        }*/
                    if (coordF[1] != 0) {
                        coordF[1]--;
                        aj.deplaceUnite(vue.terrain, coordI[1], coordI[0], coordF[1], coordF[0]);
                    } else if (coordF[0] != vue.terrain.plateau.length - 1) {
                        coordF[0]++;
                        aj.deplaceUnite(vue.terrain, coordI[1], coordI[0], coordF[1], coordF[0]);
                    }
                } else {
                    break;
                }
            }
        }
        return;
    }

    public void achatUniteRob(Joueur j){
        Random rand = new Random();
        int uniteRandom = rand.nextInt(vue.getListeUnit().length);
        Unite u = vue.createUnite(vue.getListeUnit()[uniteRandom]);
        ActionJoueur aj = new ActionJoueur(j);
        int randPosLibre = rand.nextInt(((Robot)j).availableSpace(vue.terrain).size());
        int[] pos = (int[]) ((Robot)j).availableSpace(vue.terrain).get(randPosLibre);
        if (j.getArgent()>=u.getCoutUnite() && !j.maxUnit()){
            aj.acheteUnite(u,vue.getTerrainPanel());
            j.ajouteUnite(u);
            if(pos!=null){
                aj.placeUnite(vue.terrain,u,pos[1],pos[0],false);
            }else{
                j.annuleAjout(u);
                j.setArgent(j.getArgent() + u.getCoutUnite());
            }
        }
    }

    public void robotPlay(Joueur j){
        while(!((Robot)j).allPAused()){
            deplaceUniteRob(j);
        }
        if(j.getArgent()>=100) {
            achatUniteRob(j);
        }
        finDeTour();
    }
}
