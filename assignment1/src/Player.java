/**
 * Rithvik Doshi, 2023
 */

import java.util.HashMap;

/**
 * Player class to define a player and keep track of wins
 */
public class Player {

    /**
     * Player name
     */
    private final String name;

    /**
     * Player wins
     */
    private final HashMap<String, Integer> wins = new HashMap<>();

    /**
     * Constructor
     * @param name player name
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Get player's name
     * @return string of player's name
     */
    public String getName(){
        return this.name;
    }


    /**
     * Adds a win to the player based on game key
     * @param game string name of game
     */
    public void addWin(String game){
        if (!wins.containsKey(game)) {
            wins.put(game, 0);
        }
        wins.put(game, wins.get(game) + 1);
    }

    /**
     * String representation of player
     * @return string
     */
    public String toString(){
        StringBuilder out = new StringBuilder();
        out.append(this.name).append(":");
        if (this.wins.isEmpty()){
            out.append("\n\tNo wins");
        }
        for (String key : wins.keySet())
            out.append("\n\t").append(key).append(": ").append(wins.get(key));
        return out.toString();
    }

}
