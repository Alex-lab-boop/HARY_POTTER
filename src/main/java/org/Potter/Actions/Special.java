package org.Potter.Actions;

import lombok.Getter;
import lombok.Setter;

public class Special {
    private @Getter
    @Setter int turn;
    private @Getter
    @Setter String chapter;
    private @Getter
    @Setter int numberUse;

    public Special(String chapter, int turn) {
        this.setChapter(chapter);
        this.setTurn(turn);
        this.setNumberUse(0);
    }
}
