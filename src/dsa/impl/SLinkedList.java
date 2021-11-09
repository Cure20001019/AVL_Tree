package dsa.impl;

import dsa.iface.IIterator;
import dsa.iface.IList;
import dsa.iface.INode;

public class SLinkedList<T> implements IList<T> {
   private SNode top;
   private int size;
   
   @Override
public int size(){
      return size;
   }
   
   @Override
public boolean isEmpty(){
      return size == 0;
   }
   
   @Override
public INode<T> first(){
      return top;
   }
   
   @Override
public INode<T> last(){
      SNode c = top;
      while(c.next != null){
         c = c.next;
      }
      return c;
   }
   
   @Override
public INode<T> next(INode<T> n){
      SNode n1 = (SNode) n;
      return n1.next;
   }
   
   @Override
public INode<T> prev(INode<T> n){
      SNode c = top;
      while(c != null && c.next != n){
         c = c.next;
      }
      return c;
   }
   @Override
public INode<T> insertAfter(INode<T> n, T e){
      SNode n1 = (SNode) n;
      SNode d = new SNode(e, n1.next);
      n1.next = d;
      size++;
      return d;
   }
   
   @Override
public INode<T> insertBefore(INode<T> n, T e){
      SNode n1 = (SNode) n;
      if(n1 == top){
         return insertFirst(e);
      } else {
         SNode b = (SNode) prev(n1);
         SNode d = new SNode(e, n1);
         b.next = d;
         size++;
         return d;
      }
      
   }
   
   @Override
public INode<T> insertFirst(T e){
      SNode n = new SNode(e, top);
      top = n;
      size++;
      return n;
   }
   
   @Override
public INode<T> insertLast(T e){
      if(isEmpty()){
         return insertFirst(e);
      } else{
         SNode l = (SNode) last();
         SNode d = new SNode(e, null);
         l.next = d;
         size++;
         return d;
      }
      
   }
   
   @Override
public T remove(INode<T> n){
      SNode n1 = (SNode) n;
      if(n1 == top){
         top = top.next;
         n1.next = null;
         
      } else {
         SNode b = (SNode) prev(n1);
         b.next = n1.next;
         n1.next = null;
      }
      size--;
      return n.element();
   }
   
   private class SNode implements INode<T> {
      public SNode next;
      public T element;
      
      @Override
	public T element() {
         return element;
      }
      
      public SNode( T e, SNode n ) {
         element = e;
         next = n;
      }
   }

   @Override
   public T replace( INode<T> n, T e ) {
      SNode n1 = (SNode) n;
      T toReturn = n1.element();
      n1.element = e;
      return toReturn;
   }
   
   @Override
   public IIterator<T> iterator() {
	   return new ListIterator<T>( this );
   }
}