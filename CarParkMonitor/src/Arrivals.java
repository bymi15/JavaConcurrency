
public class Arrivals implements Runnable{
	
	private CarParkControl carPark;
	
	private int delay;
	
	public Arrivals(CarParkControl carPark, int delay) {
		this.carPark = carPark;
		this.delay = delay;
	}
	
	public void run() {
		try{
			while(true) {
				carPark.arrive();
				
				Thread.sleep(delay);
			}
		} catch(InterruptedException e){}
	}

}
