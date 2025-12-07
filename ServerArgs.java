import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerArgs {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java ServerArgs <port>");
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

    try (
      ServerSocket serverSocket = new ServerSocket(port);
      Socket clientSocket = serverSocket.accept();
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    ) {
      System.out.println("Server listening on port " + port);
      System.out.println("Client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        System.out.println(inputLine);
        out.println("ACK " + inputLine);
        if ("quit".equalsIgnoreCase(inputLine)) {
          break;
        }
      }


    } catch (IOException e) {
      System.err.println("Error occured during server operation or connection");
    } 
  }
}
