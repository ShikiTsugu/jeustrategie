package com.plateau;

import com.unite.*;

public class Tower extends CaseEffect{

    public Tower(){
        super();
    }

    public void Effect(){
        unit.addBuff("tower", 1);
    }

}
