class Monster {

    // List of possible descriptions, which are randomly given to a monster though the monsterGenerator method in the "Adventure" class
    static String[] descriptionList = {"accomplished", "agitated", "alert", "ambitious", "angelic", "antique", "apprehensive", "artistic", "astonishing", "acrobatic", "adolescent", "adorable", "affectionate", "afraid",
            "aged", "aggressive", "agile", "alarmed", "amazing", "ambitious", "ancient", "anxious", "apprehensive", "arctic", "aromatic", "artistic", "ashamed", "astonishing", "athletic", "attractive", "authentic", "automatic",
            "avaricious", "average", "awesome", "awful", "awkward", "babyish", "bad", "bare", "basic", "beautiful", "big-hearted", "biodegradable", "bitter", "bland", "blind", "blue", "blushing", "bold", "bossy", "bouncy",
            "brave", "bright", "brilliant", "bronze", "brown", "bruised", "bubbly", "carefree", "careful", "careless", "cautious", "charming", "cheerful", "chubby", "classic", "clean", "clever", "clueless", "clumsy", "colorful",
            "colorless", "common", "competent", "complex", "composed", "concerned", "confused", "considerate", "content", "conventional", "cool", "corny", "corrupt", "courageous", "crafty", "crazy", "creamy", "creative", "creepy",
            "criminal", "crisp", "crooked", "cruel", "cuddly", "cultured", "curvy", "cute", "dapper", "daring", "dark", "dazzling", "decent", "decisive", "defiant", "delectable", "delicious", "delightful", "delirious", "determined",
            "devoted", "diligent", "dimpled", "dimwitted", "dirty", "discrete", "disfigured", "disgusting", "dishonest", "dismal", "dizzy", "dopey", "doting", "drab", "dramatic", "droopy", "dry", "dull", "eager", "ecstatic",
            "educated", "elated", "elegant", "eminent", "emotional", "energetic", "enlightened", "envious", "esteemed", "euphoric", "evil", "exalted", "excellent", "excited", "exemplary", "exotic", "fabulous", "fair", "fancy",
            "fantastic", "fat", "feisty", "feminine", "fickle", "filthy", "firm", "flamboyant", "flustered", "focused", "fragrant", "frosty", "fumbling", "giddy", "gifted", "glamorous", "glistening", "glittering", "gloomy", "glorious",
            "glum", "gorgeous", "graceful", "green", "gross", "grotesque", "grouchy", "grubby", "grumpy", "hairy", "handsome", "happy", "hateful", "haunting", "healthy", "hearty", "heavenly", "heavy", "hefty", "hideous", "hilarious",
            "hoarse", "hollow", "horrible", "hot", "humble", "humming", "hungry", "husky", "icky", "icy", "idealistic", "idiotic", "ignorant", "ill-informed", "illiterate", "illustrious", "impassioned", "impeccable", "imperturbable",
            "impolite", "impressive", "incomparable", "incredible", "indolent", "infatuated", "insecure", "insidious", "insubstantial", "interesting", "intrepid", "irresponsible", "irritating", "jaded", "jagged", "jaunty", "jealous",
            "jittery", "jolly", "jovial", "joyful", "joyous", "jubilant", "judicious", "juicy", "jumpy", "kaleidoscopic", "keen", "knobby", "knowledgeable", "kooky", "lame", "lanky", "lavish", "lazy", "lean", "lively", "livid",
            "loathsome", "loud", "lumbering", "luminous", "lumpy", "lustrous", "luxurious", "mad", "majestic", "marvelous", "masculine", "meaty", "mediocre", "meek", "mellow", "menacing", "merry", "messy", "milky", "mindless", "minty",
            "miserable", "miserly", "misguided", "misty", "modern", "modest", "moist", "muddy", "murky", "mushy", "musty", "naive", "nasty", "naughty", "needy", "nervous", "nifty", "nimble", "nippy", "nocturnal", "noisy", "nutty",
            "obese", "odd", "oily", "optimistic", "ornery", "outgoing", "overjoyed", "palatable", "pale", "parched", "passionate", "pastel", "perfect", "perfumed", "perky", "pesky", "pessimistic", "petty", "pink", "plaintive", "plump",
            "plush", "poised", "polished", "portly", "posh", "positive", "prestigious", "pretty", "prickly", "pristine", "proud", "prudent", "punctual", "pungent", "pure", "pushy", "putrid", "puzzled", "quaint", "quarrelsome", "queasy",
            "querulous", "quiet", "quintessential", "quirky", "quixotic", "quizzical", "radiant", "ragged", "rash", "raw", "realistic", "reasonable", "reckless", "red", "regal", "repulsive", "ripe", "rosy", "rotating", "rotten", "rough",
            "rowdy", "rubbery", "rude", "sad", "salty", "sandy", "sarcastic", "sardonic", "scented", "scholarly", "scornful", "selfish", "serene", "serious", "shabby", "shadowy", "shady", "shallow", "shameful", "shameless", "shimmering",
            "shiny", "shocked", "shy", "silky", "sinful", "sizzling", "skeletal", "skinny", "sleepy", "slim", "slimy", "slippery", "smooth", "smug", "sophisticated", "sour", "sparkling", "spectacular", "spicy", "spiffy", "spiteful",
            "splendid", "starry", "sticky", "studious", "stunning", "stupendous", "stupid", "sugary", "surprised", "svelte", "sweaty", "sweet", "sweltering", "tempting", "tender", "tense", "tepid", "terrible", "testy", "thick", "thin",
            "tricky", "trifling", "trim", "tubby", "ugly", "unacceptable", "unfortunate", "unkempt", "unpleasant", "unruly", "unsightly", "untidy", "unwieldy", "upbeat", "upset", "vain", "vapid", "velvety", "venerated", "vibrant", "vicious",
            "vigilant", "vigorous", "villainous", "violent", "vivacious", "wan", "warm", "wary", "watchful", "waterlogged", "weird", "well-groomed", "wet", "whimsical", "white", "wicked", "wild", "wonderful", "wooden", "worrisome", "wretched",
            "writhing", "wry", "yawning", "yummy", "zany", "zealous", "zesty"};

    static String[] monsterList = {"air elemental", "animated armor", "ape", "baboon", "badger", "bandit", "banshee", "bat", "bear", "beholder", "boar", "boneclaw", "camel", "cat", "centaur", "cockatrice", "cow",
            "crab", "crocodile", "cyclops", "darkling", "deer", "dolphin", "eagle", "earth elemental", "efreeti", "elk", "ettin", "fire elemental", "frog", "gargoyle", "ghoul", "gnoll", "goat", "goblin", "harpy",
            "hawk", "hobgoblin", "hyena", "imp", "jackal", "kobold", "lion", "lizard", "merfolk", "mule", "mummy", "nightwalker", "octopus", "ogre", "orc", "owl", "ox", "panther", "pegasus", "pixie", "pony", "rat",
            "raven", "salamander", "satyr", "scorpion", "skeleton", "spider", "sprite", "thug", "tiger", "treant", "troll", "vulture", "water elemental", "weasel", "wolf", "worg", "zombie"};

    static String[] championList = {"cyclops", "elephant", "giant", "hippogriff", "leviatan", "mammoth", "manticore", "minotaur", "phoenix", "wyvern", "yeti"};

    private int healthMax = 10;
    private int healthCurrent = 10;
    private int damage = 4;
    private int defense = 0;
    private int level = 1;

    private String description;
    private String monsterType;

    Monster(int descriptionNumber, int monsterNumber) { // This constructor is used to construct regular monsters for the random encounters
        description = descriptionList[descriptionNumber];
        monsterType = monsterList[monsterNumber];
    }

    Monster(String description, int monsterNumber) { // This constructor is used to construct champion monsters for the random encounters
        this.description = description; // Using the "this" keyword to reference the hidden data field "description"
        monsterType = championList[monsterNumber];
    }

    Monster(int healthMax, int healthCurrent, int damage, int defense, int level, String description) { // This constructor is used to construct the final boss
        this.healthMax = healthMax;
        this.healthCurrent = healthCurrent;
        this.damage = damage;
        this.defense = defense;
        this.level = level;
        this.description = description;
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

    String getDescription() {
        return description;
    }

    String getMonsterType() {
        return monsterType;
    }

    int getLevel() {
        return level;
    }

    void levelUp(int numberOfLevels) { // This instance method is used to automatically level up a monster when it is generated
        healthMax += 10 * numberOfLevels;
        healthCurrent += 10 * numberOfLevels;
        damage += 2 * numberOfLevels;
        defense += 2 * numberOfLevels;
        level = numberOfLevels;
    }

    void changeHealthCurrent(int damage) {
        healthCurrent += damage;
    }
}
