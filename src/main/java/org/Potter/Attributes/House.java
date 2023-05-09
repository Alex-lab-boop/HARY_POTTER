package org.Potter.Attributes;

import lombok.Getter;
import lombok.Setter;

public class House {
    private @Getter
    @Setter String name;
    private @Getter
    @Setter float heal;
    private @Getter
    @Setter float damagedone;
    private @Getter
    @Setter float damagetaken;
    private @Getter
    @Setter float accuracy;
    private @Getter
    @Setter String color;

    public House(String name, float heal, float damagedone, float damagetaken, float accuracy, String color) {
        this.name = name;
        this.heal = heal;
        this.damagedone = damagedone;
        this.damagetaken = damagetaken;
        this.accuracy = accuracy;
        this.color = color;
    }
}
