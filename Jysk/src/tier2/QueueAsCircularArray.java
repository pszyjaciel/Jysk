package tier2;

/**
 * This <CODE>class QueueAsCircularArray</CODE> implements <CODE>interface IQueue</CODE>
 * as a circular array.
 * 
 * @version 1.0; - 7th March 2004
 * @author Hans Sondergaard, <A HREF="mailto:hso@vitusbering.dk">hso@vitusbering.dk</A>
 *
 * @design
 *   Here should be a link til the design document.
 * @test
 *   <A HREF="E:\Java\OOP\reports\index.html">Test report</A>
 **/
public class QueueAsCircularArray<T> implements IBuffer<T> {
   private T[] queue;
   private int count, size;
   private int front, rear;

   /**
    * Creates an empty queue with places for <CODE>size</CODE> elements
    * @param size is the number of places in this queue
    * @precondition size > 0
    * @postcondition queue is empty 
    */
   public QueueAsCircularArray(int size) {
      queue = (T[]) (new Object[size]);  // not a clean Java solution
      this.size = size;
      count = 0;
      rear = 0;
      front = 0;
   }

   /**
    * @param x is not null  
    * @precondition queue is not full
    * @postcondition queue is not empty
    */
   public void put(T x) {
      queue[rear] = x;
      rear = (rear + 1) % queue.length;
      count++;
   }

   /**
    * @precondition queue is not empty
    * @postcondition queue is not full
    */
   public T take() {
      T obj = queue[front];
      queue[front] = null;
      front = (front + 1) % queue.length;
      count--;
      return obj;
   }

   /**
    * @precondition queue is not empty
    * @postcondition queue is not empty 
    */
   public T first() {
      return queue[front];
   }

   /**
    */
   public boolean isEmpty() {
      return count == 0;
   }

   /**
    */
   public boolean isFull() {
      return count == queue.length;
   }

   /** 
    */
   public int count() {
      return count;
   }

   public int size() {
      return size;
   }
}
