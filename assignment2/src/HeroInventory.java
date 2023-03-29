/**
 * A class that represents the inventory of a hero.
 */
public class HeroInventory {
    /**
     * The items in the inventory.
     */
    DataList<Item> items;

    /**
     * The maximum capacity of the inventory.
     */
    private final int maxCapacity = 10;

    /**
     * Constructor
     */
    public HeroInventory(){
        this.items = new DataList<>(maxCapacity);
    }

    /**
     * Returns the maximum capacity of the inventory.
     * @return the maximum capacity of the inventory.
     */
    public int size(){
        return items.size();
    }

    /**
     * String representation of the inventory.
     * @return the string representation of the inventory.
     */
    @Override
    public String toString() {
        return "Inventory (" + items.size() + "/" + maxCapacity + "):\n" + items;
    }

    /**
     * Adds an item to the inventory.
     * @param item the item to add.
     * @return true if the item was added, false otherwise.
     */
    public boolean add(Item item) {
        if (items.size() < maxCapacity) {
            items.add(item);
            return true;
        }
        return false;
    }

    /**
     * Uses an item in the inventory.
     * @param item the item to use.
     */
    public void use(Item item){
        if (item instanceof Consumable){
            Consumable itemIn = (Consumable) item;
            itemIn.use();
            if (itemIn.getUses() == 0){
                return;
            }
        }
        add(item);
    }

    /**
     * Removes an item from the inventory.
     * @param item the item to remove.
     */
    public void remove(Item item) {
        items.remove(item);
    }

}
