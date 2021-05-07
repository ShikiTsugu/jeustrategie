package com.plateau;

import com.unite.*;

public class Tower extends CaseEffect{

    public Tower(){
        super();
    }

    public void Effect(){
        if (!unit.possedeBuff("Tower")){
            unit.setPointAction(0);
        }
        unit.addBuff("Tower", 1);
        unit.setComp();
    }

}
