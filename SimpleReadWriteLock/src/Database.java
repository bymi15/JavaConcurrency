import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
	private final ReentrantReadWriteLock readWriteLock;
	
	private final Lock read;
	private final Lock write;
	
	private HashMap<String, String> database;
	
	public Database(){
		database = new HashMap<String, String>();
		readWriteLock = new ReentrantReadWriteLock();
		
		read  = readWriteLock.readLock();
		write = readWriteLock.writeLock();
	}
	
	public void set(String key, String value) {
		write.lock();
		try {
			database.put(key, value);
		} finally {
			write.unlock();
		}
	}

	public String get(String key) {
		read.lock();
		try {
			return database.get(key);
		} finally {
			read.unlock();
		}
	}

	public String[] getKeys() {
		read.lock();
		try {
			String keys[] = new String[database.size()];
			return database.keySet().toArray(keys);
		} finally {
			read.unlock();
		}
	}
}
