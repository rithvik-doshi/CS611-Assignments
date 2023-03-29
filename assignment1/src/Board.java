/**
 * Rithvik Doshi, 2023
 */

/**
 * Abstract Board Class
 */
public abstract class Board {

    /**
     * Dimensions of board
     */
    public final int cols, rows;

    /**
     * Number of pieces placed on the board
     */
    public int num_pieces;

    /**
     * The matrix of cells on a board
     */
    public final Cell[][] matrix;

    /**
     * Constructor
     * @param cols number of columns
     * @param rows number of rows
     */
    public Board(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        matrix = new Cell[this.rows][this.cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Cell(null);
            }
        }
        this.num_pieces = 0;
    }

    /**
     * Gets board dimensions
     * @return int array of dimensions
     */
    public int[] getDims() {
        return new int[]{cols, rows};
    }

    /**
     * Abstract method to place piece on a board
     * @param piece to be placed
     * @param position to be placed in
     * @return success/failure
     */
    public abstract boolean placePiece(Piece piece, int position);

    /**
     * String representation of a board
     * @return a string
     */
    public String toString() {
        StringBuilder outstring = new StringBuilder();
        int position = 1;
        for (Cell[] row : matrix) {
            for (int i = 0; i < cols; i++){
                outstring.append("+---");
            }
            outstring.append("+\n");
            for (Cell cell : row) {
                outstring.append("| ").append(cell.toString()).append(" ");
            }
            outstring.append("| (");
            int i = 0;
            outstring.append("\t");
            do {
                outstring.append(position++).append("\t");
            } while (++i < cols);
            outstring.append(")\n");
        }

        for (int i = 0; i < cols; i++){
            outstring.append("+---");
        }
        outstring.append("+");

        return outstring.toString();

    }

    /**
     * Return the board matrix
     * @return the cell array
     */
    public Cell[][] getMatrix() {
        return matrix;
    }

    /**
     * Return a transposed matrix
     * @return the cell array
     */
    public Cell[][] getMatrixT(){
        Cell[][] outmat = new Cell[cols][rows];
        for (int i = 0; i < matrix[0].length; i++){
            Cell[] outarr = new Cell[rows];
            for (int j = 0; j < outarr.length; j++){
                outarr[j] = matrix[j][i];
            }
            outmat[i] = outarr;
        }

        return outmat;
    }

    /**
     * Returns a cell array on a upward diagonal
     * @param position starting position
     * @param N_vals number of vals on the upward diagonal
     * @return cell array
     */
    public Cell[] getDiagUp(int position, int N_vals){
        Cell[] out = new Cell[N_vals];
        position--;
        for (int i = 0; i < N_vals; i++){
            out[i] = matrix[position / cols][position % cols];
            position = position - cols + 1;
        }
        return out;
    }

    /**
     * To check whether a upward diagonal can be returned
     * @param position starting position
     * @param N_vals number of vals on the upward diagonal
     * @return true if possible, no otherwise
     */
    public boolean diagUpPossible(int position, int N_vals){
        position--;
        int final_pos = position - (cols * (N_vals - 1) ) + (N_vals - 1);
        return final_pos > 0 && final_pos % cols > position % cols;
    }

    /**
     * Returns a cell array on a downward diagonal
     * @param position starting position
     * @param N_vals number of vals on the downward diagonal
     * @return cell array
     */
    public Cell[] getDiagDown(int position, int N_vals){
        Cell[] out = new Cell[N_vals];
        position--;
        for (int i = 0; i < N_vals; i++){
            out[i] = matrix[position / cols][position % cols];
            position = position + cols + 1;
        }
        return out;
    }

    /**
     * To check whether a downward diagonal can be returned
     * @param position starting position
     * @param N_vals number of vals on the downward diagonal
     * @return true if possible, no otherwise
     */
    public boolean diagDownPossible(int position, int N_vals){
        position--;
        int final_pos = position + (cols * (N_vals - 1) ) + (N_vals - 1);
        return final_pos < rows*cols && final_pos % cols > position % cols;
    }

    /**
     * Get the area of the board (# cells)
     * @return rows * cols = # cells
     */
    public int getArea(){ return rows * cols; }

    /**
     * Determines whether the board is full
     * @return true if full, false otherwise
     */
    public boolean is_full(){
        return num_pieces == cols * rows;
    }

}
