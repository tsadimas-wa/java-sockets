package BankAccount;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {

    // Κοινό "Τραπεζικό Υπόλοιπο" για όλα τα νήματα
    static int balance = 1000;

    // Critical section: οι static synchronized μέθοδοι κλειδώνουν στο BankServer.class,
    // οπότε μόνο ένα νήμα τη φορά μπορεί να διαβάσει/τροποποιήσει το balance.
    public static synchronized int deposit(int amount) {
        balance += amount;
        return balance;
    }

    // Επιστρέφει -1 αν το υπόλοιπο δεν επαρκεί. Ο έλεγχος και η αφαίρεση γίνονται
    // ατομικά, ώστε δύο νήματα να μην περάσουν μαζί τον έλεγχο και να βγει αρνητικό υπόλοιπο.
    public static synchronized int withdraw(int amount) {
        if (amount > balance) {
            return -1;
        }
        balance -= amount;
        return balance;
    }

    public static synchronized int getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(5000);
        ) {
            System.out.println("Bank server ξεκίνησε στη θύρα 5000. Υπόλοιπο: " + getBalance());

            while(true) {
                Socket clientSocket=serverSocket.accept();

                System.out.println("New connection. Assigning to thread " + clientSocket.getPort());

                ClientHandler clientHandler = new ClientHandler(clientSocket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα server: " + e.getMessage());
        }
    }

}
