package com.plateau;

import com.launcher.Jeu;
import com.player.ActionJoueur;
import com.player.Joueur;
import com.player.Robot;
import com.unite.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Vue extends JFrame{

    private ImagePane imagePane;
    private Model model;
    private JPanel TerrainPanel = new JPanel();
    private JPanel TaskBar = new JPanel();
    Terrain terrain;
    ArrayList<JButton> terrainBt;
    private Controlleur controlleur;
    private Joueur tourJoueur;
    private String[] listeUnit = {"Templier","Cavalier","Mage","Archer","Pretresse","Lancier","Assassin"};
    private JButton btStats = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats.png"));
    private JButton btAtk = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque.png"));
    private JButton btDep = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/deplace.png"));
    private JButton btSkill1 = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque.png"));
    private JButton btSkill2 = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
    private JButton btSkill3 = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
    private JButton btSkill4 = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
    private JLabel taskbarbg = new JLabel(new ImageIcon(Jeu.selectGoodPath()+"/assets/taskbarbg.png"));
    private JButton btStatsSkill = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats.png"));
    private KeyListener k;
    private int currentMap = 0;

    public String[] getListeUnit(){return listeUnit;}

    public Vue(Model m, Terrain t){
        model = m;
        imagePane = new ImagePane(model);
        setTitle("Jeu de Strategie");
        setSize(model.getImage().getWidth(),model.getImage().getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        terrain = t;

        setVisible(true);
        setLocationRelativeTo(null);
        controlleur = new Controlleur(this);
        k = controlleur.new escButtonAction();
        TerrainPanel.setFocusable(true);
    }

    //affichage initial
    public void afficheIni(){
        TerrainPanel.removeKeyListener(k);
        try {
            imagePane.imModel.setImage(ImageIO.read(new File(Jeu.selectGoodPath() + "/assets/titlescreen.png")));
        }catch(IOException e){
            System.out.println("Fichier non trouv�, chemin incorrecte.");
        }
        imagePane.removeAll();
        JButton jouer = new JButton("Jouer");
        jouer.setFont(new Font("Monospaced",Font.BOLD,20));
        jouer.setBackground(new Color(83, 214, 191));
        jouer.setForeground(Color.WHITE);
        jouer.addActionListener((ActionEvent e) -> AfficheMenuRobot());

        JButton quitter = new JButton("Quitter");
        quitter.setFont(new Font("Monospaced",Font.BOLD,20));
        quitter.setBackground(new Color(37, 150, 131));
        quitter.setForeground(Color.WHITE);
        quitter.addActionListener((ActionEvent e) -> System.exit(0));

        JLabel titre = new JLabel();
        titre.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/gameTitle.png"));

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 200)));
        imagePane.add(titre);
        jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 200)));
        imagePane.add(jouer);
        quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 20)));
        imagePane.add(quitter);

        setContentPane(imagePane);
        imagePane.updateUI();
    }

    public void AfficheMenuRobot(){
        imagePane.removeAll();

        JLabel titre = new JLabel("Select Mode");

        JButton JcJ = new JButton("Joueur vs Joueur");
        JcJ.setFont(new Font("Monospaced",Font.BOLD,20));
        JcJ.setBackground(new Color(37, 150, 131));
        JcJ.setForeground(Color.WHITE);
        JcJ.addActionListener((ActionEvent e) -> {
            controlleur.getJeu().setPlayer(true);
            AfficheMenuLevel();
        });

        JButton JcI = new JButton("Joueur vs IA");
        JcI.setFont(new Font("Monospaced",Font.BOLD,20));
        JcI.setBackground(new Color(37, 150, 131));
        JcI.setForeground(Color.WHITE);
        JcI.addActionListener((ActionEvent e) -> {
            controlleur.getJeu().setPlayer(false);
            AfficheMenuLevel();
        });

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont((new Font("Monospaced",Font.BOLD,20)));
        imagePane.add(Box.createRigidArea(new Dimension(0, 100 )));
        imagePane.add(titre);
        JcJ.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 50 )));
        imagePane.add(JcJ);
        JcI.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 50 )));
        imagePane.add(JcI);

        imagePane.updateUI();
    }

    public void AfficheMenuLevel(){
        imagePane.removeAll();

        JLabel titre = new JLabel("Map Select");

        JButton Map5x5 = new JButton("Map 5x5");
        Map5x5.setFont(new Font("Monospaced",Font.BOLD,20));
        Map5x5.setBackground(new Color(37, 150, 131));
        Map5x5.setForeground(Color.WHITE);
        Map5x5.addActionListener((ActionEvent e) -> {
            currentMap = 1;
            controlleur.SetSmallAsMap();
            controlleur.getJeu().AjouteHero();
            controlleur.getJeu().setStartingMoney(500);
            terrain = controlleur.getMap().getTerrain();
            try {
                imagePane.imModel.setImage(ImageIO.read(new File(Jeu.selectGoodPath() + "/assets/map2.png")));
            }catch(IOException ex){
                System.out.println("Fichier non trouvé, chemin incorrecte.");
            }
            AfficheTerrain();
            imagePane.add(TerrainPanel,BorderLayout.CENTER);
            imagePane.add(TaskBar,BorderLayout.SOUTH);
        });

        JButton Map14x6 = new JButton("Map 14x6");
        Map14x6.setFont(new Font("Monospaced",Font.BOLD,20));
        Map14x6.setBackground(new Color(37, 150, 131));
        Map14x6.setForeground(Color.WHITE);
        Map14x6.addActionListener((ActionEvent e) -> {
            currentMap = 2;
            controlleur.SetBigAsMap();
            controlleur.getJeu().AjouteHero();
            controlleur.getJeu().setStartingMoney(1000);
            terrain = controlleur.getMap().getTerrain();
            try {
                imagePane.imModel.setImage(ImageIO.read(new File(Jeu.selectGoodPath() + "/assets/plaine.png")));
            }catch(IOException ex){
                System.out.println("Fichier non trouvé, chemin incorrecte.");
            }
            AfficheTerrain();
            imagePane.add(TerrainPanel,BorderLayout.CENTER);
            imagePane.add(TaskBar,BorderLayout.SOUTH);
        });

        JButton Map14x6Gold = new JButton("Map 14x6 Gold");
        Map14x6Gold.setFont(new Font("Monospaced",Font.BOLD,20));
        Map14x6Gold.setBackground(new Color(37, 150, 131));
        Map14x6Gold.setForeground(Color.WHITE);
        Map14x6Gold.addActionListener((ActionEvent e) -> {
            currentMap = 3;
            controlleur.SetBigGoldAsMap();
            controlleur.getJeu().AjouteHero();
            controlleur.getJeu().setStartingMoney(2000);
            terrain = controlleur.getMap().getTerrain();
            try {
                imagePane.imModel.setImage(ImageIO.read(new File(Jeu.selectGoodPath() + "/assets/map3.png")));
            }catch(IOException ex){
                System.out.println("Fichier non trouvé, chemin incorrecte.");
            }
            AfficheTerrain();
            imagePane.add(TerrainPanel,BorderLayout.CENTER);
            imagePane.add(TaskBar,BorderLayout.SOUTH);
        });

        BoxLayout boxlayout = new BoxLayout(imagePane, BoxLayout.Y_AXIS);
        imagePane.setLayout(boxlayout);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont((new Font("Monospaced",Font.BOLD,20)));
        imagePane.add(Box.createRigidArea(new Dimension(0, 100 )));
        imagePane.add(titre);
        Map5x5.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 50 )));
        imagePane.add(Map5x5);
        Map14x6.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 50 )));
        imagePane.add(Map14x6);
        Map14x6Gold.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePane.add(Box.createRigidArea(new Dimension(0, 50 )));
        imagePane.add(Map14x6Gold);

        imagePane.updateUI();
    }

    /*Affichage d'un terrain*/
    public void AfficheTerrain(){
        imagePane.removeAll();
        imagePane.setLayout(new BorderLayout());
        generateTerrain();
        generateTaskBar();
        imagePane.updateUI();
    }

    /* Ajout d'image de fond */
    public class ImagePane extends JPanel{
        private Model imModel;
        public ImagePane(Model m){
            imModel=m;
            setPreferredSize(new Dimension(m.getImage().getWidth(), m.getImage().getHeight()));
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imModel.getImage(),0,0,this);
        }
    }

    public JButton generateButton(String s){
        JButton bt = new JButton();
        bt.setContentAreaFilled(false);
        bt.setOpaque(false);
        if(s.equals("Hero")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/hero.png"));
        }
        if(s.equals("Templier")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/templier.png"));
        }
        if(s.equals("Archer")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/archer.png"));
        }
        if(s.equals("Mage")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/mage.png"));
        }
        if(s.equals("Cavalier")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/cavalier.png"));
        }
        if(s.equals("Pretresse")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/pretresse.png"));
        }
        if(s.equals("Lancier")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/lancier.png"));
        }
        if(s.equals("Assassin")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/Assassin.png"));
        }
        if(s.equals("Prophete")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/Prophete.png"));
        }
        if(s.equals("Mouton")){
            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/mouton.png"));
        }
        return bt;
    }

    public void flippedImages(JButton bt, String s, boolean b){
        if(b) {
            if (s.equals("Hero")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/hero2.png"));
            }
            if (s.equals("Templier")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/templier2.png"));
            }
            if (s.equals("Archer")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/archer2.png"));
            }
            if (s.equals("Mage")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/mage2.png"));
            }
            if (s.equals("Cavalier")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/cavalier2.png"));
            }
            if (s.equals("Pretresse")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/pretresse2.png"));
            }
            if (s.equals("Lancier")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/lancier2.png"));
            }
            if (s.equals("Assassin")) {
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/Assassin2.png"));
            }
            if(s.equals("Prophete")){
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/Prophete2.png"));
            }
            if(s.equals("Mouton")){
                bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/mouton2.png"));
            }
        }
    }

    public void resetButton(JButton b){
        for(ActionListener al : b.getActionListeners() ) {
            b.removeActionListener(al);
        }
    }

    public void initialiseStats(JButton b){
        resetButton(btStats);
        btStats.addActionListener((ActionEvent stats) -> {
            controlleur.viewStats(b);
        });
    }

    public void initialiseAtk(JButton b){
        resetButton(btAtk);
        btAtk.setEnabled(true);
        btAtk.addActionListener((ActionEvent atk) -> {
            initialiseSkill1(b);
            initialiseSkill2(b);
            initialiseSkill3(b);
            initialiseSkill4(b);
            generateSkills(b);
            initialiseBtStatsSkill(b);
        });
    }

    public void initialiseDep(JButton b){
        resetButton(btDep);
        btDep.setEnabled(true);
        btDep.addActionListener((ActionEvent dep) -> {
            controlleur.deplaceUnite(tourJoueur, b);
        });
    }

    public void initialiseSkill1(JButton b){
        resetButton(btSkill1);
        btSkill1.setEnabled(true);
        btSkill1.addActionListener((ActionEvent useSkill1) ->{
            controlleur.useSkill(b,0);
        });
    }

    public void initialiseSkill2(JButton b){
        resetButton(btSkill2);
        btSkill2.setEnabled(true);
        btSkill2.addActionListener((ActionEvent useSkill1) ->{
            controlleur.useSkill(b,1);
        });
    }

    public void initialiseSkill3(JButton b){
        resetButton(btSkill3);
        btSkill3.setEnabled(true);
        btSkill3.addActionListener((ActionEvent useSkill1) ->{
            controlleur.useSkill(b,2);
        });
    }

    public void initialiseSkill4(JButton b){
        resetButton(btSkill4);
        btSkill4.setEnabled(true);
        btSkill4.addActionListener((ActionEvent useSkill1) ->{
            controlleur.useSkill(b,3);
        });
    }

    public void initialiseBtStatsSkill(JButton b){ //initialise le bouton pour afficher la liste des compétences de l'unité
        resetButton(btStatsSkill);
        btStatsSkill.addActionListener((ActionEvent useSkill1) ->{
            viewStatsCompetence(b);
        });
    }

    public void generateTerrain(){
        TerrainPanel.removeKeyListener(k);
        TerrainPanel.addKeyListener(k);
        terrainBt = new ArrayList<>();
        TerrainPanel.removeAll();
        boolean b;
        GridLayout grid = new GridLayout(terrain.plateau.length,terrain.plateau[0].length);
        TerrainPanel.setLayout(grid);
        //tourJoueur.displayList();
        for (int x = 0; x < terrain.plateau.length; x++){
            for (int y = 0; y < terrain.plateau[x].length; y++){
                terrain.plateau[x][y].setDeathCount(false);
                if (terrain.plateau[x][y].unit != null) {
                    JButton bt = generateButton(terrain.plateau[x][y].unit.toString());
                    setBorder(bt, x, y);
                    if(terrain.plateau[x][y].unit.getJoueur()!=controlleur.getJeu().getJoueur1()) {
                        b = true;
                        flippedImages(bt, terrain.plateau[x][y].unit.toString(), b);
                    }
                    JLabel pv = new JLabel(terrain.plateau[x][y].unit.getSanteCourante()+"/"+terrain.plateau[x][y].unit.getSanteMax());
                    pv.setFont(new Font("SansSerif",Font.BOLD,14));
                    pv.setForeground(new Color(0,200,0));
                    pv.setAlignmentX(CENTER_ALIGNMENT);
                    pv.setAlignmentY(BOTTOM_ALIGNMENT);
                    bt.add(pv);
                    bt.setToolTipText(pv.getText());
                    TerrainPanel.add(bt);
                    bt.addActionListener((ActionEvent e) -> {
                        try {
                            if (terrain.getPlateau()[bt.getY() / bt.getHeight()][bt.getX() / bt.getWidth()].getUnite().getPointAction() > 0) {
                                initialiseStats(bt);
                                initialiseAtk(bt);
                                initialiseDep(bt);
                                generateAction(bt);
                            } else {
                                initialiseStats(bt);
                                generateAction(bt);
                                btAtk.setEnabled(false);
                                btDep.setEnabled(false);
                            }
                        }catch (NullPointerException exception){
                            System.out.println("impossible de trouver l'unité");
                        }
                    });
                    if (!(tourJoueur == terrain.plateau[x][y].unit.getJoueur())) {
                        resetButton(bt);
                        pv.setForeground(new Color(200,0,0));
                    }
                    if(!tourJoueur.getIsHuman()) {
                        controlleur.robotPlay(tourJoueur);
                    }
                    bt.setPreferredSize(new Dimension(150,125));
                    bt.setFocusable(false);
                    terrainBt.add(bt);
                } else {
                    if(terrain.plateau[x][y].estObstacle()){
                        JButton bt = new JButton();
                        if (currentMap==1){
                            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/obstacle1.png"));
                        }else if (currentMap==2){
                            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/obstacle2.png"));
                        }else if (currentMap==3){
                            bt.setIcon(new ImageIcon(Jeu.selectGoodPath() + "/assets/obstacle3.png"));
                        }
                        bt = setBorder(bt, x, y);
                        TerrainPanel.add(bt);
                        bt.setOpaque(false);
                        bt.setContentAreaFilled(false);
                        bt.setPreferredSize(new Dimension(150,125));
                        bt.setFocusable(false);
                        terrainBt.add(bt);
                    }else {
                        JButton bt = new JButton();
                        bt = setBorder(bt, x, y);
                        TerrainPanel.add(bt);
                        bt.setOpaque(false);
                        bt.setContentAreaFilled(false);
                        bt.setPreferredSize(new Dimension(150, 125));
                        bt.setFocusable(false);
                        terrainBt.add(bt);
                    }
                }
            }
        }
        TerrainPanel.setOpaque(false);
        TerrainPanel.updateUI();
    }

    //Applique le visuel pour les cases Spéciaux
    public JButton setBorder(JButton bt, int x, int y){
        if (terrain.plateau[x][y] instanceof CaseGold) {
            bt.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        } else if (terrain.plateau[x][y] instanceof Tower){
            bt = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/platform.png"));
        }
        return bt;
    }

    public void generateTaskBar(){
        TaskBar.setBackground(Color.black);
        TaskBar.add(taskbarbg);
        taskbarbg.removeAll();
        FlowLayout flow = new FlowLayout();
        taskbarbg.setLayout(flow);
        JButton btBuy = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/moneyBag.png"));
        JLabel shop = new JLabel("Shop");
        shop.setAlignmentX(Component.CENTER_ALIGNMENT);
        shop.setAlignmentY(Component.CENTER_ALIGNMENT+0.2f);
        shop.setFont(new Font("SansSerif",Font.BOLD,14));
        shop.setForeground(Color.black);
        btBuy.setPreferredSize(new Dimension(200,125));
        btBuy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btBuy.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/moneyBag2.png"));
                shop.setForeground(new Color(230,200,120));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btBuy.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/moneyBag.png"));
                shop.setForeground(Color.black);
            }
        });
        btBuy.add(shop);
        btBuy.setContentAreaFilled(false);
        btBuy.setFocusable(false);
        btBuy.setBorderPainted(false);
        btBuy.addActionListener((ActionEvent e) -> {
            generateAchat();
        });
        taskbarbg.add(btBuy);

        JButton btHeroes = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/heroes.png"));
        JLabel heroes = new JLabel("Units");
        heroes.setAlignmentX(Component.CENTER_ALIGNMENT);
        heroes.setAlignmentY(Component.CENTER_ALIGNMENT+0.2f);
        heroes.setFont(new Font("SansSerif",Font.BOLD,14));
        heroes.setForeground(Color.black);
        btHeroes.setPreferredSize(new Dimension(200,125));
        btHeroes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btHeroes.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/heroes2.png"));
                heroes.setForeground(new Color(230,200,120));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btHeroes.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/heroes.png"));
                heroes.setForeground(Color.black);
            }
        });
        btHeroes.add(heroes);
        btHeroes.setContentAreaFilled(false);
        btHeroes.setFocusable(false);
        btHeroes.setBorderPainted(false);
        btHeroes.addActionListener((ActionEvent e) -> {
            generateList(tourJoueur);
        });
        taskbarbg.add(btHeroes);

        JButton btFdt = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/endturn.png"));
        btFdt.setPreferredSize(new Dimension(200,125));
        btFdt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btFdt.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/endturn2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btFdt.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/endturn.png"));
            }
        });
        btFdt.setContentAreaFilled(false);
        btFdt.setFocusable(false);
        btFdt.setBorderPainted(false);
        btFdt.addActionListener((ActionEvent e) -> {
            controlleur.finDeTour();
            generateTerrain();
            TerrainPanel.updateUI();
        });
        taskbarbg.add(btFdt);
        btFdt.setToolTipText("fin du tour");
        taskbarbg.updateUI();
    }

    private void generateList(Joueur j) {
        JFrame units = new JFrame("Units");
        units.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        units.setVisible(true);
        units.setSize(400,475);
        units.setLocationRelativeTo(imagePane);
        units.setResizable(false);
        ImagePane bg = new ImagePane(new Model(Jeu.selectGoodPath() + "/assets/shop.png"));
        units.setContentPane(bg);

        JPanel unitsCount = new JPanel(new FlowLayout());
        unitsCount.setOpaque(false);
        JLabel unitsValue = new JLabel(" Unites : "+tourJoueur.countUnits()+"/"+j.getUnites().length);
        unitsValue.setFont(new Font("SansSerif",Font.BOLD,14));
        unitsValue.setForeground(new Color(200,200,150));
        unitsCount.add(unitsValue);
        bg.setLayout(new BorderLayout());
        bg.add(unitsCount, BorderLayout.NORTH);

        JPanel liste = new JPanel(new FlowLayout());
        liste.setOpaque(false);
        for (int i = 0; i<j.getUnites().length; i++){
            if(j.getUnites()[i]!=null) {
                JButton bt = generateButton(j.getUnites()[i].toString());
                bt.setFocusable(false);
                bt.setLayout(new BorderLayout());
                String unitName = j.getUnites()[i].toString();

                JLabel displayName = new JLabel(unitName);
                displayName.setForeground(new Color(200, 200, 150));
                displayName.setHorizontalAlignment(0);
                bt.add(displayName, BorderLayout.NORTH);

                JLabel displayHealth = new JLabel(j.getUnites()[i].getSanteCourante()+"/"+j.getUnites()[i].getSanteMax());
                displayHealth.setForeground(new Color(0,200,0));
                displayHealth.setHorizontalAlignment(0);
                bt.add(displayHealth, BorderLayout.SOUTH);

                bt.setPreferredSize(new Dimension(100, 120));
                liste.add(bt);
            }else{
                JButton bt = new JButton();
                bt.setFocusable(false);
                bt.setOpaque(false);
                bt.setContentAreaFilled(false);
                bt.setPreferredSize(new Dimension(100, 120));
                liste.add(bt);
            }
        }
        bg.add(liste,BorderLayout.CENTER);
    }

    public void generateAchat(){
        JFrame shop = new JFrame("Shop");
        shop.setVisible(true);
        shop.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        shop.setSize(400,475);
        shop.setLocationRelativeTo(imagePane);
        shop.setResizable(false);
        ImagePane bg = new ImagePane(new Model(Jeu.selectGoodPath() + "/assets/shop.png"));
        shop.setContentPane(bg);
        JPanel money = new JPanel(new FlowLayout());
        money.setOpaque(false);
        JLabel moneyIcon = new JLabel(new ImageIcon(Jeu.selectGoodPath()+"/assets/coins.png"));
        JLabel moneyValue = new JLabel(" Argent : "+tourJoueur.getArgent());
        moneyValue.setFont(new Font("SansSerif",Font.BOLD,14));
        moneyValue.setForeground(new Color(200,200,150));
        money.add(moneyIcon);
        money.add(moneyValue);
        bg.setLayout(new BorderLayout());
        bg.add(money, BorderLayout.NORTH);
        JPanel liste = new JPanel(new FlowLayout());
        liste.setOpaque(false);
        for (int i = 0; i < 7; i++){
            JButton bt = generateButton(listeUnit[i]);
            bt.setFocusable(false);
            String unitName = listeUnit[i];
            JLabel displayName = new JLabel(unitName);
            displayName.setForeground(new Color(200,200,150));
            displayName.setAlignmentX(CENTER_ALIGNMENT);
            displayName.setAlignmentY(TOP_ALIGNMENT);
            bt.add(displayName);
            bt.addActionListener((ActionEvent e) -> {
                if(new ActionJoueur((tourJoueur)).acheteUnite(createUnite(unitName),TerrainPanel)) {
                    Unite unit = createUnite(unitName);
                    controlleur.acheteUnite(tourJoueur, unit);
                    shop.dispose();
                }
                //boutonAnnul();
                generateTaskBar();
            });
            bt.setPreferredSize(new Dimension(100,120));
            liste.add(bt);
        }
        bg.add(liste,BorderLayout.CENTER);
    }

    public ImageIcon generateImage(String s){
        ImageIcon i = new ImageIcon();
        if(s.equals("Hero")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/hero.png");
        }
        if(s.equals("Templier")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/templier.png");
        }
        if(s.equals("Archer")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/archer.png");
        }
        if(s.equals("Mage")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/mage.png");
        }
        if(s.equals("Cavalier")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/cavalier.png");
        }
        if(s.equals("Pretresse")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/pretresse.png");
        }
        if(s.equals("Lancier")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/lancier.png");
        }
        if(s.equals("Assassin")){
            i = new ImageIcon(Jeu.selectGoodPath() + "/assets/Assassin.png");
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
        JLabel pa = new JLabel("PA : "+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getPointAction()
                +"/"+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getPointActionMax());
        pa.setFont(new Font("SansSerif",Font.BOLD,13));
        pa.setAlignmentX(RIGHT_ALIGNMENT);
        pa.setAlignmentY(TOP_ALIGNMENT);
        pa.setForeground(new Color(200,20,80));
        JLabel pv = new JLabel("PV : "+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getSanteCourante()+
                "/"+terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.getSanteMax());
        pv.setFont(new Font("SansSerif",Font.BOLD,13));
        pv.setAlignmentX(RIGHT_ALIGNMENT);
        pv.setAlignmentY(BOTTOM_ALIGNMENT);
        pv.setForeground(new Color(0,200,100));
        unit.add(pa);
        unit.add(pv);
        return unit;
    }

    public void generateSkills(JButton unite){ //génère le nombre de bouton des compétences en fonction de la liste des compétences de l'unité
        TaskBar.setBackground(Color.black);
        TaskBar.add(taskbarbg);
        taskbarbg.removeAll();
        FlowLayout flow = new FlowLayout();
        taskbarbg.setLayout(flow);
        JButton unit = displayUnit(unite);
        taskbarbg.add(unit);
        for (int i = 0; i < terrain.getPlateau()[unite.getY()/unite.getHeight()][unite.getX()/unite.getWidth()].getUnite().getCompetences().length; i++){
            if (i == 0){
                btSkill1.setToolTipText(terrain.getPlateau()[unite.getY()/unite.getHeight()][unite.getX()/unite.getWidth()].getUnite().getCompetences()[0].getDescription());
                taskbarbg.add(btSkill1);
            }
            if (i == 1){
                btSkill2.setToolTipText(terrain.getPlateau()[unite.getY()/unite.getHeight()][unite.getX()/unite.getWidth()].getUnite().getCompetences()[1].getDescription());
                taskbarbg.add(btSkill2);
            }
            if (i == 2){
                btSkill3.setToolTipText(terrain.getPlateau()[unite.getY()/unite.getHeight()][unite.getX()/unite.getWidth()].getUnite().getCompetences()[2].getDescription());
                taskbarbg.add(btSkill3);
            }
            if (i == 3){
                btSkill4.setToolTipText(terrain.getPlateau()[unite.getY()/unite.getHeight()][unite.getX()/unite.getWidth()].getUnite().getCompetences()[3].getDescription());
                taskbarbg.add(btSkill4);
            }
        }
        btStatsSkill.setToolTipText("liste des compétences");
        btSkill1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btSkill1.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btSkill1.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque.png"));
            }
        });
        btSkill2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btSkill2.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btSkill2.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
            }
        });
        btSkill3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btSkill3.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btSkill3.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
            }
        });
        btSkill4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btSkill4.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btSkill4.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/skills.png"));
            }
        });
        btStatsSkill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btStatsSkill.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btStatsSkill.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats.png"));
            }
        });
        taskbarbg.add(btStatsSkill);
        btSkill1.setContentAreaFilled(false);
        btSkill1.setBorderPainted(false);
        btSkill1.setFocusable(false);
        btSkill2.setContentAreaFilled(false);
        btSkill2.setBorderPainted(false);
        btSkill2.setFocusable(false);
        btSkill3.setContentAreaFilled(false);
        btSkill3.setBorderPainted(false);
        btSkill3.setFocusable(false);
        btSkill4.setContentAreaFilled(false);
        btSkill4.setBorderPainted(false);
        btSkill4.setFocusable(false);
        btStatsSkill.setContentAreaFilled(false);
        btStatsSkill.setBorderPainted(false);
        btStatsSkill.setFocusable(false);
        JButton retour = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour.png"));
        retour.setPreferredSize(new Dimension(100,125));
        retour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                retour.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                retour.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour.png"));
            }
        });
        retour.setContentAreaFilled(false);
        retour.setFocusable(false);
        retour.setBorderPainted(false);
        retour.addActionListener((ActionEvent e) -> {
            generateTaskBar();
        });
        taskbarbg.add(retour);
        retour.setToolTipText("retour");
        taskbarbg.updateUI();
    }

    public void viewStatsCompetence(JButton b){ //création d'une fenêtre affichant la liste des compétences de l'unité (en détail)
        Unite u = terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit;
        JFrame stats = new JFrame(terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.toString());
        stats.setVisible(true);
        stats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        stats.setLocationRelativeTo(getTerrainPanel());

        JPanel statsPanel = new JPanel(new BorderLayout());
        JLabel unite = new JLabel(generateImage(terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.toString()));
        statsPanel.add(unite,BorderLayout.WEST);

        JPanel allCompetence = new JPanel();
        allCompetence.setPreferredSize(new Dimension(600,400));
        allCompetence.setLayout(new BoxLayout(allCompetence,BoxLayout.Y_AXIS));
        for(int i = 0; i < u.getCompetences().length; i++){
            allCompetence.add(new JLabel("Nom de la compétence "+i+": "+u.getCompetences()[i].getName()));
            allCompetence.add(new JLabel("Description: "+u.getCompetences()[i].getDescription()));
            allCompetence.add(new JLabel("Coup en PA: "+u.getCompetences()[i].getCout()));
            allCompetence.add(new JLabel("Temps de recharge (en tour): "+u.getCompetences()[i].getCooldown()));
            allCompetence.add(new JLabel("Temps de recharge actuel (en tour): "+u.getCompetences()[i].getCooldownActuel()));
            allCompetence.add(new JLabel("--------------"));
        }

        JScrollPane scrollStats = new JScrollPane(allCompetence);
        scrollStats.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollStats.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statsPanel.add(scrollStats,BorderLayout.CENTER);
        stats.setSize(650,250);
        stats.setResizable(false);
        stats.add(statsPanel);
    }

    public void generateAction(JButton unite){
        TaskBar.setBackground(Color.black);
        TaskBar.add(taskbarbg);
        taskbarbg.removeAll();
        FlowLayout flow = new FlowLayout();
        taskbarbg.setLayout(flow);
        JButton unit = displayUnit(unite);
        taskbarbg.add(unit);
        btStats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btStats.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btStats.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/stats.png"));
            }
        });
        taskbarbg.add(btStats);
        btStats.setToolTipText("Statistiques de l'unité");
        btAtk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btAtk.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btAtk.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/attaque.png"));
            }
        });
        taskbarbg.add(btAtk);
        btAtk.setToolTipText("Attaquer/Compétences");
        btDep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btDep.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/deplace2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btDep.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/deplace.png"));
            }
        });
        btStats.setContentAreaFilled(false);
        btStats.setBorderPainted(false);
        btStats.setFocusable(false);
        btAtk.setContentAreaFilled(false);
        btAtk.setBorderPainted(false);
        btAtk.setFocusable(false);
        btDep.setContentAreaFilled(false);
        btDep.setBorderPainted(false);
        btDep.setFocusable(false);
        taskbarbg.add(btDep);
        btDep.setToolTipText("Se déplacer");
        JButton retour = new JButton(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour.png"));
        retour.setPreferredSize(new Dimension(100,125));
        retour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                retour.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                retour.setIcon(new ImageIcon(Jeu.selectGoodPath()+"/assets/retour.png"));
            }
        });
        retour.setContentAreaFilled(false);
        retour.setFocusable(false);
        retour.setBorderPainted(false);
        retour.addActionListener((ActionEvent e) -> {
            generateTaskBar();
        });
        taskbarbg.add(retour);
        retour.setToolTipText("retour");
        taskbarbg.updateUI();
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
        if(s.equals("Pretresse")){
            u = new Pretresse(tourJoueur);
        }
        if(s.equals("Lancier")){
            u = new Lancier(tourJoueur);
        }
        if(s.equals("Assassin")){
            u = new Assassin(tourJoueur);
        }
        return u;
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