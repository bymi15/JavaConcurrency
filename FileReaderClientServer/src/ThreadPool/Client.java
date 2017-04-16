package ThreadPool;

/*
* Receive a file from a remote server and print it on stdout.
*
* usage:
* java Client <hostname> <filename> (after starting the server)
*/
import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			if (args.length != 2) {
				System.out.println("Usage: java Client <hostname> <filename>");
				System.exit(1);
			}
			
			String host = args[0];
			String filename = args[1];
			
			//Get the current time in milliseconds
			long start = System.currentTimeMillis();
			
			// Open socket, then input and output streams to it
			Socket socket = new Socket(host, 9999);
			BufferedReader from_server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter to_server = new PrintWriter(socket.getOutputStream());
			
			// Send filename to server
			to_server.println(filename);
			to_server.flush();

			//read and print lines until the server closes the connection
			System.out.println("Receiving file from server...");
			System.out.println("*");
			
			String line;
			while ((line = from_server.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("*");
			
			//Calculate the time taken
			long responseTime = (System.currentTimeMillis() - start);
			System.out.println("Response took " + responseTime + " ms.");
			
			socket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}