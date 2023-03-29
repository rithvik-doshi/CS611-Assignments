/**
 * Rithvik Doshi, 2023
 */

/**
 * Extension of Board Class for Connect4
 */
public class C4Board extends Board {
    /**
     * Constructor
     * @param cols # of cals
     * @param rows # or rows
     */
    public C4Board(int cols, int rows) {
        super(cols, rows);
    }

    /**
     * Places piece on a board
     * @param piece to be placed
     * @param position to be placed in
     * @return success/failure
     */
    @Override
    public boolean placePiece(Piece piece, int position) {
        if (position > cols || position < 1) return false;
        position--;
        int depth = rows - 1;

        while (depth > -1) {
            Cell cell = matrix[depth][position % cols];
            if (cell.peepPiece() != null) {
                depth--;
            } else if (cell.placePiece(piece)){
                num_pieces++;
                return true;
            }
        }
        return false;
    }

    /**
     * String representation of board
     * @return string
     */
    public String toString() {
        StringBuilder outstring = new StringBuilder();
        for (Cell[] row : matrix) {
            for (int i = 0; i < cols; i++){
                outstring.append("+---");
            }
            outstring.append("+\n");
            for (Cell cell : row) {
                outstring.append("| ").append(cell.toString()).append(" ");
            }
            outstring.append("|\n");
        }

        for (int i = 0; i < cols; i++){
            outstring.append("+---");
        }
        outstring.append("+");

        return outstring.toString();

    }

}
