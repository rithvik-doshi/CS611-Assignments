/**
 * Rithvik Doshi, 2023
 */

/**
 * Cell class that contains pieces
 */
public class Cell {

    /**
     * Piece contained
     */
    private Piece piece;

    /**
     * Constructor
     * @param piece piece to be contained
     */
    public Cell(Piece piece){
        this.piece = piece;
    }

    /**
     * Peeps value of piece
     * @return piece
     */
    public Piece peepPiece(){
        return this.piece;
    }

    /**
     * Places a piece in the cell
     * @param piece to be placed
     * @return true if successful
     */
    public boolean placePiece(Piece piece){
        this.piece = piece;
        return true;
    }

    /**
     * String representation of cell
     * @return a string
     */
    public String toString(){
        return piece != null ? piece.toString() : " ";
    }

}
