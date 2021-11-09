package dsa.iface;

/**
 * Interface to represent a Binary Tree.
 */
public interface IBinaryTree<T> extends ITree<T> {

   /**
    * Get the left child of node {@code n}.
    * 
    * @param n
    * @return
    */
   public INode<T> left( INode<T> n );

   /**
    * Get the right child of node {@code n}.
    * 
    * @param n
    * @return
    */
   public INode<T> right( INode<T> n );

   /**
    * Check whether node {@code n} has a left child.
    * 
    * @param n
    * @return {@code true} if node {@code n} has a left child, {@code false}
    *         otherwise.
    */
   public boolean hasLeft( INode<T> n );

   /**
    * Check whether node {@code n} has a right child.
    * 
    * @param n
    * @return {@code true} if node {@code n} has a right child, {@code false}
    *         otherwise.
    */
   public boolean hasRight( INode<T> n );
}
