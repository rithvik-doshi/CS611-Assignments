import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the main class of the game. It handles the game logic and the
 * game loop.
 */
public class LMH {
    /**
     * Map of the game.
     */
    private final Map map;

    /**
     * List of heroes in the game.
     */
    private DataList<Hero> heroes;

    /**
     * Maximum number of heroes allowed in the game.
     */
    static final int maxHeroes = 3;

    /**
     * Constructor for the LMH class.
     * @throws FileNotFoundException If the file is not found.
     */
    public LMH() throws FileNotFoundException {

        System.out.println("Do you want to see the instructions? (Y/N)");
        String printInst = GameEngine.getOption(new String[]{"Y", "N"});
        if (printInst.equals("Y")) printInstructions();

        System.out.println("Select a map to play on: ");
        while (true){
            System.out.println("Generating map...");
            Map tempMap = new Map(8);
            System.out.println(tempMap);
            System.out.println("Confirm this map? (1 for Yes, 2 for No)");
            String input = GameEngine.getOption(new String[]{"Y", "N"});
            if (input.equals("Y")) {
                this.map = tempMap;
                break;
            }
        }
        this.heroes = new DataList<>(maxHeroes);
        makeHeroes();
    }

    /**
     * Adds a hero to the list of heroes.
     * @param hero Hero to be added.
     */
    public void addHero(Hero hero){
        heroes.add(hero);
    }

    /**
     * Allows users to create heroes.
     * @throws FileNotFoundException If the file is not found.
     */
    private void makeHeroes() throws FileNotFoundException {
        System.out.println("Choose the number of heroes to embark on a quest");
        int num = GameEngine.getInt(1, maxHeroes);
        String[] heroClasses = {"Paladins", "Sorcerers", "Warriors"};
        DataLoader.dl.printInnerMaps(heroClasses);
        for (int i = 0; i < num; i++){
            System.out.println("Hero " + (i + 1) + ", choose your class and hero!");
            String classOption = GameEngine.getOption(heroClasses);
            String heroOption = GameEngine.getOption(DataLoader.dl.getInnerMapKeys(classOption));
            System.out.println("You have chosen: " + heroOption);
            DataMap<String, String> heroData = DataLoader.dl.getInnerMap(classOption, heroOption);
            this.addHero(new Hero(heroOption, heroData, classOption));
        }
        map.heroInitialPlace(heroes);
    }

    /**
     * Handles the game loop.
     * @throws FileNotFoundException If the file is not found.
     */
    public void start() throws FileNotFoundException {
        while (true) {

            if (checkGameOver()) {
                System.out.println(Color.color(Color.red, "All heroes are dead. Game over!"));
                System.exit(0);
            }
            System.out.println(this.map);
            char control = GameEngine.getControl(map.getSpace().getSymbol());
            if (control == 'Q') {
                System.out.println(Color.color(Color.bold, "Thanks for playing!"));
                System.exit(0);
            }
            if (control == 'I') {
                System.out.println(Color.color(Color.bgBrightWhite, "Hero Information:"));
                System.out.println(heroes);
            }
            if (control == 'M') {
                Space currentSpace = map.getSpace();
                if (currentSpace.getSymbol() == 'M') {
                    Market market = (Market) currentSpace;
                    this.heroes = market.goToMarket(heroes);
                } else {
                    System.out.println("You are not in a market.");
                }
            }
            if (control == 'W' || control == 'A' || control == 'S' || control == 'D') {
                if (!map.moveHeroes(heroes, control)) {
                    System.out.println(Color.color(Color.bgBlack, "You cannot access this space"));
                }
            }
        }
    }

    /**
     * Checks if the game is over.
     * @return True if the game is over, false otherwise.
     */
    public boolean checkGameOver(){
        for (Hero hero : heroes){
            if (hero.getStatus() == LegendStatus.ALIVE){
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the instructions of the game from instructions.md
     */
    private void printInstructions() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Instructions.md"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n\n\n");
    }
}
