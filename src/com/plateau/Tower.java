package com.plateau;

import com.unite.*;

public class Tower extends CaseEffect{

    public Tower(){
        super();
    }

    public void Effect(){
        if  (unit == null){
            return;
        }
        Buff buff = new Buff("Tower",1,unit);
        unit.getBuffs().add(buff);
        buff.readBuff();
        unit.setComp();
    }

}
