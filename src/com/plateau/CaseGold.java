package com.plateau;

import com.player.Joueur;

public class CaseGold extends CaseEffect{

    public CaseGold(){
        super();
    }

    public void Effect(){
        unit.getJoueur().setArgent(unit.getJoueur().getArgent() + 100);
    }

}
