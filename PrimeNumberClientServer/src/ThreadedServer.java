import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Multi-threaded Server application
 * Uses a Thread-per-call mechanism
 * Listens to a port number
 * Receives an integer value
 * Returns either yes or no depending on whether the integer is a prime number
*/
public class ThreadedServer{
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("Usage: java Server [port number]");
			System.exit(1);
		}
		
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(port);

			System.out.println("Server is listening on port " + port + "...");
			
			while(true){
				Socket clientSocket = serverSocket.accept();
				new ServerThread(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread{
	private Socket socket;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}

	public boolean isPrime(int n){
		for(int i = 2; i <= Math.sqrt(n); i++){
			if(n % i == 0){
				return false;
			}
		}
		return true;
	}
	
	public void run(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			int num = Integer.parseInt(reader.readLine());
			
			String result = "no";
			if(isPrime(num)){
				result = "yes";
			}
			
			writer.println(result);
			writer.flush();
			
			//close the connection
			reader.close();
			writer.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}