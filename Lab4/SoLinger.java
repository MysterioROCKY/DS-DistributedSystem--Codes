import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SoLinger {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server is running. Waiting for a client to connect...");
            Socket clientSocket = serverSocket.accept();

            // Enable SO_LINGER with a timeout of 30 seconds on the client socket
            clientSocket.setSoLinger(true, 30);
            System.out.println("SO_LINGER enabled: " + clientSocket.getSoLinger());

            // Close the sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}