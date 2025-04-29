package jettyserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // Set up the context
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");
        // Attach servlets
        handler.addServlet(UploadHandler.class, "/upload");
        handler.addServlet(DownloadHandler.class, "/download");

        server.setHandler(handler);

        // Start the server
        server.start();
        server.join();
    }
}
