import java.util.stream.Collectors;

/**
 * Hero, a class that extends Legend and represents the player's character.
 */
public class Hero extends Legend{

    /**
     * The multiplier for the hero's attributes.
     */
    private static final double attrBuff = 1.2;

    /**
     * The hero's mana points.
     */
    private int mp;

    /**
     * The hero's base mana points.
     */
    private final int baseMp;

    /**
     * The hero's experience points.
     */
    private int exp = 0;

    /**
     * The hero's strength.
     */
    private int strength;

    /**
     * The hero's agility.
     */
    private int agility;

    /**
     * The hero's dexterity.
     */
    private int dexterity;

    /**
     * The hero's money.
     */
    private int money;

    /**
     * The hero's inventory.
     */
    private final HeroInventory heroInventory;

    /**
     * The hero's weapon.
     */
    private Weapon weapon;

    /**
     * The hero's armor.
     */
    private Armor armor;

    /**
     * Creates a new Hero object.
     * @param name The hero's name.
     * @param heroData The hero's data.
     * @param classType The hero's class type.
     */
    public Hero(String name, DataMap<String, String> heroData, String classType){
        super(name, Integer.parseInt(heroData.get("starting experience")), classType);
        this.mp = this.baseMp = Integer.parseInt(heroData.get("mana"));
        this.strength = Integer.parseInt(heroData.get("strength"));
        this.agility = Integer.parseInt(heroData.get("agility"));
        this.dexterity = Integer.parseInt(heroData.get("dexterity"));
        this.money = Integer.parseInt(heroData.get("starting money"));
        this.heroInventory = new HeroInventory();
        switch (classType) {
            case "Warriors":
                this.strength *= (attrBuff);
                this.agility *= (attrBuff);
                break;
            case "Sorcerers":
                this.agility *= (attrBuff);
                this.dexterity *= (attrBuff);
                break;
            case "Paladins":
                this.strength *= (attrBuff);
                this.dexterity *= (attrBuff);
                break;
        }
    }

    /**
     * Adds an item to the hero's inventory.
     * @param item The item to add.
     * @return True if the item was added successfully, false otherwise.
     */
    public boolean addToInventory(Item item){
        return heroInventory.add(item);
    }

    /**
     * Removes an item from the hero's inventory.
     * @param item The item to remove.
     */
    public void removeFromInventory(Item item){
        heroInventory.remove(item);
    }

    /**
     * Hero buys an item.
     * @param item The item to buy.
     * @return True if the item was bought successfully, false otherwise.
     */
    public boolean buyItem(Item item){
        if (item.level > getLevel()){
            System.out.println("You are not high enough level to buy this item!");
            return false;
        }
        if (money >= item.purchasePrice){
            money -= item.purchasePrice;
            if (!addToInventory(item)){
                money += item.purchasePrice;
                System.out.println("You do not have enough space in your inventory to buy this item!");
                return false;
            }
            return true;
        }
        System.out.println("You do not have enough money to buy this item!");
        return false;
    }

    /** Gets hero's experience bound before they reach the next level.
     * @return The hero's experience bound.
     */
    private int getExpBound() {
        return getLevel()*10;
    }

    /**
     * Adds exp to the hero and does additional level up changes.
     * @param exp_add The amount of exp to add.
     */
    public void addExp(int exp_add){
        int expBound;
        exp += exp_add;
        while (exp >= (expBound = getExpBound())){
            exp %= expBound;
            setLevel(getLevel() + 1);
            setHp(getLevel() * 100);
            setMp((getMp() * 2));
            System.out.println(name + " has leveled up to level " + getLevel() + "!");
        }
        System.out.println(name + " has gained " + exp_add + " experience points! (" + exp + "/" + expBound + ") Level " + getLevel());
    }

    /**
     * Gets a list of items in the hero's inventory.
     * @return A list of items in the hero's inventory.
     */
    public String[] getInventoryNames(){
        String[] names = new String[heroInventory.size()];
        for (int i = 0; i < names.length; i++){
            names[i] = heroInventory.items.get(i).name;
        }
        return names;
    }

    /**
     * Hero sells an item
     * @param itemName The name of the item to sell.
     * @return True if the item was sold successfully, false otherwise.
     */
    public boolean sellItem(String itemName) {
        for (Item item : heroInventory.items) {
            if (item.name.equals(itemName)) {
                money += item.purchasePrice/2;
                removeFromInventory(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the hero's money
     * @return The hero's money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Gets an item from the hero's inventory.
     * @param itemName The name of the item to get.
     * @return The item if it exists, null otherwise.
     */
    public Item getItem(String itemName){
        for (Item item : heroInventory.items) {
            if (item.name.equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Equips an item.
     * @param equipOption The type of item to equip.
     */
    public void equip(String equipOption) {
        switch (equipOption) {
            case "Weapon":
                equipWeapon();
                break;
            case "Armor":
                equipArmor();
                break;
        }
    }

    /**
     * Equips a weapon.
     */
    private void equipWeapon(){
        DataList<Weapon> weaponList = getItemsByType(Weapon.class);
        if (weaponList.isEmpty()){
            System.out.println("You have no weapons to equip!");
            return;
        }
        System.out.println(weaponList);
        String weaponOption = getItemOption(weaponList);
        weapon = weaponList.get(getIndexItem(weaponList, weaponOption));
    }

    /**
     * Equips armor.
     */
    private void equipArmor(){
        DataList<Armor> armorList = getItemsByType(Armor.class);
        if (armorList.isEmpty()){
            System.out.println("You have no armor to equip!");
            return;
        }
        System.out.println(armorList);
        String armorOption = getItemOption(armorList);
        armor = armorList.get(getIndexItem(armorList, armorOption));
    }

    /**
     * Asks user to choose an item from a list.
     * @param itemList The list of items to choose from.
     * @return The name of the item chosen.
     */
    private String getItemOption(DataList<? extends Item> itemList) {
        return GameEngine.getOption(itemList.stream().map(item -> item.name).toArray(String[]::new));
    }

    /**
     * Returns a subset of the hero's inventory by type.
     * @param type The type of item to get.
     * @param <K> The return type of item.
     * @return A list of items of the specified type.
     */
    private <K extends Item> DataList<K> getItemsByType(Class<K> type){
        return heroInventory.items.stream().filter(item -> item.getClass().equals(type)).map(item -> (K) item).collect(Collectors.toCollection(DataList::new));
    }

    /**
     * Attacks a monster.
     * @param monsters The list of monsters to attack.
     * @return The list of monsters after attack.
     */
    public DataList<Monster> attackMonster(DataList<Monster> monsters) {
        if (weapon == null) {
            System.out.println("You don't have a weapon equipped!");
            equipWeapon();
            return monsters;
        }
        System.out.println("Choose a monster to attack:");
        String[] monsterOptions = GameEngine.getLegendsAlive(monsters);
        String monsterName = GameEngine.getOption(monsterOptions);
        int index = GameEngine.legendIndexByName(monsters, monsterName);
        int damageGiven = monsters.get(index).takeDamage((int) ((weapon.getDamage() + strength) * 0.1));
        System.out.println(name + " dealt " + damageGiven + " damage to " + monsters.get(index).name + "!");
        if (monsters.get(index).getStatus() == LegendStatus.DEAD) {
            System.out.println(monsters.get(index).name + " has been defeated!");
        }
        return monsters;
    }

    /**
     * String representation of a hero.
     * @return A string representation of a hero.
     */
    @Override
    public String toString() {
        return super.stringify(
                "MP: " + mp + "\n" +
                "Strength: " + strength + "\n" +
                "Agility: " + agility + "\n" +
                "Dexterity: " + dexterity + "\n" +
                "Money: " + money + "\n" +
                "-----------------------------------------\n" +
                "Weapon: " + ((weapon != null) ? weapon : "Not Equipped") + "\n" +
                "Armor: " + ((armor != null) ? armor : "Not Equipped") + "\n" +
                "-----------------------------------------\n" +
                heroInventory + "\n");
    }

    /**
     * Takes damage.
     * @param damage The amount of damage to take.
     * @return The amount of damage taken.
     */
    public int takeDamage(int damage) {
        return super.takeDamage(0.99, agility, damage, (armor != null) ? armor.getDefense() : 0);
    }

    /**
     * Casts a spell on a monster.
     * @param monsters The list of monsters to cast the spell on.
     * @return The list of monsters after spell is cast.
     */
    public DataList<Monster> castSpellOnMonster(DataList<Monster> monsters) {
        DataList<Spell> spellList = getItemsByType(Spell.class);
        if (spellList.isEmpty()){
            System.out.println("You have no spells to cast!");
            return monsters;
        }
        String spellOption = getItemOption(spellList);
        Spell spell = spellList.get(getIndexItem(spellList, spellOption));
        if (spell.getMpCost() > mp){
            System.out.println("You don't have enough MP to cast this spell!");
            heroInventory.add(spell);
            return monsters;
        }
        mp -= spell.getMpCost();
        int targetIndex = GameEngine.getIntOption(GameEngine.getLegendOptions(monsters));
        int statusEffect = monsters.get(targetIndex).applyEffect(spell);
        System.out.println(name + " cast " + spell.name + " on " + monsters.get(targetIndex).name + "!");
        System.out.println(monsters.get(targetIndex).name + "'s " + spell.getSpellEffectName() + " went down by " + statusEffect + "!");
        int inDamage = spell.getDamageAmount() + (dexterity/10000)*spell.getDamageAmount();
        int damageGiven = monsters.get(targetIndex).takeDamage(inDamage);
        System.out.println(name + " dealt " + damageGiven + " damage to " + monsters.get(targetIndex).name + "!");
        heroInventory.use(spell);
        return monsters;
    }

    /**
     * Removes an item from the hero's inventory and returns its index in the list.
     * @param itemList The list of items to remove from.
     * @param itemOption The name of the item to remove.
     */
    private int getIndexItem(DataList<? extends Item> itemList, String itemOption) {
        for (Item item : itemList){
            if (item.name.equals(itemOption)){
                removeFromInventory(item);
                if (item instanceof Armor) {
                    if (armor != null) {
                        if (!addToInventory(armor)) {
                            return -1;
                        }
                    }
                }
                if (item instanceof Weapon) {
                    if (weapon != null) {
                        if (!addToInventory(weapon)) {
                            return -1;
                        }
                    }
                }
                return itemList.indexOf(item);
            }
        }
        return -1;
    }

    /**
     * Uses a potion.
     * @return An option to restore everyone's health by some int > 0 or not (int = 0).
     */
    public int usePotion() {
        DataList<Potion> potionList = getItemsByType(Potion.class);
        if (potionList.isEmpty()){
            System.out.println("You have no potions to use!");
            return 0;
        }
        String potionOption = getItemOption(potionList);
        Potion potion = potionList.get(getIndexItem(potionList, potionOption));
        int changeAttr = potion.getChangeAttr();
        boolean allHealFlag = false;
        String[] attrs = potion.getTargetAttrs();
        for (String attr : attrs){
            switch (attr){
                case "Health":
                    setHp(getHp() + changeAttr);
                    System.out.println(name + " healed by " + changeAttr + " health!");
                    break;
                case "Mana":
                    mp += changeAttr;
                    System.out.println(name + " restored " + changeAttr + " mana!");
                    break;
                case "Strength":
                    strength += changeAttr;
                    System.out.println(name + " increased strength by " + changeAttr + "!");
                    break;
                case "Agility":
                    agility += changeAttr;
                    System.out.println(name + " increased agility by " + changeAttr + "!");
                    break;
                case "Dexterity":
                    dexterity += changeAttr;
                    System.out.println(name + " increased dexterity by " + changeAttr + "!");
                    break;
                case "Defense":
                    if (armor != null) {
                        armor.applyPotion(potion);
                        System.out.println(name + " increased defense by " + changeAttr + "!");
                    }
                    break;
                case "All_Health":
                    allHealFlag = true;
                default:
                    break;
            }
        }
        heroInventory.use(potion);
        return (allHealFlag) ? changeAttr : 0;
    }

    /**
     * Sets the hero's money.
     * @param money The amount of money to set.
     */
    public void setMoney(int money) {
        this.money = money;
        System.out.println(Color.color(Color.bgBrightYellow, name + " now has " + this.money + " gold!"));
    }

    /**
     * Sets the hero's mana.
     * @param mp The amount of mana to set.
     */
    public void setMp(int mp) {
        this.mp = mp;
    }

    /**
     * Gets the hero's mana.
     * @return The hero's mana.
     */
    public int getMp() {
        return mp;
    }

    /**
     * Gets the hero's base mana.
     * @return The hero's base mana.
     */
    public int getBaseMp() {
        return baseMp;
    }
}
