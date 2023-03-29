/**
 * Rithvik Doshi, 2023
 * Assignment 3 := Legends: Monsters and Heroes
 */

import java.io.FileNotFoundException;

/**
 * Main Class to be run in order to play the game.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to Legends: Monsters and Heroes!\n");
        LMH game = new LMH();
        game.start();
    }
}
