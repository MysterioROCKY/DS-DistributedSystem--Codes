import socket
def start_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # Get local machine name
    host = socket.gethostname()  
    port = 9999

    # Bind to the port
    server_socket.bind((host, port))  

    # Queue up to 5 requests
    server_socket.listen(5)  
    print(f"Server started! Listening on {host}:{port}")

    while True:
        # Establish a connection
        client_socket, addr = server_socket.accept()  
        print(f"Got a connection from {addr}")

        # Send a thank you message to the client
        message = 'Message received. Thank you for connecting' + "\r\n"  
        client_socket.send(message.encode('ascii'))

        # Close the connection
        client_socket.close()  

if __name__ == "__main__":
    start_server()
