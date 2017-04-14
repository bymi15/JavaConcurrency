import java.util.concurrent.CyclicBarrier;

public class Main {
	private static final int COUNT = 5;
	
	public static void main(String[] args) {
		
		final CyclicBarrier barrier = new CyclicBarrier(COUNT, new Runnable(){
            public void run(){
                //This task will be executed once all three threads complete execution
                System.out.println("All " + COUNT + " cars have reached the barrier!");
            }
        });
		
		final int NUMWAVES = 10;
		
		for(int i = 0; i < NUMWAVES; i++){
			sendCars(barrier, (i+1));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void sendCars(CyclicBarrier barrier, int waveNum){
		Thread[] t = new Thread[COUNT];

		System.out.println("");
		System.out.println("Wave " + waveNum + " is approaching the barrier");

		for (int i = 0; i < COUNT; i++) {
			t[i] = new Thread(new Car(barrier), "Car " + (i + 1));
			t[i].start();
		}
	}
}
