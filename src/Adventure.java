import java.util.*; // Wildcard import of the java.util package

/**
 * This program is a text-based adventure game. The player fights through a dungeon with key inputs.
 * The program is made of 5 classes. The "Adventure" class contains the main method and static methods
 * for generating characters, encounters, combat, loot, etc. The remaining 4 classes are used to
 * construct objects for manipulation in the main class.
 */
public class Adventure {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int stepsTaken = 0; // The progression of the game is based on the steps taken

        Character character = new Character(); // Declare and create a Character object using the no arg constructor
        Inventory inventory = new Inventory(); // Declare and create a new Inventory object using the default constructor
        Monster finalBoss = new Monster(200, 200, 35, 30, 20, "final boss"); // Declare and create a new Monster object

        System.out.println("Welcome to Generic Text-based Adventure Game 5000\n");

        int answer = -1;

        // This is the "main menu" loop of the game
        while (answer != 1) {
            System.out.printf("%-20s%-20s\n%-20s\n", "Help (0)", "Start a new adventure (1)", "Exit game (2)"); // Using printf to left justify and create the same width for each string. An escape sequences is used to make linefeeds
            answer = input.nextInt();

            switch (answer) { // Using a switch statement to make selections based on the user's input
                case 0:
                    printHelp(); // Call the printHelp method
                    break;
                case 1:
                    System.out.println("Create your character");
                    character = characterCreator(); // Create a custom Character object
                    break;
                case 2:
                    System.out.println("Bye-bye");
                    System.exit(1); // Exit program
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        long timerStart = System.currentTimeMillis(); // Starting point for a game timer using the currentTimeMillis() method from the System class

        System.out.println("As you walk along a grassy path, enjoying a nice peach, some dude comes up behind you and slaps the peach right out of your hand.");
        System.out.println("\"Suck on that!\" he yells as he runs into a dark cave. You decide to pursue the dude.");

        // This loop decides when the game ends. The game keeps going until the player or the final boss is dead or until the player quits the game
        while (character.getHealthCurrent() > 0 && finalBoss.getHealthCurrent() > 0) {
            System.out.printf("\n%-25s%-25s\n%-25s%-25s\n", "Move forward (0)", "Inventory (1)", "Statistics (2)", "Exit game (3)");
            answer = input.nextInt();

            switch (answer) {
                case 0:
                    ++stepsTaken; // When the player takes a step, the stepsTaken variable is incremented by 1 by the increment operator
                    System.out.println("Steps taken: " + stepsTaken);
                    encounterGenerator(stepsTaken, character, inventory, finalBoss); // The encounterGenerator method is invoked with 4 actual parameters. This method decides what happens after a step is taken.
                    break;
                case 1:
                    int answer2 = -1;
                    while (answer2 != 3) {
                        System.out.printf("\n%-25s%-25s\n%-25s%-25s\n", "Equipment and gold (0)", "Consumables (1)", "Use potion (2)", "Back (3)");
                        answer2 = input.nextInt();
                        switch (answer2) {
                            case 0:
                                inventory.printInventory();
                                break;
                            case 1:
                                inventory.printConsumables();
                                break;
                            case 2:
                                usePotion(character, inventory);
                                break;
                            case 3: // Case 3 has to be added, otherwise the default case will be run when 3 is entered
                                break;
                            default:
                                System.out.println("Invalid input.");
                        }
                    }
                    break;
                case 2:
                    character.getStats();
                    break;
                case 3:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid input.");
            }

            if (character.getHealthCurrent() <= 0)
                System.out.println("Game over.");
            if (finalBoss.getHealthCurrent() <= 0)
                System.out.println("\nCongratulations, " + character.getName() + ", the level " + character.getLevel() + " " + character.getGender() + " " + character.getRace() + " " + character.getClassPick() + " you killed a big baby.\nHe didn't even slap your peach or anything. You win the game though, baby murderer.");
        }

        long timerStop = System.currentTimeMillis(); // End point of the timer

        timer(timerStart, timerStop); // Displays the timer after the game is over
    }

    private static void printHelp() {
        System.out.println("Don't enter any letters when you're asked to enter numbers.\nWhen you want to use or buy potions, you first need to open \nyour or the merchant's inventory, respectively, and take \nnote of the index number of the potion\n");
    }

    // This method is used for generating player characters
    private static Character characterCreator() {
        Scanner input = new Scanner(System.in);

        System.out.print("Pick a name: ");
        String name = nameCaseFixer(input.nextLine().trim()); // Calls the nameCaseFixer method with the String input as the actual parameter. Any whitespace characters at either end of the input are removed with the trim() method from the String class

        System.out.print("Available genders: ");
        for (int i = 0; i < Character.genderList.length; i++) {
            if (i != Character.genderList.length - 1)
                System.out.print(Character.genderList[i] + ", ");
            else
                System.out.print(Character.genderList[i]);
        }

        boolean approvedGender = false;
        String gender = "";
        while (!approvedGender) {
            System.out.print("\nPick a gender: ");
            gender = input.nextLine().toLowerCase();

            for (int i = 0; i < Character.genderList.length; i++) {
                if (gender.equals(Character.genderList[i])) {
                    approvedGender = true;
                    break;
                }
            }
            if (!approvedGender)
                System.out.println("Invalid gender.");
        }

        System.out.print("Available races: ");
        for (int i = 0; i < Character.raceList.length; i++) {
            if (i != Character.raceList.length - 1)
                System.out.print(Character.raceList[i] + ", ");
            else
                System.out.print(Character.raceList[i]);
        }

        boolean approvedRace = false;
        String race = "";
        while (!approvedRace) {
            System.out.print("\nPick a race: ");
            race = input.nextLine().toLowerCase();

            for (int i = 0; i < Character.raceList.length; i++) {
                if (race.equals(Character.raceList[i])) {
                    approvedRace = true;
                    break;
                }
            }
            if (!approvedRace)
                System.out.println("Invalid race.");
        }

        System.out.print("Available classes: ");
        for (int i = 0; i < Character.classList.length; i++) {
            if (i != Character.classList.length - 1)
                System.out.print(Character.classList[i] + ", ");
            else
                System.out.print(Character.classList[i]);
        }

        boolean approvedClass = false;
        String classPick = "";
        while (!approvedClass) {
            System.out.print("\nPick a class: ");
            classPick = input.nextLine().toLowerCase();

            for (int i = 0; i < Character.classList.length; i++) {
                if (classPick.equals(Character.classList[i])) {
                    approvedClass = true;
                    break;
                }
            }
            if (!approvedClass)
                System.out.println("Invalid class.");
        }

        return new Character(name, gender, race, classPick);
    }


    // This method makes sure the first letter of each name entered is uppercased and the rest is lowercased. If the name consists of numbers, nothing is changed.
    private static String nameCaseFixer(String name) {
        int whiteSpace = 0;
        String nameCaseFixer = "";
        String nameExtractor;

        while (whiteSpace != -1) {
            whiteSpace = name.indexOf(' ');

            if (whiteSpace != -1) {
                nameExtractor = name.substring(0, whiteSpace);
                nameCaseFixer += nameExtractor.substring(0, 1).toUpperCase() + nameExtractor.substring(1).toLowerCase() + " ";
            } else
                nameCaseFixer += name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

            name = name.substring(whiteSpace + 1);
        }

        return nameCaseFixer;
    }

    // This method is used for generating random encounters
    private static void encounterGenerator(int stepsTaken, Character character, Inventory inventory, Monster finalBoss) {

        if (stepsTaken < 100) { // Before 100 steps have been taken, different normal encounters appear
            Random random = new Random();
            int randomNumberGenerator = random.nextInt(100); // Generate a random integer between 0 and 99 using the Random class. The number is used to determine encounters.

            if (randomNumberGenerator < 15) { // 15 % chance of regular monster encounter
                Monster monster = monsterGenerator(stepsTaken); // Using the first overloaded monsterGenerator method to construct a Monster object
                combat(character, monster, inventory);
            } else if (randomNumberGenerator == 15) { // 1 % chance of champion monster encounter
                Monster monster = monsterGenerator(); // Using the second overloaded monsterGenerator method to construct a champion Monster object
                combat(character, monster, inventory);
            } else if (randomNumberGenerator == 16) { // 1 % chance of merchant encounter
                Merchant merchant = merchantGenerator();
                trade(inventory, merchant);
            } else
                System.out.println("Nothing happened.");

        } else { // The final boss appears after 100 steps taken.
            combat(character, finalBoss, inventory);
        }
    }

    // This method is used for generating regular monsters
    private static Monster monsterGenerator(int stepsTaken) {

        int randomDescriptionGenerator = (int) (Math.random() * Monster.descriptionList.length); // Generate a random number used to determine the description of the monster. Casting is used to truncate the fractional part
        int randomMonsterGenerator = (int) (Math.random() * Monster.monsterList.length); // Generate a random number used to determine the type of the monster

        Monster monster = new Monster(randomDescriptionGenerator, randomMonsterGenerator); // Construct a monster object

        int monsterLevel;

        if (stepsTaken < 20)
            monsterLevel = (int) (1 + (Math.random() * 2)); // Monsters generated for the first 20 steps can be between level 1 and 2
        else if (stepsTaken < 40)
            monsterLevel = (int) (2 + (Math.random() * 2)); // Monsters generated for the next 20 steps can be between level 2 and 3
        else if (stepsTaken < 60)
            monsterLevel = (int) (3 + (Math.random() * 2)); // Monsters generated for the next 20 steps can be between level 3 and 4
        else if (stepsTaken < 80)
            monsterLevel = (int) (4 + (Math.random() * 2)); // Monsters generated for the next 20 steps can be between level 4 and 5
        else
            monsterLevel = (int) (5 + (Math.random() * 2)); // Monsters generated for the final 20 steps can be between level 5 and 6

        monster.levelUp(monsterLevel); // Level the monster object up

        return monster;
    }

    // This method is used for generating champion monsters
    private static Monster monsterGenerator() {
        int randomMonsterGenerator = (int) (Math.random() * Monster.championList.length); // Generate a random number used to determine the type of the champion monster

        Monster monster = new Monster("champion", randomMonsterGenerator); // A champion monster always has the "champion" description

        monster.levelUp(7); // A champion monster is always level 7

        return monster;
    }

    private static void combat(Character character, Monster monster, Inventory inventory) {

        Scanner input = new Scanner(System.in);
        int answer = -1;

        if (!monster.getDescription().equals("final boss"))
            System.out.println("You encounter a(n) " + monster.getDescription() + " " + monster.getMonsterType() + " (level " + monster.getLevel() + ")");
        else
            System.out.println("You encounter the " + monster.getDescription() + ", the dragon Big Baby Boi!");

        boolean skipPlayerTurn = false;

        // Combat keeps going until either the player's or monster's health is 0 or less or until the player flees
        while (character.getHealthCurrent() > 0 && monster.getHealthCurrent() > 0 && answer != 3) {
            System.out.printf("\n%-20s%-20s\n%-20s%-20s\n", "Attack (0)", "Inventory (1)", "Statistics (2)", "Flee (3)");
            answer = input.nextInt();

            switch (answer) {
                case 0:
                    String turn;
                    int damage;

                    if (!skipPlayerTurn) { // Player's turn
                        turn = "player";
                        damage = damageCalculator(character, monster, turn); // Calculate damage
                        monster.changeHealthCurrent(-damage); // Negatively change the monster's current health
                        System.out.println("You do " + damage + " damage to the monster");
                        System.out.println("The monster has " + monster.getHealthCurrent() + "/" + monster.getHealthMax() + " HP remaining");
                    } else
                        System.out.println("You spent your turn drinking a potion.");

                    if (monster.getHealthCurrent() <= 0) { // If the monster's health is 0 or less, the fight is over
                        System.out.println("You killed the monster.\n");
                        lootGenerator(character, monster, inventory); // Generate loot
                    } else { // Monster's turn
                        turn = "monster";
                        damage = damageCalculator(character, monster, turn); // Calculate damage
                        character.changeHealthCurrent(-damage); // Negatively change the player's current health
                        System.out.println("The monster does " + damage + " damage to you");
                        System.out.println("You have " + character.getHealthCurrent() + "/" + character.getHealthMax() + " HP remaining");
                    }

                    if (character.getHealthCurrent() <= 0) // If the player's health is 0 or less, the fight is over
                        System.out.println("You die.");

                    skipPlayerTurn = false; // Reset the skipPlayerTurn to false, so the player gets a turn next loop
                    break;
                case 1:
                    int answer2 = -1;
                    while (answer2 != 3) { // The while loop keeps going until the player chooses the "back" option
                        System.out.printf("\n%-25s%-25s\n%-25s%-25s\n", "Equipment and gold (0)", "Consumables (1)", "Use potion (2)", "Back (3)");
                        answer2 = input.nextInt();
                        switch (answer2) {
                            case 0:
                                inventory.printInventory();
                                break;
                            case 1:
                                inventory.printConsumables();
                                break;
                            case 2:
                                usePotion(character, inventory);
                                skipPlayerTurn = true; // If the player uses a potion, the player's doesn't get to attack before next loop
                                break;
                            case 3: // Case 3 has to be added, otherwise the default case will be run when 3 is entered
                                break;
                            default:
                                System.out.println("Invalid input.");
                        }
                    }
                    break;
                case 2:
                    character.getStats();
                    break;
                case 3:
                    if (!monster.getDescription().equals("final boss")) // Player may only flee from monsters that are not the final boss
                        System.out.println("You fled.");
                    else {
                        System.out.println("You cannot flee from Big Boi!");
                        answer = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static int damageCalculator(Character character, Monster monster, String turn) {
        int damage;

        if (turn.equals("player")) // If it is the String turn is equal to "player", calculate the damage the player does. Else calculate the monster's damage
            damage = character.getDamage() - monster.getDefense();
        else
            damage = monster.getDamage() - character.getDefense();

        return (damage >= 0) ? damage : 0; // If the damage number is negative, 0 is returned instead
    }

    private static void lootGenerator(Character character, Monster monster, Inventory inventory) {

        int experienceCalculator = ((int) (10 + (Math.random() * 11))) * monster.getLevel(); // Player gains between 10 and 20 base experience points multiplied by the monster's level
        int goldCalculator = ((int) (10 + (Math.random() * 21))) * monster.getLevel(); // Player gains between 10 and 30 base gold multiplied by the monster's level
        int itemGenerator = (int) (Math.random() * 10); // Generate a random number between 0 and 9 to determine item drops

        System.out.println("You gained " + experienceCalculator + " EXP and " + goldCalculator + " gold");

        character.gainExp(experienceCalculator); // Add the exp
        inventory.changeGold(goldCalculator); // Add the gold

        if (itemGenerator % 2 == 0) { // Player has 50 % chance of getting an item drop

            // This int variable generates a random slot for the dropped item using Math.random
            int itemSlotGenerator = (int) ((Math.random() * Inventory.slotList.length));

            // Generate a number corresponding to a random name in the randomItemNames array in the generated slot number (e.g. randomItemNames[0][0] is "axe")
            // The rows in the two-dimensional array randomItemNames are slots. The columns are item names
            int itemNameGenerator = (int) (Math.random() * Inventory.randomItemNames[itemSlotGenerator].length);

            String newItemName = Inventory.randomItemNames[itemSlotGenerator][itemNameGenerator]; // This is the name of the new item
            int randomValue = (int) (1 + (Math.random() * monster.getLevel())); // Generate a value for the new item based on the monster's level. The value increases either damage or defense

            Scanner input = new Scanner(System.in);
            System.out.println("\nYou found a(n) " + newItemName + " (+" + randomValue + ")");
            if (!inventory.getInventoryItemsNames(itemSlotGenerator).equals("Empty")) // Check if the slot is empty
                System.out.println("Your current item in this slot is a(n) " + inventory.getInventoryItemsNames(itemSlotGenerator) + " (+ " + inventory.getInventoryItemsValues(itemSlotGenerator) + ")");
            else
                System.out.println("You currently have no item in this slot.");
            System.out.printf("%-20s%-20s\n", "Equip new item (0)", "Discard new item (1)");
            int answer = input.nextInt();

            switch (answer) {
                case 0:
                    inventory.changeInventoryItemsNames(itemSlotGenerator, newItemName); // Set the name for the new item
                    inventory.changeInventoryItemsValues(itemSlotGenerator, randomValue, character); // Set the value for the new item
                    System.out.println("You equip the new item.");
                    break;
                case 1:
                    System.out.println("You smash the new item against the ground. It breaks into a million pieces.");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    private static Merchant merchantGenerator() {

        int randomNameGenerator = (int) (Math.random() * Merchant.namesList.length); // Generate a random number used to determine the name of the merchant
        int randomGenderGenerator = (int) (Math.random() * Merchant.genderList.length); // Generate a random number used to determine the gender of the merchant
        int randomRaceGenerator = (int) (Math.random() * Merchant.raceList.length); // Generate a random number used to determine the race of the merchant

        return new Merchant(randomNameGenerator, randomGenderGenerator, randomRaceGenerator);
    }

    // This method allows the player to view the merchant's inventory and buy potions
    private static void trade(Inventory inventory, Merchant merchant) {

        for (int i = 0; i < merchant.getInventoryItemsStockAndPrice().length; i++) { // This loop randomly initializes the stock of each merchant item
            int randomStock = (int) (1 + (Math.random() * 10));
            merchant.changeInventoryItemsStock(i, randomStock);
        }

        int answer = -1;

        System.out.println("\nYou come across the " + merchant.getGender() + " " + merchant.getRace() + " merchant named " + merchant.getName());

        while (answer != 3) {
            Scanner input = new Scanner(System.in);
            System.out.printf("\n%-25s%-25s\n%-25s%-25s\n", "Merchant inventory (0)", "Player inventory (1)", "Buy items (2)", "Move on (3)");
            answer = input.nextInt();

            switch (answer) {
                case 0:
                    merchant.printInventory();
                    break;
                case 1:
                    int answer2 = -1;
                    while (answer2 != 2) {
                        System.out.printf("\n%-25s%-25s\n%-25s\n", "Equipment and gold (0)", "Consumables (1)", "Back (2)");
                        answer2 = input.nextInt();
                        switch (answer2) {
                            case 0:
                                inventory.printInventory();
                                break;
                            case 1:
                                inventory.printConsumables();
                                break;
                            case 2: // Case 2 has to be added, otherwise the default case will be run when 2 is entered
                                break;
                            default:
                                System.out.println("Invalid input.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter the index of the item you wish to buy and the amount or enter -1 twice to go back");
                    int index = input.nextInt();
                    int amount = input.nextInt();

                    if (amount > 0 && merchant.getInventoryItemsStock(index) >= amount) { // Make sure the number of items purchased is positive & that the merchant has the chosen amount in stock
                        int price = merchant.getInventoryItemsPrice(index) * amount;

                        if (inventory.getGold() * amount >= price) {
                            inventory.changeGold(-price); // Subtract the spent gold from the player's inventory
                            inventory.changeConsumableItemsStock(index, amount); // Add the purchased item(s)
                            merchant.changeInventoryItemsStock(index, -amount); // Subtract the purchased item from the merchant's inventory
                            System.out.println("The merchant thanks you for your purchase.");
                        } else
                            System.out.println("You cannot afford that.");
                    } else if (amount <= 0)
                        System.out.println("You have to buy at least 1 item");
                    else if (merchant.getInventoryItemsStock(index) < amount)
                        System.out.println("The merchant does not have that item in stock");

                    break;
                case 3: // Case 3 has to be added, otherwise the default case will be run when 3 is entered
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }

    // This method enables the player to use different potions
    private static void usePotion(Character character, Inventory inventory) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the index number of the potion you wish to use or enter -1 to go back");
        int index = input.nextInt();

        if (index != -1) {
            if (inventory.getConsumableItemsStock(index) > 0) { // Make sure the player has at least one of the selected potion
                inventory.changeConsumableItemsStock(index, -1); // Remove the used potion
                if (index == 0) {
                    character.changeHealthCurrent(100); // Add health if health potion was used
                    System.out.println("You drank a healing potion. Your health is " + character.getHealthCurrent());
                } else if (index == 1) {
                    character.changeDamage(1); // Add damage if damage potion was used
                    System.out.println("You drank a +1 damage potion. Your damage went up.");
                } else if (index == 2) {
                    character.changeDefense(1); // Add defense if defense potion was used
                    System.out.println("You drank a +1 defense potion. Your defense went up.");
                }
            } else
                System.out.println("You do not have that item");
        }
    }

    // This timer provides the game time down to the millisecond
    private static void timer(long timerStart, long timerStop) {

        long totalMilliseconds = timerStop - timerStart;
        long currentMillisecond = totalMilliseconds % 10;

        long totalDeciseconds = totalMilliseconds / 10;
        long currentDecisecond = totalDeciseconds % 10;

        long totalCentiseconds = totalDeciseconds / 10;
        long currentCentisecond = totalCentiseconds % 10;

        long totalSeconds = totalCentiseconds / 10;
        long currentSecond = totalSeconds % 60;

        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        long totalHours = totalMinutes / 60;
        long currentHour = totalHours % 24;

        System.out.println("\nGame time: " + currentHour + " hour(s) " + currentMinute + " minute(s) " + currentSecond + " second(s) " + currentCentisecond + " centisecond(s) " + currentDecisecond + " decisecond(s) and " + currentMillisecond + " millisecond(s)");
    }
}
