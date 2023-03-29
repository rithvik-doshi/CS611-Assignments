/**
 * Legend class that describes an entity in the world of Heroes and Monsters.
 */
public abstract class Legend {
    /**
     * Name of the Legend.
     */
    final String name;
    /**
     * Level of the Legend.
     */
    private int level;
    /**
     * Base HP of the Legend.
     */
    private final int baseHp;
    /**
     * Current HP of the Legend.
     */
    private int hp;
    /**
     * Type of the Legend.
     */
    final String type;

    /**
     * Constructor for the Legend class.
     * @param name Name of the Legend.
     * @param level Level of the Legend.
     * @param type Type of the Legend.
     */
    public Legend(String name, int level, String type){
        this.name = name;
        this.level = level;
        this.hp = this.baseHp = createHp(level);
        this.type = type;
    }

    /**
     * Initializes the HP of the Legend based on the level.
     * @param level Level of the Legend.
     * @return HP of the Legend.
     */
    private static int createHp(int level) {
        return level * 100;
    }

    /**
     * Gets the level of the Legend.
     * @return level of the Legend.
     */
    public int getLevel(){
        return level;
    }

    /**
     * Sets the level of the Legend.
     * @param level Level of the Legend.
     */
    public void setLevel(int level) {this.level = level;}

    /**
     * Gets the HP of the Legend.
     * @return HP of the Legend.
     */
    public int getHp(){
        return hp;
    }

    /**
     * Sets the HP of the Legend.
     * @param hp HP of the Legend.
     */
    public void setHp(int hp){
        this.hp = hp;
    }

    /**
     * Gets the status of the Legend.
     * @return Status of the Legend.
     */
    public LegendStatus getStatus(){
        if (hp <= 0){
            return LegendStatus.DEAD;
        } else return LegendStatus.ALIVE;
    }

    /**
     * Returns a string representation of a legend
     * @param legendSpecific String specific to the legend type
     * @return String representation of the legend
     */
    public String stringify(String legendSpecific) {
        return (getStatus() == LegendStatus.ALIVE) ?
                "-----------------------------------------\n" +
                "Name: " + name + "\n" +
                "Level: " + getLevel() + "\n" +
                "HP: " + getHp() + "\n" +
                "Class: " + type + "\n" +
                legendSpecific +
                "-----------------------------------------\n"
                :
                "-----------------------------------------\n" +
                "Name: " + name + "\n" +
                "Status: " + getStatus() + "\n" +
                "-----------------------------------------\n";
    }

    /**
     * Gets base HP of the Legend.
     * @return Base HP of the Legend.
     */
    protected int getBaseHp() {
        return baseHp;
    }

    /**
     * Allows legend to take damage
     * @param dodgeFactor the factor to determine the chance of dodging
     * @param dodgeAmt the dodge stat of the legend
     * @param damage the initial damage to be taken from the adversary
     * @param defense the defense stat of the legend
     * @return the damage taken
     */
    public int takeDamage(double dodgeFactor, int dodgeAmt, int damage, int defense) {
        if (Math.random() < (1 - Math.pow(dodgeFactor, dodgeAmt))){
            System.out.println(name + " dodged the attack!");
            return 0;
        }
        int giveDamage = (int) (damage / (0.05*(Math.max(1, defense * 0.10))));
        setHp((getHp() - (giveDamage)));
        return giveDamage;
    }

    /**
     * Allows legend to take damage
     * @param damage the initial damage to be taken from the adversary
     * @return the damage taken
     */
    public abstract int takeDamage(int damage);
}
