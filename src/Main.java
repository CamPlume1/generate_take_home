import connection.ConnectionHandler;
import model.BarcodeSolver;
import model.Strategies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        String token = getTokenFromFile();
        List<String> challenges = connectionHandler.getChallenge(token);
        List<String> results = BarcodeSolver.solveAll(challenges, Strategies.BASIC);

        assert challenges.size() == results.size();
        System.out.println(results);
        System.out.println(connectionHandler.submit(token, results));

    }


    private static List<String> getChallengesFromFile() {
        //Set path
        String filePath = "C:\\Users\\Cam\\Desktop\\generate_barcode\\src\\fileResources\\challenge.txt";

        //initialize list of challenges
        List<String> challenges = new ArrayList<>();

        try {
            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                challenges.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return challenges;
    }

    private static String getTokenFromFile() {
        String filePath = "C:\\Users\\Cam\\Desktop\\generate_barcode\\src\\fileResources\\token.txt";

        StringBuilder token;
        try {
            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Wrap the FileReader in a BufferedReader for efficient reading
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Use StringBuilder to concatenate lines into a single String
            token = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                token.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return token.toString();
    }
}