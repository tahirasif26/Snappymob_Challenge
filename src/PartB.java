import java.io.*;
import java.nio.charset.StandardCharsets;

public class PartB {

    private static final String INPUT_FILE = "random_data_objects.txt";
    private static final String OUTPUT_FILE = "processed_data.txt";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE, StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, writer);
            }

            System.out.println("Processing completed. Output saved to: " + OUTPUT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * It parses the file line by line and identify the type of string using regex.
     * @param line
     * @param writer
     * @throws IOException
     */
    private static void processLine(String line, BufferedWriter writer) throws IOException {

        String[] parts = line.split(",");

        for (String part : parts) {

            part = part.strip();
            String output;

            // match the part by regex and identify the part type
            if (part.matches("[a-zA-Z]+")) {
                output = "Alphabetic String: " + part + " (Type: String)";
            } else if (part.matches("\\d+")) {
                output = "Integer: " + part + " (Type: Integer)";
            } else if (part.matches("\\d+\\.\\d+")) {
                output = "Real Number: " + part + " (Type: Double)";
            } else {
                output = "Alphanumeric: " + part + " (Type: Alphanumeric String)";
            }

            // print output in console
            System.out.println(output);

            // write output into file
            writer.write(output);
            writer.newLine();
        }

    }

}
