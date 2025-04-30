package jettyserver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class UploadHandler extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Step 1: Read the incoming data from the request
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = req.getReader()) { // Receives BufferedReader tied to the body of the HTTP request.
                                                        // The request body contains the JSON string sent by the client.
            String line;
            while ((line = reader.readLine()) != null) {  // This is becuase HTTP body doesn't arrive all at once
                jsonBuilder.append(line).append("\n");
            }
        }
        String receivedJson = jsonBuilder.toString();
        System.out.println("Received JSON content:\n" + receivedJson);

        // Step 2: Create inner directory: data/<name>
        File baseDir = new File("data");
        File innerDir = new File(baseDir, "innerdata");

        if (!innerDir.exists()) {
            boolean created = innerDir.mkdirs(); // creates both "data" and "data/James"
            if (created) {
                System.out.println("Created directory: " + innerDir.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory.");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        }

        // Step 3: Save the data to a file
        File outputFile = new File(innerDir,"uploaded.json"); // You can customize the filename if needed
        try (FileWriter writer = new FileWriter(outputFile)) { // creating a file reference in the current working directory
            writer.write(receivedJson);
        }

        // Step 4: Respond with 200 OK
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("JSON file uploaded and saved successfully."); // Sending a message back to the client
    }
}