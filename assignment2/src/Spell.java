/**
 * Item class for Spells that are consumable and can be used by heroes.
 */
public class Spell extends Item implements Consumable{
    /**
     * Number of uses left
     */
    private int uses = 3;

    /**
     * Damage amount
     */
    private final int damageAmount;

    /**
     * Mana cost
     */
    private final int manaCost;

    /**
     * Type of spell
     */
    private String type;

    /**
     * Constructor for Spell
     * @param name Name of spell
     * @param itemData Data for spell
     * @param type Type of spell
     */
    public Spell(String name, DataMap<String, String> itemData, String type){
        super(name, itemData);
        this.damageAmount = Integer.parseInt(itemData.get("damage"));
        this.manaCost = Integer.parseInt(itemData.get("mana cost"));
        switch (type){
            case "FireSpells":
                this.type = "Fire";
                break;
            case "IceSpells":
                this.type = "Ice";
                break;
            case "LightningSpells":
                this.type = "Lightning";
                break;
        }
    }

    /**
     * Get number of uses left
     * @return Number of uses left
     */
    public int getUses(){
        return this.uses;
    }

    /**
     * Use the spell once
     */
    public void use(){
        this.uses--;
        System.out.println(getUses() + " uses left of " + name);
    }

    /**
     * Get the damage amount
     * @return Damage amount
     */
    public int getDamageAmount(){
        return this.damageAmount;
    }

    /**
     * Get the spell type
     * @return Spell type
     */
    public String getSpellType(){
        return this.type;
    }

    /**
     * String representation of Spell
     * @return String representation of Spell
     */
    @Override
    public String toString() {
        return super.stringify(
    "Type: " + type + "\n\t" +
            "Mana Cost: " + manaCost + "\n\t" +
            "Uses left: " + uses + "\n\t" +
            "Damage: " + damageAmount
        );
    }

    /**
     * Get the mana cost
     * @return Mana cost
     */
    public int getMpCost() {
        return manaCost;
    }

    /**
     * Get the stat that the spell affects
     * @return stat
     */
    public String getSpellEffectName() {
        switch (type) {
            case "Fire":
                return "defense";
            case "Ice":
                return "damage";
            case "Lightning":
                return "dodge";
            default:
                return "";
        }
    }
}
