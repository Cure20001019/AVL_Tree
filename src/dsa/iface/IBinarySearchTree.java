package dsa.iface;

public interface IBinarySearchTree<T extends Comparable<T>> extends IBinaryTree<T> {

	/**
	 * Insert a new element into the tree. No effect if it already contained in
	 * the tree.
	 * 
	 * @param element
	 */
	public void insert(T element);

	/**
	 * Remove an element from the tree. No effect if it is not found in the
	 * tree.
	 * 
	 * @param element
	 */
	public void remove(T element);

	/**
	 * Check whether an element is contained in the tree.
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(T element);
}
