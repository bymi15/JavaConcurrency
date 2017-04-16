
public class Departures implements Runnable{
	
	private CarParkControl carPark;

	private int delay;
	
	public Departures(CarParkControl carPark , int delay) {
		this.carPark = carPark;
		this.delay = delay;
	}
	
	public void run() {
		try{
			while(true) {
				carPark.depart();
				
				Thread.sleep(delay);
			}
		} catch(InterruptedException e){}
	}

}
