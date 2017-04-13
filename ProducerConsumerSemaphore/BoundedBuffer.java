public class BoundedBuffer<E> implements Buffer<E>{
	public final int size;
	private E[] buf;
	private int in, out;
	private int count;
	
	private Semaphore full;
	private Semaphore empty;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(int size){
		in = out = 0;
		count = 0;
		this.size = size;
		buf = (E[]) new Object[size];
		full = new Semaphore(0);
		empty = new Semaphore(size);
	}
	
	public void put(E o) throws InterruptedException{
		empty.down();
		
		synchronized(this){
			buf[in] = o;
			count++;
			in = (in+1) % size;
		}
		
		full.up();
	}

	public E get() throws InterruptedException {
		full.down();

		synchronized(this){
			E o = buf[out];
			buf[out] = null;
			count++;
			out = (out+1) % size;
			empty.up();
			
			return o;
		}
	}
	
	public E[] toArr(){
		return buf;
	}

}
