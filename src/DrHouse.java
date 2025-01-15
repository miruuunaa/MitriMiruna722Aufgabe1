import java.io.*;
import java.util.*;

public class DrHouse {
    // Aufgabe a:
    /**
     * Reads data from a file.
     * The data is split into an array of strings based on the delimiter "/t".
     *
     * @param filePath The path to the input file.
     * @return A list of string arrays, each representing a student's information.
     */
    public static List<String[]> readData(String filePath) {
        List<String[]> data = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split("/t"));
            }
        } catch (IOException e) {
            System.exit(1);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.exit(1);
            }
        }
        return data;
    }

    // Aufgabe b
    public static void displayStudentsByLetter(List<String[]> data, String letter) {
        for (String[] entry : data) {
            String name = entry[1];
            if (name.startsWith(letter)) {
                System.out.println(name);
            }
        }
    }
}
