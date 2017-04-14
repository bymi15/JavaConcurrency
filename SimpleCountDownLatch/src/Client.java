import java.util.concurrent.CountDownLatch;

public class Client implements Runnable {
	private final String name;
    private final int duration;
    private final CountDownLatch latch;
    
    public Client(String name, int duration, CountDownLatch latch) {
    	this.name = name;
        this.duration = duration;
        this.latch = latch;
    }

    public void run() {
    	try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.println( name + " has connected");
        latch.countDown(); //reduce count of CountDownLatch by 1
    }
}
