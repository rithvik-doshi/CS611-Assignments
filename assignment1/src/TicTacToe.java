/**
 * Rithvik Doshi, 2023
 */

import java.util.Scanner;

/**
 * Tic Tac Toe Game Class
 */
public class TicTacToe extends Game {

    /**
     * Rules to be used by TTT
     */
    private static class TTT_Rules extends Rules {

        /**
         * Constructor
         * @param diag diagnoal checking
         * @param min_num of pieces for win
         */
        public TTT_Rules(boolean diag, int min_num) {
            super(diag, min_num);
        }

        /**
         * Determine status of game
         * @param board to check
         * @return 1 if win, -1 if draw, 0 if continue
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
     * @param players array of players to play the game
     */
    public TicTacToe(Player[] players) {
        super(makePieces(players.length), players);
    }

    /**
     * method to start the game
     * @return array of players, updated
     */
    @Override
    protected Player[] play_game() {

        Scanner scanner = new Scanner(System.in);

        int baseN = 3;

        setBoardSize(scanner, baseN, games_implemented[0]);

        int[] board_dims = board.getDims();
        boolean diag = true;
        int min_num = -1;
        int lower_limit = 3;
        String baseConditions = "Diagonals enabled\n\tWin condition: straight row, col or diag from edge to edge (no set #)\n";
        Object[] params = new Object[]{diag, min_num};
        params = getRuleParams(scanner, baseN, board_dims, diag, min_num, lower_limit, baseConditions, params);

        this.rules = new TTT_Rules((Boolean) params[0], (int) params[1]);

        while (true){
            printBoard();
            System.out.println(players[turn].getName() + "'s turn. Select a square:");
            int position = GameEngine.getNextInt(scanner);

            while (placePiece(pieceset[turn], position)){
                printBoard();
                System.out.println(players[turn].getName() + "'s turn. Select a square:");
                position = GameEngine.getNextInt(scanner);
            }

            int result = rules.apply_rules(board);

            if (result == 0){
                advance_turn();
            } else if (result == 1) {
                printBoard();
                players[turn].addWin(games_implemented[0]);
                System.out.println(players[turn].getName() + " won! Try again? (1 to continue, 0 to exit)");
                if (resetBoard(scanner, games_implemented[0])) break;
            } else {
                printBoard();
                System.out.println("Draw! Try again? (1 to continue, 0 to exit)");
                if (resetBoard(scanner, games_implemented[0])) break;
            }
        }

        return players;

    }




}