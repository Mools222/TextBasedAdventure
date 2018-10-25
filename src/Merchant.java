class Merchant {

    // Static variables
    static String[] namesList = {"Alfred", "Barney", "Calvin", "Edmund", "Edwin", "Eugene", "Finn", "Frank", "Frederick", "Gilbert", "Gus", "Harold", "Howard", "Humphrey", "Isaac", "Joseph", "Louis", "Milton", "Mortimer", "Ralph", "Seymour", "Walter", "Wilbur"};
    static String[] genderList = {"male", "female"};
    static String[] raceList = {"human", "dwarf"};

    // Instance variables
    private String name, gender, race;
    private String[] inventoryItemsNames = {"Healing potion", "Damage potion", "Defense potion"};
    private String[] inventoryItemsDescriptions = {"Heals you for 100 HP", "Permanent damage +1", "Permanent defense +1"};
    private int[][] inventoryItemsStockAndPrice = {{0, 100}, {0, 500}, {0, 500}};

    Merchant(int nameNumber, int genderNumber, int raceNumber) {
        name = namesList[nameNumber];
        gender = genderList[genderNumber];
        race = raceList[raceNumber];
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

    int[][] getInventoryItemsStockAndPrice() { // This getter is only useful for getting the length of the array inventoryItemsStock
        return inventoryItemsStockAndPrice;
    }

    int getInventoryItemsStock(int index) {
        return inventoryItemsStockAndPrice[index][0];
    }

    int getInventoryItemsPrice(int index) {
        return inventoryItemsStockAndPrice[index][1];
    }

    void changeInventoryItemsStock(int stockIndex, int stock) { // This method is used for changing the stock of the merchant's items. The price cannot be changed
        inventoryItemsStockAndPrice[stockIndex][0] += stock;
    }

    void printInventory() {
        System.out.printf("\n%-20s%-30s%-20s%-20s%-20s\n", "Item name", "Item description", "Quantity", "Price", "Index");
        System.out.println("-----------------------------------------------------------------------------------------------");

        for (int i = 0; i < inventoryItemsNames.length; i++) { // This loop automatically displays all the merchant's wares and their stock, price and indices
            System.out.printf("%-20s%-30s%-20d%-20d%-20d\n", inventoryItemsNames[i], inventoryItemsDescriptions[i], inventoryItemsStockAndPrice[i][0], inventoryItemsStockAndPrice[i][1], i);
        }
    }
}
