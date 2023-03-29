import java.util.Scanner;

/**
 * This class contains input/output methods that are used throughout the game.
 */
public class GameEngine {

    /**
     * Takes user input and returns a valid control
     * @param spaceType the type of space the player is on
     * @return the control the player entered
     */
    public static char getControl(char spaceType){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Make a move: WASD to move, Q to quit, I for info");
            if (spaceType == 'M') System.out.print(", M for market");
            System.out.print(": ");
            String input = scanner.nextLine();
            try{
                if (spaceType == 'M'){
                    if (input.matches("[WwAaSsDdQqIiMm]")) return Character.toUpperCase(input.charAt(0));
                } else {
                    if (input.matches("[WwAaSsDdQqIi]")) return Character.toUpperCase(input.charAt(0));
                }
                throw new IllegalArgumentException();
            } catch(IllegalArgumentException e){
                System.out.println("Invalid entry.");
            }
        }
    }

    /**
     * Takes user input and returns a valid option from a list of strings
     * @param options the list of options
     * @return the option the player entered
     */
    public static String getOption(String[] options){
        return options[getIntOption(options)];
    }

    /**
     * Takes user input and returns a valid option from a list of strings
     * @param options the list of options
     * @return the index of the option the player entered
     */
    public static int getIntOption(String[] options){
        Scanner scanner = new Scanner(System.in);
        while(true){
            for (int i = 0; i < options.length; i++){
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();
            try{
                int option = Integer.parseInt(input);
                if (option > 0 && option <= options.length) return option - 1;
                throw new IllegalArgumentException();
            } catch(IllegalArgumentException e){
                System.out.println("Invalid entry.");
            }
        }
    }

    /**
     * Takes user input and returns a valid integer between min and max
     * @param min the minimum value
     * @param max the maximum value
     * @return the integer the player entered
     */
    public static int getInt(int min, int max){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            String input = scanner.nextLine();
            try{
                int num = Integer.parseInt(input);
                if (num >= min && num <= max) return num;
                throw new IllegalArgumentException();
            } catch(IllegalArgumentException e){
                System.out.println("Invalid entry.");
            }
        }
    }

    /**
     * Makes a random choice from a list of strings
     * @param choices the list of choices
     * @return a random choice
     */
    public static String makeRandomChoice(String[] choices){
        return choices[(int)(Math.random() * choices.length)];
    }

    /**
     * Takes user input to choose a legend from a list of legends
     * @param legends the list of legends
     * @return the name of the legend the player chose
     */
    public static String chooseLegend(DataList<? extends Legend> legends) {
        return GameEngine.getOption(getLegendOptions(legends));
    }

    /**
     * Returns legend index by name
     * @param legends the list of legends
     * @param name the name of the legend
     * @return the index of the legend in legends
     */
    public static int legendIndexByName(DataList<? extends Legend> legends, String name) {
        for (int i = 0; i < legends.size(); i++) {
            if (legends.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns an array of legend names
     * @param legends the array of legends
     * @return an array of legend names
     */
    public static String[] getLegendOptions(DataList<? extends Legend> legends) {
        return legends.stream().map(legend -> legend.name).toArray(String[]::new);
    }

    /**
     * Returns an array of legend names that are alive
     * @param legends the array of legends
     * @return an array of legend names that are alive
     */
    public static String[] getLegendsAlive(DataList<? extends Legend> legends) {
        return legends.stream().filter(legend -> (legend.getStatus() == LegendStatus.ALIVE)).map(legend -> legend.name).toArray(String[]::new);
    }



}
