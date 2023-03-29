/**
 * Rithvik Doshi, 2023
 */

/**
 * Class defining a game piece
 */
public class Piece {

    /**
     * Symbol of piece
     */
    private final char symbol;

    /**
     * Constructor
     * @param symbol symbol of piece
     */
    public Piece(char symbol){
        this.symbol = symbol;
    }

    /**
     * String representation of piece
     * @return string
     */
    @Override
    public String toString() {
        return Character.toString(symbol);
    }

    /**
     * Piece equality based on symbol
     * @param obj piece to compare
     * @return true if symbols are equal, false otherwise
     */
    public boolean equals(Piece obj) {
        return this.symbol == obj.symbol;
    }
}
