import connection.ConnectionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Registration {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Cam\\Desktop\\generate_barcode\\src\\challenge.txt";

        try {
            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Use StringBuilder to concatenate lines into a single String
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
                System.out.println(line);
            }

            // Close the BufferedReader to release resources
            bufferedReader.close();

            // Get the final concatenated string
            String fileContent = stringBuilder.toString();

            //split on commas
            String[] challenges = fileContent.split(",");
            System.out.println(Arrays.toString(challenges));
            for (int i = 0; i < challenges.length; i++){
                challenges[i] = challenges[i].replaceAll("\"", "");
            }

            //write to challenges.txt
            FileWriter writer = new FileWriter(filePath);
            System.out.print(Arrays.toString(challenges));
            for (String challenge : challenges){
                writer.write(challenge + "\n");
            }
            writer.close();
        } catch (IOException ignored) {

        }
    }


}
