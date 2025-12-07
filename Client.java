import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
  public static void main(String[] args) {

    try (
      Socket clientSocket = new Socket("localhost", 5000);
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
    )
    {
    
      System.out.println("connected to server");
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