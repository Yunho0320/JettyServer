package client;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws Exception {
        // 1. Create the JSON string
        String jsonString = "{\"name\":\"John\",\"age\":30}";

        // 2. Send the JSON string directly without saving to file
        uploadJsonString(jsonString, "http://localhost:8080/upload");
    }

    public static void uploadJsonString(String jsonString, String targetUrl) throws Exception {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Write the JSON string directly to output stream
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Server responded with code: " + responseCode);
    }
}
