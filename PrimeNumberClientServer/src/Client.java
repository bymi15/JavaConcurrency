import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Client <hostname> <port> <integer>");
			System.exit(1);
		}

		String host = args[0];
		int port = Integer.parseInt(args[1]);
		int num = Integer.parseInt(args[2]);
		
		try {
			Socket socket = new Socket(host, port);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			writer.println(num);
			writer.flush();
			
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
			writer.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
