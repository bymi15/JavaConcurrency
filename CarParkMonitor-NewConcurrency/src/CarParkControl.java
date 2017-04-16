import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarParkControl {
	private int spaces;
	public final int capacity;
	
	private Lock carParkLock = new ReentrantLock();
	private Condition spacesAvailable = carParkLock.newCondition();
	private Condition carsAvailable = carParkLock.newCondition();
	
	public CarParkControl(int n){
		capacity = spaces = n;
	}
	
	public void arrive() throws InterruptedException{
		carParkLock.lock();
		
		try{
			while(spaces <= 0) spacesAvailable.await();
			spaces--;
			carsAvailable.signal();
		}finally{
			carParkLock.unlock();
		}
	}
	
	public void depart() throws InterruptedException{
		carParkLock.lock();
		
		try{
			while(spaces == capacity) carsAvailable.await();
			spaces++;
			spacesAvailable.signal();
		}finally{
			carParkLock.unlock();
		}
	}
	
	synchronized public int getNumCars(){
		return capacity - spaces;
	}
}
