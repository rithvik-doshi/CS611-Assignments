/**
 * Rithvik Doshi, 2023
 */

/**
 * Board to be used by TTT-like games
 */
public class TTTBoard extends Board {

    /**
     * Constructor
     * @param cols # of cols
     * @param rows # of rows
     */
    public TTTBoard(int cols, int rows) {
        super(cols, rows);
    }

    /**
     *
     * @param piece to be placed
     * @param position to be placed in
     * @return success/failure
     */
    public boolean placePiece(Piece piece, int position) {
        if (position > cols * rows || position < 1) return false;
        position--;
        Cell cell = matrix[position / cols][position % cols];
        if (cell.peepPiece() == null && cell.placePiece(piece)){
            num_pieces++;
            return true;
        }
        return false;
    }

}
