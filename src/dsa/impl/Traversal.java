package dsa.impl;

import dsa.iface.IIterator;
import dsa.iface.INode;
import dsa.iface.ITree;

public class Traversal<T>{
	
	public Traversal (){}
	
	private void visitNode(INode<T> x)
	{
		System.out.println(x.element());
		// TODO: Implement the visitNode(...) method.
	}
	
	
	public void preOrderTraversal(ITree<T> tree)
	{
		INode<T> n = tree.root();
		preorder(tree, n);
		
		// TODO:	Replace type Object with suitable tree type
		//			Implement the preOrderTraversal(...) method.
	}

	
	public void inOrderTraversal(AbstractBinaryTree<T> bt)
	{
		BTNode<T> n = (BTNode<T>) bt.root();
		inorder(bt, n);
		// TODO: 	Replace type Object  with suitable tree type
		//			Implement the inOrderTraversal(...) method.
	}

	public void postOrderTraversal(ITree<T> tree)            
	{
		INode<T> n = tree.root();
		postorder(tree, n);
		// TODO: 	Replace type Object with suitable tree type
		//			Implement the postOrderTraversal(...) method.
	}
	
	//Comments: the parameter types of methods preOrderTraversal() and postOrderTraversal() 
	//are set to ITree<T> because either of these two types of traversal can be applied to any kind of tree; 
	//the parameter type of method inOrderTraversal() is set to AbstractBinaryTree<T> because an inorder traversal 
	//only makes sense when it is done to a binary tree where you can put the step of printing the element of a node 
	//between the steps of accessing the elements of its two children.
	
	private void preorder(ITree<T> tree, INode<T> n) {
		visitNode(n);
		IIterator<INode<T>> i = tree.children(n);
		while(i.hasNext()) {
			preorder(tree, i.next());
		}
	}
	
	private void inorder(AbstractBinaryTree<T> bt, BTNode<T> n) {
		if(bt.hasLeft(n)) {
			inorder(bt, (BTNode<T>) bt.left(n));
		}
		visitNode(n);
		if(bt.hasRight(n)) {
			inorder(bt, (BTNode<T>) bt.right(n));
		}
		
	}
	
	private void postorder(ITree<T> tree, INode<T> n) {
		IIterator<INode<T>> i = tree.children(n);
		while(i.hasNext()) {
			postorder(tree,i.next());
		}
		visitNode(n);
	}
	
	
}
