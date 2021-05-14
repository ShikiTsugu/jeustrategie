package com.plateau;

public class CaseGold extends CaseEffect{

    public CaseGold(){
        super();
    }

    public void Effect(){
        if (unit == null){
            return;
        }
        if (unit.toString().equals("Templier")) unit.getJoueur().setArgent(unit.getJoueur().getArgent() + 200);
        else unit.getJoueur().setArgent(unit.getJoueur().getArgent() + 100);
    }

}
