/**
 * Rithvik Doshi, 2023
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Order and Chaos Game Class
 */
public class OrderAndChaos extends Game {

    /**
     * Rules used by Order and Chaos
     */
    private static class OC_Rules extends Rules {

        /**
         * Constructor
         * @param diag diagonal checking
         * @param min_num of pieces to win
         */
        public OC_Rules(boolean diag, int min_num) {
            super(diag, min_num);
        }

        /**
         * Determines whether a loss has happened (i.e., Chaos wins)
         * @param board board to apply on
         * @return success/failure
         * @implNote This method hasn't been fully implemented as of yet, for now it just returns false. The only way
         * for chaos to win is if they force a draw.
         */
        private boolean check_loss(Board board){
//            TO BE IMPLEMENTED
//            Check to see if in any row, col, diag, there are enough empty spaces to win

            Cell[][] matrix = board.getMatrix();

//            checkMatLoss(mat)
//            checkMatLoss(matT)
//            checkDiagsLoss

            return false;
        }

        /**
         * Rules to determine status of game
         * @param board to apply rules on
         * @return 1 if win, -1 if loss, 0 to continue game
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
            } else if (check_loss(board)){
                return -1;
            }
            return 0;
        }
    }

    /**
     * Constructor
     * @param players array of players
     */
    public OrderAndChaos(Player[] players) {
        super(makePieces(players.length), players);
    }

    /**
     * Method to start the game
     * @return array of players with updated stats
     */
    @Override
    protected Player[] play_game() {

        Scanner scanner = new Scanner(System.in);

        int baseN = 6;

        setBoardSize(scanner, baseN, games_implemented[1]);

        int[] board_dims = board.getDims();
        boolean diag = true;
        int min_num = 5;
        int lower_limit = 5;
        String baseConditions = "Diagonals enabled\n\tWin condition: 5 in a row\n";
        Object[] params = new Object[]{diag, min_num};
        params = getRuleParams(scanner, baseN, board_dims, diag, min_num, lower_limit, baseConditions, params);

        this.rules = new OrderAndChaos.OC_Rules((Boolean) params[0], (int) params[1]);

        System.out.println("Who will be Order and start first? The other player will be Chaos");
        for (int i = 1; i < players.length + 1; i++){
            System.out.println(i + ". " + players[i - 1].getName());
        }
        int orderNum = GameEngine.getConfirm(scanner, 1, 2, "Invalid option, enter one of the numbers of the players: ");

        turn = orderNum - 1;
        int order_or_chaos = 0;
        String[] ocString = new String[]{"Order", "Chaos"};
        Pattern pattern = Pattern.compile("[0-9]+,[OX]");
        Matcher matcher;

        while (true){
            printBoard();
            System.out.println(ocString[order_or_chaos] + "'s turn (" + players[turn].getName() + "). Select a square and piece (#,O) or (#,X):");
            String position = scanner.next();
            matcher = pattern.matcher(position);
            while (!matcher.matches()){
                System.out.println("Not a valid input. Try again: ");
                position = scanner.next();
                matcher = pattern.matcher(position);
            }
            String[] splitstr = position.split(",");

            while (placePiece(pieceset[(splitstr[1].equals("X")) ? 0 : 1], Integer.parseInt(splitstr[0]))){
                printBoard();
                System.out.println(ocString[order_or_chaos] + "'s turn (" + players[turn].getName() + "). Select a square and piece (#,O) or (#,X):");
                position = scanner.next();
                matcher = pattern.matcher(position);
                while (!matcher.matches()){
                    System.out.println("Not a valid input. Try again: ");
                    position = scanner.next();
                    matcher = pattern.matcher(position);
                }
                splitstr = position.split(",");
            }

            int result = rules.apply_rules(board);

            if (result == 0) {
                advance_turn();
                order_or_chaos = advance(order_or_chaos);
            } else if (result == 1) {
                printBoard();
                int win_player = (order_or_chaos == 0) ? turn : advance(turn);
                players[win_player].addWin(games_implemented[1]);
                System.out.println(players[win_player].getName() + " (Order) won! Try again? (1 to continue, 0 to exit)");
                order_or_chaos = 0;
                if (resetBoard(scanner, games_implemented[1])) break;
            } else {
                printBoard();
                int win_player = (order_or_chaos == 1) ? turn : advance(turn);
                players[win_player].addWin(games_implemented[1]);
                System.out.println(players[win_player].getName() + " (Chaos) won! Try again? (1 to continue, 0 to exit)");
                order_or_chaos = 0;
                if (resetBoard(scanner, games_implemented[1])) break;
            }

        }

        return players;
    }


}
