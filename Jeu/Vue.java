import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class Vue extends JFrame{

    private ImagePane imagePane;
    private Model model;
    private JPanel panel = new JPanel();
    Terrain terrain;
    
    public Vue(Model m, Terrain t){
    	model = m;
        setTitle("Jeu de Strategie");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        terrain = t;

        if (terrain != null) {
            AfficheTerrain();
        }
    }

    /*Affichage d'un terrain*/
    public void AfficheTerrain(){
        imagePane = new ImagePane();

        GridLayout grid = new GridLayout(5,5);
        imagePane.setLayout(grid);
        generateTerrain();

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
        for (int x = 0; x < terraintmp.length; x++){
            for (int y = 0; y < terraintmp[x].length; y++){
                JButton bt = new JButton();
                try {
                    if (terraintmp[x][y].unit != null) {
                        imagePane.add(bt);
                        bt.addActionListener((ActionEvent e) -> {
                            System.out.println(bt.getX());
                            System.out.println(bt.getY());
                        });
                    }
                } catch (Exception e){
                    System.out.println("erreur");
                }
                bt.setContentAreaFilled(false);
                bt.setOpaque(false);
                imagePane.add(bt);
                bt.addActionListener((ActionEvent e) -> {
                    System.out.println(bt.getX());
                    System.out.println(bt.getY());
                });
            }
        }
    }

}