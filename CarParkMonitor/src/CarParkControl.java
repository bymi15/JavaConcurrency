
public class CarParkControl {
	private int spaces;
	public final int capacity;
	
	public CarParkControl(int n){
		capacity = spaces = n;
	}
	
	synchronized public void arrive() throws InterruptedException{
		while(spaces <= 0) wait();
		spaces--;
		notifyAll();
	}
	
	synchronized public void depart() throws InterruptedException{
		while(spaces==capacity) wait();
		spaces++;
		notifyAll();
	}
	
	synchronized public int getNumCars(){
		return capacity - spaces;
	}
}
