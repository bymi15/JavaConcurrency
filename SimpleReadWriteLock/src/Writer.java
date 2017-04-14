import java.util.Random;

public class Writer implements Runnable{
	
	private Database database;
	
	private int delay;

	private String name;
	
	private volatile boolean running = true;
	
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private Random random;
	
	public Writer(String name, Database database, int delay){
		this.name = name;
		this.database = database;
		this.delay = delay;
		this.random = new Random();
	}
	
	public void run() {
		while(running){
			String key = "";
			String value = "";
			//generate random strings
			for(int i = 0; i < (3+random.nextInt(10)); i++){
				key = key + alphabet.charAt(random.nextInt(alphabet.length()));
			}
			for(int i = 0; i < (3+random.nextInt(10)); i++){
				value = value + alphabet.charAt(random.nextInt(alphabet.length()));
			}
			
			database.set(key, value);
			
			System.out.println(name + " has written (" + key + "," + value + ")");
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		this.running = false;
	}

}
