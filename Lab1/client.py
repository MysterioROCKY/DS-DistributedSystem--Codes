import socket

# Server configuration
HOST = '127.0.0.1'  # Server IP
PORT = 12345        # Server port

def start_client():
    """Starts the client and communicates with the server."""
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        client_socket.connect((HOST, PORT))
        print("Connected to the server.")
        message = input("Enter a message to send to the server: ")
        client_socket.send(message.encode())
        response = client_socket.recv(1024).decode()
        print(f"Server response: {response}")
    except Exception as e:
        print(f"Error: {e}")
    finally:
        client_socket.close()
        print("Disconnected from the server.")

if __name__ == "__main__":
    start_client()
