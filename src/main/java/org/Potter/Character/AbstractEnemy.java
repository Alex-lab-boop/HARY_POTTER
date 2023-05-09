package org.Potter.Character;

import lombok.Getter;
import lombok.Setter;
import org.Potter.Actions.AbstractSpell;

public abstract class AbstractEnemy extends Character {
    private @Getter
    @Setter AbstractSpell action;
}
