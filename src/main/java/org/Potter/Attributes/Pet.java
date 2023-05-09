package org.Potter.Attributes;

import java.util.Random;

public enum Pet {
    hibou,
    chat,
    rat,
    chien,
    crapaud;

    public static Pet getRandomPet() {
        Pet[] pets = Pet.values();
        int index = new Random().nextInt(pets.length);
        return pets[index];
    }
}

