/* CSCD 427 - Project 2 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.util.*;


// TO DO Before Submission 
// Put name at top 
// Uncomment out the args
// Uncomment out stopwords parser
// Uncomment out website planner

class WordFrequency
{
   public static boolean Assert_InsertBTNodeInternalWorks(){
      
      /*
      BPlusTree t1 = new BPlusTree(null); 
      // if tree looks like this: P: C,E; CA: B,D; CB: D; CC: E 
      t1.insertWord("B");
      t1.insertWord("C"); 
      t1.insertWord("D");
      t1.insertWord("E"); 
      t1.insertWord("F");
      t1.insertWord("G");
      
      System.out.println(t1);*/

      BPlusTree t2 = new BPlusTree(null); 
      // if tree looks like this: P: C,E; CA: B,D; CB: D; CC: E 
      System.out.println("Inserting B");
      t2.insertWord("B");
      System.out.println("The tree after printing B: \n" + t2);
      
      System.out.println("Inserting E");
      t2.insertWord("E"); 
      System.out.println("The tree after printing E: \n" + t2);

      System.out.println("Inserting F");
      t2.insertWord("F");
      System.out.println("The tree after printing F: \n" + t2);

      System.out.println("Inserting G");
      t2.insertWord("G");
      System.out.println("The tree after printing G: \n" + t2);

      
      System.out.println("Inserting H");
      t2.insertWord("H");
      System.out.println("The tree after printing H: \n" + t2);

      
      System.out.println("Inserting J");
      t2.insertWord("J");
      System.out.println("The tree after printing J: \n" + t2);

      
      System.out.println("Inserting K");
      t2.insertWord("K");
      System.out.println("The tree after printing K: \n" + t2);

      
      System.out.println("Inserting I");
      t2.insertWord("I");
      //System.out.println("Inserting: " + "A");
      //System.out.println("T2 is currently equal to: " + t2);
      //t2.insertWord("A");
      System.out.println("Final Result: \n " + t2);


      

      return false; 
   }

   public static boolean Assert_InsertAIntoFullLeaf(){
      // Arrange
      BPlusTree tree = new BPlusTree(null); 
      
      // Act
      tree.insertWord("A");
      return false; 
   
   }

   public static boolean Assert_AddA_F(){
      // Arrange
      BPlusTree tree = new BPlusTree(null);
      
      // Act 
      tree.insertWord("A");
      tree.insertWord("B");
      tree.insertWord("C"); 
      tree.insertWord("D");
      tree.insertWord("E"); 
      tree.insertWord("F");
      
      //Assert
      boolean P = ((BTNodeInternal)tree.root).keys.get(0).equals("b") && ((BTNodeInternal)tree.root).keys.get(1).equals("d");
      boolean C1 = ((BTNodeInternal)tree.root).children.get(0).keys.get(0).equals("a") && ((BTNodeInternal)tree.root).children.get(0).keys.get(1).equals("b"); 
      boolean C2 = ((BTNodeInternal)tree.root).children.get(1).keys.get(0).equals("c") && ((BTNodeInternal)tree.root).children.get(1).keys.get(1).equals("d"); 
      boolean C3 = ((BTNodeInternal)tree.root).children.get(2).keys.get(0).equals("e") && ((BTNodeInternal)tree.root).children.get(2).keys.get(1).equals("f");
      boolean pass = P && C1 && C2 && C3; 
      return pass;  
   }
   public static void main(String[] args)
   {
      Assert_InsertBTNodeInternalWorks(); 
   
     /*
      BPlusTree tree = new BPlusTree(null); 
      tree.insertWord("B");
      tree.insertWord("C");
      tree.insertWord("D"); 
      tree.insertWord("E");
      tree.insertWord("F");     
      System.out.println(tree);
      */
      //System.out.println("\n\nInserting A: \n");
      //tree.insertWord("A");

 

      
      /*
      // The hash set of words to ignore
      HashSet<String> ignoreSet = new HashSet<String> (); 
      Document document = null;
      String doc_text = "";
      String[] tokenized_text = null;
      BPlusTree tree;
      Scanner fin;
      Scanner kb;
      
      //Check for valid arguments
      if(args.length != 2)
      {
         System.out.println("ERROR: Incorrect format for command line arguments.");
         System.out.println("java WordFrequency <URL> <ignore_file_name.txt>");
         System.out.println("Exiting.");
         System.exit(-1);
      }
      
      //Program will take time to set up tree; inform user
      System.out.println("WordFrequency is processing HTML. Please wait...");
      
      //Read stopwords ignoreSet
      
      try
      {
         String filename = "stopwords.txt";
         fin = new Scanner(new File(filename));
         while(fin.hasNextLine())
         {
            ignoreSet.add(fin.nextLine());
         }
         fin.close();
      }
      catch(Exception e)
      {
         System.out.println("File IO Exception; initializing BPlusTree without stopwords.");
      }
      
       
      //Initialize BPlusTree
      tree = new BPlusTree(ignoreSet);
        
      // Get HTML.
       / Convert HTML to string.
       // Tokenize string. 
      
       
      try
      {
         String website = "https://en.wikipedia.org/wiki/Hubble_Space_Telescope"; 
         document = Jsoup.connect(website).get();
         doc_text = document.text();
         tokenized_text = doc_text.split("(\\W+)?\\s(\\W+)?");
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
         System.exit(-1);
      }
      
      for(String s: tokenized_text)
      {
         tree.insertWord(s);
      }
      
      document = null;
      doc_text = null;
      tokenized_text = null;
      
      //Initialize keyboard scanner
      kb = new Scanner(System.in);
      
      mainMenu(tree);
      */
   }
   
   private static void mainMenu(BPlusTree tree)
   {
      Scanner kb = new Scanner(System.in);
      String userInput;
      int menuSelect = 0;
      Boolean validSelect = true;
      Boolean quit = false;
      
      do
      {
         System.out.println();
         
         //Show menu
         System.out.println("MAIN MENU");
         System.out.println("1) Print all words in order.");
         System.out.println("2) Display tree with Node IDs and keys.");
         System.out.println("3) Insert a word.");
         System.out.println("4) Search a word.");
         System.out.println("5) Search by range.");
         System.out.println("6) Quit.");
         
         do
         {
            System.out.print("Select an option: ");
            
            validSelect = true;
            
            //Get user selection
            userInput = kb.nextLine();
            
            //Validate selection input
            try
            {
               menuSelect = Integer.parseInt(userInput);
               
               if(menuSelect < 1 || menuSelect > 6)
               {
                  System.out.println("Invalid selection. Enter an integer from 1 to 6.");
                  validSelect = false;
               }
            }
            catch(NumberFormatException nfe)
            {
               System.out.println("Invalid selection. Enter an integer from 1 to 6.");
               validSelect = false;
            }
         }while(!validSelect);
         
         System.out.println();
         
         //Execute menu selection
         switch(menuSelect)
         {
            case 1:
               tree.printLeavesInSequence();
               break;
               
            case 2:
               tree.printStructureWKeys();
               break;
               
            case 3:
               tree.userInsertWord(kb);
               break;
            
            case 4:
               tree.searchWord(kb);
               break;
            
            case 5:
               tree.rangeSearch(kb);
               break;
               
            default:
               quit = true;
               break;
         }
         
      }while(!quit);
      
      
      System.out.println("Quitting.");
   }
}