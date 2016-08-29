package tier2;

public class BoundedBuffer<T> extends QueueAsCircularArray<T> {

   public BoundedBuffer(int size) {
      super(size);
   }

   public synchronized void put(T x) {
      while (super.isFull()) { // wait, until not full
         try {
            wait();
         }
         catch (InterruptedException e) {
         }
         ;
      }
      // now not full
      super.put(x);

      // notify: an object has been put on buffer
      notifyAll();
   }

   public synchronized T take() {
      while (super.isEmpty()) { // wait, until not empty
         try {
            wait();
         }
         catch (InterruptedException e) {
         }
         ;
      }
      // now not empty
      T x = super.take();

      // notify: an object has been taken from buffer
      notifyAll();

      return x;
   }

   public synchronized int count() {
      return super.count();
   }

   public synchronized boolean isEmpty() {
      return super.isEmpty();
   }

   public synchronized boolean isFull() {
      return super.isFull();
   }
}
