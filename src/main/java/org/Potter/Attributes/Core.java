package org.Potter.Attributes;

public enum Core {
    Coeur_de_Dragon("Coeur de Dragon"),
    Plume_de_Phoénix("Plume de Phoénix"),
    Poil_de_queue_de_Licorne("Poil de queue de Licorne"),
    Cheuveux_de_Veela("Cheuveux de Veela"),
    Poil_de_queue_de_Thestral("Poil de queue de Thestral");

    private String name;

    Core(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}