public class Reader implements Runnable{

	private Database database;
	
	private int delay;
	
	private String name;
	
	private volatile boolean running = true;
	
	public Reader(String name, Database database, int delay){
		this.name = name;
		this.database = database;
		this.delay = delay;
	}
	
	public void run() {
		while(running){
			String [] keys = database.getKeys();
			for (String key : keys) {
				String value = database.get(key);
				System.out.println(name + " has read ("+ key + "," + value + ")");
			}
			
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
