
public class Main {
	private static final int NUM_WRITERS = 2;
	private static final int NUM_READERS = 3;
	
	
	private static Database database = new Database();
	private static Writer[] writers = new Writer[NUM_WRITERS];
	private static Reader[] readers = new Reader[NUM_READERS];
	private static Thread[] writerThreads = new Thread[NUM_WRITERS];
	private static Thread[] readerThreads = new Thread[NUM_READERS];
	
	public static void main(String[] args){
		//initialise and start the writers
		for(int i = 0; i < NUM_WRITERS; i++){
			writers[i] = new Writer("Writer " + (i+1), database, 2000);
			writerThreads[i] = new Thread(writers[i]);
			writerThreads[i].start();
		}
		//initialise and start the readers
		for(int i = 0; i < NUM_READERS; i++){
			readers[i] = new Reader("Reader " + (i+1), database, 4000);
			readerThreads[i] = new Thread(readers[i]);
			readerThreads[i].start();
		}
		
		//wait 10 seconds
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//stop the writers and readers
		for(int i = 0; i < NUM_WRITERS; i++){
			writers[i].stop();
		}
		for(int i = 0; i < NUM_READERS; i++){
			readers[i].stop();
		}
		
		System.out.println("Writers and readers have been stopped.");
		
		//display final database state
		System.out.println("");
		System.out.println("---Final-Database-State---");
		String[] keys = database.getKeys();
		for(String key : keys){
			System.out.println(key  + " : " + database.get(key));
		}
		System.out.println("--------------------------");
		
	}
}
