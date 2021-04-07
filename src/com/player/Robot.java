package com.player;

import com.launcher.Jeu;

public class Robot extends Joueur{
    public Robot(int a,Jeu jeu){
        super(a,jeu);
        isHuman = false;
    }
}
