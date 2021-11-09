package dsa.impl;

import java.util.logging.Logger;

import dsa.iface.IBinarySearchTree;
import dsa.iface.IIterator;
import dsa.iface.INode;
import dsa.iface.ITree;

public class BinarySearchTree<T extends Comparable<T>> extends ProperLinkedBinaryTree<T> implements IBinarySearchTree<T> {

   private static Logger logger = Logger.getLogger( BinarySearchTree.class.getName() );

   /**
    * Find a node in the tree that contains a particular value.
    * @param start The node to begin the search from (to search an entire tree, begin at its root).
    * @param value The value to search for.
    * @return The internal node that contains {@code value}, if it is contained in the tree. If {@code value} is not
    *    contained in the tree, returns the external node that the search terminated at.
    */
   protected INode<T> find( INode<T> start, T value ) {
      if ( isExternal( start ) )
         return start;

      int result = value.compareTo( start.element() );

      if ( result < 0 )
         return find( left( start ), value );
      else if ( result > 0 )
         return find( right( start ), value );

      return start;
   }

   @Override
public void insert( T value ) {
      INode<T> position = find( root(), value );
      if ( isExternal( position ) ) {
         // value is not in the tree so insert it here
         expandExternal( position, value );
      }
   }

   @Override
public void remove( T value ) {
      INode<T> position = find( root(), value );
      if ( isInternal( position ) ) {
         // value is in the tree so remove it
         if ( isInternal( left( position ) ) && isInternal( right( position ) ) ) {
            // both children are internal, so find the position of the next
            // largest value
            // copy the value and remove the position
            INode<T> current = right( position );
            while ( isInternal( left( current ) ) )
               current = left( current );
            replace( position, current.element() );
            remove( current );
         }
         else {
            remove( position );
         }
      }
   }

   @Override
public boolean contains( T value ) {
      INode<T> position = find( root(), value );
      return isInternal( position );
   }

   
   
   public static <T extends Comparable<T>> IBinarySearchTree<T> fromTree( ITree<T> t ) {
      BinarySearchTree<T> out = new BinarySearchTree<T>();
      INode<T> root = t.root();
      out.importSubTree( out.root(), t, root );
      if ( ! out.isValid( out.root() ) ) {
         throw new RuntimeException( "Error: Invalid Binary Search Tree" );  
      }
      return out;
   }

   private void importSubTree( INode<T> btn, ITree<T> t, INode<T> n ) {
      logger.info( "Processing [" + n.element() + "]" );
      expandExternal( btn, n.element() );

      IIterator<INode<T>> it = t.children( n );
      int i = 0;
      while ( it.hasNext() ) {
         INode<T> child = it.next();
         if ( i == 0 ) {
            if ( child.element() != null ) {
               importSubTree( left( btn ), t, child );
            }
         }
         else if ( i == 1 ) {
            if ( child.element() != null ) {
               importSubTree( right( btn ), t, child );
            }
         }
         else {
            throw new RuntimeException( "Error: Conversion to Binary Search Tree failed: the node [" + n.element() + "] has more than 2 children" );
         }
         i++;
      }
   }
   
   // verify whether a subtree is a valid binary search tree
   private boolean isValid( INode<T> root ) {
      if ( isExternal( root ) )
         return true;
      
      return true;
   }
}
