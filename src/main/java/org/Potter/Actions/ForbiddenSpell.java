package org.Potter.Actions;

public class ForbiddenSpell extends AbstractSpell {
    public ForbiddenSpell(String name, String description, int damage, int accuracy, String side) {
        this.setName(name);
        this.setDescription(description);
        this.setAccuracy(accuracy);
        this.setDamage(damage);
        this.setSideEffect(side);
    }
}
