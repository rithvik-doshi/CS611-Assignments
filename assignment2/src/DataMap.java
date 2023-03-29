import java.util.Collections;
import java.util.TreeMap;

/**
 * This class is a wrapper for TreeMap. It is used to store data and print it in a specific manner
 * @param <K>
 * @param <V>
 */
public class DataMap<K, V> extends TreeMap<K, V> {

    /**
     * String representation of the layer of datamap
     * @param dv the datamap
     * @param layer the layer of the datamap
     * @return the string representation of the datamap
     */
    public String stringify(DataMap<K, V> dv, int layer) {

        StringBuilder result = new StringBuilder();

        for (K key : dv.keySet()) {
            String tabs = String.join("", Collections.nCopies(layer, "\t"));
            result.append(tabs).append(key).append(":");
            if (dv.get(key) instanceof DataMap<?, ?>) {
                result.append("\n").append(stringify((DataMap<K, V>) dv.get(key), layer + 1));
            } else {
                result.append(" ").append(dv.get(key)).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * This method is used to print the datamap in a specific manner
     * @return the datamap as a string
     */
    @Override
    public String toString() {
        return stringify(this, 0);
    }

}