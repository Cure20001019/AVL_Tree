package dsa.impl;

import dsa.iface.INode;
import dsa.impl.BTNode;

public class BTNode<T> implements INode<T> {
    public BTNode<T> parent;
    public BTNode<T> left, right;
    public T element;

    public BTNode( T e, BTNode<T> p ) {
       this( e, p, null, null );
    }

    public BTNode( T e, BTNode<T> p, BTNode<T> l, BTNode<T> r ) {
       element = e;
       parent = p;
       left = l;
       right = r;
    }

    @Override
	public T element() {
       return element;
    }
 }
