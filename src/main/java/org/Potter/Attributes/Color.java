package org.Potter.Attributes;

import lombok.Getter;
import lombok.Setter;

public class Color {
    private @Getter
    @Setter String color;

    public Color(String color) {
        this.color = color;
    }
}

