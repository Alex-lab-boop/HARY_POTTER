package org.Potter.Attributes;

import lombok.Getter;
import lombok.Setter;

public class Wand {
    private @Getter
    @Setter int size;
    private @Getter
    @Setter Core core;

    public Wand(int size, Core core) {
        this.size = size;
        this.core = core;
    }
}