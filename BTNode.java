/* CSCD 427 - Project 2 
Name: Jordan Driscoll
Date: 4/26/2023
Description: Abstract node class
*/

import java.util.ArrayList;

abstract class BTNode
{
   protected int nodeID;
   public ArrayList<String> keys;
   protected BTNodeInternal parent;
   // The maximum number of elements allowed in that particular node 
   protected int N = 3; 

   
   public BTNodeInternal getParent() { return parent; }
   
   public abstract void insert(String key, BPlusTree tree);
      
   public abstract void printLeavesInSequence();
      
   public abstract void printStructureWKeys(int depth);
   
   public abstract Boolean rangeSearch(String startWord, String endWord);
   
   public abstract Boolean searchWord(String word);
   
   public abstract String getTail(); 
}