class Character {

    // Static variables
    static String[] genderList = {"male", "female"};
    static String[] raceList = {"human", "dwarf", "elf", "gnome"};
    static String[] classList = {"fighter", "defender"};

    // Instance variables
    private int healthMax = 100;
    private int healthCurrent = 100;
    private int damage = 7;
    private int defense = 4;
    private int exp = 100;
    private int level = 1;
    private String name, gender, race, classPick;

    Character() { // no-arg constructor
    }

    Character(String name, String gender, String race, String classPick) {
        this.name = name;
        this.gender = gender;
        this.race = race;
        this.classPick = classPick;
    }

    int getLevel() {
        return level;
    }

    String getName() {
        return name;
    }

    String getGender() {
        return gender;
    }

    String getRace() {
        return race;
    }

    String getClassPick() {
        return classPick;
    }

    int getHealthMax() {
        return healthMax;
    }

    int getHealthCurrent() {
        return (healthCurrent >= 0) ? healthCurrent : 0; // Conditional expressions makes sure negative health isn't displayed
    }

    int getDamage() {
        return damage;
    }

    int getDefense() {
        return defense;
    }

    int getExp() {
        return exp;
    }

    void changeHealthCurrent(int amount) {
        if (amount > 0) { // If the amount is positive, the player is drinking a healing potion
            if (amount + healthCurrent <= healthMax) // Makes sure the current health doesn't exceed the maximum health
                healthCurrent += amount;
            else
                healthCurrent += (healthMax - healthCurrent);
        } else // If the amount is 0 or negative, the player is taking damage
            healthCurrent += amount;
    }

    void changeDamage(int value) {
        damage += value;
    }

    void changeDefense(int value) {
        defense += value;
    }

    void getStats() {
        System.out.println(getName() + ", the " + getGender() + " " + getRace() + " " + getClassPick());
        System.out.println("Player statistics:");
        System.out.println("---------------------");
        System.out.println("Level:\t\t\t" + getLevel()); // An escape sequences is used to make tabulations
        System.out.println("Max health:\t\t" + getHealthMax());
        System.out.println("Current health:\t" + getHealthCurrent());
        System.out.println("Damage:\t\t\t" + getDamage());
        System.out.println("Defense:\t\t" + getDefense());
        System.out.println("Experience:\t\t" + getExp());
    }

    void gainExp(int exp) {
        this.exp += exp;

        if (this.exp / 100 > level) // 1 level is gained per 100 EXP earned
            levelUp();
    }

    private void levelUp() {
        healthMax += 10;
        damage += 4;
        defense += 1;
        ++level;
        System.out.println("You have ascended to level " + level + ". Max health, damage and defense have increased.");
    }
}
