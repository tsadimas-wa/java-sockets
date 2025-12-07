import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) {
    try (
      ServerSocket serverSocket = new ServerSocket(5000);
      Socket clientSocket = serverSocket.accept();
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    ) {
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
