import java.util.ArrayList;

abstract class BTNode
{
   protected int nodeID;
   public ArrayList<String> keys;
   protected BTNodeInternal parent;
   // The maximum number of elements allowed in that particular node 
   protected int N = 2; 

   
   public BTNodeInternal getParent() { return parent; }
   
   public abstract void insert(String key, BPlusTree tree);
      
   public abstract void printLeavesInSequence();
      
   public abstract void printStructureWKeys();
   
   public abstract Boolean rangeSearch(String startWord, String endWord);
   
   public abstract Boolean searchWord(String word);
   
   public abstract String getTail(); 
}