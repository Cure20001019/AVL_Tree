package dsa.impl;

import dsa.iface.INode;

public class ImprovedAVL<T extends Comparable<T>> extends BinarySearchTree<T>  {

	//the class AVLNode is an improved version of BTNode which stores the height of a node
	//it is nested in the class ImprovedAVL here as it has a close relation to the class 
	protected static class AVLNode<T extends Comparable<T>> extends BTNode<T> {
	    int height = 1;

	    public AVLNode( T e, BTNode<T> p ) {
	    	super(e, p);
	    }
	    
	    public int balanceFactor() {
	    //to calculate the balance factor(i.e.the difference between the heights of children nodes)
	    //used for judging if a node is balanced
	    	int leftHeight;
	    	int rightHeight;
	    	if(left == null && right == null) {
	    		//if both left and right attributes are null, their parent node must be external, 
		    	//thus a balance factor <=1 is returned.
	    		return 0;
	    	}
	        leftHeight = ((AVLNode<T>)left).height;
	    	rightHeight = ((AVLNode<T>)right).height;
	    	return leftHeight - rightHeight;
	    }
	    
	    private void updateHeight() {
	    	int leftHeight = 0;
	    	int rightHeight = 0;
	    	if(left == null && right == null) {
	    	//if both left and right attributes are null, their parent node must be external, 
	    	//thus it is assigned a height of 0
	    		height = 0;
	    	}else {
	    		leftHeight = ((AVLNode<T>)left).height;
	    		rightHeight = ((AVLNode<T>)right).height;
	    		height = Math.max(leftHeight, rightHeight)+1;
	    	}
	    }
	 }
	
	
	//specified constructor for improved AVL tree as the root node has to be an AVLNode. 
	//Using the default constructor in ProperLinkedBinaryTree class would construct root to be 
	//a BTNode instance which makes it impossible to cast the node to an AVLNode which 
	//has height of the node stored inside
	public ImprovedAVL() { 
		root = createNewNode(null,null);
	}
	private AVLNode<T>  createNewNode(T e, BTNode<T> p){//used for constructing new AVLNodes
		return (new AVLNode<T>(e,p));
		
	}
	
	
	//copied from ProperLinkedBinaryTree, the use of BTNode is changed to AVLNode to instantiate 
	//every node in the tree as an AVLNode, the heights of the two new external nodes are updated 
	//using the updateHeight method in AVLNode class
	private void AVLexpandExternal(INode<T> n, T e) {
		// cannot expand an internal node
		if (isInternal(n))
			throw new RuntimeException("Not an external node");
		// cast to BTNode so we can see element/left/right
		AVLNode<T> node = (AVLNode<T>) n;
		
		// store the element
		node.element = e;
		
		// add two new external nodes as children. For both, the element is 'null' and the parent is the node we are expanding
		//the heights of these two new external nodes must be updated right after their creation because they are external so that 
		//their heights won't be updated after insertion in which the loop only goes through the internal nodes.
		node.left = createNewNode(null, node);
		node.right = createNewNode(null, node);
		updateHeight(node.left);
		updateHeight(node.right);
		
		// two new nodes have been created, so update the size
		size += 2;
	}
	
	
	//copied from BinarySearchTree
	private void AVLinsert( T value ) {
	      INode<T> position = find( root(), value );
	      if ( isExternal( position ) ) {
	         // value is not in the tree so insert it here
	         AVLexpandExternal( position, value );
	      }
	}
	
	
	@Override
	public void insert( T element ) {
		AVLinsert(element);
		BTNode<T> temp = (BTNode<T>) find(this.root(),element);
		while(temp!=null) {
			if(!isBalanced(temp)) {
				trinodeRotation(temp); 
			}
			//the maintainence of the heights of balanced AVLNodes is done in the outer loop, as long as a node is balanced, we update its height, or else we do the restructuring.
			//The maintainence of the heights of unbalanced AVLNodes is done after each restructuring.
			//this method makes sure that every node in the tree has the correct height after each insertion or removal.
			//compared to using recursion to calculate the height of a node every time its height is needed, this way of maintaining heights stored inside each node reduces the 
			//complexity of insertion and removal to O(h) where h is the height of the tree which equals to log(n).
			updateHeight(temp);
			temp = temp.parent; 
		}
	   // TODO: Implement the insert(...) method.
	}

	@Override
	public boolean contains( T element ) {
		// TODO: Implement the contains(...) method.
		return super.contains(element);
	}
	
	
	//no need for a designated method for removal as all nodes have been instantiated to be AVLNodes which 
	//naturally makes them BTNodes, so removal using super.remove() is fine here.
	@Override
	public void remove( T element ) {
		BTNode<T> temp= (BTNode<T>) find(this.root(),element); 
		//in case of removing an internal node, it is necessary to locate the node before it is removed
		//(i.e.replacing the element of the node with the element stored in its successor, 
		//reference to the node is unchanged). 
		super.remove(element);
		while(temp!=null) {
			if(!isBalanced(temp)) {
				trinodeRotation(temp);
			}
			//the reason for putting updateHeight here is the same as that in method insert
			updateHeight(temp);
			temp = temp.parent; 
		}
		// TODO: Implement the remove(...) method.
	}

	
	//first deduce the structure under the unbalanced and locate x, then pass in x or its parent depending 
	//on the deduced structure to one of the four types of trinode restructuring situations.
	private void trinodeRotation( INode<T> n ) {
		AVLNode<T> x = (AVLNode<T>) n;
		AVLNode<T> left = (AVLNode<T>) x.left;
		AVLNode<T> right = (AVLNode<T>) x.right;
        int  rightHeight = right.height;
        int leftHeight = left.height;
		if(leftHeight<rightHeight) {
			x = right;
			rightHeight = ((AVLNode<T>)x.right).height;
			leftHeight = ((AVLNode<T>)x.left).height;
			if(leftHeight<rightHeight) {
				x=(AVLNode<T>) x.right;
				RRRotation((AVLNode<T>) x.parent);
			}else if(leftHeight>rightHeight) {
				x=(AVLNode<T>) x.left;
				LRRotation(x);
			}
		}else {
			x=left;
			rightHeight = ((AVLNode<T>)x.right).height;
			leftHeight = ((AVLNode<T>)x.left).height;
			if(leftHeight<rightHeight) {
				x=(AVLNode<T>) x.right;
				RLRotation(x);				
			}else if(leftHeight>rightHeight) {
				x=(AVLNode<T>) x.left;
				LLRotation((AVLNode<T>) x.parent);
			}
		}
		// TODO: Implement the trinodeRotation(...) method.
	}
	
	private void LLRotation(AVLNode<T> x) {
		AVLNode<T> parent = (AVLNode<T>) this.parent(x);
		AVLNode<T> grdparent = (AVLNode<T>) this.parent(this.parent(x));
		AVLNode<T> temp = (AVLNode<T>) x.right;

		if(parent.equals(root)) {
			x.parent= parent.parent;
			temp.parent = parent;
			x.right = parent;
			parent.parent = x;
			parent.left = temp;
			root=x;
		}else {
			if(parent.equals(grdparent.left)) {
				x.parent= parent.parent;
				temp.parent = parent;
				x.right = parent;
				parent.parent = x;
				parent.left = temp;
				grdparent.left = x;
			}else if(parent.equals(grdparent.right)) {
				x.parent= parent.parent;
			    temp.parent = parent;
				x.right = parent;
				parent.parent = x;
				parent.left = temp;
				grdparent.right = x;
			}
		}
		updateHeight(parent);
		//only the height of the parent need to be updated because the height of x is unchanged 
		//after each rotation 

	}
	
	private void LRRotation(AVLNode<T> x) {
		LLRotation(x);
		RRRotation(x);
	}
 
	private void RRRotation(AVLNode<T> x) {
		AVLNode<T> parent = (AVLNode<T>) this.parent(x);
		AVLNode<T> grdparent = (AVLNode<T>) this.parent(this.parent(x));
		AVLNode<T> temp = (AVLNode<T>) x.left;
		if(parent.equals(root)) {
			x.parent= parent.parent;
			temp.parent = parent;
			x.left = parent;
			parent.parent = x;
			parent.right = temp;
			root=x;
		}else {
			if(parent.equals(grdparent.left)) {
				x.parent= parent.parent;
				temp.parent = parent;
				x.left = parent;
			    parent.parent = x;
				parent.right = temp;
				grdparent.left = x;
			}else if(parent.equals(grdparent.right)) {
				x.parent= parent.parent;
				temp.parent = parent;
				x.left = parent;
				parent.parent = x;
				parent.right = temp;
				grdparent.right = x;
			}
		}
		updateHeight(parent);
		//only the height of the parent need to be updated because the height of x is unchanged 
		//after each rotation

	}
 
    private void RLRotation(AVLNode<T> x) {
		RRRotation(x);
		LLRotation(x);
	}
	
	private void updateHeight(BTNode<T> x) {
		//an encapsulation of the method updateHeight() in class AVLNode. 
		//this is done because a cast would make the code look
		//messy if it is not encapsulated.
		((AVLNode<T>)x).updateHeight();
	}
    
	private boolean isBalanced(BTNode<T> x) {
		//checks if a node is balanced by accessing its balance factor and comparing its absolute value to 1
		return Math.abs(((AVLNode<T>)x).balanceFactor())<=1;
	}

}
