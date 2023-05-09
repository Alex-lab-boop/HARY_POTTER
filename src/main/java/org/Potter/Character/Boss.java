package org.Potter.Character;

import org.Potter.Actions.AbstractSpell;

public class Boss extends AbstractEnemy {
    public Boss(String name, int hp, String color, AbstractSpell action) {
        this.setName(name);
        this.setAlive(true);
        this.setHp(hp);
        this.setMaxHp(hp);
        this.setDamageMul(1);
        this.setTakenMul(1);
        this.setAccuracyMul(1);
        this.setColor(color);
        this.setAction(action);
        this.setState("");
    }
}
