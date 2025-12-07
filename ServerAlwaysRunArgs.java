import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAlwaysRunArgs {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java ServerAlwaysRunArgs <port>");
      System.exit(1);
    }

    int port;
    try {
      port = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("Error: Port must be a valid integer");
      System.exit(1);
      return;
    }
    
    // Το ServerSocket δηλώνεται εδώ και θα κλείσει αυτόματα στο τέλος του try-with-resources.
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server listening on port " + port + ". Ready to accept connections sequentially.");

      // Outer loop: Κρατάει τον Server ζωντανό για να δέχεται νέους clients
      while (true) {
        Socket clientSocket = null;
        System.out.println("\nWaiting for a new client connection...");

        try {
          // Block until a client connects, creating a dedicated socket
          clientSocket = serverSocket.accept(); 
          System.out.println("Client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

          // Inner try-with-resources block for communication streams
          // Αυτό διασφαλίζει ότι τα streams κλείνουν αυτόματα
          try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          ) {
            String inputLine;
            // Communication loop for the current client
            while ((inputLine = in.readLine()) != null) {
              System.out.println("Received: " + inputLine);
              out.println("ACK " + inputLine);
              if ("quit".equalsIgnoreCase(inputLine)) {
                break;
              }
            }
          }
        } catch (IOException e) {
          System.err.println("Error during client communication: " + e.getMessage());
        } finally {
          // Manually close the client socket if it was successfully accepted
          if (clientSocket != null) {
            try {
              clientSocket.close();
              System.out.println("Connection with client closed.");
            } catch (IOException e) {
              System.err.println("Error closing client socket: " + e.getMessage());
            }
          }
        }
      } // End of outer while(true) loop
    } catch (IOException e) {
      // Catches errors if the port is busy or the ServerSocket cannot be created
      System.err.println("Error occurred during server setup: " + e.getMessage());
    } 
  }
}
