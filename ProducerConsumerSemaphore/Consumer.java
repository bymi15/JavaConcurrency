
public class Consumer implements Runnable{
	
	BoundedBuffer<Character> buf;
	String alphabet = "abcdefghijklmnopqrstuvwxyz";

	private int delay = 0;
	
	public Consumer(BoundedBuffer<Character> buf){
		this.buf = buf;
	}
	
	public Consumer(BoundedBuffer<Character> buf, int delay){
		this.buf = buf;
		this.delay = delay;
	}
	
	public void run() {
		try{
			while (true) {
				char c = (Character) buf.get();
				//System.out.println("consumer took: " + c);

				Thread.sleep(delay);
			}
		}catch(InterruptedException e){}
	}

}
