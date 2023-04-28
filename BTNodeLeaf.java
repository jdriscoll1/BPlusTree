import java.util.Collections;
import java.util.ArrayList;

class BTNodeLeaf extends BTNode
{
   ArrayList<Integer> keyCounts;
   BTNodeLeaf nextLeaf;
   // Current number of elements in node
   int n; 
   
   public BTNodeLeaf(BTNodeInternal parent, int id)
   {
      // Define the parent node
      this.parent = parent; 
      this.n = 0; 
      super.keys = new ArrayList<String>();
      super.nodeID = id; 
      
   }
   
   public void split(BPlusTree tree){
      if(super.keys.size() < super.N + 1){
         return; 
      }
      System.out.println("Splitting Leaf Node: " + this);

   
      // Assign the parent node
      BTNodeInternal P = (this.parent == null) ? new BTNodeInternal(null, tree.assignNodeID()) : this.parent; 
      
      // Assign the children node
      BTNodeLeaf CA = new BTNodeLeaf(P, tree.assignNodeID());
      BTNodeLeaf CB = new BTNodeLeaf(P, tree.assignNodeID()); 
      
      /* GENERATE THE CENTRAL NODES */
      // middle node 
      int m = (int)Math.ceil(super.keys.size() / 2); 
      
      // The word being added
      String w; 
      
      // Fill CA 
      for(int i = 0; i <= m; i++){
         w = super.keys.get(i); 
         CA.insert(w, tree); 
      }
      
      // Fill CB
      for(int i = m+1; i <= super.N; i++){
         w = super.keys.get(i); 
         CB.insert(w, tree); 
      }
      w = super.keys.get(m); 
      // Insert w 
      System.out.println("The Parent Being Inserted Into Is: " + P);
      P.insert_key(w);
      
      System.out.print(P);
      
      System.out.println("Adding CA: " + CA); 
      System.out.println("Adding CB: " + CB);
            
      P.addChildNode(CA); 
      P.addChildNode(CB);

      System.out.print(P); 
      // Remove this node from the parent's children
      P.children.remove(this); 
      P.split(tree); 

 
      
      // Next do the merge
      if(this.parent == null){
         tree.root = P; 
         return; 
      } 
      this.parent = P; 
      this.parent.merge(P); 
      
       
   }
   
   public void insert(String word, BPlusTree tree)
   {
      System.out.println("Inserting " + word + " into leaf: " + this);
      int pos = Collections.binarySearch(super.keys, word);
      if (pos < 0) {
         this.keys.add(-pos-1, word);
      }
      // Check if L has enough space, insert word 
      if(n < N){

         this.n++; 
         return;
         
      }
      this.split(tree); 
      /*
      
      // Split the tree into a parent and two children node
      BTNodeInternal P = (this.parent == null) ? new BTNodeInternal(null, tree.assignNodeID()) : this.parent; 
      if(this.parent == null){ tree.root = P;}
      BTNodeLeaf CA = new BTNodeLeaf(P, tree.assignNodeID());
      BTNodeLeaf CB = new BTNodeLeaf(P, tree.assignNodeID()); 
      
      
      // Select the center index to push upwards 
      int i = (int)Math.ceil((double)super.N / 2); 
      
      // Insert the children nodes into their respective nodes
      for(int j = 0; j <= i; j++){
         CA.insert(super.keys.get(j), tree); 
      }
      for(int j = i+1; j <= N; j++){
         CB.insert(super.keys.get(j), tree);
      }
      
      // Add the two children node to the parent
      P.addChildNode(CA).addChildNode(CB);

      // Remove this node from the parent's children
      P.children.remove(this); 
      
      System.out.println(P);

      // Necessary to add relevant node to the top layer
      P.insert_recurse(this.keys.get(i), tree); 
      */
      //

      
      
   }
   
   public void printLeavesInSequence()
   {
      for(String key : super.keys){
         System.out.printf("%s ", key);
      } 
      if(this.nextLeaf != null){
         this.nextLeaf.printLeavesInSequence(); 
      }
   }
   
   public void printStructureWKeys()
   {
      
   }
   
   public Boolean rangeSearch(String startWord, String endWord)
   {
      return true;
   }
   
   public Boolean searchWord(String word)
   {
	return false; 
   }
   
   @Override
   public String toString(){
      String str = ""; 
      str += "Leaf Node ID: " + this.nodeID; 
      for(String key : super.keys){
         str += " " + key; 
      }
      str += "\n";
      return str; 
      
   }
   
   public String getTail(){
      return super.keys.get(super.keys.size() - 1); 
   }
}