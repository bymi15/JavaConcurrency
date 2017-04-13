
public class Main {
	public static void main(String[] args) {
		BoundedBuffer<Character> buf = new BoundedBuffer<Character>(5);
		
		Consumer consumer = new Consumer(buf, 1000);
		Producer producer = new Producer(buf, 500);
		
		Thread ct = new Thread(consumer);
		Thread pt = new Thread(producer);
		
		ct.start();
		pt.start();
		
		while(true){
			Object[] arr = buf.toArr();
			for(int i = 0; i < buf.size; i++){
				char c;
				if(arr[i] == null){
					c = '_';
				}else{
					c = (char) arr[i];
				}
				
				System.out.print("" + c + " ");
			}
			System.out.println("");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
