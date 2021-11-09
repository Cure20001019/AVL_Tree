package dsa.impl;

import dsa.iface.INode;

public class ProperLinkedBinaryTree<T> extends AbstractBinaryTree<T> {

	/**
	 * Constructor - create an empty tree
	 */
	public ProperLinkedBinaryTree() {
		// create external root node with null parent and null element.
		this.root = newNode( null, null );
		
		// the size of the tree is now 1.
		this.size = 1;
	}

	/**
	 * Expand an external node - Store a value in the external node - Create two
	 * external nodes as children, making {@code n} an internal node
	 * 
	 * @param n
	 *            The node to expand. An exception will be thrown if it is not
	 *            external.
	 * @param e
	 *            The value to be stored in node {@code n}.
	 */
	public void expandExternal(INode<T> n, T e) {
		// cannot expand an internal node
		if (isInternal(n))
			throw new RuntimeException("Not an external node");
		// cast to BTNode so we can see element/left/right
		BTNode<T> node = (BTNode<T>) n;
		
		// store the element
		node.element = e;
		
		// add two new external nodes as children. For both, the element is 'null' and the parent is the node we are expanding 
		node.left = newNode(null, node);
		node.right = newNode(null, node);
		
		// two new nodes have been created, so update the size
		size += 2;
	}

	/**
	 * Remove a node from the tree
	 * 
	 * @param n
	 *            The node to be removed
	 */
	public T remove(INode<T> n) {
		BTNode<T> node = (BTNode<T>) n;
		// the left child is external, so remove 'n' and its left child.
		if (isExternal(node.left)) {
			// this is the root node, so replace with its right child.
			if (node == root) {
				root = node.right;
				node.right.parent = null;
			}
			// 'n' is the left child of its parent
			else if (node.parent.left == node) {
				node.parent.left = node.right;
				node.right.parent = node.parent;
			}
			// 'n' is the right child of its parent
			else {
				node.parent.right = node.right;
				node.right.parent = node.parent;
			}

		}
		// the left child was not external, but the right child is
		// ... so remove 'n' and its right child in the same way as above.
		else if (isExternal(node.right)) {
			if (node == root) {
				root = node.left;
				node.left.parent = null;
			} else if (node.parent.left == node) {
				node.parent.left = node.left;
				node.left.parent = node.parent;
			} else {
				node.parent.right = node.left;
				node.left.parent = node.parent;
			}
		}
		// both children were internal: cannot remove
		else {
			throw new RuntimeException("Cannot remove a node with two internal children.");
		}

		// two nodes have been removed from the tree, so update the size.
		size -= 2;

		return node.element; // return the element that was stored in the removed node
	}

	public static <E> ProperLinkedBinaryTree<E> merge(E root, ProperLinkedBinaryTree<E> a, ProperLinkedBinaryTree<E> b) {
		ProperLinkedBinaryTree<E> toReturn = new ProperLinkedBinaryTree<E>();
		toReturn.root.element = root;
		toReturn.root.left = a.root;
		a.root.parent = toReturn.root;
		toReturn.root.right = b.root;
		b.root.parent = toReturn.root;
		return toReturn;
	}
}
