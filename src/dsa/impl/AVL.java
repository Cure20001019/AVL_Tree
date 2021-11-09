package dsa.impl;

import dsa.iface.IIterator;
import dsa.iface.INode;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T> {

	// can be generally used to help calculate the relative depth of a deeper node to 
	//a node closer to the root. 
	//In this case specifically, it's used to calculate the relative depth 
	//between an external node and a specified internal node. 
	public int relativeDepthOf(INode<T> n, INode<T> ref) {
		   int depth;
		   if(n.equals(ref)) {
			   return depth=0;
		   }else {
			   depth = 1;
			   while(this.parent(n)!=ref) {
				   n = this.parent(n);
				   depth++;
			   }
		   }	   
		   return depth;
	}
	
	//for a specified internal node, get all of its descendants that are external.
	private void getExternals(INode<T> n, SLinkedList<INode<T>> externals){
		INode<T> temp = n;
		if(this.isExternal(temp)){
		   externals.insertLast(temp);
		}
		IIterator<INode<T>> i = this.children(temp);
		while(i.hasNext()) {
		   getExternals(i.next(),externals);
		}
	}
	
	//used to find the max relative depth among the external descendants of a specific node, 
	//which is essentially the height of the node.
	public int getHeight(INode<T> s) {
		   int temp=0;
		   int maxRelativeDepth=0;
		   SLinkedList<INode<T>> externals = new SLinkedList<INode<T>>();
		   getExternals(s,externals);
		   INode<INode<T>> n = externals.first();
		   maxRelativeDepth = relativeDepthOf(n.element(),s);
		   while(externals.next(n)!=null) {
			   n = externals.next(n);
			   temp = relativeDepthOf(n.element(),s);
			   if(temp>maxRelativeDepth) {
				   maxRelativeDepth = temp;
			   }
		   }
		   return maxRelativeDepth;
	}
	
	
	@Override
	public void insert( T element ) {
		super.insert(element);
		INode<T> n = find(this.root(),element);
		while(n!=null) {
			if(!isBalanced(n)) {
				trinodeRotation(n);
				return;
			}else{
				n = this.parent(n);
			}
		}
	   // TODO: Implement the insert(...) method.
	}

	@Override
	public boolean contains( T element ) {
		// TODO: Implement the contains(...) method.
		return super.contains(element);
	}

	@Override
	public void remove( T element ) {
		//in case of removing an internal node, it is necessary to locate the node before it is removed
		//(i.e.replacing the element of the node with the element stored in its successor, the node remains the same).
		INode<T>  n= find(this.root(),element);  
		super.remove(element);
		while(n!=null) {
			if(!isBalanced(n)) {
				trinodeRotation(n);
			}else {
				n = this.parent(n);
			}
		}
		// TODO: Implement the remove(...) method.
	}

	//first deduce the structure under the unbalanced and locate x, then pass in x or its parent depending on 
	//the deduced structure to one of the four types of trinode restructuring situations.
	private void trinodeRotation( INode<T> n ) {
		BTNode<T> x = (BTNode<T>) n;
		if(getHeight(x.left)<getHeight(x.right)) {
			x=x.right;
			if(getHeight(x.left)<getHeight(x.right)) {
				x=x.right;
				RRRotation(x.parent);
			}else if(getHeight(x.left)>getHeight(x.right)) {
				x=x.left;
				LRRotation(x);
			}
		}else {
			x=x.left;
			if(getHeight(x.left)<getHeight(x.right)) {
				x=x.right;
				RLRotation(x);				
			}else if(getHeight(x.left)>getHeight(x.right)) {
				x=x.left;
				LLRotation(x.parent);
			}
		}
        	
		// TODO: Implement the trinodeRotation(...) method.
	}
	
	private void LLRotation(BTNode<T> x) {
		BTNode<T> parent = (BTNode<T>) this.parent(x);
		BTNode<T> grdparent = (BTNode<T>) this.parent(this.parent(x));
		BTNode<T> temp = x.right;
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

	}
	
	private void LRRotation(BTNode<T> x) {
		LLRotation(x);
		RRRotation(x);
	}
    
	private void RRRotation(BTNode<T> x) {
		BTNode<T> parent = (BTNode<T>) this.parent(x);
		BTNode<T> grdparent = (BTNode<T>) this.parent(this.parent(x));
		BTNode<T> temp = x.left;
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
	}
    
    private void RLRotation(BTNode<T> x) {
		RRRotation(x);
		LLRotation(x);
	}
	
	
	private boolean isBalanced(INode<T> x) {
		BTNode<T> temp = (BTNode<T>) x;
		int h1=2,h2=0;
		if(isExternal(temp.left)&&isExternal(temp.right)) {
			return true;
		}else if(isExternal(temp.left)&&!isExternal(temp.right)) {
			h1=0;
			h2 = getHeight((INode<T>)temp.right);
		}else if(!isExternal(temp.left)&&isExternal(temp.right)) {
			h2=0;
			h1 = getHeight((INode<T>)temp.left);
		}else if(!isExternal(temp.left)&&!isExternal(temp.right)){
			h1 = getHeight((INode<T>)temp.left);
			h2 = getHeight((INode<T>)temp.right);
		}
		return Math.abs(h1-h2)<=1;
	}
	
}
