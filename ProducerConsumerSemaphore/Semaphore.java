
public class Semaphore {
	private int value;
	
	public Semaphore(int initialValue){
		this.value = initialValue;
	}
	
	synchronized public void up(){
		value++;
		notify();
	}
	
	synchronized public void down() throws InterruptedException{
		while(value==0) wait();
		value--;
	}
}
