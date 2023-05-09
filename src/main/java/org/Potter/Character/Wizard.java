package org.Potter.Character;

import lombok.Getter;
import lombok.Setter;
import org.Potter.Actions.AbstractSpell;
import org.Potter.Attributes.House;
import org.Potter.Attributes.Pet;
import org.Potter.Actions.Potion;
import org.Potter.Actions.Spell;
import org.Potter.Attributes.Wand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Wizard extends Character {
    private @Getter
    @Setter String firstname;
    private @Getter
    @Setter String lastname;
    private @Getter
    @Setter int level; //1 -> 7
    private @Getter
    @Setter Pet pet;
    private @Getter
    @Setter House house;
    private @Getter
    @Setter Wand wand;
    private @Getter
    @Setter List<AbstractSpell> knownSpells = new ArrayList<>();
    private @Getter
    @Setter List<Potion> potions = new ArrayList<>();
    private @Getter
    @Setter int luck = 75;


    public Wizard(String firstname, String lastname, Pet pet, Wand wand, House house) {
        this.setName("Vous");
        this.firstname = firstname;
        this.lastname = lastname;
        this.pet = pet;
        this.wand = wand;
        this.house = house;
        this.level = 1;
        this.setHp(25);
        this.setMaxHp(25);
        this.setAlive(true);
        this.setTakenMul(house.getDamagetaken());
        this.setDamageMul(house.getDamagedone());
        this.setAccuracyMul(house.getAccuracy());
        this.setHeal(house.getHeal());
        this.setColor(house.getColor());
        this.setState("");
    }

    public static boolean defend(int luck) {
        System.out.println("Tu essais de te défendre.");
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        if (random < luck) {
            System.out.println("Ca marche !");
            return false;
        } else {
            System.out.println("Ca a échoué...");
        }
        return true;
    }
}