package org.Potter;

import org.Potter.Actions.*;
import org.Potter.Attributes.*;
import org.Potter.Character.*;
import org.Potter.Character.Character;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Game {
    public void play() {
        System.out.println("                            +--------------------------+");
        System.out.println("                            | " + yellow.getColor() + "HARRY POTTER A LA MAISON" + resetColor.getColor() + " |");
        System.out.println("                            +--------------------------+");
        System.out.println();
        Wizard player = youAreAWizard();
        houseBuff(player);
        System.out.println();
        whoAmI(player);
        System.out.println("Veux-tu jouer avec " + player.getColor() + player.getFirstname() + " " + player.getLastname() + resetColor.getColor() + " ?");
        System.out.println("[1] pour OUI | n'importe quel autre touche NON");
        String input = "";
        while (Objects.equals(input, "")) {
            input = scan.nextLine();
        }
        if (Objects.equals(input, "1")) {
            System.out.println("Tape [Enter] pour commencer le jeu !");
            System.out.println("Bonne chance et amuse-toi !");
            waitInput(true);
            hundred();
        } else {
            hundred();
            play();
            System.exit(0);
        }
        addPotion(player, accPot, 1);
        chapterA(player);
        chapter1(player);
        chapter2(player);
        chapterB(player);
        chapter3(player);
        chapter4(player);
        chapter5(player);
        whoAmI(player);
    }

    private final Scanner scan = new Scanner(System.in);
    List<Potion> savePotion = new ArrayList<>();

    Color resetColor = new Color("\u001B[0m");
    Color black = new Color("\u001B[30m");
    Color red = new Color("\u001B[31m");
    Color green = new Color("\u001B[32m");
    Color yellow = new Color("\u001B[33m");
    Color blue = new Color("\u001B[34m");
    Color purple = new Color("\u001B[35m");
    Color cyan = new Color("\u001B[36m");
    Color white = new Color("\u001B[37m");

    Potion little = new Potion("PETITE POTION", 10, "");
    Potion mid = new Potion("POTION MOYENNE", 20, "");
    Potion perfect = new Potion("POTION PARFAITE", 100, "");
    Potion accPot = new Potion("POTION DE PRECISION", 0, "PRECISION");
    Potion defPot = new Potion("POTION DE DEFENCE", 0, "DEFENCE");

    Spell basic = new Spell("SORT DE BASE", "Frappe un ennemi", 4, 90, "");
    Spell incendio = new Spell("INCENDIO", "Brûle ton enemi (-2HP par tour)", 3, 80, "brûle");
    Spell stupefy = new Spell("STUPEFY", "Frappe un enemy qui a une chance sur deux d'être étouri", 6, 70, "étourdi");
    Spell sectumsempra = new Spell("SECTUMSEMPRA", "Lacère ton enemi", 15, 60, "");
    Spell expelliarmus = new Spell("EXPELLIARMUS", "Frappe un enemi et désarme-le", 8, 80, "désarmé");
    Spell sledgehammer = new Spell("SLEDGEHAMMER", "", 7, 75, "");
    Spell bite = new Spell("BITE", "", 8, 75, "");
    Spell soulstealing = new Spell("Vole d'âme", "", 2, 80, "heal1");
    Spell ratAtk = new Spell("Attaque de rat", "", 5, 75, "");
    Spell hellTest = new Spell("Goût de l'enfer", "", 15, 75, "");
    Spell dark = new Spell("Sort des ténèbres", "", 15, 75, "");
    Spell ultima = new Spell("Lumière ultime", "", 30, 80, "");
    ForbiddenSpell crucio = new ForbiddenSpell("CRUCIO", "Un sort interdit, as-tu vraiment envie d'en savoir plus ?", 20, 75, "");
    ForbiddenSpell avada = new ForbiddenSpell("AVADA KEDAVRA", "", 0, 100, "death");

    public void waitInput(boolean loud) {
        if (loud) {
            System.out.println(white.getColor() + "(presse [ENTER])" + resetColor.getColor());
        }
        String input = "HHBBGDGDBAStart";
        while (Objects.equals(input, "HHBBGDGDBAStart")) {
            input = scan.nextLine();
        }
    }

    public void hundred() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public Wizard youAreAWizard() {
        System.out.println("  -----------------------------");
        System.out.println("  Chapitre 0 - Tu es un sorcier");
        System.out.println("  -----------------------------");
        System.out.println("Tu as reçu une lettre de Poudlard, la fameuse école de sorcelerie ! \nQuel est ton prénom d'ailleurs ?");
        String firstname = scan.nextLine();
        System.out.println("Et quel est ton nom ?");
        String lastname = scan.nextLine();
        System.out.println("Bonjour " + firstname + " " + lastname + " ! \nTu as été invité à rejoindre Poudlard, tu peux choisir un animal de compagnie. \nLequel choisis-tu ?");
        Scanner scan = new Scanner(System.in);
        Pet pet = null;
        while (pet == null) {
            System.out.println("Choisissez un animal parmi hibou, chat, rat, chien, crapaud (ou 0 pour aléatoire) : ");
            String petName = scan.nextLine().toLowerCase();
            if (petName.equals("0")) {
                pet = Pet.getRandomPet();
                System.out.println("Vous avez choisi l'animal : " + pet);
            } else {
                try {
                    int petIndex = Integer.parseInt(petName) - 1;
                    if (petIndex >= 0 && petIndex < Pet.values().length) {
                        pet = Pet.values()[petIndex];
                        System.out.println("Vous avez choisi l'animal : " + pet);
                    } else {
                        System.out.println("Veuillez choisir un numéro d'animal valide ou 0 pour aléatoire");
                    }
                } catch (NumberFormatException e) {
                    try {
                        pet = Pet.valueOf(petName);
                        System.out.println("Vous avez choisi l'animal : " + pet);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Veuillez choisir un animal valide ou 0 pour aléatoire");
                    }
                }
            }
        }
        System.out.println("Tu entres dans le magasin de baguettes d'Ollivander !");
        System.out.println("Choisis la taille de ta baguette (en centimètres) : ");
        int size = 0;
        while (size < 15 || size > 30) {
            size = scan.nextInt();
            if (size < 15 || size > 30) {
                System.out.println("La taille doit être entre 15 et 30 centimètres. Réessaye : ");
            }
        }
        Core[] validCores = Core.values();
        System.out.println("Choisis le type de coeur de ta baguette :");
        for (int i = 0; i < validCores.length; i++) {
            System.out.println(i + 1 + ". " + validCores[i].toString());
        }
        System.out.print("Entrez le numéro correspondant au coeur de votre choix : ");
        Core core = null;
        while (core == null) {
            try {
                int coreIndex = scan.nextInt();
                scan.nextLine(); // consomme la ligne vide
                core = validCores[coreIndex - 1];
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.print("Coeur invalide. Entrez un numéro valide : ");
                scan.nextLine(); // consomme l'entrée invalide
            }
        }
        System.out.println("Tu as choisi une baguette avec un coeur de " + core + ".");
        Wand wand = new Wand(size, core);
        System.out.println("Tu as maintenant une baguette de " + wand.getSize() + " centimètres, avec un coeur de " + wand.getCore() + " !");
        System.out.println();
        House house = SortingHat.sortHouse(scan);
        return new Wizard(firstname, lastname, pet, wand, house);
    }

    public void whoAmI(Wizard player) {
        System.out.println(player.getColor() + "--------------------INFORMATION JOUEUR---------------------------" + resetColor.getColor());
        System.out.println("Tu es" + player.getColor() + player.getFirstname() + " " + player.getLastname() + resetColor.getColor() + ".");
        System.out.println("Tu es un étudiant de " + player.getColor() + player.getHouse().getName() + resetColor.getColor() + " et tu as un adorable " + player.getPet() + ".");
        System.out.println("Ta baguette fait " + player.getWand().getSize() + " centimètres, et est fait de " + player.getWand().getCore() + ".");
        System.out.println("Tu es niveau " + player.getLevel() + ". Tu as " + player.getHp() + "/" + player.getMaxHp() + " HP.");
        System.out.println("STATISTIQUES : ATTAQUE = x" + player.getDamageMul() + " ; DEFENCE = x" + player.getTakenMul() + " ; PRECISION = x" + player.getAccuracyMul() + "; HP = x" + player.getHeal());
        System.out.println(player.getColor() + "-----------------------------------------------------------------" + resetColor.getColor());
    }

    public void addSpell(Wizard player, AbstractSpell spell) {
        List<AbstractSpell> SpList = player.getKnownSpells();
        for (AbstractSpell value : SpList) {
            if (Objects.equals(value.getName(), spell.getName())) {
                return;
            }
        }
        SpList.add(spell);
        player.setKnownSpells(SpList);
        System.out.print(cyan.getColor());
        if (spell.getClass() == ForbiddenSpell.class) {
            System.out.print(red.getColor());
        }
        System.out.println("=> Tu as appris le sort " + spell.getName() + " !");
        System.out.println(spell.getName() + ", DEGAT : " + spell.getDamage() + ", PRECISION : " + spell.getAccuracy() + ", " + spell.getDescription() + resetColor.getColor());
    }

    public void addPotion(Wizard player, Potion potion, int uses) {
        List<Potion> potList = player.getPotions();
        System.out.println(green.getColor() + "=> Tu as obtenu " + uses + " potion : " + potion.getName() + " !");
        String boost = potion.getBoost();
        for (Potion value : potList) {
            if (Objects.equals(value.getName(), potion.getName())) {
                value.setUses(value.getUses() + uses);
                if (Objects.equals(boost, "")) {
                    System.out.println(value.getName() + ", HP : " + value.getHeal() + ", RESTANT  : " + value.getUses() + resetColor.getColor());
                } else {
                    System.out.println(value.getName() + ", Boost de : " + boost + "(+0.5), RESTANT: " + value.getUses() + resetColor.getColor());
                }
                return;
            }
        }
        potion.setUses(uses);
        potList.add(potion);
        player.setPotions(potList);
        if (Objects.equals(boost, "")) {
            System.out.println("NOUVELLE POTION ! " + potion.getName() + ", HP : " + potion.getHeal() + ", RESTANT : " + potion.getUses() + resetColor.getColor());
        } else {
            System.out.println("NOUVELLE POTION ! " + potion.getName() + ", Boost de : " + boost + "(+0.5), RESTANT : " + potion.getUses() + resetColor.getColor());
        }
    }

    public void listSpell(Wizard player) {
        System.out.println("-LISTE DES SORTS :");
        List<AbstractSpell> SpList = player.getKnownSpells();
        for (int i = 0; i < SpList.size(); i++) {
            AbstractSpell sp = SpList.get(i);
            int num = i + 1;
            int damage = sp.getDamage();
            float multiplier = player.getDamageMul();
            damage = (int) Math.floor(damage * multiplier);
            int accuracy = sp.getAccuracy();
            float accMul = player.getAccuracyMul();
            accuracy = (int) Math.min(Math.ceil(accuracy * accMul), 100);
            if (sp.getClass() == ForbiddenSpell.class) {
                System.out.print("\u001B[40m" + red.getColor());
            }
            System.out.printf(num + ") " + sp.getName() + ", DEGAT : " + sp.getDamage());
            if (sp.getDamage() != damage) {
                System.out.printf("(" + damage + ")");
            }
            System.out.printf(" , PRECISION : " + sp.getAccuracy());
            if (sp.getAccuracy() != accuracy) {
                System.out.printf("(" + accuracy + ")");
            }
            System.out.println(" , " + sp.getDescription() + resetColor.getColor());
        }
    }

    public void listPotions(Wizard player) {
        System.out.println("-LISTS DES POTIONS :");
        List<Potion> potList = player.getPotions();
        for (int i = 0; i < potList.size(); i++) {
            Potion potion = potList.get(i);
            int num = i + 1;
            int heal = potion.getHeal();
            float multiplier = player.getHeal();
            heal = (int) Math.floor(heal * multiplier);
            System.out.print(num + ") " + potion.getName());
            String boost = potion.getBoost();
            if (Objects.equals(boost, "")) {
                System.out.print("");
                System.out.printf(", ¨HP : " + potion.getHeal());
                if (potion.getHeal() != heal) {
                    System.out.printf("(" + heal + ")");
                }
            } else {
                System.out.printf(", Boost de : " + boost + "(+0.5)");
            }
            System.out.printf(", RESTANT : " + potion.getUses());
            System.out.println();
        }
    }

    public void win(Wizard player, List<Float> stats) {
        System.out.println(yellow.getColor() + "BRAVO !");
        waitInput(false);
        waitInput(true);
        resetStats(player, stats);
        whoAmI(player);
        player.setLevel(player.getLevel() + 1);
        System.out.println("=> Tu es maintenant niveau " + player.getLevel() + " !");
        System.out.println("=> Veux-tu augmenter ta vie (1) ou tes dégats (2) ?");
        int input = scan.nextInt();
        if (input == 1) {
            player.setMaxHp(player.getMaxHp() + 5);
            System.out.println("=> Tu as mainentant " + player.getMaxHp() + " HP !" + resetColor.getColor());
        } else if (input == 2) {
            player.setDamageMul((float) (Math.round((player.getDamageMul() + 0.2f) * 10.0) / 10.0));
            System.out.println("=> Tu as une attaque de  " + player.getDamageMul() + " !" + resetColor.getColor());
        }
        player.setHp(player.getMaxHp());
        player.setState("");
        player.setLuck(75);
    }

    public boolean death(Wizard player, List<Float> stats) {
        player.setHp(0);
        resetStats(player, stats);
        System.out.println(red.getColor() + "Bah, tu es nul ?");
        waitInput(false);
        System.out.println("Veux-tu réessayer ? (0/N)" + resetColor.getColor());
        String input = "HHBBGDGDBAStart";
        while (Objects.equals(input, "HHBBGDGDBAStart")) {
            input = scan.nextLine();
        }
        if (Objects.equals(input, "O")) {
            hundred();
            player.setAlive(true);
            player.setHp(player.getMaxHp());
            player.setState("");
            player.setLuck(75);
            return true;
        } else if (Objects.equals(input, "N")) {
            hundred();
            System.out.println("                      " + "\u001B[40m" + red.getColor() + " GAME OVER " + resetColor.getColor());
            System.out.println();
            whoAmI(player);
            System.exit(0);
        }
        return false;
    }

    public boolean enemyAlive(List<AbstractEnemy> enemyList) {
        for (AbstractEnemy enemy : enemyList) {
            if (enemy.getClass() == Boss.class) {
                if (!enemy.isAlive()) {
                    return false;
                }
            }
            if (enemy.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public void houseBuff(Wizard player) {
        House house = player.getHouse();
        if (house == SortingHat.hufflepuff) {
            System.out.println("(Tu as un boost de vie)");
        } else if (house == SortingHat.slytherin) {
            System.out.println("(Tu as un boost d'attaque)");
        } else if (house == SortingHat.gryffindor) {
            System.out.println("(Tu as un boost de défence)");
        } else if (house == SortingHat.ravenclaw) {
            System.out.println("(Tu as un boost de précision)");
        }
    }

    public Color lvlHp(Character player) {
        int hp = player.getHp();
        int maxHp = player.getMaxHp();
        Color color = null;
        if (hp >= (maxHp / 2)) {
            color = green;
        } else if (hp >= (maxHp / 4)) {
            color = yellow;
        } else {
            color = red;
        }
        return color;
    }

    public void sideHP(Character character) {
        if (Objects.equals(character.getState(), "brûle")) {
            System.out.printf(red.getColor() + " [BRULE]" + resetColor.getColor());
        }
    }

    public void health(Character player) {
        int hp = player.getHp();
        int maxHp = player.getMaxHp();
        String name = player.getName();
        Color color = lvlHp(player);
        System.out.printf(name + " : " + color.getColor() + hp + "/" + maxHp + " HP" + resetColor.getColor());
        sideHP(player);
        System.out.print(" ; ");
    }

    public void healthBar(Character character) {
        int hp = character.getHp();
        int maxHp = character.getMaxHp();
        String name = character.getName();
        Color color = lvlHp(character);
        System.out.printf(character.getColor() + name + resetColor.getColor() + " : ");
        int life = 0;
        int taken = 10;
        int hpbar = hp * 100 / maxHp;
        while (life < hpbar) {
            System.out.printf(color.getColor() + "=" + resetColor.getColor());
            life += 10;
            taken -= 1;
        }
        for (int i = 0; i < taken; i++) {
            System.out.print("=");
        }
        System.out.print(" " + color.getColor() + hp + "/" + maxHp + " HP" + resetColor.getColor());
        sideHP(character);
    }

    public void activeSideEffect(Character character) {
        if (Objects.equals(character.getState(), "brûle")) {
            character.setHp(character.getHp() - 2);
            System.out.println(character.getName() + " à pris des dégats " + red.getColor() + "BRULE" + resetColor.getColor() + " !");
            if (character.getHp() <= 0) {
                character.setAlive(false);
                System.out.println(character.getName() + " est mort !");
            } else {
                System.out.println(character.getName() + " a " + character.getHp() + "HP restant.");
            }
        }
        if (Objects.equals(character.getState(), "heal1")) {
            int hpBefore = character.getHp();
            character.setHp(Math.min(character.getHp() + 1, character.getMaxHp()));
            System.out.printf("Ca soigne " + character.getName() + " : ");
            if (character.getHp() != hpBefore) {
                System.out.println(character.getName() + " a maintenant " + character.getHp() + " HP restant.");
            } else {
                System.out.println(character.getName() + " a déjà tous ses HP.");
            }
            character.setState("");
        }
    }

    public List<Float> saveStats(Character player) {
        List<Float> stats = new ArrayList<>();
        stats.add(player.getDamageMul());
        stats.add(player.getTakenMul());
        stats.add(player.getAccuracyMul());
        stats.add(player.getHeal());
        return stats;
    }

    public void resetStats(Character player, List<Float> stats) {
        player.setDamageMul(stats.get(0));
        player.setTakenMul(stats.get(1));
        player.setAccuracyMul(stats.get(2));
        player.setHeal(stats.get(3));
    }

    public void specialChoice(Character player, Special special, int turn) {
        switch (special.getChapter()) {
            case "1" -> {
                float multiplier1 = player.getDamageMul();
                int damage1 = (int) Math.floor(15 * multiplier1);
                System.out.print(blue.getColor() + "WINGARDIUM LEVIOSA" + resetColor.getColor() + " : Fait léviter un évier et le fait retomber sur le troll ! (prend deux tours) , DEGATS: 15");
                if (15 != damage1) {
                    System.out.printf("(" + damage1 + ")");
                }
            }
            case "2" -> {
                int distance = 20;
                distance = distance - 5 * special.getNumberUse();
                if (distance != 0) {
                    System.out.print("ACCIO : Apporte une DENT DE BASILIC plus proche du journal de T. M. Riddle (C'est à " + red.getColor() + distance + resetColor.getColor() + " mètres de distance)");
                } else {
                    System.out.println(green.getColor() + "DENT DE BASILIC" + resetColor.getColor() + " : Détruit le jounrla de T. M. Riddle !");
                }
            }
            case "2G" -> {
                float multiplier2 = player.getDamageMul();
                int damage2 = (int) Math.floor(20 * multiplier2);
                System.out.print(red.getColor() + "EPEE DE GRYFFONDOR" + resetColor.getColor() + " : Utilise cette épee légenaire pour te défendre ! , DEGATS : 20");
                if (20 != damage2) {
                    System.out.printf("(" + damage2 + ")");
                }
            }
            case "3" -> {
                if (special.getNumberUse() == 0) {
                    System.out.print("EXPECTO... ? : Entraine toi à utiliser ton PATRONUS ! Cela t'aidera à combattre les DETRAQUEURS " + blue.getColor() + "[2 de plus]" + resetColor.getColor());
                } else if ((special.getNumberUse() == 1)) {
                    System.out.print("EXPECTO PATRO... ? : Entraine toi à utiliser ton PATRONUS ! Cela t'aidera surement à combattre les DETRAQUEURS " + blue.getColor() + "[1 de plus]" + resetColor.getColor());
                } else {
                    float multiplier3 = player.getDamageMul();
                    int damage3 = (int) Math.floor(6 * multiplier3);
                    float accMul = player.getAccuracyMul();
                    int accuracy3 = (int) Math.min(Math.ceil(75 * accMul), 100);
                    System.out.print(blue.getColor() + "EXPECTO PATRONUM" + resetColor.getColor() + " : Utilise ton PATRONUS pour te défendre des DETRAQEURS ! , DEGATS : 6");
                    if (6 != damage3) {
                        System.out.print("(" + damage3 + ")");
                    }
                    System.out.print(" , PRECISION : 75");
                    if (75 != accuracy3) {
                        System.out.printf("(" + accuracy3 + ")");
                    }
                    System.out.print(blue.getColor() + " [POUR TOUS LES ENEMIS]" + resetColor.getColor());

                }
            }
            case "4" -> {
                int distance = 25;
                distance = distance - 5 * special.getNumberUse();
                if (distance != 0) {
                    System.out.print("ACCIO : Tu n'as aucunes chances ! Apport la CLE pour t'envoler ! (C'est à " + red.getColor() + distance + resetColor.getColor() + " mètres de distance)");
                } else {
                    System.out.println(yellow.getColor() + "CLE" + resetColor.getColor() + " : S'ENFUIE DE CET ENDROIT !");
                }
            }
            case "5" -> {
                float multiplier2 = player.getDamageMul();
                int damage5 = (int) Math.floor(15 * multiplier2);
                float accMul = player.getAccuracyMul();
                int accuracy5 = (int) Math.min(Math.ceil(75 * accMul), 100);
                System.out.print(red.getColor() + "FEU D'ARTIFICE" + resetColor.getColor() + " : Utilise ces FEU D'ARTIFICE pour faire reigner le désordre ! , DEGAT : 15");
                if (20 != damage5) {
                    System.out.printf("(" + damage5 + ")");
                }
                System.out.print(" , PRECISION : 75");
                if (75 != accuracy5) {
                    System.out.printf("(" + accuracy5 + ")");
                }
            }
        }
        System.out.println();
    }

    public void specialAction(Wizard player, Special special, int turn, List<AbstractEnemy> enemyList) {
        switch (special.getChapter()) {
            case "1" -> {
                player.setState("passer");
                System.out.println("Tu as fait léviter l'évier !");
            }
            case "2" -> {
                int distance = 20;
                distance = distance - 5 * special.getNumberUse();
                if (distance != 0) {
                    System.out.println("Tu utilises ACCIO sur DENT DE BASILIC !");
                    distance = distance - 5;
                    if (distance != 0) {
                        System.out.println("C'est maintenant à " + red.getColor() + distance + resetColor.getColor() + " mètres de distance !");
                    } else {
                        System.out.println(green.getColor() + "Tu as réussi à l'attraper !\nTu peux maintenant l'utiliser !" + resetColor.getColor());
                    }
                    special.setNumberUse(special.getNumberUse() + 1);
                } else {
                    enemyList.get(0).setAlive(false);
                    System.out.println("Tu as détruit le journal de T. M. Jedusor !");
                    System.out.println("Le BASILIC n'est plus contrôler par le fantôme.");
                    System.out.println(green.getColor() + "Le " + green.getColor() + "BASILIC" + resetColor.getColor() + " s'en va !" + resetColor.getColor());
                }
            }
            case "2G" -> {
                Spell sword = new Spell(red.getColor() + "EPEE DE GRYFFONDOR" + resetColor.getColor(), "", 20, 100, "");
                player.attack(sword, player, enemyList.get(0));
            }
            case "3" -> {
                if (special.getNumberUse() == 0) {
                    System.out.println("Tu essais de faire un PATRONUS.");
                    System.out.println("Mais rien ne se produit...");
                    waitInput(false);
                    waitInput(true);
                } else if ((special.getNumberUse() == 1)) {
                    System.out.println("Tu essais de faire un PATRONUS.");
                    System.out.println("Un petit peu de fumée sort de ta baguette.");
                    waitInput(false);
                    waitInput(true);
                } else {
                    if ((special.getNumberUse() == 2)) {
                        System.out.println("Tu essais de faire un PATRONUS.");
                        System.out.println("Tu vois un majestueux " + blue.getColor() + player.getPet() + resetColor.getColor() + " sortir de ta baguette !!");
                        waitInput(false);
                        waitInput(true);
                    }
                    Spell patronum = new Spell("EXPECTO PATRONUM", "", 6, 75, "");
                    player.multiattack(patronum, player, enemyList);
                }
                special.setNumberUse(special.getNumberUse() + 1);
            }
            case "4" -> {
                int distance = 25;
                distance = distance - 5 * special.getNumberUse();
                if (distance != 0) {
                    System.out.println("Tu utilises ACCIO sur CLE !");
                    distance = distance - 5;
                    if (distance != 0) {
                        System.out.println("C'est maintenant à " + red.getColor() + distance + resetColor.getColor() + " mètres de distance !");
                    } else {
                        System.out.println(green.getColor() + "Tu as réussi à l'attraper !\nTu peux maintenant l'utiliser !" + resetColor.getColor());
                    }
                    special.setNumberUse(special.getNumberUse() + 1);
                } else {
                    enemyList.get(0).setAlive(false);
                    enemyList.get(0).setAlive(false);
                    System.out.println("Tu utilises la CLE !");
                    System.out.println("Tu utilise la CLE, tu as réussi à échapper à une mort horrible !");
                }

            }
            case "5" -> {
                Spell fireworks = new Spell(red.getColor() + "FEU D'ARTIFICE" + resetColor.getColor(), "", 15, 75, "brûle");
                player.attack(fireworks, player, enemyList.get(0));
            }
        }
        System.out.println();
    }

    public int turn(Wizard player, List<AbstractEnemy> enemyList, int turnNum, Special special, int luck) {
        System.out.println("___________________________");
        System.out.println("  TOUR " + turnNum + " :");
        healthBar(player);
        System.out.println();
        boolean bossOrNot = false;
        for (AbstractEnemy value : enemyList) {
            if (bossOrNot) {
                System.out.print(" | ");
                bossOrNot = false;
            }
            if (value.getClass() == Boss.class) {
                healthBar(value);
                bossOrNot = true;
            } else if (value.isAlive()) {
                health(value);
            } else {
                System.out.printf(value.getName() + " : " + red.getColor() + "MORT" + resetColor.getColor() + " ; ");
            }
        }
        System.out.println();
        System.out.println("----------");
        boolean action = false;
        boolean enemyplay = true;
        boolean stun = true;
        while (!action & !Objects.equals(player.getState(), "passer")) {
            stun = false;
            System.out.println("1) SORTS \n2) POTIONS \n3) PROTECTION (" + luck + "%) \n4) INFORMATIONS DU JOUEUR");
            if (special != null) {
                System.out.print("5) ");
                specialChoice(player, special, turnNum);
            }
            int inputAction = scan.nextInt();
            if (inputAction == 1) {
                System.out.println("---------");
                System.out.println("0) RETOUR");
                listSpell(player);
                int inputSpell = scan.nextInt();
                if (inputSpell != 0 & inputSpell <= player.getKnownSpells().size()) {
                    AbstractSpell spellUsed = player.getKnownSpells().get(inputSpell - 1);
                    System.out.println("---------");
                    System.out.println("-CIBLE ?");
                    for (int i = 0; i < enemyList.size(); i++) {
                        int num = i + 1;
                        System.out.printf(num + ") " + enemyList.get(i).getName() + " ; ");
                    }
                    System.out.println();
                    System.out.println("0) RETOUR");
                    int inputEnemy = scan.nextInt();
                    if (inputEnemy != 0 & inputEnemy <= enemyList.size()) {
                        Character enemyAttacked = enemyList.get(inputEnemy - 1);
                        player.attack(spellUsed, player, enemyAttacked);
                        player.setLuck(75);
                        action = true;
                        System.out.println();
                    }
                }
            } else if (inputAction == 2) {
                System.out.println("---------");
                System.out.println("0) RETOUR");
                listPotions(player);
                int inputPot = scan.nextInt();
                if (inputPot != 0 & inputPot <= player.getPotions().size()) {
                    Potion potionUsed = player.getPotions().get(inputPot - 1);
                    potionUsed.healing(player, potionUsed);
                    player.setLuck(75);
                    action = true;
                    System.out.println();
                }
            } else if (inputAction == 3) {
                System.out.println();
                enemyplay = Wizard.defend(luck);
                if (!enemyplay) {
                    player.setLuck(player.getLuck() - 25);
                } else {
                    player.setLuck(75);
                }
                action = true;
            } else if (inputAction == 4) {
                whoAmI(player);
            } else if (inputAction == 5 & special != null) {
                specialAction(player, special, turnNum, enemyList);
                action = true;
            } else {
                turn(player, enemyList, turnNum, special, luck);
            }
        }
        if (stun & Objects.equals(player.getState(), "passer")) {
            if (special != null) {
                if (Objects.equals(special.getChapter(), "1")) {
                    float multiplier = player.getDamageMul();
                    int damage = (int) Math.floor(15 * multiplier);
                    AbstractEnemy troll = enemyList.get(0);
                    troll.setHp(troll.getHp() - damage);
                    System.out.println("Tu relâches l'évier sur la tête du TROLL !");
                    if (troll.getHp() > 0) {
                        System.out.println(troll.getName() + " a " + troll.getHp() + " HP restant.");
                    } else {
                        System.out.println("Tu as vaincu le TROLL !");
                        troll.setAlive(false);
                    }
                    System.out.println();

                }
            }
            player.setState("");
        }

        for (AbstractEnemy enemy : enemyList) {
            if (enemy.isAlive()) {
                if (enemyplay) {
                    if (!Objects.equals(enemy.getState(), "étourdi")) {
                        enemy.attack(enemy.getAction(), enemy, player);
                    }
                } else {
                    System.out.println(enemy.getName() + " est incapable d'agir !");
                }
                activeSideEffect(enemy);
                System.out.println();
                if (Objects.equals(enemy.getState(), "étourdi")) {
                    enemy.setState("");
                }
            }
        }
        System.out.println();
        return turnNum + 1;
    }

    public void chapterA(Wizard player) {
        System.out.println("  ------------------------------");
        System.out.println("  LECON 1 - Premier entrainement");
        System.out.println("  ------------------------------");
        System.out.println("Prof. Quirrell :\"Bonjour à toi ! ... !\"");
        System.out.println("Prof. Quirrell :\"Aujourd'hui, je vais vous apprendre le SORT DE BASE.\"");
        System.out.println("Prof. Quirrell :\"Essaie sur ce NPC !\"");
        System.out.println();
        addSpell(player, basic);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Enemy("NPC", 15, basic));
        Special special = null;
        System.out.println("Un SORT est défini par ses dégats (DEGATS), sa précision (PRECISION) et sa description.");
        System.out.println("Si tes STATISTIQUES d'ATTAQUE ou de PRECISION sont augmentés, tu verras un nouveau nombre dans les parenthèses,\nce sont les DEGATS et la PRECISION réels.");
        System.out.println();
        waitInput(true);
        System.out.println("  MENU DE COMBAT :");
        System.out.println("Dans ce menu MENU, tu peux ouvrir ta liste de SORTS (1) pour les utiliser sur les ennemis, \ntu peux ouvrir la liste des POTIONS (2) et utiliser l'une d'elle pour te soigner,");
        System.out.println("tu peux utiliser PROTECTION (3) qui annule toutes les attaques ennemis \n(la probabilité de succes est montrée dans les parenthèses mais décroit si PROTECTION a été utilisé au tour précedent).");
        System.out.println("You can see your PLAYER INFORMATIONS with PLAYER INFO (4).");
        System.out.println("Maintanant, bat ce NPC !");
        System.out.println();
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
            if (turnNum == 2) {
                System.out.println("___________________________");
                addPotion(player, little, 1);
                System.out.println();
                System.out.println("Tu as une PETITE POTION ! Cela peux te soigner 10 HP ! \nUtilise-la quand tu es en danger.\n");
                waitInput(false);
                waitInput(true);
            }
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapterA(player);
        } else {
            win(player, stats);
            System.out.println();
            System.out.println("C'est la fin de cette lesson !");
            System.out.println();
            System.out.println("INFORMATION : Qu'une attaque fasse des DEGATS CRITIQUES ! \nDans ce cas, les DEGATS sont multipliés par 1,5 !");
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter1(Wizard player) {
        System.out.println("  ------------------------------------");
        System.out.println("  Chapitre I - Les toilettes du donjon");
        System.out.println("  ------------------------------------");
        System.out.println();
        addPotion(player, little, 1);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("TROLL", 40, blue.getColor(), sledgehammer));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            special = new Special("1", turnNum);
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter1(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter2(Wizard player) {
        System.out.println("  ------------------------------------");
        System.out.println("  Chapitre II - La chambre des secrets");
        System.out.println("  ------------------------------------");
        System.out.println();
        addSpell(player, incendio);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("BASILIC", 60, green.getColor(), bite));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            if (Objects.equals(player.getHouse().getName(), "Gryffondor")) {
                if (turnNum >= 3) {
                    special = new Special("2G", turnNum);
                }
            } else {
                int numUse = 0;
                if (turnNum != 1) {
                    assert special != null;
                    numUse = special.getNumberUse();
                }
                special = new Special("2", turnNum);
                special.setNumberUse(numUse);
            }
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter2(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapterB(Wizard player) {
        System.out.println("  -----------------------------------");
        System.out.println("  LECON 2 - ENTRAINEMENT AVEC DES NPC");
        System.out.println("  -----------------------------------");
        System.out.println();
        addSpell(player, stupefy);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Enemy("NPC", 15, basic));
        enemyList.add(new Enemy("NPC", 15, basic));
        enemyList.add(new Enemy("NPC", 15, basic));
        Special special = null;
        System.out.println();
        waitInput(true);
        System.out.println();
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapterB(player);
        } else {
            win(player, stats);
            System.out.println();
            System.out.println("C'est la fin de cette leçon !");
            System.out.println();
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter3(Wizard player) {
        System.out.println("  -------------------------------------");
        System.out.println("  Chapitre III - Le prisonier d'Azkaban");
        System.out.println("  -------------------------------------");
        System.out.println();
        addPotion(player, mid, 2);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        for (int i = 1; i <= 5; i++) {
            enemyList.add(new Enemy("DETRAQUEUR " + i, 15, soulstealing));
        }
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            int numUse = 0;
            if (turnNum != 1) {
                numUse = special.getNumberUse();
            }
            special = new Special("3", turnNum);
            special.setNumberUse(numUse);
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter3(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter4(Wizard player) {
        System.out.println("  -----------------------------");
        System.out.println("  Chapitre IV - La coupe de feu");
        System.out.println("  -----------------------------");
        System.out.println();
        addPotion(player, defPot, 1);
        addPotion(player, accPot, 1);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("VOLDEMORT", 100, "\u001B[40m" + white.getColor(), crucio));
        enemyList.get(0).setDamageMul(0.75f);
        enemyList.add(new Enemy("PETTER PETTIGROW", 50, ratAtk));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            int numUse = 0;
            if (turnNum != 1) {
                numUse = special.getNumberUse();
            }
            special = new Special("4", turnNum);
            special.setNumberUse(numUse);
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter4(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter5(Wizard player) {
        System.out.println("  -------------------------------");
        System.out.println("  Chapitre V - L'ordre du Phoenix");
        System.out.println("  -------------------------------");
        System.out.println();
        addPotion(player, mid, 2);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("OMBRAGE", 75, purple.getColor(), hellTest));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            int numUse = 0;
            if (turnNum >= 5) {
                special = new Special("5", turnNum);
            }
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter5(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public boolean chapter6(Wizard player) {
        System.out.println("  --------------------------------");
        System.out.println("  Chapitre VI - L'ordre du Phoénix");
        System.out.println("  --------------------------------");
        System.out.println();
        addPotion(player, mid, 2);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Enemy("MANGEMORT", 25, dark));
        enemyList.add(new Enemy("MANGEMORT", 25, dark));
        enemyList.add(new Enemy("MANGEMORT", 25, dark));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter5(player);
        } else {
            player.setHp(player.getMaxHp());
            player.setState("");
            player.setLuck(75);
            resetStats(player, stats);
            int input = 1;
            if (Objects.equals(player.getHouse().getName(), "Serpentard")) {
                System.out.println("Bon (1) ou mauvais (2) ?");
                input = scan.nextInt();
            }
            if (input == 1) {
                return false;
            } else if (input == 2) {
                return true;
            }
        }
        return false;
    }

    public void chapter62(Wizard player) {
        addPotion(player, perfect, 1);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("SNAPE", 75, black.getColor(), sectumsempra));
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            chapter62(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }

    public void chapter6S(Wizard player) {
        addSpell(player, crucio);
        addPotion(player, perfect, 2);
        List<AbstractEnemy> enemyList = new ArrayList<>();
        System.out.println();
        enemyList.add(new Boss("DUMBLEDORE", 100, "\u001B[44m" + white.getColor(), ultima));
        enemyList.get(0).setTakenMul(1.5f);
        Special special = null;
        System.out.println();
        waitInput(true);
        List<Float> stats = saveStats(player);
        int turnNum = 1;
        while (player.isAlive() & enemyAlive(enemyList)) {
            turnNum = turn(player, enemyList, turnNum, special, player.getLuck());
        }
        if (!player.isAlive()) {
            boolean retry = false;
            while (!retry) {
                retry = death(player, stats);
            }
            //chapter7S(player);
        } else {
            win(player, stats);
            waitInput(false);
            waitInput(true);
            hundred();
        }
    }
}

