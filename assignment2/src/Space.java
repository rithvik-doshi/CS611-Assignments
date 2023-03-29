import java.io.FileNotFoundException;

/**
 * This class is the parent class of all the spaces in the game. It contains the
 * basic information of a space.
 */
public abstract class Space {

    /**
     * Whether the space has been visited. This feature is not used in the current implementation of the game but can be extended as requirements change.
     */
    boolean visited;

    /**
     * Symbol of the space
     */
    private final char symbol;

    /**
     * Constructor for Space
     * @param symbol Symbol of the space
     */
    public Space(char symbol){
        visited = false;
        this.symbol = symbol;
    }

    /**
     * Method to print the space
     * @return String representation of the space
     */
    @Override
    public String toString() {
        return Color.color(Color.bgBlack, (symbol + ""));
    }

    /**
     * Method to get the symbol of the space
     * @return Symbol of the space
     */
    public char getSymbol(){
        return symbol;
    }

    /**
     * Method to hold heroes in a space
     * @param heroes List of heroes to be held.
     */
    public abstract boolean tryAccess(DataList<Hero> heroes) throws FileNotFoundException;
}
