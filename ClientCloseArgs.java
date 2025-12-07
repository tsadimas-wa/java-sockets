import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCloseArgs {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: java ClientCloseArgs <host> <port>");
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

    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    BufferedReader stdIn = null;

    try {
      clientSocket = new Socket(host, port);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      stdIn = new BufferedReader(new InputStreamReader(System.in));

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
      System.out.println("Unknown host");
    } catch (IOException e) {
      System.out.println("Cannot get IO for the connection");
    } finally {
      if (stdIn != null) {
        try {
          stdIn.close();
        } catch (IOException e) {
        }
      }
      if (out != null) {
        out.close();
      }
      if (clientSocket != null) {
        try {
          clientSocket.close();
          System.out.println("Close socket manually");
        } catch (IOException e) {
          System.out.println("Error closing socket");
        }
      }

    }
  }
}
