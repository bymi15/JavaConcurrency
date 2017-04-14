import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
	private CyclicBarrier barrier;

    public Car(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting at the barrier");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " has passed the barrier");
        } catch (InterruptedException e) {
        	e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
