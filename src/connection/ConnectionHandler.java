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

public class ConnectionHandler {
    private final HttpClient client;

    private final String baseURL = "https://generate-coding-challenge-server-rellb.ondigitalocean.app/";


    public ConnectionHandler(HttpClient client) {
        this.client = client;
    }

    public ConnectionHandler() {
        this.client = HttpClient.newHttpClient();
    }


    /**
     * This function handles registering for the generate coding challenge
     * @param name- The name you wish to use to register
     * @param NUID- The NUID you wish to use to register
     * @return- body of the HTTPResponse, as a String
     */
    public String register(String name, String NUID) {
        String payload = "{ \"name\" : \"" + name + "\", \"nuid\": \"" + NUID + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL +"register"))
                .header("Content-Type", "application/json") // Set content type as JSON
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        return retrieveResponse(request);
    }

    /**
     * This function handles retrieving challenge values for the generate coding challenge
     * @param token- the user token for
     * @return- body of the HTTPResponse, as a String
     */
    public List<String> getChallenge(String token){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL +"challenge/" + token))
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
        System.out.println(challenges);
        return challenges;
    }


    public String submit(String token, List<String> answers) {
        StringBuilder content = new StringBuilder().append('[');
        for (String answer : answers){
            content.append("\"").append(answer).append("\",");
        }
        content.deleteCharAt(content.length()-1);
        content.append(']');
        System.out.println(content);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL +"submit/" + token))
                .header("Content-Type", "application/json") // Set content type as JSON
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();
        return retrieveResponse(request);
    }

    //Helper- > Sends generated request and retrieves response body as a String
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
