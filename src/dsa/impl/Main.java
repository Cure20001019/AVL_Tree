package dsa.impl;

import java.io.BufferedReader;import java.io.File;import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
   private static void simpleTest() {
	      //For a rather simple test
	      System.out.println("Simple test:");
	      //Please change the code manually to switch between AVL and ImprovedAVL
	      //AVL<Integer> myAVL = new AVL<Integer>();
	      ImprovedAVL<Integer> myAVL = new ImprovedAVL<Integer>();
	      Traversal<Integer> myTraversal = new Traversal<Integer>();
	      
	      //At the root:
	      //LR rotation
	      System.out.println("Left-right rotation at root:");
	      //Preparing operations to lead to restructuring
	      myAVL.insert(1);
	      myAVL.insert(3);
	      //Before:
	      System.out.println("Before left-right Rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');;
	      
	      //Operation that causes restructuring
	      myAVL.insert(2);
	      
	      //After:
	      System.out.println("After left-Right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //LL rotation
	      System.out.println("Left-left rotation at root:");
	      //Preparing operations to lead to restructuring
	      myAVL.remove(1);
	      myAVL.remove(3);
	      myAVL.insert(1);
	      //Before:
	      System.out.println("Before left-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');

	      //Operation that causes restructuring
	      myAVL.insert(0);
	      
	      //After:
	      System.out.println("After left-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //RL rotation
	      System.out.println("Right-left rotation at root:");
	      //Preparing operations to lead to restructuring
	      myAVL.remove(0);
	      myAVL.remove(1);
	      myAVL.insert(0);
	      //Before:
	      System.out.println("Before right-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.insert(1);
	      
	      //After:
	      System.out.println("After right-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //RR rotation
	      System.out.println("Right-right rotation at root:");
	      //Preparing operations to lead to restructuring
	      myAVL.remove(0);
	      //Before:
	      System.out.println("Before right-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.insert(3);
	      
	      //After:
	      System.out.println("After right-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');

	      
	      
	      
	      //To test deeper in the tree
	      //RR rotation
	      System.out.println("Deep-in-tree right-right rotation:");
	      //Preparing operations to lead to restructuring
	      myAVL.remove( 2 );
	      myAVL.remove( 1 );
	      myAVL.remove( 3 );
	      myAVL.insert( 50);
	      myAVL.insert( 25);
	      myAVL.insert( 75);
	      myAVL.insert( 20 );
	      myAVL.insert( 30 );
	      myAVL.insert( 70);
	      myAVL.insert( 80 );
	      myAVL.insert( 76 );
	      myAVL.insert( 90 );
	      
	      //Before:
	      System.out.println("Before right-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.insert( 95 );
	      
	      //After:
	      System.out.println("After right-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //LR rotation
	      System.out.println("Deep-in-tree left-right rotation:");
	      //Preparing operations to lead to restructuring
	      myAVL.insert( 22 );
	      myAVL.insert( 79 );
	      
	      //Before:
	      System.out.println("Before left-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.insert( 78 );
	      
	      //After:
	      System.out.println("After left-right rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //LLRotation
	      System.out.println("Deep-in-tree left-left rotation:");
	      //Preparing operations to lead to restructuring
	      myAVL.insert(10);
	      
	      //Before:
	      System.out.println("Before left-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.insert(16);
	      
	      //After:
	      System.out.println("After left-left rotation:");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      
	      //RLRotation(followed by a LRRotation)
	      System.out.println("Deep-in-tree right-left rotation"
	      + "(followed by a consecutive left-right rotation):");
	      //Preparing operations to lead to restructuring
	      myAVL.remove(22);
	      myAVL.remove(25);
	      
	      //Before:
	      System.out.println("Before right-left rotation(followed by a consecutive left-right rotation):");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
	      
	      //Operation that causes restructuring
	      myAVL.remove(30);
	   
	      //After:
	      System.out.println("After right-left rotation(followed by a consecutive left-right rotation):");
	      System.out.println("Preorder:");
	      myTraversal.preOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Postorder:");
	      myTraversal.postOrderTraversal(myAVL);
	      System.out.println(' ');
	      System.out.println("Inorder:");
	      myTraversal.inOrderTraversal(myAVL);
	      System.out.println(' ');
   }
   
   private static boolean areTheSame(BTNode<Integer> n1, BTNode<Integer> n2) {
	 //compares if any two binary trees are the same
	   if(n1 == null && n2 == null) {
		   return true;
	   }
	   if(n1 != null && n2 != null) {
           if(n1.element == n2.element) {
               //check if the elements stored in the two nodes are the same
               if(areTheSame(n1.left, n2.left)) {
            	 //check if left subtrees are the same
                   if(areTheSame(n1.right, n2.right)) {
                	 //check if right subtrees are the same
                       return true;
                   }
               }
           }
       }
       //when one node is null and the other is not, return false	   
	   return false;   
   }
   
   
   private static void complexTest() throws IOException {
	      //For a rather complex test
	      System.out.println("Complex test:");
	      //Please change the code manually to switch between AVL and ImprovedAVL
          //AVL<Integer> myAVL = new AVL<Integer>();
	      ImprovedAVL<Integer> myAVL = new ImprovedAVL<Integer>();
	      BinarySearchTree<Integer> correctOutput = new BinarySearchTree<Integer>();
	      correctOutput.insert(75);
	      correctOutput.insert(50);
	      correctOutput.insert(80);
	      correctOutput.insert(16);
	      correctOutput.insert(70);
	      correctOutput.insert(78);
	      correctOutput.insert(90);
	      correctOutput.insert(10);
	      correctOutput.insert(20);
	      correctOutput.insert(76);
	      correctOutput.insert(79);
	      correctOutput.insert(90);
	      correctOutput.insert(95);
	      //The correct output was deduced manually and hardcoded.
	      
	      
	      File f = new File("test.txt");
	      //In the text file "test.txt", I designed a testing process(sequence of insertion
	      //and removal operations) that can test all four possible cases in trinode restructuring(i.e. LL,
	      //RR,LR,RL rotations) at both the root and deep in the tree. If the text file is lost, the
	      //testing process can be retrieved from the method "simpleTest()" above, the tests ran in the method
	      //is the same. However, anyone who wants to test the AVL implementation can also write a different 
	      //text file with a different testing process designed by himself or herself, but remember to either 
	      //change the code or use the same file name(i.e."test.txt").
		  BufferedReader br = new BufferedReader(new FileReader(f));
	      Scanner scanner = new Scanner(new FileInputStream("test.txt"));
	      String operation = "";
	      int element;
	      while(br.readLine()!=null){
	    	  operation = scanner.next() ;
	    	  element = Integer.parseInt(scanner.next());
	    	  if(operation.equals("insert")) {
	    		  myAVL.insert(element);
	    	  }else if(operation.equals("remove")) {
	    		  myAVL.remove(element);
	    	  }
	      }
	      br.close();
	      scanner.close();
	      System.out.println("Structure and content of AVL tree:");
	      System.out.println(myAVL);
	      System.out.println("Structure and content of the correct output presented by a binary search tree:");
	      System.out.println(correctOutput);
	      System.out.println("Whether if the conetnts and structures of the two trees are the same:" + " " 
	      + areTheSame(myAVL.root, correctOutput.root));
	      System.out.println("Size of AVL tree:"+myAVL.size);
	      System.out.println("Size of BST:"+correctOutput.size);
	      
	      //If the test file written by me is used, the output results of the AVL and the BST should meet.
	      //If a different test file is used, 
	      //the output results of the AVL and the BST won't meet with each other
	      //unless the harcoded insertions into the BST are changed manually.
	     
   }
   
   public static void main(String[] args ) throws IOException {
	   //I wrote two test methods for testing of the codes I wrote, both have the same testing process
	   //I designed, but the results are presented in different manners.
	    simpleTest();
		complexTest();      
      
   }
}
