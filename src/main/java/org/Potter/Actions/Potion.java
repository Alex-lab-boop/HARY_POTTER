package org.Potter.Actions;

import lombok.Getter;
import lombok.Setter;
import org.Potter.Attributes.Color;
import org.Potter.Character.Wizard;

import java.util.Objects;

public class Potion {
    private @Setter
    @Getter String name;
    private @Setter
    @Getter int heal;
    private @Setter
    @Getter int uses;
    private @Setter
    @Getter String boost;
    Color resetColor = new Color("\u001B[0m");
    Color red = new Color("\u001B[31m");
    Color green = new Color("\u001B[32m");
    Color blue = new Color("\u001B[34m");

    public Potion(String name, int heal, String boost) {
        this.name = name;
        this.heal = heal;
        this.uses = 1;
        this.boost = boost;
    }

    public void healing(Wizard player, Potion potion) {
        System.out.print("Tu as maintenant");
        if (Objects.equals(potion.boost, "")) {
            int heal = potion.getHeal();
            float mult = player.getHeal();
            heal = (int) Math.ceil(heal * mult);
            player.setHp(Math.min(player.getHp() + heal, player.getMaxHp()));
            System.out.print(green.getColor() + player.getHp() + "HP");
        } else if (Objects.equals(potion.boost, "DEFENCE")) {
            player.setTakenMul(player.getTakenMul() + 0.5f);
            System.out.print(red.getColor() + player.getTakenMul() + " DEFENCE");
        } else if (Objects.equals(potion.boost, "PRECISION")) {
            player.setAccuracyMul(player.getAccuracyMul() + 0.5f);
            System.out.print(blue.getColor() + player.getAccuracyMul() + " PRECISION");
        }
        potion.setUses(potion.getUses() - 1);
        if (potion.getUses() == 0) {
            player.getPotions().remove(potion);
        }
        System.out.println(resetColor.getColor() + " !");
        System.out.println("Tu as " + potion.getUses() + " " + potion.getName() + " restante.");
    }
}
