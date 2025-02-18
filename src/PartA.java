import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class PartA {

    private static final String FILE_NAME = "random_data_objects.txt";
    private static final int FILE_SIZE_MB = 10;
    private static final int FILE_SIZE_BYTES = FILE_SIZE_MB * 1024 * 1024;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, StandardCharsets.UTF_8))) {
            int currentSize = 0;

            while (currentSize < FILE_SIZE_BYTES) {
                String randomData = generateRandomDataObjects();
                writer.write(randomData);
                currentSize += randomData.getBytes(StandardCharsets.UTF_8).length + System.lineSeparator().getBytes(StandardCharsets.UTF_8).length;
            }

            System.out.println("Random data file generated successfully: " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * It creates the random string according to the random object type.
     * @return String
     */
    private static String generateRandomDataObjects() {

        // stores the random generated string
        StringBuilder randomString = new StringBuilder();

        // random number of objects per line 1 to 4
        int objectCount = 1 + RANDOM.nextInt(4);

        for (int index = 0; index < objectCount; index++) {

            // randomly select the object type
            int objectType = RANDOM.nextInt(4);

            if (index > 0) {
                randomString.append(",");
            }

            switch (objectType) {
                // for alphabetical strings
                case 0 -> randomString.append(generateAlphabeticString(10 + RANDOM.nextInt(10)));
                // for real numbers
                case 1 -> randomString.append(RANDOM.nextDouble() * 1000);
                // for integers
                case 2 -> randomString.append(RANDOM.nextInt(10000));
                // for alphanumerics
                case 3 -> randomString.append(generateAlphanumericWithSpaces(RANDOM));
            }

        }

        return randomString.toString();
    }

    /**
     * It is responsible to create the alphabetic string
     * @param length
     * @return String
     */
    private static String generateAlphabeticString(int length) {

        StringBuilder sb = new StringBuilder(length);

        for (int index = 0; index < length; index++) {
            sb.append((char) ('a' + RANDOM.nextInt(26)));
        }

        return sb.toString();

    }

    /**
     * It is responsible to create the alphanumeric string.
     * @param random
     * @return String
     */
    private static String generateAlphanumericWithSpaces(Random random) {

        int length = random.nextInt(10) + 5; // 5 to 15 characters
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {

            char randomCharacter;
            if (random.nextBoolean()) {
                // random lowercase letter
                randomCharacter = (char) (random.nextInt(26) + 'a');
            } else {
                // random digit
                randomCharacter = (char) (random.nextInt(10) + '0');
            }
            builder.append(randomCharacter);

        }

        // generate spaces with a strict max limit of 10
        int spacesBefore = random.nextInt(11); // 0 to 10 spaces before
        int spacesAfter = random.nextInt(11);  // 0 to 10 spaces after

        return " ".repeat(spacesBefore) + builder + " ".repeat(spacesAfter);
    }

}
