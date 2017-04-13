
public class Producer implements Runnable{
	
	BoundedBuffer<Character> buf;
	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	private int delay = 0;
	
	public Producer(BoundedBuffer<Character> buf){
		this.buf = buf;
	}

	public Producer(BoundedBuffer<Character> buf, int delay){
		this.buf = buf;
		this.delay = delay;
	}
	
	public void run() {
		try{
			int i = 0;
			while (true) {
				char c = alphabet.charAt(i);
				buf.put(c);
				i = (i + 1) % alphabet.length();
				//System.out.println("producer placed: " + c);
				
				Thread.sleep(delay);
			}
		}catch(InterruptedException e){}
	}

}
