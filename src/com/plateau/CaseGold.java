package com.plateau;

import com.player.Joueur;

public class CaseGold extends CaseEffect{

    public CaseGold(){
        super();
    }

    public void Effect(){
        if (unit.toString().equals("Templier")) unit.getJoueur().setArgent(unit.getJoueur().getArgent() + 200);
        else unit.getJoueur().setArgent(unit.getJoueur().getArgent() + 100);
    }

}
