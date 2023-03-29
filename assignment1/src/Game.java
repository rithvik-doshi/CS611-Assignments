/**
 * Rithvik Doshi, 2023
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Abstract Game Class that contains general methods to interact with board, rules, other objects, plus a static method
 * to run the game system.
 */
public abstract class Game {

    /**
     * Board that the game uses
     */
    protected Board board;

    /**
     * Player array that the game uses to keep track of players
     */
    protected Player[] players;

    /**
     * Number of players
     */
    protected final int N_Players;

    /**
     * Number to determine which player's turn it is
     */
    protected int turn;

    /**
     * Names of games implemented
     */
    protected static String[] games_implemented = GameEngine.games_implemented;

    /**
     * Set of pieces for players to use
     */
    protected final Piece[] pieceset;

    /**
     * Set of rules to be used
     */
    protected Rules rules;

    /**
     * Constructor for Game class
     *
     * @param pieceset set of pieces to be passed into the game.
     * @param players  set of players to be passed into the game.
     */
    public Game(Piece[] pieceset, Player[] players) {
        this.players = players;
        this.N_Players = this.players.length;
        this.turn = 0;
        this.pieceset = pieceset;
    }

    /**
     * Prints the board using its String representation.
     */
    public void printBoard() {
        System.out.println(board);
    }

    /**
     * Clears the board by setting a new board, based on the game that is passed in.
     *
     * @param game String that says which game is being played.
     */
    public void clearBoard(String game) {
        int[] dims = board.getDims();
        if (game.equals(games_implemented[2])) {
            this.board = new C4Board(dims[0], dims[1]);
        } else {
            this.board = new TTTBoard(dims[0], dims[1]);
        }
    }

    /**
     * Places a piece on a board.
     *
     * @param piece    Piece to be placed
     * @param position Position in which to place it.
     * @return false if succeeds, true if it fails.
     */
    public boolean placePiece(Piece piece, int position) {
        boolean out = board.placePiece(piece, position);
        if (!out) {
            System.out.println("Not a valid piece placement.");
        }
        return !out;
    }

    /**
     * Advances the turn variable depending on N_players
     */
    public void advance_turn() {
        turn = (turn + 1) % N_Players;
    }

    /**
     * Advances an integer (useful for Order And Chaos where there's a separate count of which player is Order/Chaos)
     *
     * @param in int to be advanced
     * @return the advanced int
     */
    public int advance(int in) {
        return (in + 1) % N_Players;
    }

    /**
     * Determines whether to clear the board and restart the game, or exit the game, based on user input.
     *
     * @param scanner scanner used to read next int
     * @param game    String that says which game is being played.
     * @return true if no restart, false + board cleared if restart requested.
     */
    public boolean resetBoard(Scanner scanner, String game) {
        int try_again = GameEngine.getConfirm(scanner, 0, 1, "Invalid entry. Try again with a 0 (exit) or " +
                "1 (continue):");
        if (try_again == 0) {
            return true;
        }
        clearBoard(game);
        turn = chooseTurn(scanner);
        return false;
    }

    /**
     * Allows the player to choose whether they would like to start first or let their opponent go first. Only if 2
     * players are playing a game, else defaults to first player.
     *
     * @param scanner scanner used to read next int
     * @return the index of the player who will go next.
     */
    private int chooseTurn(Scanner scanner) {
        if (N_Players == 2) {
            String opponent = players[(turn + 1) % N_Players].getName();
            System.out.println(players[turn].getName() + ", since you won, would you like to start first? Or would " +
                    "you like to let " + opponent + " start? (1 = You, 0 = " + opponent + ")");
            int startAgain = GameEngine.getConfirm(scanner, 0, 1, "Invalid entry. Try again with a 0 (" +
                    opponent + ") or 1 (You):");
            return (turn + startAgain + 1) % N_Players;
        } else {
            return 0;
        }
    }

    /**
     * Initializes a board based on user preferences, minimum square size and the game being set up
     *
     * @param scanner scanner used to read board dimensions
     * @param baseN   minimum board size
     * @param game    String that says which game is being played.
     */
    public void setBoardSize(Scanner scanner, int baseN, String game) {
        System.out.println("Enter the size of your board (WxH, " + baseN + " <= {W, H} <= 20): ");
        Pattern pattern = Pattern.compile("[0-9]+x[0-9]+");
        String dims = scanner.next();
        Matcher matcher = pattern.matcher(dims);
        while (!matcher.matches() || GameEngine.notWithinLimits(baseN, 20, dims, "x")) {
            System.out.println("Not a valid input. Try again: ");
            dims = scanner.next();
            matcher = pattern.matcher(dims);
        }

        String[] splitstr = dims.split("x");
        if (game.equals(games_implemented[2])) {
            this.board = new C4Board(Integer.parseInt(splitstr[0]), Integer.parseInt(splitstr[1]));
        } else {
            this.board = new TTTBoard(Integer.parseInt(splitstr[0]), Integer.parseInt(splitstr[1]));
        }
    }

    /**
     * Decides the rules for each game based on user input
     * @param scanner scanner to get input
     * @param baseN smallest possible board dimensions
     * @param board_dims dimensions that user asked for
     * @param diag whether diagonal checking is on (true by default)
     * @param min_num minimum number of pieces in a row to determine victory
     * @param lower_limit lower limit of min num
     * @param baseConditions String describing the default conditions of the game
     * @param params output parameters
     * @return params
     */
    public static Object[] getRuleParams(Scanner scanner, int baseN, int[] board_dims, boolean diag, int min_num, int lower_limit, String baseConditions, Object[] params) {
        if (board_dims[0] > baseN || board_dims[1] > baseN){
            System.out.println("Your selected board is larger than a " + baseN + "x" + baseN + " board. Would you like to change the win conditions from default?\n" +
                    "Default conditions:\n\t" + baseConditions + "(1 = yes, 0 = no)");
            int confirm = GameEngine.getConfirm(scanner, 0, 1, "Invalid option, enter either 1 (yes) or 0 (no)");
            if (confirm == 1){
                System.out.println("Do you want to enable diagonal checking? (1 = yes, 0 = no)");
                int confirm_diag = GameEngine.getConfirm(scanner, 0, 1, "Invalid option, enter either 1 (yes) or 0 (no)");
                diag = confirm_diag == 1;
                System.out.println("Enter the min number of pieces needed to declare a win: ");
                min_num = GameEngine.getNextInt(scanner);
                while (min_num < lower_limit || min_num > Math.min(board_dims[0], board_dims[1])){
                    System.out.println("Invalid option, enter a number between " + lower_limit + " and " + Math.min(board_dims[0], board_dims[1]));
                    min_num = GameEngine.getNextInt(scanner);
                }
            }
            params = new Object[]{diag, min_num};
        }
        return params;
    }

    /**
     * For games with 2 < players <= 9, creates enough to be used by each player.
     *
     * @param N_players number of players
     * @return Piece array
     */
    public static Piece[] makePieces(int N_players) {
        Piece[] outpieces = new Piece[N_players];
        String pieces = "XO□∂ƒßπΩ";
        for (int i = 0; i < Math.min(N_players, pieces.length()); i++) {
            outpieces[i] = new Piece(pieces.charAt(i));
        }
        return outpieces;
    }

    /**
     * Method to start and run a specific game.
     *
     * @return player array of players who participated in the game and updated win count.
     */
    protected abstract Player[] play_game();

}
