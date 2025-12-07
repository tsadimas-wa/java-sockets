import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable  {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        ) {
            String message;
            System.out.println("Νέο νήμα χειρίζεται client " + socket.getInetAddress());

            while ((message = in.readLine()) != null ) {
                System.out.println("Client " + socket.getPort() + " λέει " + message);
                
                if ("quit".equalsIgnoreCase(message)) {
                    out.println("Γειά ! Η σύνδεση τερματίζει");
                    break;
                }

                String response = "Server : Έλαβα " + message;
                out.println(response);
            }

        } catch (IOException e) {
            System.err.println("Σφάλμα επικοινωνίας με client");
        } finally {
            try {

                socket.close();
                System.out.println("Σύνδεση με client " + socket.getPort() + "  τερματίστηκε!");
            } catch (IOException e) {
                System.out.println("Σφάλμα κατά το κλείσιμο του socket " + e.getMessage());
            }
        }
    }
    

}
