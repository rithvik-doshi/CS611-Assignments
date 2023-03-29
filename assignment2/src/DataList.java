import java.util.ArrayList;

/**
 * This class is a wrapper for ArrayList. It is used to store data and print it in a specific manner
 * @param <K>
 */
public class DataList<K> extends ArrayList<K> {

    /**
     * Constructor with specified size
     * @param size the size of the list
     */
    public DataList(int size){
        super(size);
    }

    /**
     * Default constructor
     */
    public DataList() {
        super();
    }

    /**
     * This method is used to print the list in a specific manner
     * @return the list as a string
     */
    @Override
    public String toString() {
        if (this.size() == 0){
            return "Empty!";
        }
        StringBuilder result = new StringBuilder();
        for (K item : this){
            result.append(item).append("\n");
        }
        return result.toString();
    }

}
