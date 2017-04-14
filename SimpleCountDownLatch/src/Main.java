import java.util.concurrent.CountDownLatch;

public class Main {
	private static final int COUNT = 5;
	
	public static void main(String[] args) {
		
		final CountDownLatch latch = new CountDownLatch(COUNT);

		Thread[] t = new Thread[COUNT];

		for (int i = 0; i < COUNT; i++) {
			t[i] = new Thread(new Client("Client " + (i+1), 1000, latch));
			t[i].start();
		}
		
		try {
			latch.await(); // main thread is waiting for CountDownLatch to finish counting down to 0
			System.out.println("All clients are connected. The meeting will now begin.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//continue with the application
	}
}
