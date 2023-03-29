/**
 * Accessible space that allows heroes to buy and sell items.
 */
public class Market extends Space implements Accessible {

    /**
     * List of heroes in the market
     */
    DataList<Hero> heroes = null;

    /**
     * Constructor for Market
     */
    public Market(){
        super('M');
    }

    /**
     * Holds heroes in the market.
     * @param heroes List of heroes to be held.
     */
    @Override
    public void holdHeroes(DataList<Hero> heroes) {
        this.heroes = heroes;
        markVisited();
    }

    /**
     * Removes heroes from the market.
     */
    @Override
    public void leaveHeroes() {
        heroes = null;
    }

    /**
     * Marks the space as visited
     */
    @Override
    public void markVisited() {
        visited = true;
    }

    /**
     * Method to try and access a space
     * @param heroes List of heroes trying to access the space
     * @return True if access is granted, false otherwise
     */
    @Override
    public boolean tryAccess(DataList<Hero> heroes) {
        holdHeroes(heroes);
        System.out.println(Color.color(Color.green, "Market!"));
        return true;
    }

    /**
     * Method to print the space
     * @return String representation of the space
     */
    @Override
    public String toString() {
        return (heroes != null) ? Color.color(Color.bgGreen , "H") : Color.color(Color.green, (getSymbol() + ""));
    }

    /**
     * Method to allow heroes to buy and sell items.
     * @param heroes List of heroes to be allowed to buy and sell items.
     * @return List of heroes that have bought and sold items.
     */
    public DataList<Hero> goToMarket(DataList<Hero> heroes) {
        do {
            String[] itemTypes = {"Potions", "Weaponry", "Armory", "FireSpells", "IceSpells", "LightningSpells"};
            DataLoader.dl.printInnerMaps(itemTypes);
            for (Hero hero : heroes) {
                System.out.println(hero);
                System.out.println(hero.name + ", would you like to buy or sell items?");
                String buyOrSell = GameEngine.getOption(new String[]{"Buy", "Sell", "Skip"});
                if (buyOrSell.equals("Buy")) {
                    System.out.println("Select an item class to buy from: ");
                    String itemType = GameEngine.getOption(itemTypes);
                    System.out.println("Select an item to buy: ");
                    String itemName = GameEngine.getOption(DataLoader.dl.getInnerMapKeys(itemType));
                    System.out.println("You have selected: " + itemName);
                    System.out.println("Buy " + itemName + " for " + DataLoader.dl.getInnerMap(itemType, itemName).get("cost") + "? " + hero.name + " has " + hero.getMoney() + " gold.");
                    if (GameEngine.getOption(new String[]{"Yes", "No"}).equals("No")) {
                        continue;
                    }
                    if (!hero.buyItem(Item.itemCreator(itemType, itemName, DataLoader.dl.getInnerMap(itemType, itemName)))) {
                        System.out.println(Color.color(Color.brightYellow, "Failed to buy " + itemName));
                    } else {
                        System.out.println(Color.color(Color.cyan, "Successfully bought " + itemName));
                    }
                }
                if (buyOrSell.equals("Sell")) {
                    System.out.println("Select an item to sell: ");
                    String itemName = GameEngine.getOption(hero.getInventoryNames());
                    System.out.println("You have selected: " + itemName);
                    System.out.println("Sell " + itemName + " for " + hero.getItem(itemName).purchasePrice / 2 + "? " + hero.name + " has " + hero.getMoney() + " gold.");
                    if (GameEngine.getOption(new String[]{"Yes", "No"}).equals("No")) {
                        continue;
                    }
                    if (!hero.sellItem(itemName)) {
                        System.out.println(Color.color(Color.brightYellow, "You cannot sell this item."));
                    }
                }
            }
            System.out.println("Party of heroes, would you like to continue shopping?");
        } while (!GameEngine.getOption(new String[]{"Yes", "No"}).equals("No"));

        return heroes;
    }

}
