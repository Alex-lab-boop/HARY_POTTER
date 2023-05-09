package org.Potter.Actions;

public class Spell extends AbstractSpell {
    public Spell(String name, String description, int damage, int accuracy, String side) {
        this.setName(name);
        this.setDescription(description);
        this.setAccuracy(accuracy);
        this.setDamage(damage);
        this.setSideEffect(side);
    }
}
