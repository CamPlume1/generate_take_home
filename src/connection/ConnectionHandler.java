package connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * ConnectionHandler Class
 *
 * This class handles communication with the Generate Coding Challenge server, providing functionality for registration,
 * retrieving challenge values, and submitting answers.
 */
public class ConnectionHandler {

    /** The HTTP client used for making requests. */
    private final HttpClient client;

    /** The base URL of the Generate Coding Challenge server. */
    private final String baseURL = "https://generate-coding-challenge-server-rellb.ondigitalocean.app/";

    /**
     * Constructs a ConnectionHandler with a provided HttpClient.
     * @TODO: using for testing purposes
     *
     * @param client The HttpClient to be used for making requests.
     */
    public ConnectionHandler(HttpClient client) {
        this.client = client;
    }

    /**
     * Constructs a ConnectionHandler with a default HttpClient.
     */
    public ConnectionHandler() {
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Handles user registration for the Generate Coding Challenge.
     *
     * @param name The name to use for registration.
     * @param NUID The NUID to use for registration.
     * @return The body of the HTTPResponse as a String.
     */
    public String register(String name, String NUID) {
        String payload = "{ \"name\" : \"" + name + "\", \"nuid\": \"" + NUID + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "register"))
                .header("Content-Type", "application/json") // Set content type as JSON
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        return retrieveResponse(request);
    }

    /**
     * Retrieves challenge values for the Generate Coding Challenge.
     *
     * @param token The user token.
     * @return A List of challenge values as Strings.
     */
    public List<String> getChallenge(String token){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "challenge/" + token))
                .build();
        HttpResponse<String> response;
        List<String> challenges = new ArrayList<>();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            // Parse the JSON response
            JsonNode jsonNode = mapper.readTree(response.body());
            JsonNode challengeArrayNode = jsonNode.get("challenge");
            for (JsonNode element : challengeArrayNode) {
                challenges.add(element.asText());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return challenges;
    }

    /**
     * Submits answers for the Generate Coding Challenge.
     *
     * @param token The user token.
     * @param answers A List of answers as Strings.
     * @return The submit outcome of either success or Failure.
     */
    public SubmitOutcome submit(String token, List<String> answers) {

        //Build request
        StringBuilder content = new StringBuilder().append('[');
        for (String answer : answers){
            content.append("\"").append(answer).append("\",");
        }
        content.deleteCharAt(content.length()-1);
        content.append(']');
        System.out.println("Content pre serialization" + content);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "submit/" + token))
                .header("Content-Type", "application/json") // Set content type as JSON
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();

        //Send and generate response
        // TODO: Refactor duplicate code
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Received: " + response.toString());
        }
        catch (IOException | InterruptedException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.exit(-1);
        }

        try {
            // Parse the response JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.body());

            //TODO: Remove once mocks are properly defined
            System.out.println("JsonNode Value:");
            System.out.println(jsonResponse);

            // Check the 'correct' field in the response
            boolean isCorrect = jsonResponse.get("correct").asBoolean();

            // Determine the SubmitOutcome based on the response
            if (isCorrect) {
                return SubmitOutcome.SUCCESS;
            } else {
                System.out.println("True Failure");
                return SubmitOutcome.FAILURE;
            }

        } catch (Exception e) {
            // Handle exceptions, e.g., network issues or JSON parsing errors
            e.printStackTrace();
        }


        // Default outcome in case of errors
        return SubmitOutcome.FAILURE;

    }

    /**
     * Helper method that sends a generated request and retrieves the response body as a String.
     *
     * @param request The HttpRequest to be sent.
     * @return The body of the HTTPResponse as a String.
     */
    private String retrieveResponse(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.exit(-1);
        }
        assert response != null;
        System.out.println(response);
        return response.body();
    }
}
