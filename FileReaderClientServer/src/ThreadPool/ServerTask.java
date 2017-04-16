package ThreadPool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTask implements Runnable {
	private Socket socket;

	public ServerTask(final Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader from_client = null;
		PrintWriter to_client = null;
		try {
			from_client = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			to_client = new PrintWriter(socket.getOutputStream());
			// Get filename from client and check if it exists
			String filename = from_client.readLine();
			File inputFile = new File(filename);
			if (inputFile.exists()) {
				// Read lines from filename and send them to the client
				System.out.println("reading from file " + filename);
				BufferedReader input = new BufferedReader(new FileReader(inputFile));
				String line;
				while ((line = input.readLine()) != null)
					to_client.println(line);
				input.close();
			} else {
				to_client.println("cannot open file: " + filename);
			}
			to_client.close();
			from_client.close();
			System.out.println("client: " + socket.getInetAddress().toString() + " disconnected\n");
			socket.close();
		} catch (IOException except) {
			System.out.println("IO Exception occurred: " + except);
		}
	}
}