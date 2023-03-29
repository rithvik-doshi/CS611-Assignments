/**
 * Common (battle) space, where heroes can fight monsters.
 */
public class Common extends Space implements Accessible{

    /**
     * List of heroes
     */
    private DataList<Hero> heroes;

    /**
     * Constructor
     */
    public Common(){
        super(' ');
    }

    /**
     * Method to hold heroes in a space
     * @param heroes List of heroes to be held.
     */
    @Override
    public void holdHeroes(DataList<Hero> heroes) {
        this.heroes = heroes;
        markVisited();
    }

    /**
     * Method to let heroes leave a space
     */
    @Override
    public void leaveHeroes() {
        heroes = null;
    }

    /**
     * Method to check if a space has been occupied
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
        if (Math.random() < 0.5){
            System.out.println(Color.color(Color.brightRed, "Battle!"));
            heroes = battle(heroes);
        } else {
            System.out.println(Color.color(Color.brightYellow, "No battle!"));
        }
        holdHeroes(heroes);
        return true;
    }

    /**
     * Battle method
     * @param heroes List of heroes before battle
     * @return List of heroes after battle
     */
    private DataList<Hero> battle(DataList<Hero> heroes) {

        DataList<Monster> monsters = generateMonsters(heroes);
        System.out.println(Color.color(Color.brightRed, "Monsters:\n" + monsters));
        
        while (someAlive(heroes) && someAlive(monsters)){
            for (Hero hero : heroes){
                if (hero.getStatus() == LegendStatus.ALIVE){
                    System.out.println("Hero " + hero.name + "'s turn: ");
                    String playerOption;
                    String[] playerOptions = {"Attack with weapon", "Cast a spell", "Use a potion", "Equip weapon/armor", "Info"};
                    while ((playerOption = GameEngine.getOption(playerOptions)).equals("Info")) {
                        printInfo(heroes, monsters);
                        System.out.println("Hero " + hero.name + "'s turn: ");
                    }
                    switch (playerOption){
                        case "Attack with weapon":
                            monsters = hero.attackMonster(monsters);
                            break;
                        case "Cast a spell":
                            monsters = hero.castSpellOnMonster(monsters);
                            break;
                        case "Use a potion":
                            int changeAttr;
                            if ((changeAttr = hero.usePotion()) > 0){
                                for (Hero healed : heroes) {
                                    healed.setHp(healed.getHp() + changeAttr);
                                    System.out.println(Color.color(Color.bgGreen, healed.name + " healed by " + changeAttr + " health!"));
                                }
                            }
                            break;
                        case "Equip weapon/armor":
                            String equipOption = GameEngine.getOption(new String[]{"Weapon", "Armor"});
                            hero.equip(equipOption);
                            break;
                    }
                    if (!someAlive(monsters)){
                        break;
                    }
                } else {
                    System.out.println(Color.color(Color.bgRed, hero.name + " is dead!"));
                }
            }
            for (Monster monster : monsters){
                if (monster.getStatus() == LegendStatus.ALIVE){
                    System.out.println("Monster " + monster.name + "'s turn: ");
                    heroes = monster.attackHero(heroes);
                }
            }
        }
        System.out.println(Color.color(Color.bgBlue, "Battle over!"));
        if (someAlive(heroes)){
            System.out.println(Color.color(Color.brightGreen, "Heroes won!"));
            heroes = calculateStats(heroes, monsters);
        }
        return heroes;
    }

    /**
     * Update heroes' stats after battle
     * @param heroes List of heroes
     * @param monsters List of monsters defeated
     * @return List of heroes with updated stats
     */
    private DataList<Hero> calculateStats(DataList<Hero> heroes, DataList<Monster> monsters) {
        int maxLevel = getMaxLevel(monsters);
        for (Hero hero : heroes){
            if (hero.getStatus() == LegendStatus.ALIVE){
                hero.setMoney(hero.getMoney() + 100*maxLevel);
                hero.setHp((int) (hero.getHp() + hero.getBaseHp()*1.1));
                hero.setMp((int) (hero.getMp() + hero.getBaseMp()*1.1));
                hero.addExp(monsters.size()*maxLevel*10);
                System.out.println(Color.color(Color.bgGreen, hero.name + " now has " + hero.getHp() + " HP!"));
                System.out.println(Color.color(Color.magenta, hero.name + " now has " + hero.getMp() + " MP!"));
            }
        }
        return heroes;
    }

    /**
     * Get info about a hero or monster
     * @param heroes List of heroes
     * @param monsters List of monsters
     */
    private void printInfo(DataList<Hero> heroes, DataList<Monster> monsters) {
        if (GameEngine.getOption(new String[]{"Heroes", "Monsters"}).equals("Heroes")){
            String heroOption = GameEngine.chooseLegend(heroes);
            System.out.println(getByName(heroes, heroOption));
        } else {
            String monsterOption = GameEngine.chooseLegend(monsters);
            System.out.println(getByName(monsters, monsterOption));
        }
    }

    /**
     * Get a legend by name
     * @param legends List of legends
     * @param name Name of legend
     * @return Legend selected
     */
    private Legend getByName(DataList<? extends Legend> legends, String name){
        for (Legend legend : legends){
            if (legend.name.equals(name)){
                return legend;
            }
        }
        return null;
    }

    /**
     * Check if any legends are alive
     * @param legends List of legends
     * @return True if any are alive, false otherwise
     */
    private boolean someAlive(DataList<? extends Legend> legends){
        for (Legend legend : legends){
            if (legend.getStatus() == LegendStatus.ALIVE){
                return true;
            }
        }
        return false;
    }

    /**
     * Generate monsters for battle
     * @param heroes List of heroes
     * @return List of monsters
     */
    private DataList<Monster> generateMonsters(DataList<Hero> heroes) {
        int maxLevel = getMaxLevel(heroes);
        DataList<Monster> monsters = new DataList<>(LMH.maxHeroes);
        do {
            monsters.add(Monster.monsterCreator(maxLevel));
        } while (Math.random() < (0.8 - (Math.pow(0.4, heroes.size()))) && monsters.size() < LMH.maxHeroes);
        return monsters;

    }

    /**
     * Get the highest legend level from a list of legends
     * @param legends List of legends
     * @return Highest level
     */
    private int getMaxLevel(DataList<? extends Legend> legends){
        int maxLevel = 0;
        for (Legend legend : legends){
            if (legend.getLevel() > maxLevel){
                maxLevel = legend.getLevel();
            }
        }
        return maxLevel;
    }

    /**
     * String representation of a Common space
     * @return string
     */
    @Override
    public String toString() {
        return (heroes != null) ? Color.color(Color.brightRed, "H") : Color.color(Color.bgYellow, (getSymbol() + ""));
    }

}
