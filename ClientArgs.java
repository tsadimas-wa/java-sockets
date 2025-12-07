import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientArgs {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: java ClientArgs <host> <port>");
      System.exit(1);
    }

    String host = args[0];
    int port;
    try {
      port = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.err.println("Error: Port must be a valid integer");
      System.exit(1);
      return;
    }

    try (
      Socket clientSocket = new Socket(host, port);
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
    )
    {
    
      System.out.println("Connected to server at " + host + ":" + port);
      String userInput;
      while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("Server replied :" + in.readLine());

        if ("quit".equalsIgnoreCase(userInput)) {
          break;
        }
      }
    } catch (UnknownHostException e) {
      System.out.println("Unknown host " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Cannot get IO for the connection");
    }

  }
}
