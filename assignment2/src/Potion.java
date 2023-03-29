/**
 * Item class for potions that can be consumed by heroes.
 */
public class Potion extends Item implements Consumable {

    /**
     * Number of uses left
     */
    private int uses = 1;

    /**
     * Attribute change
     */
    private final int changeAttr;

    /**
     * Attributes affected (string)
     */
    private final String targetAttribute;

    /**
     * Attributes affected (split string)
     */
    private final String[] targetAttrs;

    /**
     * Constructor for Potion
     * @param name Name of potion
     * @param itemData Data for potion
     */
    public Potion(String name, DataMap<String, String> itemData){
        super(name, itemData);
        this.changeAttr = Integer.parseInt(itemData.get("attribute increase"));
        this.targetAttribute = itemData.get("attribute affected");
        targetAttrs = targetAttribute.split("/");
    }

    /**
     * Get number of uses left
     * @return Number of uses left
     */
    public int getUses(){
        return this.uses;
    }

    /**
     * Use the potion once
     */
    public void use(){
        this.uses--;
    }

    /**
     * Get the attribute change
     * @return Attribute change
     */
    public int getChangeAttr(){
        return this.changeAttr;
    }

    /**
     * Get the target attributes
     * @return Target attributes
     */
    public String[] getTargetAttrs(){
        return this.targetAttrs;
    }

    /**
     * String representation of potion
     * @return String representation
     */
    @Override
    public String toString() {
        return super.stringify(
                "Effect: " + targetAttribute + "\n\t" +
                        "Change: " + changeAttr
        );
    }

}

