import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SO_KEEPALIVE {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            System.out.println("Server is running. Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();

            // Enable SO_KEEPALIVE
            clientSocket.setKeepAlive(true);
            // Test SO_KEEPALIVE
            System.out.println("SO_KEEPALIVE enabled: " + clientSocket.getKeepAlive());

            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
