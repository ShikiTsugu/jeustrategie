package com.plateau;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Model {
    private BufferedImage image;

    public Model(String chemin){
        try{
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            System.out.println("Fichier non trouv�, chemin incorrecte.");
            System.exit(1);
        }
    }

    public void setImage(BufferedImage im){image=im;}

    public BufferedImage getImage(){return image;}
}