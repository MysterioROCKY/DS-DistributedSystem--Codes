import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to the server");

            // Create output stream to send messages to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Create input stream to receive messages from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Create input stream to read from console
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Enter a message to send (type 'exit' to quit): ");
                message = consoleInput.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Send the message to the server
                out.println(message);

                // Receive the reversed message from the server
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
