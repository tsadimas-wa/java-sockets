import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiThread {

  public static void main(String[] args) {
    try (
      ServerSocket serverSocket = new ServerSocket(5000);

    ) {

     while(true) {
      Socket clientSocket=serverSocket.accept();

      System.out.println("Νέα σύνδεση. Ανάθεση σε νήμα" + clientSocket.getPort());

      ClientHandler clientHandler = new ClientHandler(clientSocket);

      Thread thread = new Thread(clientHandler);
      thread.start();
     }


    } catch (IOException e) {
      System.err.println("Error occured during server operation or connection");
    } 
  }
}
