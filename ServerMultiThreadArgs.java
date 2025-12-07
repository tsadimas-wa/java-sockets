import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultiThreadArgs {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: java ServerMultiThreadArgs <port>");
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

    ) {
      System.out.println("Multi-threaded server listening on port " + port);

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
