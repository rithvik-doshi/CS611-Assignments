/**
 * Rithvik Doshi, 2023
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Game Engine Class that provides general I/O methods and a static method to run the system of games.
 */
public class GameEngine {

    /**
     * List of games that have been implemented
     */
    protected static String[] games_implemented = new String[]{"Tic Tac Toe", "Order and Chaos", "Connect4"};
    protected static String[] games_in_dev = new String[]{"Checkers", "3D Chess"};


    /**
     * Wrapper method to get an integer from the console
     *
     * @param scanner scanner used to read next int
     * @return validated input, int that is read in
     */
    public static int getNextInt(Scanner scanner) {
        int outint;
        do {
            try {
                outint = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid entry. Try again with a number:");
                scanner.nextLine();
            }
        } while (true);
        return outint;
    }

    /**
     * Determines which game is to be played based on user input, and customizes text slightly based on N_players
     *
     * @param scanner   scanner used to get int
     * @param N_players number of players
     * @return the game number/integer menu option
     */
    public static int getGameInt(Scanner scanner, int N_players) {
        int game_int;
        System.out.println("Now that that's settled, which game would you" + ((N_players == 2) ? " both" : " all") +
                " like to play? Enter the corresponding number, 0 to exit, or -1 for stats.");
        for (int i = 1; i < games_implemented.length + 1; i++) {
            System.out.println(i + ". " + games_implemented[i - 1]);
        }
        System.out.println("Games coming soon: " + Arrays.toString(games_in_dev) + " (hopefully)");
        game_int = getConfirm(scanner, -1, games_implemented.length, "Invalid game id. Try again");
        return game_int;
    }

    /**
     * Seeks an input integer value within a given range, prints an error message until valid input
     *
     * @param scanner scanner used to read next int
     * @param low     minimum value of range, inclusive
     * @param high    maximum value of range, inclusive
     * @param message error message to be printed
     * @return a valid integer
     */
    public static int getConfirm(Scanner scanner, int low, int high, String message) {
        int value = getNextInt(scanner);
        while (value < low || value > high) {
            System.out.println(message);
            value = getNextInt(scanner);
        }
        return value;
    }

    /**
     * Prints statistics regarding each player.
     *
     * @param players array of players to print stats about, printed using string representations.
     */
    private static void printPlayers(Player[] players) {
        System.out.println("Wins by each player: ");
        for (Player player : players) {
            System.out.println(player);
        }
    }

    /**
     * Determines if two numbers, passed in through a string with a delimiter, are not within a certain range.
     *
     * @param min   minimum value of range, inclusive
     * @param max   maximum value of range, inclusive
     * @param vals  input string to be split and evaluated
     * @param delim delimiter to separate the values
     * @return true if not within range, false otherwise.
     */
    public static boolean notWithinLimits(int min, int max, String vals, String delim) {
        String[] splitstr = vals.split(delim);
        if (splitstr.length > 2) return false;
        int val1 = Integer.parseInt(splitstr[0]);
        int val2 = Integer.parseInt(splitstr[1]);
        return min > val1 || val1 > max || min > val2 || val2 > max;
    }

    /**
     * Determines if two numbers, passed in through a string with a delimiter, are equal to each other.
     *
     * @param vals  input string to be split and evaluated
     * @param delim delimiter to separate the values
     * @return true if equal, false otherwise
     */
    public static boolean equalDelimInts(String vals, String delim) {
        String[] splitstr = vals.split(delim);
        if (splitstr.length > 2) return false;
        int val1 = Integer.parseInt(splitstr[0]);
        int val2 = Integer.parseInt(splitstr[1]);
        return val1 == val2;
    }

    /**
     * Static method that creates players and presents a menu with the options to choose a game to play, print stats or
     * exit the menu.
     */
    public static void selectAndStart(){
        Scanner scanner;
        int N_players;
        Player[] players;
        int game_int;

        System.out.println("Welcome to Rithvik's IntelliJ'nt Gaming System (RIGS for short).");
        System.out.print("Enter the number of players who'd like to play on the RIGS: ");
        scanner = new Scanner(System.in);
        N_players = getNextInt(scanner);
        while (N_players < 2) {
            System.out.println("Not enough players. Try again with 2 or more players.");
            N_players = getNextInt(scanner);
        }
        players = new Player[N_players];
        for (int i = 0; i < N_players; i++) {
            System.out.println("Player " + (i + 1) + ", enter your name: ");
            String name = scanner.next();
            players[i] = new Player(name);
        }

        while (true) {
            game_int = getGameInt(scanner, N_players);
            while (game_int < 0) {
                printPlayers(players);
                game_int = getGameInt(scanner, N_players);
                if (game_int == 0) {
                    break;
                }
            }
            if (game_int == 0) {
                break;
            }
            switch (games_implemented[game_int - 1]) {
                case "Tic Tac Toe":
                    if (N_players > 9) {
                        System.out.println("Unfortunately, (and reasonably), up to 9 can play the game (1 to " +
                                "confirm, 0 to exit): ");
                        int confirm = getConfirm(scanner, 0, 1, "Invalid option, enter either 1 " +
                                "(confirm) or 0 (exit)");
                        if (confirm == 0) {
                            System.out.println("Exiting...");
                            return;
                        }
                    }
                    System.out.println("Starting Tic Tac Toe...");
                    TicTacToe tttgame = new TicTacToe((N_players <= 9) ? players :
                            (Player[]) Arrays.stream(players, 0, 9).toArray());
                    Player[] tttoutplayers = tttgame.play_game();
                    if (N_players > 9) {
                        System.arraycopy(tttoutplayers, 0, players, 0, 9);
                    } else {
                        players = tttoutplayers;
                    }
                    break;
                case "Order and Chaos":
                    int P1, P2;
                    if (N_players > 2) {
                        System.out.println("Unfortunately, only 2 players can play this game. Select the players " +
                                "that will play this game (#,#): ");
                        final Pattern pattern = Pattern.compile("[0-9]+,[0-9]+");
                        for (int i = 1; i < players.length + 1; i++) {
                            System.out.println(i + ". " + players[i - 1].getName());
                        }
                        String playerStrs = scanner.next();
                        Matcher matcher = pattern.matcher(playerStrs);
                        while (!matcher.matches() || notWithinLimits(1, players.length, playerStrs, ",")
                                || equalDelimInts(playerStrs, ",")) {
                            System.out.println("Not a valid input. Remember, two different players have to be " +
                                    "selected. Try again: ");
                            playerStrs = scanner.next();
                            matcher = pattern.matcher(playerStrs);
                        }
                        String[] splitstr = playerStrs.split(",");
                        System.out.println("Starting Order and Chaos...");
                        P1 = Integer.parseInt(splitstr[0]) - 1;
                        P2 = Integer.parseInt(splitstr[1]) - 1;
                    } else {
                        P1 = 0;
                        P2 = 1;
                    }
                    OrderAndChaos ocgame = new OrderAndChaos(new Player[]{players[P1], players[P2]});
                    Player[] ocoutplayers = ocgame.play_game();
                    players[P1] = ocoutplayers[0];
                    players[P2] = ocoutplayers[1];
                    break;
                case "Connect4":
                    if (N_players > 9) {
                        System.out.println("Unfortunately, (and reasonably), up to 9 can play the game (1 to " +
                                "confirm, 0 to exit): ");
                        int confirm = getConfirm(scanner, 0, 1, "Invalid option, enter either 1 " +
                                "(confirm) or 0 (exit)");
                        if (confirm == 0) {
                            System.out.println("Exiting...");
                            return;
                        }
                    }
                    System.out.println("Starting Connect 4...");
                    Connect4 c4game = new Connect4((N_players <= 9) ? players :
                            (Player[]) Arrays.stream(players, 0, 9).toArray());
                    Player[] c4outplayers = c4game.play_game();
                    if (N_players > 9) {
                        System.arraycopy(c4outplayers, 0, players, 0, 9);
                    } else {
                        players = c4outplayers;
                    }
                    break;
                default:
                    break;
            }
        }
        printPlayers(players);
    }
}
