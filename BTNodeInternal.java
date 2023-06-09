/* CSCD 427 - Project 2 
Name: Jordan Driscoll
Date: 4/26/2023
Description: The internal node 
*/

import java.util.Collections;
import java.util.ArrayList;

class BTNodeInternal extends BTNode
{
   public ArrayList<BTNode> children;
   // Current number of elements permitted in a single node
   private int n; 
   
   
   public BTNodeInternal(BTNodeInternal parent, int id) 
   { 
      this.n = 0;
      super.keys = new ArrayList<String>(); 
      this.children = new ArrayList<BTNode>(); 
      super.parent = parent; 
      super.nodeID = id; 

   }
   
   public void insert_key(String key){
      int pos = Collections.binarySearch(super.keys, key);
      if (pos < 0) {
         this.keys.add(-pos-1, key);
      }

   }
     
   // It can insert a node into the tree
   public void insert(String key, BPlusTree tree){
      // Look for the location at which the child is less than the next index
      // If it is not larger than any of them, add it to the back       
      String k = key; 
      
      // The total number of keys in this node
      int n = super.keys.size(); 
      
      // The node which will be inserted into 
      BTNode c; 
      
      // Loop through each of the keys to see if this node should be placed into its prepended child node
      for(int i = 0; i < n; i++){
         
         // The value of the current key being compared to 
         String K = super.keys.get(i); 
         
         // Run comparison function 
         if(k.compareTo(K) < 0){
         
            // Insert node into child
            c = this.children.get(i); 
            c.insert(k, tree);
            return;  
         }
      } 
           
      c = this.children.get(this.children.size() - 1);
      
      
      c.insert(k, tree); 
 
   }
   
   // This node takes a node and splits its contents into two children 
   public void split(BPlusTree tree){
      
      
      if(this.keys.size() < this.N + 1){
         return; 
      }
      

      // Create new leaves in which to insert the elements
      BTNodeInternal P = (this.parent == null) ? new BTNodeInternal(null, tree.assignNodeID()) : this.parent;
      
      // If the parent is null, set this root as the parent
      if(P.parent == null){
         tree.root = P; 
      }
      this.parent = P; 

      BTNodeInternal CA = new BTNodeInternal(P, tree.assignNodeID());
      BTNodeInternal CB = new BTNodeInternal(P, tree.assignNodeID());
      
      
      // Select the center index to push upwards 
      int i = (int)Math.ceil(super.N / 2); 
   
      
      // Insert all of the children nodes into the left child node
      for(int j = 0; j < i; j++){
         CA.insert_key(super.keys.get(j)); 
      }
      for(int j = i+1; j <= super.N; j++){
         CB.insert_key(super.keys.get(j));
      }
      
      for(int j = 0; j <= i; j++){
         CA.addChildNode(this.children.get(j)); 
      }
      for(int j = i+1; j <= super.N + 1; j++){
         CB.addChildNode(this.children.get(j)); 
      }
      P.insert_key(super.keys.get(i));
      P.addChildNode(CA);
      P.addChildNode(CB);
      P.children.remove(this); 

      P.split(tree);  
     
   }
  
   
   
   public BTNodeInternal addChildNode(BTNode child){
      child.parent = this; 
      if(this.children.size() == 0){
         this.children.add(0, child); 
         return this; 
      }
      // Get the last index of the child node
      String key = child.getTail();
      
      // Starting at the 0th child 
      for(int i = 0; i < this.children.size() - 1; i++){
         // if it is less than the next one and greater than the current one, or the first one, insert
         if(key.compareTo(super.keys.get(i)) <= 0){
            this.children.add(i, child); 
            return this; 
         }
      } 
      this.children.add(child);
      return this; 
   } 
   
   
  
   public void insert_recurse(String key, BPlusTree tree){
      // Obtain the current node 
      // First, find the correct 
      int pos = Collections.binarySearch(super.keys, key);
      if (pos < 0) {
         this.keys.add(-pos-1, key);
      }
      // Check if L has enough space, insert word 
      if(this.n < super.N){
         this.n++; 
         return; 
      }
      
      // Create new leaves in which to insert the elements
      BTNodeInternal P = (this.parent == null) ? new BTNodeInternal(null, tree.assignNodeID()) : this.parent;
      if(P.parent == null){
         tree.root = P; 
      }
      
      // Create a child node CA
      BTNodeInternal CA = new BTNodeInternal(P, tree.assignNodeID());
      // Create a child node CB
      BTNodeInternal CB = new BTNodeInternal(P, tree.assignNodeID());
       
      

      
      
      // Select the center index to push upwards 
      int i = (int)Math.ceil(super.N / 2); 
      for(int j = 0; j < i; j++){
         CA.addChildNode(this.children.get(j)); 
      }
      for(int j = i; j < super.N; j++){
         CB.addChildNode(this.children.get(j)); 
      }
      

      
      // Insert all of the children nodes into the left child node
      for(int j = 0; j <= i; j++){
         CA.insert(super.keys.get(j), tree); 
      }
      P.insert_recurse(this.keys.get(i), tree); 
      for(int j = i; j < N; j++){
         CB.insert(super.keys.get(j), tree);
      }
     
   
   }
   
   
   public void printLeavesInSequence()
   {
      for(BTNode child : this.children){
         child.printLeavesInSequence(); 
      }
      
   }
   
   public void printStructureWKeys(int depth)
   {
      int i = 0; 
      for(String key : super.keys){
         for(int d = 0; d < depth; d++){
            System.out.print("\t");
         }
         System.out.println("[" + this.nodeID + "] "+ key);
        
         this.children.get(i).printStructureWKeys(depth + 1); 
         i++; 
      }
      this.children.get(this.children.size() - 1).printStructureWKeys(depth + 1); 
      
   }
   
   public Boolean rangeSearch(String startWord, String endWord)
   {     
      // When we explore the next child, there will be 4 options at maximum 
      // For the first option, the key has to be greater than the start word
      // For the second option, the key has to be l than the end word
      boolean isFound = false; 
      for(BTNode child : this.children){
         if(child.rangeSearch(startWord, endWord)){
            isFound = true; 
         }
      }
      return isFound;  
   }
   
   public Boolean searchWord(String word)
   {
      // if it's smaller than all the words
      
      if(word.compareTo(super.keys.get(0)) < 0){
         return this.children.get(0).searchWord(word); 
      }
      for(int i = 0; i < super.keys.size() - 1; i++){
          if(word.compareTo(super.keys.get(i)) >= 0 && word.compareTo(super.keys.get(i + 1)) < 0){
            return this.children.get(i+1).searchWord(word);
          }
      }
      return this.children.get(super.keys.size()).searchWord(word);
   }
   
   @Override
   public String toString(){
      String str = ""; 
      str += "Internal Node " + this.nodeID + ":"; 
      for(String key : super.keys){
         str += " " + key; 
      }
      str += "\n";
      for(BTNode child : this.children){
         str += child.toString(); 
      }
      return str; 
      
   }
     
   public String getTail(){
      return super.keys.get(super.keys.size() - 1); 
   }

}