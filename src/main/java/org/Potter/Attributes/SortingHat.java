package org.Potter.Attributes;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SortingHat {
    public static House hufflepuff = new House("Poufsoufle", 1.5f, 1, 1, 1, "\u001B[33m");
    public static House slytherin = new House("Serpentard", 1, 1.5f, 1, 1, "\u001B[32m");
    public static House gryffindor = new House("Gryffondor", 1, 1, 1.5f, 1, "\u001B[31m");
    public static House ravenclaw = new House("Serdaigle", 1, 1, 1, 1.5f, "\u001B[34m");

    public static House sortHouse(Scanner scan) {
        House house = null;
        String colorReset = "\u001B[0m";
        System.out.println("Choipeau :\"Un de plus... Veux-tu choisir une maison précise (1), ou me fais-tu confiance (2)?\"");
        int choice = scan.nextInt();
        int goodchoice = 0;
        while (goodchoice == 0) {
            if (choice == 1) {
                System.out.println("Choipeau :\"Tu me déçois... enfin bon, laquelle veux-tu ?\"");
                int housechoice = 0;
                while (housechoice == 0) {
                    System.out.println("1) " + hufflepuff.getColor() + "Poufsoufle" + colorReset + " ; 2) " + slytherin.getColor() + "Serpentard" + colorReset + " ; 3) " + gryffindor.getColor() + "Gryffondor" + colorReset + " ; 4) " + ravenclaw.getColor() + "Serdaigle" + colorReset);
                    int housewanted = scan.nextInt();
                    if (housewanted == 1) {
                        house = hufflepuff;
                        housechoice = 1;
                    } else if (housewanted == 2) {
                        house = slytherin;
                        housechoice = 1;
                    } else if (housewanted == 3) {
                        house = gryffindor;
                        housechoice = 1;
                    } else if (housewanted == 4) {
                        house = ravenclaw;
                        housechoice = 1;
                    } else {
                        System.out.println("Choipeau :\"Non ! Si tu veux choisir, choisis bien !\"");
                    }
                }
                goodchoice = 1;
            } else if (choice == 2) {
                System.out.println("Choipeau :\"Quelqu'un de brave ! Donc ce sera ...\"");
                int housegave = ThreadLocalRandom.current().nextInt(1, 5);
                if (housegave == 1) {
                    house = hufflepuff;
                } else if (housegave == 2) {
                    house = slytherin;
                } else if (housegave == 3) {
                    house = gryffindor;
                } else if (housegave == 4) {
                    house = ravenclaw;
                }
                goodchoice = 1;
            } else {
                System.out.println("Choipeau :\"Essais encore...Veux-tu une maison précise (1), ou me fais-tu confiance (2)?\"");
            }
            System.out.println("Choipeau :\"" + house.getColor() + house.getName() + colorReset + "!\"");
        }
        return house;
    }
}
