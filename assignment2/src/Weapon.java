/**
 * A weapon is an item that can be used to attack enemies.
 */
public class Weapon extends Item {
    /**
     * The damage that the weapon inflicts
     */
    private final int damage;

    /**
     * Constructor
     * @param name The name of the weapon
     * @param itemData The itemData structure to get information about the weapon
     */
    public Weapon(String name, DataMap<String, String> itemData){
        super(name, itemData);
        this.damage = Integer.parseInt(itemData.get("damage"));
    }

    /**
     * Get the damage the weapon inflicts
     * @return The damage the weapon inflicts
     */
    public int getDamage(){
        return damage;
    }

    /**
     * Return a string representation of the weapon
     * @return The weapon string
     */
    @Override
    public String toString() {
        return super.stringify(
            "Damage: " + damage
        );
    }
}
