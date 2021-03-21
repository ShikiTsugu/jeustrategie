package com.plateau;

import com.launcher.Jeu;
import com.launcher.Reader;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.unite.*;

import javax.sound.sampled.BooleanControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Vue extends JFrame{

    private ImagePane imagePane;
    private Model model;
    private JPanel TerrainPanel = new JPanel();
    private JPanel TaskBar = new JPanel();
    Terrain terrain;
    ArrayList<JButton> terrainBt = new ArrayList<>();
    private Controlleur controlleur = new Controlleur(this);
    private Joueur tourJoueur;
    private String[] listeUnit = {"Templier","Cavalier","Mage","Archer"};
    private JButton btAtk = new JButton("Attaquer");
    private JButton btDep = new JButton("Déplacer");

    public Vue(Model m, Terrain t, Joueur j){
        model = m;
        imagePane = new ImagePane();
        tourJoueur = j;
        imagePane.add(TerrainPanel);
        imagePane.add(TaskBar);
        setTitle("Jeu de Strategie");
        setSize(1680,1050);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        terrain = t;

        if (terrain != null) {
            AfficheTerrain();
        }

        setVisible(true);
    }

    /*Affichage d'un terrain*/
    public void AfficheTerrain(){

        BoxLayout box = new BoxLayout(imagePane,BoxLayout.Y_AXIS);
        imagePane.setLayout(box);
        generateTerrain();
        generateTaskBar();

        setContentPane(imagePane);
    }

    /* Ajout d'image de fond */
    public class ImagePane extends JPanel{
        public ImagePane(){
            setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(model.getImage(),0,0,this);
        }
    }

    public JButton generateButton(String s){
        JButton bt = new JButton();
        bt.setContentAreaFilled(false);
        bt.setOpaque(false);
        if(s.equals("Hero")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/plateau/hero.png"));
        }
        if(s.equals("Templier")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/plateau/templier.png"));
        }
        if(s.equals("Archer")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/plateau/archer.png"));
        }
        if(s.equals("Mage")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/plateau/mage.png"));
        }
        if(s.equals("Cavalier")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/plateau/cavalier.png"));
        }
        return bt;
    }

    public void generateTerrain(){
        TerrainPanel.removeAll();
        GridLayout grid = new GridLayout(5,5);
        TerrainPanel.setLayout(grid);
        for (int x = 0; x < terrain.plateau.length; x++){
            for (int y = 0; y < terrain.plateau[x].length; y++){
                if (terrain.plateau[x][y].unit != null) {
                    JButton bt = generateButton(terrain.plateau[x][y].unit.toString());
                    bt.setText("PV : "+terrain.plateau[x][y].unit.getSanteCourante()+"/"+terrain.plateau[x][y].unit.getSanteMax());
                    TerrainPanel.add(bt);
                    if (!(tourJoueur == terrain.plateau[x][y].unit.getJoueur())) {
                        bt.setBackground(Color.BLACK);
                    }
                    bt.addActionListener((ActionEvent e) -> {
                        System.out.println(bt.getX()/bt.getWidth());
                        System.out.println(bt.getY()/bt.getHeight());
                        btAtk.addActionListener((ActionEvent atk) -> {
                            controlleur.attaque(bt);
                        });
                        btDep.addActionListener((ActionEvent dep) -> {
                            controlleur.deplaceUnite(bt);
                        });
                        if(terrain.getPlateau()[bt.getY()/bt.getHeight()][bt.getX()/bt.getWidth()].getUnite().getPointAction()>0)
                        generateAction();
                    });
                    bt.setPreferredSize(new Dimension(150,150));
                    terrainBt.add(bt);
                } else {
                    JButton bt = new JButton();
                    TerrainPanel.add(bt);
                    bt.setOpaque(false);
                    bt.setContentAreaFilled(false);
                    bt.addActionListener((ActionEvent e) -> {
                        System.out.println(bt.getX()/bt.getWidth());
                        System.out.println(bt.getY()/bt.getHeight());
                    });
                    bt.setPreferredSize(new Dimension(150,150));
                    terrainBt.add(bt);
                }
            }
        }
        TerrainPanel.setOpaque(false);
        TerrainPanel.updateUI();
    }

    public void generateTaskBar(){
        TaskBar.removeAll();
        FlowLayout flow = new FlowLayout();
        TaskBar.setLayout(flow);
        JButton btBuy = new JButton("Acheter une unité");
        btBuy.setPreferredSize(new Dimension(300,150));
        btBuy.addActionListener((ActionEvent e) -> {
            generateAchat();
        });
        TaskBar.add(btBuy);
        JButton btFdt = new JButton("Fin de tour");
        btFdt.setPreferredSize(new Dimension(300,150));
        btFdt.addActionListener((ActionEvent e) -> {
            controlleur.finDeTour();
            generateTerrain();
            TerrainPanel.updateUI();
        });
        TaskBar.add(btFdt);
        TaskBar.updateUI();
    }

    public void generateAction(){
        TaskBar.removeAll();
        FlowLayout flow = new FlowLayout();
        TaskBar.setLayout(flow);
        btAtk.setPreferredSize(new Dimension(300,150));
        TaskBar.add(btAtk);
        btDep.setPreferredSize(new Dimension(300,150));
        TaskBar.add(btDep);
        JButton retour = new JButton("retour");
        retour.setPreferredSize(new Dimension(300,150));
        retour.addActionListener((ActionEvent e) -> {
            generateTaskBar();
        });
        TaskBar.add(retour);
        TaskBar.updateUI();
    }

    public Unite createUnite(String s){
        Unite u=null;
        if(s.equals("Templier")){
            u = new Templier(tourJoueur);
        }
        if(s.equals("Cavalier")){
            u = new Cavalier(tourJoueur);
        }
        if(s.equals("Mage")){
            u = new Mage(tourJoueur);
        }
        if(s.equals("Archer")){
            u = new Archer(tourJoueur);
        }
        return u;
    }

    public void generateAchat(){
        TaskBar.removeAll();
        FlowLayout flow = new FlowLayout();
        TaskBar.setLayout(flow);
        for (int i = 0; i < 4; i++){
            JButton bt = generateButton(listeUnit[i]);
            String unitName = listeUnit[i];
            bt.addActionListener((ActionEvent e) -> {
                if(new ActionJoueur((tourJoueur)).acheteUnite(createUnite(unitName),TerrainPanel)) {
                    Unite unit = createUnite(unitName);
                    controlleur.acheteUnite(tourJoueur, unit);
                }
                //boutonAnnul();
                generateTaskBar();
            });
            bt.setPreferredSize(new Dimension(100,150));
            TaskBar.add(bt);
        }
        JButton retour = new JButton("retour");
        retour.addActionListener((ActionEvent e) -> {
            generateTaskBar();
        });
        retour.setPreferredSize(new Dimension(100,150));
        TaskBar.add(retour);
        TaskBar.updateUI();
    }

    // bouton pour annuler l'achat d'unité (ne marche pas encore)
    public void boutonAnnul(Unite u){
        TaskBar.removeAll();
        JButton Annul = new JButton("retour");
        Annul.addActionListener((ActionEvent e) -> {
            generateTaskBar();
            tourJoueur.annuleAjout(u);
        });
        Annul.setPreferredSize(new Dimension(800,150));
        TaskBar.add(Annul);
    }

    
    public ArrayList<JButton> getTerrainBt(){
        return terrainBt;
    }

    public Joueur getTourJoueur(){
        return tourJoueur;
    }

    public void setTourJoueur(Joueur j){
        tourJoueur = j;
    }

    public JPanel getTerrainPanel(){return TerrainPanel;}

    public Terrain getTerrain(){
        return terrain;
    }

    public void setTerrain(Terrain t){
        terrain = t;
    }

    public Controlleur getControlleur(){
        return controlleur;
    }

}