package com.plateau;

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

    public Vue(Model m, Terrain t){
        model = m;
        imagePane = new ImagePane();
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

    public void generateTerrain(){
        Case[][] terraintmp = terrain.plateau;
        imagePane.add(TerrainPanel);
        GridLayout grid = new GridLayout(5,5);
        TerrainPanel.setLayout(grid);
        for (int x = 0; x < terraintmp.length; x++){
            for (int y = 0; y < terraintmp[x].length; y++){
                if (terraintmp[x][y].unit != null) {
                    JButton bt = new JButton(terraintmp[x][y].unit.toString());
                    TerrainPanel.add(bt);
                    bt.addActionListener((ActionEvent e) -> {
                        System.out.println(bt.getX());
                        System.out.println(bt.getY());
                    });
                    bt.setPreferredSize(new Dimension(150,150));
                    terrainBt.add(bt);
                } else {
                    JButton bt = new JButton();
                    TerrainPanel.add(bt);
                    bt.setOpaque(false);
                    bt.setContentAreaFilled(false);
                    bt.addActionListener((ActionEvent e) -> {
                        System.out.println(bt.getX());
                        System.out.println(bt.getY());
                    });
                    bt.setPreferredSize(new Dimension(150,150));
                    terrainBt.add(bt);
                }
            }
        }
        TerrainPanel.setOpaque(false);
    }

    public void generateTaskBar(){
        FlowLayout flow = new FlowLayout();
        TaskBar.setLayout(flow);
        for (int i = 0; i < 4; i++){
            JButton bt = new JButton("test");
            bt.setPreferredSize(new Dimension(300,150));
            TaskBar.add(bt);
        }
        imagePane.add(TaskBar);
    }
    
    public ArrayList<JButton> getTerrainBt(){
        return terrainBt;
    }

}