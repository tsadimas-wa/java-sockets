package BankAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String message;
            System.out.println("Νέο νήμα χειρίζεται client " + socket.getPort());

            while ((message = in.readLine()) != null) {
                message = message.trim();
                System.out.println("Client " + socket.getPort() + " λέει " + message);

                if ("quit".equalsIgnoreCase(message)) {
                    out.println("Γειά ! Η σύνδεση τερματίζει");
                    break;
                }

                out.println(handleCommand(message));
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

    // Επεξεργάζεται μία εντολή: DEPOSIT:<ποσό>, WITHDRAW:<ποσό> ή BALANCE
    private String handleCommand(String message) {
        if ("BALANCE".equalsIgnoreCase(message)) {
            return "BALANCE:" + BankServer.getBalance();
        }

        String[] parts = message.split(":");
        if (parts.length != 2) {
            return "ERROR:Άγνωστη εντολή. Χρήση DEPOSIT:<ποσό>, WITHDRAW:<ποσό>, BALANCE, quit";
        }

        String command = parts[0].trim().toUpperCase();
        int amount;
        try {
            amount = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            return "ERROR:Μη έγκυρο ποσό";
        }
        if (amount <= 0) {
            return "ERROR:Το ποσό πρέπει να είναι θετικό";
        }

        switch (command) {
            case "DEPOSIT":
                return "OK:Νέο υπόλοιπο " + BankServer.deposit(amount);
            case "WITHDRAW":
                int newBalance = BankServer.withdraw(amount);
                if (newBalance < 0) {
                    return "ERROR:Ανεπαρκές υπόλοιπο";
                }
                return "OK:Νέο υπόλοιπο " + newBalance;
            default:
                return "ERROR:Άγνωστη εντολή " + command;
        }
    }

}
