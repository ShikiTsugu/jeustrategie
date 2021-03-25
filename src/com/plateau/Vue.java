package com.plateau;

import com.launcher.Jeu;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.unite.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setTitle("Jeu de Strategie");
        setSize(model.getImage().getWidth(),model.getImage().getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        terrain = t;

        if (terrain != null) {
            AfficheTerrain();
        }

        setVisible(true);
    }

    //affichage initial
    public void afficheIni(){
        JButton jouer = new JButton("Jouer");
        jouer.setFont(new Font("Monospaced",Font.BOLD,20));
        jouer.setBackground(new Color(83, 214, 191));
        jouer.setForeground(Color.WHITE);
        jouer.addActionListener((ActionEvent e) -> {
            AfficheTerrain();
            imagePane.add(TerrainPanel);
            imagePane.add(TaskBar);
        });

        JButton quitter = new JButton("Quitter");
        quitter.setFont(new Font("Monospaced",Font.BOLD,20));
        quitter.setBackground(new Color(37, 150, 131));
        quitter.setForeground(Color.WHITE);
        quitter.addActionListener((ActionEvent e) -> System.exit(0));

        JLabel titre = new JLabel(new ImageIcon(Jeu.selectGoodPath() + "/plateau/titre.png"));

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 80)));
        imagePane.add(titre);
        jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
        imagePane.add(jouer);
        quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
        imagePane.add(quitter);

        setContentPane(imagePane);
    }

    /*Affichage d'un terrain*/
    public void AfficheTerrain(){
        imagePane.removeAll();
        BoxLayout box = new BoxLayout(imagePane,BoxLayout.Y_AXIS);
        imagePane.setLayout(box);
        generateTerrain();
        generateTaskBar();
        imagePane.updateUI();
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

    public void resetButton(JButton b){
        for(ActionListener al : b.getActionListeners() ) {
            b.removeActionListener(al);
        }
    }

    public void initialiseAtk(JButton b){
        resetButton(btAtk);
        btAtk.setEnabled(true);
        btAtk.addActionListener((ActionEvent atk) -> {
            controlleur.attaque(tourJoueur,b);
        });
    }

    public void initialiseDep(JButton b){
        resetButton(btDep);
        btDep.setEnabled(true);
        btDep.addActionListener((ActionEvent dep) -> {
            controlleur.deplaceUnite(tourJoueur,b);
        });
    }

    public void generateTerrain(){
        TerrainPanel.removeAll();
        GridLayout grid = new GridLayout(5,5);
        TerrainPanel.setLayout(grid);
        for (int x = 0; x < terrain.plateau.length; x++){
            for (int y = 0; y < terrain.plateau[x].length; y++){
                if (terrain.plateau[x][y].unit != null) {
                    JButton bt = generateButton(terrain.plateau[x][y].unit.toString());
                    JLabel pv = new JLabel(terrain.plateau[x][y].unit.getSanteCourante()+"/"+terrain.plateau[x][y].unit.getSanteMax());
                    pv.setFont(new Font("SansSerif",Font.BOLD,14));
                    pv.setForeground(new Color(0,200,0));
                    pv.setAlignmentX(CENTER_ALIGNMENT);
                    pv.setAlignmentY(BOTTOM_ALIGNMENT);
                    bt.add(pv);
                    TerrainPanel.add(bt);
                    bt.addActionListener((ActionEvent e) -> {
                        System.out.println(terrain.plateau[bt.getY()/bt.getHeight()][bt.getX()/bt.getWidth()].unit);
                        if(terrain.getPlateau()[bt.getY()/bt.getHeight()][bt.getX()/bt.getWidth()].getUnite().getPointAction()>0){
                            initialiseAtk(bt);
                            initialiseDep(bt);
                            generateAction(bt);
                        }else{
                            generateAction(bt);
                            btAtk.setEnabled(false);
                            btDep.setEnabled(false);
                        }
                    });
                    if (!(tourJoueur == terrain.plateau[x][y].unit.getJoueur())) {
                        resetButton(bt);
                        pv.setForeground(new Color(200,0,0));
                    }
                    bt.setPreferredSize(new Dimension(150,125));
                    terrainBt.add(bt);
                } else {
                    JButton bt = new JButton();
                    TerrainPanel.add(bt);
                    bt.setOpaque(false);
                    bt.setContentAreaFilled(false);
                    bt.setPreferredSize(new Dimension(150,125));
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
        btBuy.setPreferredSize(new Dimension(200,100));
        btBuy.addActionListener((ActionEvent e) -> {
            generateAchat();
        });
        TaskBar.add(btBuy);
        JButton btFdt = new JButton("Fin de tour");
        btFdt.setPreferredSize(new Dimension(200,100));
        btFdt.addActionListener((ActionEvent e) -> {
            controlleur.finDeTour();
            generateTerrain();
            TerrainPanel.updateUI();
        });
        TaskBar.add(btFdt);
        TaskBar.updateUI();
    }

    public ImageIcon generateImage(String s){
        ImageIcon i = new ImageIcon();
        if(s.equals("Hero")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/plateau/hero.png");
        }
        if(s.equals("Templier")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/plateau/templier.png");
        }
        if(s.equals("Archer")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/plateau/archer.png");
        }
        if(s.equals("Mage")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/plateau/mage.png");
        }
        if(s.equals("Cavalier")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/plateau/cavalier.png");
        }
        return i;
    }

    public JButton displayUnit(JButton b){
        JButton unit = new JButton(generateImage(terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()]
                .unit.toString()));
        unit.setBorderPainted(false);
        unit.setContentAreaFilled(false);
        unit.setFocusPainted(false);
        unit.setOpaque(false);
        unit.setPreferredSize(new Dimension(250,125));
        JLabel pa = new JLabel("PA : "+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getPointAction());
        pa.setFont(new Font("SansSerif",Font.BOLD,12));
        pa.setAlignmentX(RIGHT_ALIGNMENT);
        pa.setAlignmentY(TOP_ALIGNMENT);
        JLabel pv = new JLabel("PV : "+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getSanteCourante()+
                "/"+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getSanteMax());
        pv.setFont(new Font("SansSerif",Font.BOLD,12));
        pv.setAlignmentX(RIGHT_ALIGNMENT);
        pv.setAlignmentY(BOTTOM_ALIGNMENT);
        unit.add(pa);
        unit.add(pv);
        return unit;
    }

    public void generateAction(JButton unite){
        TaskBar.removeAll();
        FlowLayout flow = new FlowLayout();
        TaskBar.setLayout(flow);
        JButton unit = displayUnit(unite);
        TaskBar.add(unit);
        btAtk.setPreferredSize(new Dimension(100,125));
        TaskBar.add(btAtk);
        btDep.setPreferredSize(new Dimension(100,125));
        TaskBar.add(btDep);
        JButton retour = new JButton("retour");
        retour.setPreferredSize(new Dimension(100,125));
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
            JLabel displayName = new JLabel(unitName);
            displayName.setAlignmentX(CENTER_ALIGNMENT);
            displayName.setAlignmentY(TOP_ALIGNMENT);
            bt.add(displayName);
            bt.addActionListener((ActionEvent e) -> {
                if(new ActionJoueur((tourJoueur)).acheteUnite(createUnite(unitName),TerrainPanel)) {
                    Unite unit = createUnite(unitName);
                    controlleur.acheteUnite(tourJoueur, unit);
                }
                //boutonAnnul();
                generateTaskBar();
            });
            bt.setPreferredSize(new Dimension(100,125));
            TaskBar.add(bt);
        }
        JButton retour = new JButton("retour");
        retour.addActionListener((ActionEvent e) -> {
            generateTaskBar();
        });
        retour.setPreferredSize(new Dimension(80,50));
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