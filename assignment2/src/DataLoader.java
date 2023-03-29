import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * DataLoader is a class that loads data from files in the data directory into a static object that can be referenced by different classes during execution.
 */
public class DataLoader {

    /**
     * The static object that is loaded with data from the data directory.
     */
    public static DataLoader dl;

    /**
     * The static block that loads the data from the data directory into the static object.
     */
    static {
        try {
            dl = new DataLoader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The data map that contains all the data from the data directory.
     */
    private final DataMap<String, DataMap<String, DataMap<String, String>>> allFiles;

    /**
     * The private constructor that loads the data from the data directory into the data map.
     *
     * @throws FileNotFoundException if the data directory is not found.
     */
    private DataLoader() throws FileNotFoundException {
        String directory = "src/data/";
        String[] filenames = getFilenames(directory);
        allFiles = new DataMap<>();
        for (String filename : filenames) {
            String[] lines = loadFile(Paths.get(directory + filename));
            filename = filename.substring(0, filename.length() - 4);
            allFiles.put(filename, dataMapOfFile(splitStringArr(lines)));
        }
    }

    /**
     * Gets the filenames of all the files in the data directory.
     * @param directory the directory to get the filenames from.
     * @return the filenames of all the files in the data directory.
     */
    public static String[] getFilenames(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        String[] filenames = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            filenames[i] = listOfFiles[i].getName();
        }
        return filenames;
    }

    /**
     * Loads the contents of a file into a string array.
     * @param filename the path of the file to load.
     * @return the contents of the file as a string array.
     * @throws FileNotFoundException if the file is not found.
     */
    public static String[] loadFile(Path filename) throws FileNotFoundException {
        File file = new File(filename.toString());
        Scanner scanner = new Scanner(file);
        String[] lines = new String[0];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] newLines = new String[lines.length + 1];
            System.arraycopy(lines, 0, newLines, 0, lines.length);
            newLines[newLines.length - 1] = line;
            lines = newLines;
        }
        return lines;
    }

    /**
     * Splits a string by a delimiter.
     * @param line the string to split.
     * @param delimiter the delimiter to split the string by.
     * @return the string split by the delimiter.
     */
    public static String[] splitByDelimiter(String line, String delimiter) {
        return line.split(delimiter);
    }

    /**
     * Splits a string array by a delimiter.
     * @param lines the string array to split.
     * @return the string array split by the delimiter.
     */
    public static String[][] splitStringArr(String[] lines) {
        String[][] result = new String[lines.length][];
        result[0] = splitByDelimiter(lines[0], "/");
        for (int i = 1; i < lines.length; i++) {
            result[i] = splitByDelimiter(lines[i], "\\s+");
        }
        return result;
    }

    /**
     * Creates a data map from a string array.
     * @param lines the string array to create the data map from.
     * @return the data map created from the string array.
     */
    public static DataMap<String, DataMap<String, String>> dataMapOfFile(String[][] lines) {
        DataMap<String, DataMap<String, String>> result = new DataMap<>();
        for (int i = 1; i < lines.length; i++) {
            DataMap<String, String> innerMap = new DataMap<>();
            if (lines[i].length <= 1) {
//                Signifies an empty line, no information to parse
                continue;
            }
            for (int j = 1; j < lines[i].length; j++) {
                innerMap.put(lines[0][j], lines[i][j]);
            }
            result.put(lines[i][0], innerMap);
        }
        return result;
    }

    /**
     * Gets an inner map from the data map.
     * @param filename the filename of the inner map to get.
     * @return the inner map.
     */
    public String[] getInnerMapKeys(String filename) {
        return allFiles.get(filename).keySet().toArray(new String[0]);
    }

    /**
     * Prints the inner maps of specified filenames
     * @param filenames the filenames of the inner maps to print.
     */
    public void printInnerMaps(String[] filenames) {
        DataMap<String, DataMap<String, DataMap<String, String>>> print = new DataMap<>();
        for (String filename : filenames) {
            print.put(filename, allFiles.get(filename));
        }
        System.out.println(print);
    }

    /**
     * Gets an inner map from the data map.
     * @param filename the filename of the inner map to get.
     * @param key the key of the inner map to get.
     * @return the inner map.
     */
    public DataMap<String, String> getInnerMap(String filename, String key) {
        return allFiles.get(filename).get(key);
    }

    /**
     * String representation of the data map.
     * @return the string representation of the data map.
     */
    @Override
    public String toString() {
        return allFiles.toString();
    }

}