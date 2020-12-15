import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
 
public class Lab3 extends JFrame implements ActionListener {
       static final long serialVersionUID = 1;
       JLabel scramletLabel = new JLabel("Scramlet");
       JTextField scramletText = new JTextField(10);
       JButton unscrmbleButton = new JButton("Unscramble");
       JTextArea result = new JTextArea(20,40);
       JScrollPane scroller = new JScrollPane();
       RandomAccessFile indexFile = null;
       RandomAccessFile wordsFile = null;
       public Lab3() {
              setTitle("Scramlet Solver");
              setLayout(new java.awt.FlowLayout());
              setSize(500,430);
              setDefaultCloseOperation(DISPOSE_ON_CLOSE);
              add(scramletLabel);
              add(scramletText);
              add(unscrmbleButton); unscrmbleButton.addActionListener(this);
              scroller.getViewport().add(result);
              add(scroller);
       }

       public static void main(String[] args) {
              Lab3 display = new Lab3();
              display.setVisible(true);
       }

       void getFiles() {
              String indexName = getFileName("Index File");
              if (indexName == null) {
                      result.setText("No index file chosen");
                      return;
              }
              String wordsName = getFileName("Words File");
              if (wordsName == null) {
                      result.setText("No words file chosen");
                      return;
              }
              try {
                      indexFile = new RandomAccessFile(indexName, "r");
              } catch (FileNotFoundException e) {
                      result.setText("Index file not found");
                      return;
              }
              try {
                      wordsFile = new RandomAccessFile(wordsName, "r");
              } catch (FileNotFoundException e) {
                      result.setText("Words file not found");
                      return;
              }
       }

       String getFileName(String title) {
              JFileChooser fc = new JFileChooser();
              fc.setDialogTitle(title);
              int returnVal = fc.showOpenDialog(this);
              if (returnVal == JFileChooser.APPROVE_OPTION)
                      return fc.getSelectedFile().getPath();
              else
                      return null;
       }

       public void actionPerformed(ActionEvent evt) {
              result.setText("");   //clear TextArea
              if (indexFile == null || wordsFile == null)
                      getFiles();
              try {
                      unscramble();
              } catch (IOException e) {
                      result.setText("I/O eror occurred");
              }
       }
 
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * */
   	/*
  	 Program: Lab 3
  	 Name: Zach Macadam
  	 Class: Techniques in Programming
  	 Semester: Spring 2018
  	 Due Date: 4/10/2018
  	 */
       int searchNum;
       int foundNum;
       void unscramble () throws IOException {
    	   searchNum = 1;
    	   foundNum = 0;
    	   if (indexFile == null || wordsFile == null) //if either file is not selected, try again
    		   return;
    	   String input = scramletText.getText().trim().toLowerCase(); //the words file is lowercase and does not provide spaces
    	   if (input.length() == 0) {
    		   result.setText("Please input some letters!");
    	   	   return;
    	   }
    	   if (input.length() > 10) { //10 letters would search over 3.5 million times, that would take too long on low end machines
    		   result.setText("We don't have all day to wait! Try something less than 10 letters.");
    	   	   return;
    	   }
    	   for (int i = 0; i < input.length(); i++) { //this loop verifies that only letter have been input to the text field
    		   if(!(Character.isLetter(input.charAt(i)))) {
    			   result.setText("Scramlet can unscramble letters only.");
    	   		   return; }
    	   }
    	   String[] allPerms = permute(input); //this line creates an array (an array because we know the size will be the number of letters factorial) with all premutations of the letters
    	   System.out.println("Array created with " + factorial(input.length()) + " permutations." );
    	   int indexWords = (int)(indexFile.length()); //the length is a long, I can convert it to an int as long as the file size is less than 2,147,483,647 bytes, or 2.147 GB, so it will work in this case
    	   int totalWords = (indexWords / 4) - 1; //the index file contains 4 bytes per word location and has an offset of 4 bytes,   										   
    	   System.out.println("The Words File contains " + totalWords + " words.");	//by dividing by 4 and subtracting 1, I can find the total number of words
    	   for (int i = 0; i < allPerms.length; i++) //this loop has the program search all possible permutations of the string for a match in the words file
    		   if (binarySearch(allPerms[i], 0, totalWords - 1) >= 0) { //if the location of a word exists, an int greater than or equal to 0 is returned by the binarySearch method
    			   if (!(result.getText().contains(allPerms[i]))) { //this if is reached if the word exists in the word file, if it has not been printed, add it to the result text area
						result.append(allPerms[i] + "\n");
			  			foundNum++;
    			   }
    		   }
    	   	  if (result.getText().equals(""))  {//if the program reaches this if statement after running the binarySearch, there are no matches 
    	   		  result.setText("\n" + "No words found!");
    	   	  }
    	  System.out.println(foundNum + " word(s) found in " + (searchNum - 1) + " searches.");
       }
       //this method is used to find the length of the letters input factorial, to create an array of the right size
       public int factorial(int f) {
    		   int fact = 1;
    		   for (int i = 2; i<= f; i++)
    			   fact = fact*i;
    		   return fact;
    	   }
       	 public String[] permute(String str) {
       		 String[] perms = new String[factorial(str.length())]; //by creating the array to the size of the length factorial, we know it will hold all possible permutations
       		 int n = str.length();
       		 int a = 0;
       		 if (n == 0) return null; //the string is empty
       		 if (n == 1) perms[0] = str; //the string contains 1 character
       		 else {	 
       			 for (int i = 0; i < n; i++) {
       				 char prefix = str.charAt(i); //this keeps the first character so the rest can be permuted
       				 String[] strPerm = permute(str.substring(0, i) + str.substring(i+1)); //this creates an array of the permutations disregarding the character at i
       				 for (int j = 0; j < strPerm.length; j++) { //this loop allows me to add all the permutations to the array that is being returned
       					 perms[a] = (prefix + strPerm[j]); //prefix is the character that was not passed back into my permute method, the strPerm array contains all permutations of the remaining characters
       					 a++; //a must be incremented so that the array moves to the next location
       				 }
       			 }	
       		 }
       		 return perms; 
       	 }
    	 public int binarySearch(String word, int lower, int upper) throws IOException {
    		 int middle = (lower + upper) / 2; //this finds the middle word number in the indexfile
    		 int middleBytes = middle * 4; //because we are working with bytes and the indexfile contains ints, the middle number must be converted to the number of bytes
    		 String compWord = "";
    		 if (upper < lower) { //the upper limit cannot be less than 0, this means that the word does not exist in the file
    			 System.out.println("Search " + searchNum + " could not find a match.");
    			 searchNum++;
    			 return -1;
    		 }
    		 indexFile.seek(middleBytes); //this moves the position of the RandomAccessFile to the middle
    		 int startPos = indexFile.readInt(); //this int is the byte offset of where the word begins
    		 int endPos = indexFile.readInt(); //this int is the byte offset of where the next word begins
    		 int wordLength = (endPos - startPos) / 2; //by subtracting the byte offset of where the next word begins from the first word, we know how many bytes long the word is
    		 wordsFile.seek(startPos);				   //and because the words are stored as chars that are 2 bytes long, dividing the number by 2 gives the number of characters
    		 for (int i = 0; i < wordLength; i++) //this loop creates a string of the word we are comparing by reading a character n times (n = the length derived from the above lines)
    			 compWord = compWord + wordsFile.readChar(); 
    		 if (word.compareTo(compWord) > 0) //when the word is compared, if the result is greater than 0, then the word we are searching for (if it exists in the file) is in the upper half 
    			 return binarySearch(word, middle + 1, upper); //runs through the binarySearch method again, this time only searching the upper half of the wordsFile
    		 if (word.equals(compWord)) { //if the word we have created by reading characters equals the word we are searching for, we have found it! so the middle word number is returned
    			 System.out.println("Search " + searchNum + " found a match at word number: " + middle);
    			 searchNum++;
    			 return middle;
    		 }
    		 return binarySearch(word, lower, middle - 1); //if neither of the above if statements have been run, run through the binarySearch method again, this time only searching the lower half of the file
    	 }
}