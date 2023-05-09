package org.Potter.Character;

import lombok.Getter;
import lombok.Setter;
import org.Potter.Actions.AbstractSpell;
import org.Potter.Attributes.Color;
import org.Potter.Actions.ForbiddenSpell;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
    private @Getter
    @Setter String name;
    private @Getter
    @Setter boolean alive;
    private @Setter
    @Getter int hp;
    private @Setter
    @Getter int maxHp;
    private @Getter
    @Setter float heal;
    private @Getter
    @Setter float damageMul;
    private @Getter
    @Setter float takenMul;
    private @Getter
    @Setter float accuracyMul;
    private @Getter
    @Setter String color;
    private @Getter
    @Setter String state;
    Color resetColor = new Color("\u001B[0m");
    Color red = new Color("\u001B[31m");
    int avada = 0;

    public void attack(AbstractSpell spell, Character attacker, Character defender) {
        int hp = defender.hp;
        if (Objects.equals(spell.getSideEffect(), "death")) {
            if (avada == 0) {
                System.out.println("\u001B[40m" + red.getColor() + "VOLDEMORT :\"AVADA...");
                avada += 1;
            } else if (avada == 1) {
                System.out.println("\u001B[40m" + red.getColor() + "VOLDEMORT :\"KEDAVRA !");
                defender.setAlive(false);
                avada += 1;
            }
        }
        if (spell.getClass() == ForbiddenSpell.class) {
            System.out.print("\u001B[40m" + red.getColor());
        }
        System.out.print(attacker.name + " use " + spell.getName() + " !");
        if (spell.getClass() == ForbiddenSpell.class) {
            System.out.println(resetColor.getColor());
        } else {
            System.out.println();
        }
        int accuracy = spell.getAccuracy();
        float accMul = attacker.accuracyMul;
        accuracy = (int) Math.ceil(accuracy * accMul);
        int luck = ThreadLocalRandom.current().nextInt(0, 100);
        if (luck < accuracy) {
            int damage = spell.getDamage();
            if (luck < 5) {
                damage = (int) Math.floor(damage * 1.5f);
                if (spell.getClass() == ForbiddenSpell.class) {
                    System.out.print("\u001B[40m" + red.getColor());
                }
                System.out.print("CRITICAL HIT!");
                if (spell.getClass() == ForbiddenSpell.class) {
                    System.out.println(resetColor.getColor());
                } else {
                    System.out.println();
                }
            }
            float multiplier = attacker.getDamageMul();
            damage = (int) Math.floor(damage * multiplier);
            float defense = 1 / defender.takenMul;
            damage = (int) Math.floor(damage * defense);
            hp = hp - damage;
            if (hp > 0) {
                if (Objects.equals(defender.getName(), "YOU")) {
                    if (spell.getClass() == ForbiddenSpell.class) {
                        System.out.print("\u001B[40m" + red.getColor());
                    }
                    System.out.print("YOU have " + hp + "HP left.");
                    if (spell.getClass() == ForbiddenSpell.class) {
                        System.out.println(resetColor.getColor());
                    } else {
                        System.out.println();
                    }
                } else {
                    System.out.println(defender.name + " has " + hp + "HP left.");
                }
                defender.setHp(hp);
            } else {
                if (Objects.equals(defender.getName(), "YOU")) {
                    if (spell.getClass() == ForbiddenSpell.class) {
                        System.out.print("\u001B[40m" + red.getColor());
                    }
                    System.out.print("YOU are dead !");
                    if (spell.getClass() == ForbiddenSpell.class) {
                        System.out.println(resetColor.getColor());
                    } else {
                        System.out.println();
                    }
                } else {
                    System.out.println(defender.name + " is dead !");
                }
                defender.setAlive(false);
            }
            giveSideEffect(attacker, defender, spell);
        } else {
            if (Objects.equals(attacker.getName(), "YOU")) {
                if (spell.getClass() == ForbiddenSpell.class) {
                    System.out.print("\u001B[40m" + red.getColor());
                }
                System.out.print("Your attack missed !");
                if (spell.getClass() == ForbiddenSpell.class) {
                    System.out.println(resetColor.getColor());
                } else {
                    System.out.println();
                }
            } else {
                System.out.println(attacker.name + "'s attack missed !");
            }
        }
    }

    public void multiattack(AbstractSpell spell, Character attacker, List<AbstractEnemy> enemyList) {
        System.out.println(attacker.name + " use " + spell.getName() + " !");
        for (Character defender : enemyList) {
            if (defender.isAlive()) {
                int hp = defender.getHp();
                int accuracy = spell.getAccuracy();
                float accMul = attacker.accuracyMul;
                accuracy = (int) Math.ceil(accuracy * accMul);
                int luck = ThreadLocalRandom.current().nextInt(1, 101);
                if (luck <= accuracy) {
                    int damage = spell.getDamage();
                    if (luck < 5) {
                        damage = (int) Math.floor(damage * 1.5f);
                        System.out.println("CRITICAL HIT!");
                    }
                    float multiplier = attacker.getDamageMul();
                    damage = (int) Math.floor(damage * multiplier);
                    float defense = 1 / defender.getTakenMul();
                    damage = (int) Math.floor(damage * defense);
                    hp = hp - damage;
                    if (hp > 0) {
                        System.out.println(defender.getName() + " has " + hp + "HP left.");
                        defender.setHp(hp);
                    } else {
                        System.out.println(defender.getName() + " is dead !");
                        defender.setAlive(false);
                    }
                } else {
                    System.out.println("MISS !");
                }
            }
        }
    }

    public void giveSideEffect(Character attacker, Character defender, AbstractSpell spell) {
        String side = spell.getSideEffect();
        switch (side) {
            case "burn":
                if (!Objects.equals(side, "") & !Objects.equals(side, defender.getState())) {
                    defender.setState(side);
                    System.out.println(defender.getName() + " is now " + red.getColor() + "BURN" + resetColor.getColor() + " !");
                }
                break;
            case "heal1":
                attacker.setState(side);
                break;
            case "flinch":
                int luck = ThreadLocalRandom.current().nextInt(1, 101);
                if (luck <= 50) {
                    System.out.println(defender.name + " is STUN !");
                    defender.setState(side);
                }
            case "disarmed":
                if (Objects.equals(defender.getName(), "VOLDEMORT")) {
                    avada = 0;
                }
                defender.setState("flinch");
                System.out.println("YOU cancel " + defender.getName() + "'s attack !");
        }
    }
}

