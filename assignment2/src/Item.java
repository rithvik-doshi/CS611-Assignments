/**
 * Abstract class for all items in the game that heroes can interact with.
 */
public abstract class Item {
    /**
     * Name of the item.
     */
    public final String name;
    /**
     * Price of the item.
     */
    public final int purchasePrice;
    /**
     * Level required to purchase the item.
     */
    public final int level;

    /**
     * Constructor for the Item class.
     * @param name Name of the item.
     * @param itemData DataMap containing the item's data.
     */
    public Item(String name, DataMap<String, String> itemData){
        this.name = name;
        this.purchasePrice = Integer.parseInt(itemData.get("cost"));
        this.level = Integer.parseInt(itemData.get("required level"));
    }

    /**
     * Get a string representation of the item.
     * @param itemSpecific String containing the item's specific data.
     * @return String representation of the item.
     */
    public String stringify(String itemSpecific){
        return name + ":\n\t" +
                "Purchase Price: " + purchasePrice + "\n\t" +
                "Level: " + level + "\n\t" +
                itemSpecific;
    }

    /**
     * Item factory
     * @param itemType Type of item to create.
     * @param itemName Name of item to create.
     * @param itemData DataMap containing the item's data.
     * @return Item object.
     */
    public static Item itemCreator(String itemType, String itemName, DataMap<String, String> itemData){
        switch (itemType) {
            case "Weaponry":
                return new Weapon(itemName, itemData);
            case "Armory":
                return new Armor(itemName, itemData);
            case "Potions":
                return new Potion(itemName, itemData);
            default:
                return new Spell(itemName, itemData, itemType);
        }
    }

}
