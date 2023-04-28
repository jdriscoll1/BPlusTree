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
   
   // This method takes the properties of a node and squishes it into this particular node
   public BTNodeInternal merge(BTNodeInternal node){
      // insert key()
      // insertChildren()
 

      return this; 
   }
   
   public void merge(BTNode node){
   
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
         if(k.compareTo(K) <= 0){
         
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
      BTNodeInternal CA = new BTNodeInternal(P, tree.assignNodeID());
      BTNodeInternal CB = new BTNodeInternal(P, tree.assignNodeID());
       
      

      
      
      // Select the center index to push upwards 
      int i = (int)Math.ceil(super.N / 2); 
      for(int j = 0; j < Math.ceil(super.N / 2); j++){
         CA.addChildNode(this.children.get(j)); 
      }
      for(int j = (int)Math.ceil((double)super.N / 2.0); j < super.N; j++){
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
   
   /*
   
   public void insert(String key, BPlusTree tree)
   {  
      // When inserting into an internal node, it is necessary to loop to find which child the key belongs in 
      
      // Gotta find the relevant child node 
      // Start off at the first index      
      for(int i = 0; i < super.keys.size() - 1; i++){
         // if it is less than the next one and greater than the current one, or the first one, insert
         if(key.compareTo(super.keys.get(i)) < 0){
            System.out.println(key + " < " + super.keys.get(i));
            this.children.get(i).insert(key, tree); 
            return; 
         }
      }
      this.children.get(this.children.size() - 1).insert(key, tree); 
   
   }
   */
   
   public void printLeavesInSequence()
   {
      this.children.get(0).printLeavesInSequence(); 
      
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
      return true;
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