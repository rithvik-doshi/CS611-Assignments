import java.io.FileNotFoundException;

/**
 * Map Class
 */
public class Map {

    /**
     * Dimension of square map
     */
    public final int N;

    /**
     * The matrix of cells on a map
     */
    public final Space[][] matrix;

    public int row, col;

    /**
     * Constructor
     * @param N map size
     */
    public Map(int N) {
        this.N = N;
        matrix = new Space[this.N][this.N];
        row = matrix.length - 1;
        col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == matrix.length - 1 && j == 0) {
                    matrix[i][j] = new Market();
                    continue;
                }
                double rand = Math.random();
                if (rand < 0.2) {
                    matrix[i][j] = new Inaccessible();
                } else if (rand < 0.4) {
                    matrix[i][j] = new Market();
                } else {
                    matrix[i][j] = new Common();
                }
            }
        }
    }

    /**
     * String representation of a map
     * @return a string
     */
    public String toString() {
        StringBuilder outstring = new StringBuilder();
        for (Space[] row : matrix) {
            for (int i = 0; i < N; i++){
                outstring.append("+---");
            }
            outstring.append("+\n");
            for (Space cell : row) {
                outstring.append("| ").append(cell.toString()).append(" ");
            }
            outstring.append("|\n");
        }

        for (int i = 0; i < N; i++){
            outstring.append("+---");
        }
        outstring.append("+");

        return outstring.toString();

    }

    /**
     * Moves the heroes on the map
     * @param heroes the list of heroes
     * @param control the control character
     * @return true if the move was successful
     * @throws FileNotFoundException if the file is not found
     */
    public boolean moveHeroes(DataList<Hero> heroes, char control) throws FileNotFoundException {
        int newrow = row, newcol = col;
        switch (control){
            case 'W':
                newrow--;
                break;
            case 'A':
                newcol--;
                break;
            case 'S':
                newrow++;
                break;
            case 'D':
                newcol++;
                break;
        }
        if (newrow < 0 || newrow >= N || newcol < 0 || newcol >= N){
            return false;
        }
        if (matrix[newrow][newcol].tryAccess(heroes)){
            Accessible currentSpace = (Accessible) matrix[row][col];
            currentSpace.leaveHeroes();
            row = newrow;
            col = newcol;
            return true;
        }
        return false;
    }

    /**
     * Places the heroes on the map at the start of the game
     * @param heroes the list of heroes
     * @throws FileNotFoundException if the file is not found
     */
    public void heroInitialPlace(DataList<Hero> heroes) throws FileNotFoundException {
        matrix[row][col].tryAccess(heroes);
    }

    /**
     * Gets the current space
     * @return the current space
     */
    public Space getSpace() {
        return matrix[row][col];
    }
}
