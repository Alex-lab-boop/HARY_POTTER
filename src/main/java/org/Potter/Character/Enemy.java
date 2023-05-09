package org.Potter.Character;

import org.Potter.Actions.AbstractSpell;

public class Enemy extends AbstractEnemy {
    public Enemy(String name, int hp, AbstractSpell action) {
        this.setName(name);
        this.setAlive(true);
        this.setHp(hp);
        this.setMaxHp(hp);
        this.setDamageMul(1);
        this.setTakenMul(1);
        this.setAccuracyMul(1);
        this.setAction(action);
        this.setState("");
    }
}
