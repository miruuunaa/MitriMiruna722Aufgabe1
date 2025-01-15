import java.io.*;
import java.util.*;

public class DrHouse {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\admin\\IdeaProjects\\MitriMiruna722Aufgabe1\\src\\fallakten.csv";
        List<String[]> data = readData(filePath);

        // Aufgabe b
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie einen Großbuchstaben ein: ");
        String letter = scanner.nextLine().toUpperCase();

        System.out.println("\nNamen mit '" + letter + "' beginnen:");
        displayStudentsByLetter(data, letter);

        // Aufgabe d
        saveFalleKrankenhaus(data, "C:\\Users\\admin\\IdeaProjects\\MitriMiruna722Aufgabe1\\src\\fallakten.txt");
        System.out.println("\nDas Ergebnis gespeichert");
    }
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

    /**
     * Saves the house cup results to an output file.
     * The results include house names and their corresponding total points,
     * sorted by points in descending order.
     *
     * @param data       The list of student data.
     * @param outputPath The path to the output file.
     */
    // Aufgabe d: Ergebnis speichern
    public static void saveFalleKrankenhaus(List<String[]> data, String outputPath) {
        Map<String, Integer> HospitalFalle = new HashMap<>();
        for (String[] entry : data) {
            String falle = entry[2];
            int points = Integer.parseInt(entry[4]);
            if (HospitalFalle.containsKey(falle)) {
                HospitalFalle.put(falle, HospitalFalle.get(falle) + points);
            } else {
                HospitalFalle.put(falle, points);
            }
        }

        // Hospital sortieren nach falle abst
        List<String> sortedHospital = new ArrayList<>(HospitalFalle.keySet());
        for (int i = 0; i < sortedHospital.size() - 1; i++) {
            for (int j = i + 1; j < sortedHospital.size(); j++) {
                if (HospitalFalle.get(sortedHospital.get(i)) < HospitalFalle.get(sortedHospital.get(j))) {
                    String temp = sortedHospital.get(i);
                    sortedHospital.set(i, sortedHospital.get(j));
                    sortedHospital.set(j, temp);
                }
            }
        }

        // Ergebnisse in Datei speichern
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputPath));
            for (String house : sortedHospital) {
                writer.write(house + "$" + HospitalFalle.get(house));
                writer.newLine();
            }
        } catch (IOException e) {
            System.exit(1);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }
}
