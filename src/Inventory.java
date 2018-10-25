/** The Inventory class is defined without constructors. Java automatically provides a default constructor */
class Inventory {

    /** The player has 6 item slots as listed in the array slotList. The values of the variables
    randomItemNames, inventoryItemsNames and inventoryItemsValues correspond to these 6 slots
    (e.g. randomItemNames[0], inventoryItemsNames[0] and inventoryItemsValues[0] all correspond
    to the right hand (weapon) slot) */
    static String[] slotList = {"Right hand", "Left hand", "Head", "Chest", "Gloves", "Boots"};

    static String[][] randomItemNames = {
            {"axe", "dagger", "javelin", "mace", "scepter", "spear", "sword"},
            {"buckler", "small shield", "large shield", "kite shield", "spiked shield", "tower shield", "bone shield", "gothic shield"},
            {"cap", "skull cap", "helm", "full helm", "great helm", "mask", "crown", "bone helm"},
            {"quilted armor", "leather armor", "hard leather armor", "studded leather", "ring mail", "scale mail", "breast plate", "chain mail", "splint mail", "light plate", "field plate", "plate mail", "gothic plate", "full plate mail", "ancient armor"},
            {"leather gloves", "heavy gloves", "chain gloves", "light gauntlets", "gauntlets"},
            {"boots", "heavy boots", "chain boots", "light plated boots", "greaves"}};

    private String[] inventoryItemsNames = {"Empty", "Empty", "Empty", "Empty", "Empty", "Empty"};
    private int[] inventoryItemsValues = {0, 0, 0, 0, 0, 0};

    private int gold = 10;

    private String[] consumableItemsNames = {"Healing potion", "Damage potion", "Defense potion"};
    private String[] consumableItemsDescriptions = {"Heals you for 100 HP", "Permanent damage +1", "Permanent defense +1"};
    private int[] consumableItemsStock = {5, 0, 0};

    int getGold() {
        return gold;
    }

    void changeGold(int gold) {
        this.gold += gold;
    }

    String getInventoryItemsNames(int slot) {
        return inventoryItemsNames[slot];
    }

    void changeInventoryItemsNames(int slot, String itemName) {
        inventoryItemsNames[slot] = itemName;
    }

    int getInventoryItemsValues(int slot) {
        return inventoryItemsValues[slot];
    }

    void changeInventoryItemsValues(int slot, int value, Character character) {
        if (slot == 0) { // Slot 0 is weapon slot
            int difference = value - inventoryItemsValues[slot]; // If the player already has an item equipped, the difference in damage value gets added (or subtracted, in case the new item is worse) to the player's damage
            character.changeDamage(difference);
            inventoryItemsValues[slot] = value;
        }
        else { // The rest of the slots are armor slots
            int difference = value - inventoryItemsValues[slot]; // If the player already has an item equipped, the difference in defense value gets added (or subtracted, in case the new item is worse) to the player's defense
            character.changeDefense(difference);
            inventoryItemsValues[slot] = value;
        }
    }

    int getConsumableItemsStock(int index) {
        return consumableItemsStock[index];
    }

    void changeConsumableItemsStock(int index, int stock) {
        consumableItemsStock[index] += stock;
    }

    void printInventory() {
        System.out.printf("\n%-20s%-20s%-20s\n", "Equipment slot", "Equipment Name", "Equipment Stats");
        System.out.println("-------------------------------------------------------");

        for (int i = 0; i < slotList.length; i++) { // This loop automatically displays all the characters's equipment slots, worn equipment and equipment stats
            System.out.printf("%-20s%-20s%-20s\n", slotList[i], inventoryItemsNames[i], "+ " + inventoryItemsValues[i]); // The int variable "inventoryItemsValues" is concatenated into a String
        }

        System.out.println("\nGold: " + gold);
    }

    void printConsumables() {
        System.out.printf("\n%-20s%-30s%-20s%-20s\n", "Item name", "Item description", "Quantity", "Index");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0; i < consumableItemsNames.length; i++) { // This loop automatically displays all the characters's consumable items, their stock and their indices
            System.out.printf("%-20s%-30s%-20d%-20d\n", consumableItemsNames[i], consumableItemsDescriptions[i], consumableItemsStock[i], i);
        }
    }
}
