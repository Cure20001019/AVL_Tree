package dsa.iface;

/**
 * Interface representing a Tree data structure.
 * @author daithi
 *
 */

public interface ITree<T> {
   /**
    * Get the root node of the tree.
    * @return
    */
    public INode<T> root();
    
    /**
     * Get the parent of node {@code n} (or {@code null} if the node has no parent (i.e. is the root).
     * @param n
     * @return
     */
    public INode<T> parent(INode<T> n);
    
    /**
     * Get an {@link IIterator} to iterate over the child nodes of {@code n}.
     * @param n
     * @return
     */
    public IIterator<INode<T>> children(INode<T> n);
    
    /**
     * Test whether node {@code n} is an internal node (i.e. has children).
     * @param n
     * @return
     */
    public boolean isInternal(INode<T> n);
    
    /**
     * Test whether node {@code n} is external (i.e. has no children).
     * @param n
     * @return
     */
    public boolean isExternal(INode<T> n);
    
    /**
     * Test whether node {@code n} is the root node in the tree.
     * @param n
     * @return
     */
    public boolean isRoot(INode<T> n);
    
    /**
     * Get the size of the tree (i.e. the number of nodes it contains).
     * @return
     */
    public int size();
    
    /**
     * Test whether the tree is empty.
     * @return
     */
    public boolean isEmpty();
    
    /**
     * Get an {@link IIterator} that iterates over all the elements contained in the tree's nodes. 
     * @return
     */
    public IIterator<T> iterator();
    
    /**
     * Get an {@link IIterator} that iterates over all the nodes in the tree.
     * @return
     */
    public IIterator<INode<T>> nodes();
    
    /**
     * Replace the element contained in a node.
     * @param n
     * @param t
     * @return The element that was formerly contained in the node before replacement.
     */
    public T replace(INode<T> n, T t);
}
