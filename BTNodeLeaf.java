import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

class BTNodeLeaf extends BTNode
{
   public ArrayList<Integer> keyCounts;
   BTNodeLeaf nextLeaf;
   BTNodeLeaf prevLeaf; 
   // Current number of elements in node
   int n; 
   
   public BTNodeLeaf(BTNodeInternal parent, int id)
   {
      // Define the parent node
      this.parent = parent; 
      this.n = 0; 
      super.keys = new ArrayList<String>();
      super.nodeID = id; 
      this.nextLeaf = null; 
      this.prevLeaf = null; 
      this.keyCounts = new ArrayList<Integer>(); 
      
   }
   
   public void split(BPlusTree tree){
      if(super.keys.size() < super.N + 1){
         return;
      }

   
      // Assign the parent node
      BTNodeInternal P = (this.parent == null) ? new BTNodeInternal(null, tree.assignNodeID()) : this.parent; 
      
      // Assign the children node
      BTNodeLeaf CA = new BTNodeLeaf(P, tree.assignNodeID());
      BTNodeLeaf CB = new BTNodeLeaf(P, tree.assignNodeID()); 
      CA.setNextLeaf(CB);
      CB.setNextLeaf(this.getNextLeaf());
      CB.setPrevLeaf(CA);
      CA.setPrevLeaf(this.getPrevLeaf());
      if(this.getPrevLeaf() != null){
         this.getPrevLeaf().setNextLeaf(CA);
      }
      if(this.getNextLeaf() != null){
         this.getNextLeaf().setPrevLeaf(CB);
      }
      
      /* GENERATE THE CENTRAL NODES */
      // middle node 
      int m = (int)Math.ceil(super.keys.size() / 2); 
      
      // The word being added
      String w; 
      
      // Fill CA 
      for(int i = 0; i < m; i++){
         w = super.keys.get(i); 
         for(int j = 0; j < this.keyCounts.get(i); j++){
            CA.insert(w, tree); 
         }
      }
      
      // Fill CB
      for(int i = m; i <= super.N; i++){
         w = super.keys.get(i); 
         for(int j = 0; j < this.keyCounts.get(i); j++){
            CB.insert(w, tree); 
         }

      }
      w = super.keys.get(m); 
      // Insert w 
      P.insert_key(w);
            
      P.addChildNode(CA); 
      P.addChildNode(CB);

      // Remove this node from the parent's children
      P.children.remove(this); 
      P.split(tree); 

 
      
      // Next do the merge
      if(this.parent == null){
         tree.root = P; 
         return; 
      } 
      this.parent = P; 
      
       
   }
   
   public void insert(String word, BPlusTree tree)
   {
      int pos = Collections.binarySearch(super.keys, word);
      if (pos < 0) {
         this.keys.add(-pos-1, word);
         this.keyCounts.add(1); 
      }
      else{
         this.keyCounts.set(pos, this.keyCounts.get(pos) + 1); 
         return; 
         
      }
      // Check if L has enough space, insert word 
      if(n < N){

         this.n++; 
         return;
         
      }
      this.split(tree); 
          
      
   }
   
   public void printLeavesInSequence()
   {
      for(int i = 0; i < super.keys.size(); i++){
         System.out.println(super.keys.get(i) + ": " + this.keyCounts.get(i));
      }


   }
   
   public void setPrevLeaf(BTNodeLeaf prevLeaf){
      this.prevLeaf = prevLeaf; 
   }
   
   public BTNodeLeaf getPrevLeaf(){
      return this.prevLeaf; 
   }
   
   public void setNextLeaf(BTNodeLeaf nextLeaf){
      this.nextLeaf = nextLeaf; 
   }
   
   public BTNodeLeaf getNextLeaf(){
      return this.nextLeaf; 
   }
   
   public void printStructureWKeys(int depth){
 
     for(int i = 0; i < super.keys.size(); i++){
         for(int d = 0; d < depth; d++){
            System.out.print('\t');
         }
         System.out.println("[" + this.nodeID + "] " + super.keys.get(i) + ": " + this.keyCounts.get(i));
      }
   }
   
   public Boolean rangeSearch(String startWord, String endWord)
   {
      int i = 0; 
      boolean isFound = false; 
      for(String key : super.keys){
         if(startWord.compareTo(key) <= 0 && endWord.compareTo(key) >= 0){
            System.out.println("[" + this.nodeID + "] " + key + ": " + this.keyCounts.get(i));
            isFound = true; 
         }
         i++; 
      }
      return isFound;
   }
   
   
   public Boolean searchWord(String word)
   {
      // The word being searched for should be inside of this node
      int w_i = super.keys.indexOf(word);
      System.out.println((w_i == -1) ? word + " was not found" : word + " has " + this.keyCounts.get(w_i) + " occurences");
	   return (w_i != -1); 
   }
   
   
   // A toString Method
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