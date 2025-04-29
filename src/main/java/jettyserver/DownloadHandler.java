package jettyserver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File uploadedFile = new File("uploaded.json");

        if (uploadedFile.exists()) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);

            try (BufferedReader reader = new BufferedReader(new FileReader(uploadedFile));
                 PrintWriter writer = resp.getWriter()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("No uploaded file found.");
        }
    }
}
