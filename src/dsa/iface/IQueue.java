package dsa.iface;

/**
 * ADT to represent a Queue. A Queue is a First-In, First-Out (FIFO) data structure.
 *
 */
public interface IQueue<T> {
   
   /**
    * Add {@code value} to the back of the queue.
    * @param value
    */
   public void enqueue( T value );
   
   /**
    * Remove and return the first element in the queue.
    * @return
    */
   public T dequeue();
   
   /**
    * Get the number of elements in the queue.
    * @return
    */
   public int size();
   
   /**
    * Check if the queue is empty.
    * @return {@code true} if the queue contains no elements, {@code false} otherwise.
    */
   public boolean isEmpty();
   
   /**
    * Get (but don't remove) the element at the front of the queue.
    * @return
    */
   public T front();
}
