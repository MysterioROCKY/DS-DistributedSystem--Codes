import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketTest {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 8080;

        try {
            // Create a socket and connect to the server
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server " + serverAddress + " on port " + serverPort);

            // Keep the connection open for 10 seconds
            try {
                Thread.sleep(10000); // 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Close the socket
            socket.close();
            System.out.println("Disconnected from server.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}