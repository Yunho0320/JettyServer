package jettyserver;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class UploadHandler extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Step 1: Read the incoming data from the request
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line).append("\n");
            }
        }

        String receivedJson = jsonBuilder.toString();
        System.out.println("Received JSON content:\n" + receivedJson);

        // Step 2: Save the data to a file
        File outputFile = new File("uploaded.json"); // You can customize the filename if needed
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(receivedJson);
        }

        // Step 3: Respond with 200 OK
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("JSON file uploaded and saved successfully.");
    }
}