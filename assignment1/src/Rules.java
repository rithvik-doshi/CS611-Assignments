/**
 * Rithvik Doshi, 2023
 */

/**
 * Abstract Rules class for games
 */
public abstract class Rules {

    /**
     * Diagonal checking rule
     */
    private final boolean diag;

    /**
     * minimum number of pieces to consider a win
     */
    private final int min_num;

    /**
     * Constructor
     * @param diag diagonal checking
     * @param min_num of pieces to win
     */
    public Rules(boolean diag, int min_num){
        this.diag = diag;
        this.min_num = min_num;
    }

    /**
     * Check the board to determine if a win has occurred
     * @param board to check
     * @return true if win, false if no win
     */
    public boolean check_win(Board board){
        Cell[][] matrix = board.getMatrix();
        /* Rows */

        if (checkMat(matrix)) return true;

        /* Columns (Matrix transposed) */

        Cell[][] matrixT = board.getMatrixT();

        if (checkMat(matrixT)) return true;

        /* Diagonals (Can be toggled on and off) */

        if (!diag) return false;

        boolean winD;
        int diag_bound = Math.min(board.rows, board.cols);
        int limit = (min_num < 0) ? diag_bound : min_num;

        for (int i = 1; i < board.getArea() + 1; i++){
            if (board.diagUpPossible(i, limit)){
                Cell[] check = board.getDiagUp(i, limit);
                winD = true;
                for (int j = 1; j < check.length; j++){
                    if (check[j].peepPiece() != null && check[j-1].peepPiece() != null){
                        if (!check[j].peepPiece().equals(check[j-1].peepPiece()))
                            winD = false;
                    } else winD = false;
                }
                if (winD) return true;
            }
            if (board.diagDownPossible(i, limit)){
                Cell[] check = board.getDiagDown(i, limit);
                winD = true;
                for (int j = 1; j < check.length; j++){
                    Piece Pj = check[j].peepPiece();
                    Piece Pj_1 = check[j-1].peepPiece();
                    if (Pj != null && Pj_1 != null){
                        if (!Pj.equals(Pj_1))
                            winD = false;
                    } else winD = false;
                }
                if (winD) return true;
            }
        }

        return false;

    }

    /**
     * Determines whether a win has occurred row-wise in a matrix
     * @param inMat matrix to check
     * @return true if win, false if no win
     * @implNote Note that I use this method to check column-wise, by transposing the matrix before using this method.
     */
    public boolean checkMat(Cell[][] inMat) {
        for (Cell[] cells : inMat) {
            boolean win = true;
            int limit = (min_num < 0) ? cells.length : min_num;
            int count = limit-1;
            for (int j = 1; j < cells.length; j++) {
                Piece piece1 = cells[j - 1].peepPiece();
                Piece piece2 = cells[j].peepPiece();
                if (piece1 != null && piece2 != null) {
                    if (piece1.equals(piece2)){
                        win = true;
                        count--;
                    } else {
                        win = false;
                        count = limit-1;
                    }
                } else {
                    win = false;
                    count = limit-1;
                }
                if (count == 0) break;
            }
            if (win && count == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to apply rules to determine a win
     * @param board to apply rules on
     * @return a value based on the result and status of the game
     */
    public abstract int apply_rules(Board board);
}
