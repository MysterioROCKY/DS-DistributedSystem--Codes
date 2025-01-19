import socket
import threading
from datetime import datetime

# Server configuration
HOST = '127.0.0.1'  # Localhost
PORT = 12345        # Port to listen on

def handle_client(client_socket, client_address):
    """Handles communication with a single client."""
    print(f"[{datetime.now()}] New connection from {client_address}")
    try:
        while True:
            message = client_socket.recv(1024).decode()
            if not message:
                break
            print(f"[{datetime.now()}] Received from {client_address}: {message}")
            response = f"Message received: {message}"
            client_socket.send(response.encode())
    except Exception as e:
        print(f"[{datetime.now()}] Error with {client_address}: {e}")
    finally:
        print(f"[{datetime.now()}] Closing connection with {client_address}")
        client_socket.close()

def start_server():
    """Starts the server and listens for client connections."""
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen(5)  # Listen for up to 5 connections
    print(f"[{datetime.now()}] Server listening on {HOST}:{PORT}")

    try:
        while True:
            client_socket, client_address = server_socket.accept()
            client_thread = threading.Thread(target=handle_client, args=(client_socket, client_address))
            client_thread.start()
    except KeyboardInterrupt:
        print(f"\n[{datetime.now()}] Server shutting down.")
    finally:
        server_socket.close()

if __name__ == "__main__":
    start_server()
