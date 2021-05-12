package com.plateau;

import com.launcher.Jeu;
import com.player.*;
import com.player.Robot;
import com.unite.Unite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;

//Classe de "controle" permettant de gérer les boutons sur l'interface graphique, le déplacement, l'attaque, l'achat etc..
public class Controlleur {
    private final Vue vue;
    private Jeu jeu;
    private int nbTour;
    private final Map map;
    private MapSerialized mapSave;

    public Controlleur(Vue v){
        jeu = new Jeu();
        vue = v;
        nbTour = 1;
        map = new Map();
        mapSave = new MapSerialized();
        //GenerateSaveMap();
        //SaveMap();
        LoadMap();
    }

    public MapSerialized getMapSave() { return mapSave; }
    public Map getMap() { return map; }
    public Jeu getJeu(){ return jeu; }
    public void setJeu(Jeu j){ jeu = j; }

    //Regarde si l'unité choisit a bien été acheté ou non, va être utile pour ensuite la placer.
    public boolean acheteUnite(Joueur j, Unite u){
        //Si on ne peut pas ajouter d'unité c'est que la liste est déjà pleine.
        if (!j.ajouteUnite(u)) {
            JOptionPane.showMessageDialog(vue.getTerrainPanel(),
                    "Le max d'unité a été atteint.",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
            return false;
        } else {
            ActionJoueur aj = new ActionJoueur(j);
            aj.setBought(true);
            boolean b = jeu.getTourDuJoueur() == jeu.getJoueur1();
            placeUniteApresAchat(u, aj,b);
            return true;
        }
    }

    //Place l'unité achetée sur la case cliqué.
    public void placeUniteApresAchat(Unite u, ActionJoueur j, boolean J1){
        for (JButton b : vue.terrainBt) {
            //Regarde toutes les cases sur le terrain, si c'est une zone possible d'achat selon le joueur,
            //on la marque en vert pour faciliter le placement, sinon la case est rouge.
            if(j.getJoueur()==jeu.getJoueur1()){
                if(vue.terrain.getB1()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]) {
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
                if(vue.terrain.getB2()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]) {
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
            //Affecte à tous les boutons sur le terrain la possibilité d'accueillir une unité lorsqu'on clique dessus.
            //L'unité est ajouté seulement si la case est vide et si c'est bien dans la zone de placement du joueur.
            b.addActionListener((ActionEvent e) -> {
                if (j.getJoueur()==jeu.getJoueur1()) {
                    if (vue.terrain.getB1()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]) {
                        if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                            j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                        } else {
                            j.getJoueur().annuleAjout(u);
                            j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                            JOptionPane.showMessageDialog(vue.getTerrainPanel(),
                                    "Il y a déjà une unité.",
                                    "",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        j.getJoueur().annuleAjout(u);
                        j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                        JOptionPane.showMessageDialog(vue.getTerrainPanel(),
                                "Impossible, en dehors de la zone d'achat.",
                                "",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
                if (j.getJoueur()==jeu.getJoueur2()) {
                    if (vue.terrain.getB2()[b.getY() / b.getHeight()][b.getX() / b.getWidth()]) {
                        if (vue.getTerrain().getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estVide()) {
                            j.placeUnite(vue.terrain, u, b.getX() / b.getWidth(), b.getY() / b.getHeight(), J1);
                        } else {
                            j.getJoueur().annuleAjout(u);
                            j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                            JOptionPane.showMessageDialog(vue.getTerrainPanel(),
                                    "Il y a déjà une unité.",
                                    "",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        j.getJoueur().annuleAjout(u);
                        j.getJoueur().setArgent(j.getJoueur().getArgent() + u.getCoutUnite());
                        JOptionPane.showMessageDialog(vue.getTerrainPanel(),
                                "Impossible, en dehors de la zone d'achat.",
                                "",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
                //On actualise l'interface graphique
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    public void useSkill(Joueur j, JButton cast,int c) {
        int[] coordI = {cast.getX() / cast.getWidth(), cast.getY() / cast.getHeight()};
        int[] coordF = new int[2];
        Unite atq = vue.terrain.getPlateau()[coordI[1]][coordI[0]].unit;
        for (JButton b : vue.terrainBt) {
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setContentAreaFilled(true);
                    if (vue.terrain.getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].estUnit() && ((Math.abs(b.getY() / b.getHeight() - coordI[1]) + Math.abs(b.getX() / b.getWidth() - coordI[0])) <= atq.getCompetences()[c].getPortee())
                            && vue.terrain.getPlateau()[b.getY() / b.getHeight()][b.getX() / b.getWidth()].unit != atq) {
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
                }catch (NullPointerException ex){
                    if(atq.toString().equals("Cavalier"))JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Vous venez de charger droit devant vous", "", JOptionPane.PLAIN_MESSAGE);
                }
                vue.generateTerrain();
                vue.generateTaskBar();
                DetectWin();
            });

        }
    }

    public void DetectWin(){ //permet d'afficher le joueur gagnant
        if (jeu.getJoueur1().getHero().getSanteCourante() <= 0) {
            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 2 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
            vue.afficheIni();
        } else if (jeu.getJoueur2().getHero().getSanteCourante() <= 0) {
            JOptionPane.showMessageDialog(vue.getTerrainPanel(), "Le Joueur 1 à gagné.", "", JOptionPane.PLAIN_MESSAGE);
            vue.afficheIni();
        }
        LoadMap();
    }

    //Déplacement d'unité en cliquant sur une case.
    public void deplaceUnite(Joueur j, JButton posIni){
        ActionJoueur aj = new ActionJoueur(j);
        int[]coordI = {posIni.getX()/posIni.getWidth(), posIni.getY()/posIni.getHeight()};
        int[]coordF = new int[2];
        Unite u = vue.terrain.plateau[coordI[1]][coordI[0]].unit;
        for (JButton b : vue.terrainBt) {
            //Marque en vert toutes les cases où l'unité peut se déplacer.
            if(u.casesDisponibleDeplacement(vue.terrain, u, coordI[0], coordI[1], b.getX()/b.getWidth(), b.getY()/b.getHeight())) {
                b.setContentAreaFilled(true);
                b.setBackground(new Color(0, 150, 0));
            }
            //Affecte à toutes les cases la possibilité d'accueillir l'unité si elle peut s'y déplacer.
            b.addActionListener((ActionEvent e) -> {
                coordF[0] = b.getX()/b.getWidth();
                coordF[1] = b.getY()/b.getHeight();
                aj.deplaceUnite(vue.terrain, coordI[0],coordI[1],coordF[0],coordF[1]);
                vue.generateTerrain();
                vue.generateTaskBar();
            });
        }
    }

    //Affiche les différents statistiques d'une unité en cliquant sur elle puis en sélectionnant l'icone de stats.
    public void viewStats(JButton b){
        Unite u = vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit;
        JFrame stats = new JFrame(vue.terrain.getPlateau()[b.getY()/b.getHeight()][b.getX()/b.getWidth()].unit.toString());
        stats.setVisible(true);
        stats.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        stats.setLocationRelativeTo(vue.getTerrainPanel());

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
        for(int i =0;i < u.getBuffs().size();i++){
            allStats.add(new JLabel(u.getBuffs().get(i).getNom()));
        }
        allStats.add(new JLabel("Debuff : "));
        for(int i =0;i < u.getDebuffs().size();i++){
            allStats.add(new JLabel(u.getDebuffs().get(i).getNom()));
        }

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

    public void finDeTour(){ //fonction pour déterminer un fin de tour, d'un joueur "humain" ou du robot
        jeu.finDeTour();
        nbTour++;
        if(jeu.getJoueur2().getIsHuman()) { //si c'est un fin de tour d'un joueur humain
            //affiche un popup pour indiquer le compteur de tour, et le tour du joueur
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
            if (jeu.getTourDuJoueur() == jeu.getJoueur1()) { //à la fin du tour du joueur, réinitialisation des PA des unités du joueur venant de passer son tour
                jeu.getJoueur1().resetPointAction();
                jTour = new JLabel("Tour du joueur 1");
            } else if (jeu.getTourDuJoueur() == jeu.getJoueur2()) {
                if (nbTour == 2) jeu.getJoueur2().getHero().setPointAction(0); //cas particulier
                else jeu.getJoueur2().resetPointAction(); //à la fin du tour du joueur, réinitialisation des PA des unités du joueur venant de passer son tour
                jTour = new JLabel("Tour du joueur 2");
            }
            jeu.activateAlterationEtats(); //applique et actualise les altérations d'états

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
        }else{ //si l'adversaire est un robot
            if (jeu.getTourDuJoueur() == jeu.getJoueur1()) {
                jeu.getJoueur1().resetPointAction(); //à la fin du tour du joueur, réinitialisation des PA des unités du joueur venant de passer son tour
                JOptionPane.showMessageDialog(vue.getTerrainPanel(), "A votre tour, le robot a joué.", "Tour "+nbTour, JOptionPane.PLAIN_MESSAGE); //affiche que le robot a joué
            } else if (jeu.getTourDuJoueur() == jeu.getJoueur2()) {
                if (nbTour == 2) jeu.getJoueur2().getHero().setPointAction(0); //cas particulier
                else jeu.getJoueur2().resetPointAction(); //à la fin du tour du robot, réinitialisation des PA des unités du robot
            }
            jeu.activateAlterationEtats(); //applique et actualise les altérations d'états
        }
        vue.setTourJoueur(jeu.getTourDuJoueur());
        TerrrainEffect(); 
        vue.generateTerrain();
        vue.generateTaskBar();
    }

    //Implémentation d'une fenêtre proposant de quitter ou de continuer une partie lorsqu'on appuie sur échap.
    public void escapeAction(){
        JFrame esc = new JFrame("Exit");
        esc.setVisible(true);
        esc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        esc.setSize(new Dimension(250, 100));
        esc.setLocationRelativeTo(null);
        esc.setResizable(false);

        JPanel choice = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton resume = new JButton("Continuer");
        JButton quit = new JButton("Quitter");
        //Si on souhaite continuer on ferme juste la fenetre.
        resume.addActionListener((ActionEvent e) -> {
            esc.dispose();
        });
        //Sinon on retourne au menu principal.
        quit.addActionListener((ActionEvent e) -> {
            esc.dispose();
            vue.afficheIni();
        });
        choice.add(resume);
        choice.add(quit);
        esc.add(choice);
    }

    //Sous classe permettant de détecter l'appuie sur un bouton du clavier.
    class escButtonAction implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        //Plus précisémment on s'intéresse au cas lorsque le bouton échap est relaché.
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE) {
                escapeAction();
            }
        }
    }

    //Méthodes concernant le robot

    //Permet de stocker dans une liste toutes les cases disponibles là où une unité peut se déplacer.
    public LinkedList<Case> getCaseDispo(Unite u){
        LinkedList<Case> casesDispo = new LinkedList<>();
        for(int y = 0; y<vue.terrain.getPlateau().length; y++) {
            for (int x = 0; x < vue.terrain.getPlateau()[y].length; x++) {
                if(u.casesDisponibleDeplacement(vue.terrain, u, u.getCurrentX(), u.getCurrentY(), x, y)){
                    casesDispo.add(vue.terrain.getPlateau()[y][x]);
                }
            }
        }
        return casesDispo;
    }

    //Attaque l'unité détecté par l'unité choisit par le robot, et détecte la condition de victoire aussi si l'unité détectée
    //était le héro adverse et si il est mort.
    public void attackUniteRob(Joueur j, Unite u){
        int[] coordTarget = u.getCoordTarget().getFirst();
        ActionJoueur aj = new ActionJoueur(j);
        aj.attaqueUnite(vue.terrain, u.getCurrentX(), u.getCurrentY(), coordTarget[0], coordTarget[1]);
        DetectWin();
    }

    //Bouge l'unité vers sa cible si elle n'est pas dans sa portée d'attaque.
    public void moveUntilAtRange(Joueur j, Unite u){
        int[] coordTarget = u.getCoordTarget().getFirst();
        Unite target = vue.terrain.getPlateau()[coordTarget[1]][coordTarget[0]].getUnite();
        ActionJoueur aj = new ActionJoueur(j);
        LinkedList<int[]> coords = ((Robot)j).availableSpaceAroundTarget(vue.terrain, target);
        boolean moved = false;
        for(int[] coord : coords){
            //Gère le déplacement de l'unité, si c'est dans sa portée etc..
            if(u.casesDisponibleDeplacement(vue.terrain, u, u.getCurrentX(), u.getCurrentY(), coord[0], coord[1])){
                int[] destination = u.getDeplacementDisponible().get(0).casePos(vue.terrain);
                aj.deplaceUnite(vue.terrain, u.getCurrentX(), u.getCurrentY(), destination[0], destination[1]);
                moved = true;
                break;
            }
        }
        //Si l'unité n'a pas été déplacé vers sa cible, donc que ce n'est pas à portée ou qu'il n'y a pas de place
        // on continue de la faire se déplacer à une case possible aléatoire, notamment pour détecter une autre cible possible.
        if(!moved){
            LinkedList<Case> casesDispo = getCaseDispo(u);
            Random rand = new Random();
            int randInd = rand.nextInt(casesDispo.size());
            Case randCase = casesDispo.get(randInd);
            int xF = randCase.casePos(vue.terrain)[0];
            int yF = randCase.casePos(vue.terrain)[1];
            if(u.getPointAction()>0) {
                u.deplaceUnite(vue.terrain, u.getCurrentX(), u.getCurrentY(), xF, yF);
            }
        }
    }

    //Déplace l'unité choisit par le robot dans des cases aléatoires à sa portée.
    public void deplaceUniteRob(Joueur j){
        if(((Robot) j).pickUnit(vue.terrain)) {
            int[] coordI = ((Robot) j).getCoord();
            Unite u = vue.terrain.plateau[coordI[0]][coordI[1]].unit;
            LinkedList<Case> casesDispo = getCaseDispo(u);
            //Si l'unité lors de son déplacement à détecter une cible, on arrête tout et on regarde si elle peut attaquer
            //directement ou non, si elle peut on attaque, sinon on se déplace vers la cible.
            try {
                if (((Robot) j).targetDetected(vue.terrain, u.getCurrentX(), u.getCurrentY(), u.getPorteeDeplacement(), u, j)) {
                    if (((Robot) j).canAttack(vue.terrain, u.getCurrentX(), u.getCurrentY(), u.getPorteeAttaque(), u, j)
                    && u.getPointAction()>0) {
                        attackUniteRob(j, u);
                        u.getCoordTarget().clear();
                    } else {
                        moveUntilAtRange(j, u);
                        u.getCoordTarget().clear();
                    }
                    return;
                }
            }catch(NullPointerException ex){
                u.getCoordTarget().clear();
                ((Robot) j).targetDetected(vue.terrain, u.getCurrentX(), u.getCurrentY(), u.getPorteeDeplacement(), u, j);
                return;
            }
            Random rand = new Random();
            int randInd = rand.nextInt(casesDispo.size());
            Case randCase = casesDispo.get(randInd);
            int xF = randCase.casePos(vue.terrain)[0];
            int yF = randCase.casePos(vue.terrain)[1];
            if(u.getPointAction()>0) {
                u.deplaceUnite(vue.terrain, u.getCurrentX(), u.getCurrentY(), xF, yF);
            }
        }
    }

    //Le robot choisit une unité au hasard dans la boutique, l'achète puis la place.
    public void achatUniteRob(Joueur j) {
        Random rand = new Random();
        int uniteRandom = rand.nextInt(vue.getListeUnit().length);
        Unite u = vue.createUnite(vue.getListeUnit()[uniteRandom]);
        ActionJoueur aj = new ActionJoueur(j);
        int randPosLibre = rand.nextInt(((Robot) j).availableSpace(vue.terrain).size());
        int[] pos = (int[]) ((Robot) j).availableSpace(vue.terrain).get(randPosLibre);
        //Vérifie que le robot possède l'argent nécessaire à l'achat, le cas échéant on retire l'unité.
        if (j.getArgent() >= u.getCoutUnite() && !j.maxUnit()) {
            aj.acheteUnite(u, vue.getTerrainPanel());
            j.ajouteUnite(u);
            if (pos != null) {
                aj.placeUnite(vue.terrain, u, pos[1], pos[0], false);
            } else {
                j.annuleAjout(u);
                j.setArgent(j.getArgent() + u.getCoutUnite());
            }
        }
    }

    //Système de gameplay du robot
    public void robotPlay(Joueur j){
        //Tant que les unités du robot ont des PA à dépenser, il les déplace/attaque
        while(!((Robot)j).allPAused()){
            deplaceUniteRob(j);
        }
        //Tant que le robot à suffisamment d'argent il achète une unité et la place.
        if(j.getArgent()>=100) {
            achatUniteRob(j);
        }
        //Une fois que tout a été fait, il passe son tour.
        finDeTour();
    }

    public void SaveMap(){
        try{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Map.ser"));

            oos.writeObject(mapSave);

        } catch (IOException IE){
            System.out.println(IE);
        }
    }

    public void LoadMap(){
        try{

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Map.ser"));

            mapSave = (MapSerialized)ois.readObject();

        } catch (FileNotFoundException FE){
            System.out.println("Pas de Map Serialiser");
        } catch (ClassNotFoundException CE){
            System.out.println(CE);
        } catch (IOException IO){
            System.out.println(IO);
        }
    }

    public void GenerateSaveMap(){
        getMap().Map5x5();
        getMapSave().getMapList().add(getMap().getTerrain());
        getMap().Map14x6();
        getMapSave().getMapList().add(getMap().getTerrain());
        getMap().Map14x6Gold();
        getMapSave().getMapList().add(getMap().getTerrain());
    }

    public void SetSmallAsMap(){
        getJeu().setTerrain(getMapSave().getMapList().get(0));
        getMap().setMap(getMapSave().getMapList().get(0));
    }

    public void SetBigAsMap(){
        getJeu().setTerrain(getMapSave().getMapList().get(1));
        getMap().setMap(getMapSave().getMapList().get(1));
    }

    public void SetBigGoldAsMap(){
        getJeu().setTerrain(getMapSave().getMapList().get(2));
        getMap().setMap(getMapSave().getMapList().get(2));
    }
}
