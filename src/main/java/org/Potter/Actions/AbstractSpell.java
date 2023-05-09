package org.Potter.Actions;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractSpell {
    private @Getter
    @Setter String name;
    private @Getter
    @Setter String description;
    private @Getter
    @Setter int accuracy;
    private @Getter
    @Setter int damage;
    private @Getter
    @Setter String sideEffect;
}
