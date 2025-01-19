import socket

def start_client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Friend's IP address
    host = '10.70.18.18'  
    port = 9999

    # Connect to the server
    client_socket.connect((host, port))  

    # Receive the message from the server
    message = client_socket.recv(1024)  
    print(message.decode('ascii'))

    # Send "Hi" to the server
    client_socket.send("Hi".encode('ascii'))  

    # Close the connection
    client_socket.close()  

if __name__ == "__main__":
    start_client()
