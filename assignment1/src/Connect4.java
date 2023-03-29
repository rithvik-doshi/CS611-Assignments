/**
 * Rithvik Doshi, 2023
 */

import java.util.Scanner;

/**
 * Connect4 Game Class
 */
public class Connect4 extends Game {

    /**
     * Rules to be used by Connect 4
     */
    private static class C4_Rules extends Rules {

        /**
         * Constructor
         * @param diag diagonal checking
         * @param min_num of pieces to win
         */
        public C4_Rules(boolean diag, int min_num) {
            super(diag, min_num);
        }

        /**
         * Determines whether a win has taken place
         * @param board board to check
         * @return number depending on status (1 for win, 0 for continue, -1 for draw)
         */
        @Override
        public int apply_rules(Board board) {
            if (board.is_full()) {
                if (!check_win(board)) {
                    return -1;
                }
            }
            if (check_win(board)){
                return 1;
            }
            return 0;
        }
    }

    /**
     * Constructor
     * @param players array of players that are playing the game
     */
    public Connect4(Player[] players) {
        super(makePieces(players.length), players);
    }

    /**
     * Method to play the game
     * @return updated player array
     */
    @Override
    protected Player[] play_game() {

        Scanner scanner = new Scanner(System.in);

        int baseN = 6;

        setBoardSize(scanner, baseN, games_implemented[2]);

        int[] board_dims = board.getDims();
        boolean diag = true;
        int min_num = 4;
        int lower_limit = 4;
        String baseConditions = "Diagonals enabled\n\tWin condition: straight row, col or diag from edge to edge (no set #)\n";
        Object[] params = new Object[]{diag, min_num};
        params = getRuleParams(scanner, baseN, board_dims, diag, min_num, lower_limit, baseConditions, params);

        this.rules = new C4_Rules((Boolean) params[0], (int) params[1]);

        while (true){
            printBoard();
            System.out.println(players[turn].getName() + "'s turn. Enter a column number from 1 to " + board.cols + " :");
            int position = GameEngine.getNextInt(scanner);

            while (placePiece(pieceset[turn], position)){
                printBoard();
                System.out.println(players[turn].getName() + "'s turn. Enter a column number from 1 to " + board.cols + " :");
                position = GameEngine.getNextInt(scanner);
            }

            int result = rules.apply_rules(board);

            if (result == 0){
                advance_turn();
            } else if (result == 1) {
                printBoard();
                players[turn].addWin(games_implemented[2]);
                System.out.println(players[turn].getName() + " won! Try again? (1 to continue, 0 to exit)");
                if (resetBoard(scanner, games_implemented[2])) break;
            } else {
                printBoard();
                System.out.println("Draw! Try again? (1 to continue, 0 to exit)");
                if (resetBoard(scanner, games_implemented[2])) break;
            }
        }

        return players;
    }


}
