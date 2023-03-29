/**
 * Armor is an item that can be equipped by a hero to reduce damage taken.
 */
public class Armor extends Item {
    /**
     * The defense of the armor
     */
    private int defense;

    /**
     * Constructor
     * @param name The name of the armor
     * @param itemData The itemData structure to get information about the armor
     */
    public Armor(String name, DataMap<String, String> itemData){
        super(name, itemData);
        this.defense = Integer.parseInt(itemData.get("damage reduction"));
    }

    /**
     * Return a string representation of the armor
     * @return The armor string
     */
    @Override
    public String toString() {
        return super.stringify(
                "Defense: " + defense
        );
    }

    /**
     * Get the defense of the armor
     * @return The defense of the armor
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Apply a potion to the armor
     * @param potion The potion to apply
     */
    public void applyPotion(Potion potion) {
        defense += potion.getChangeAttr();
    }
}
