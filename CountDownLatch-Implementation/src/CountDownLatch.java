import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatch {
	private int count;
	
	private Lock countLock = new ReentrantLock();
	private Condition countAvailable = countLock.newCondition();
	
	public CountDownLatch(int count){
		this.count = count;
	}
	
	public void countDown(){
		countLock.lock();
		try{
			count--;
			if(count == 0) countAvailable.signal();
		}finally{
			countLock.unlock();
		}
	}
	
	public void await() throws InterruptedException{
		countLock.lock();
		try{
			while(count > 0) countAvailable.await();
		}finally{
			countLock.unlock();
		}
	}
}
