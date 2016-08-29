package tier2;

public interface IBuffer<T> {
   // public void put(Pallet o) throws InterruptedException; // put object into buffer
   // public Pallet get() throws InterruptedException; // get an object from buffer

   void put(T x) throws InterruptedException;

   T take() throws InterruptedException;

   T first() throws InterruptedException;

   boolean isEmpty() throws InterruptedException;

   boolean isFull() throws InterruptedException;

   int count() throws InterruptedException;

   int size() throws InterruptedException;

}
