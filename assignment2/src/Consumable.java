/**
 * Interface for objects that can be consumed by a hero
 */
public interface Consumable {
    /**
     * Method to get the number of uses left
     * @return Number of uses left
     */
    int getUses();

    /**
     * Method to use the consumable once
     */
    void use();
}
