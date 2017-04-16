package ThreadPerRequest;
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String args[]) {
		ServerSocket listen = null;
		try {
			// Create server socket to listen for connection on port 9999
			listen = new ServerSocket(9999);
			System.out.println("server is listening to port " + listen.getLocalPort());
			while (true) {
				Socket socket = listen.accept(); // Wait for a client
				System.out.println("client: " + socket.getInetAddress().toString() + " connected");
				
				// Client has connected ... create a Thread to serve it
				// and then get back to listening for the next client.
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			System.err.println(e);
			try {
				if (listen != null) listen.close();
			} catch (IOException ex) {
				System.err.println("Exception trying to close socket: " + e);
			}
		}
	}
}
