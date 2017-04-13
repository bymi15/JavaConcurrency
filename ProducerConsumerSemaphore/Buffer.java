
public interface Buffer<E>{
	public void put(E o) throws InterruptedException;
	public E get() throws InterruptedException;
}
