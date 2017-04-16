package ThreadPool;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
	public static void main(String args[]) {
		//Creates a thread pool
		//Core pool size = 10 threads
		//Max pool size = 20 threads
		//Thread idle timeout = 60 seconds
		Executor pool = new ThreadPoolExecutor(10, 20, 60000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		ServerSocket listen = null;
		
		try {
			// Create server socket to listen for connection on port 9999
			listen = new ServerSocket(9999);
			System.out.println("server is listening to port " + listen.getLocalPort());
			
			while (true) {
				Socket socket = listen.accept(); // Wait for a client
				System.out.println("client: " + socket.getInetAddress().toString() + " connected");
				
				// Create a Runnable for the client socket and execute it using the thread pool
				// and then get back to listening for the next client.
				ServerTask task = new ServerTask(socket);
				pool.execute(task);
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