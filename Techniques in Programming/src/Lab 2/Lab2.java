import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Lab2 extends JFrame implements ActionListener {
	JButton open = new JButton("Next Program");
	JTextArea result = new JTextArea(20,40);
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();
	
	public Lab2() {
		setLayout(new java.awt.FlowLayout());
		setSize(500,430);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(open); open.addActionListener(this);
		scroller.getViewport().add(result);
		add(scroller);
		add(errors);
	}
	
	public void actionPerformed(ActionEvent evt) {
		result.setText("");	//clear TextArea for next program
		errors.setText("");
		processProgram();
	}
	
	public static void main(String[] args) {
		Lab2 display = new Lab2();
		display.setVisible(true);
	}
	
	String getFileName() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile().getPath();
		else
			return null;
	}

/************************************************************************/
/* Put your implementation of the processProgram method here.           */
/* Use the getFileName method to allow the user to select a program.    */
/* Then simulate the execution of that program.                         */
/* You may add any other methods that you think are appropriate.        */
/* However, you should not change anything in the code that I have      */
/* written.                                                             */
/************************************************************************/
	/*
	 Program: Lab 2
	 Name: Zach Macadam
	 Class: Techniques in Programming
	 Semester: Spring 2018
	 Due Date: 3/8/2018
	 */
	int goLine;
	int lineNum;
	int newVar;
	double val1;
	double val2;
	String currentLine;
	StringTokenizer tok;
	ArrayList<String> lineCount = new ArrayList<String>(); //ArrayList for the lines of the imported program
	ArrayList<String> varName = new ArrayList<String>();//ArrayList for all the variables encountered
	ArrayList<Double> varValue = new ArrayList<Double>();//ArrayList for all the variable values encountered
	
	private void processProgram() {
		BufferedReader file;
		String fn = getFileName();
		System.out.println(fn); //this is the path to the file
		if (fn != null) { // if it DOES exist, we try to pass it in
			result.setText("");
			try {
				file = new BufferedReader(new FileReader(fn));
				String line;
				//if I do not clear the ArrayLists after each file, the program does not work more than once per run
				lineCount.clear(); 
				varName.clear();
				varValue.clear();
				lineCount.add(""); //this line is VERY important, this is my place holder so that the lines read start at 1 and not 0
					while ((line = file.readLine()) != null) {
						line.trim();
						lineCount.add(line);
						System.out.println(line);}
					System.out.println("Program contains " + (lineCount.size() - 1) + " lines");
					file.close();
				}
			 catch (IOException e) {
				 errors.setText("Invalid File");
				 return;
			}
		}
		else {
			errors.setText("No file selected");
			return;
		}
			if (!((String)lineCount.get(lineCount.size() - 1)).equals("END")) { //this makes sure that END is the last line, if it is not, the program cannot run
				errors.setText("END must be last line of program"); return; }
			goLine = 1; //this is the index of my LineCount ArrayList throughout the program
			lineNum = 0;// this is my lineCounter for more understandable error messages
			while (true) {
			if (!errors.getText().equals("")) { //this if statement checks to make sure that no errors have been detected thus far, if there has been one, the program stops
				result.setText("");
				System.out.println("Current line is not valid");
				return; }
			if (goLine >= lineCount.size() || goLine <= 0) {
				errors.setText("Invalid GOTO statement in line " + lineNum);
				result.setText("");
				System.out.println("Line " + goLine + " does not exist");
				return; 
				}
			System.out.println("\n" + "Current line number is " + goLine);
			currentLine = (String)lineCount.get(goLine); //this grabs whatever my index number is starting at 1 but is subject to change with GOTO statements
			System.out.println("Current line code is " + currentLine);
			lineNum = goLine;
			goLine += 1;
			if (currentLine.equals("END")) {
				System.out.println("ENDING PROGRAM");
				return; }
			else decodeLine(currentLine);
		
			}
		} 
		private void decodeLine(String code) {
				System.out.println("Current line is valid");
				tok = new StringTokenizer(code);//this StringTokenizer is for going through each part of the line to see what it is telling the program to do
				StringTokenizer tokCheck = new StringTokenizer(code); //this StringTokenizer is solely for checking to make sure END does not exist hidden inside of a string
					for (int i = 0; i<tokCheck.countTokens(); i++) {
					String check = tokCheck.nextToken();
					if (check.equals("END")) {
						errors.setText("Invalid use of END in line " + lineNum);
						return; }
					}
			if (code.startsWith("GOTO")) {
				System.out.println("Current line is a GOTO statement");
				tok.nextToken(); //this passes over the GOTO token because I do not need to do anything with it
				String goVar = tok.nextToken();
				try {
					goLine = Integer.parseInt(goVar); //if the token following GOTO is a number, my counter becomes that number
					System.out.println("Program will attempt to move to line " + goLine);
					return; //the program will go back to my loop with goLine set to the new value
				}
				catch (Exception e) {
					errors.setText("Invalid GOTO statement in line " + lineNum);
					return;
				}
			}
			if (code.startsWith("PRINT")) {
				System.out.println("Current line is a PRINT statement");
				if (tok.countTokens() > 2) { //PRINT statements cannot be more than 2 tokens, this is a preemptive check to make sure it is valid
					errors.setText("Invalid PRINT statement in line " + lineNum);
					return; }
				tok.nextToken(); //this passes over the PRINT token because I no longer need it
				String var = tok.nextToken();
				if (varName.contains(var)) { //this checks to see that the variable has been assigned a value
					val1 = (double) varValue.get(varName.indexOf(var));
					result.append(String.format("%.2f", Double.valueOf(val1))+ "\n"); //this format gives the variable 2 decimal places (extra credit? :-) )
					System.out.println("Printing " + val1 +  " for variable " + var);
				}
				else  { //if the program reaches this else statement, it means that the variable does not have a value and cannot be printed
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return;
					}
				return;
				}
			if (code.startsWith("IF")) {
				System.out.println("Current line is an IF statement");
				tok.nextToken(); //this passes over the IF token, it is not needed anymore
				String var = tok.nextToken();
				if (varName.contains(var)) //this is just like my PRINT check to see that the variable does have a value
					val1 = (double) varValue.get(varName.indexOf(var));
				else {
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return;
				}
				String is = tok.nextToken();
				if (!(is.equals("IS"))) {
					errors.setText("Invalid statement, IS must follow variable in line " + lineNum);
					return; }
				else { //if the 3rd token is "IS" then we can determine the value of the 4th token
					String val = tok.nextToken();
					if (varName.contains(val)) { //the value we are comparing CAN be a variable, so if it exists in the ArrayList we can get the value of it
						val2 = (double) varValue.get(varName.indexOf(val));
					}
					else { //the value we are comparing could be a number so if it did not pass the variable check, we try to parse it 
						try {
							val2 = Double.parseDouble(val);					
						}
						catch (Exception e) { //if it did not parse, it is not valid
							errors.setText("Invalid variable in line " + lineNum);
							return;
						}
				}
				String then = tok.nextToken();
				if (!(then.equals("THEN"))) {
					errors.setText("Invalid statement, THEN is not present in line " + lineNum);
					return; }
				if (val1 == val2) { //if the two values we are comparing ARE equal to each other and all checks have been passed, the next tokens are the SimpleStatement to be executed
					String statement = tok.nextToken();
					while (tok.hasMoreTokens()) //this while loop gets all the tokens left to create the statement to be executed
						statement = statement + " " + tok.nextToken(); 
					System.out.println("Statement to be tested equals " + statement);
				decodeLine(statement); //now that we have our SimpleStatement, we pass it back to my decodeLine method to have it executed
					}
				else System.out.println("Values do not equal");
				}
				return;
			}
			defineVariable(code); //if the current line of code did not match any of the if statements, it is either a variable declaration or invalid code
		}
			
		private void defineVariable(String code) {
			System.out.println("Current line is being tested as a Variable Declaration");
			newVar = 0;
			val1 = 0;
			StringTokenizer tok = new StringTokenizer(code); //I have passed in the entire line of code and need to tokenize it
			if (tok.countTokens() < 3) { //if there is less than 3 tokens, it cannot be a valid variable declaration
					errors.setText("Invalid variable assignment in line " + lineNum);
					return; }
			
			String var = tok.nextToken();
			if (!Character.isLetter(var.charAt(0))) { //this if statement checks to make sure that my variable starts with a letter
				errors.setText("Invalid variable name in line " + lineNum);
				return;
			}
			for (int i = 1; i < var.length(); i++) { //this loop checks to make sure that all the characters following are either letters or numbers
				if (Character.isLetterOrDigit(var.charAt(i))==false) {
					errors.setText("Invald variable, letters and numbers only in line " + lineNum);
					return; }
				}
			
			if (!(varName.contains(var))) { //if my variable cannot be found, it has not been declared yet
					varName.add(var); //so I have to add it to my ArrayList
					newVar = 1; } //this counter is used for later
			
				String equal = tok.nextToken();
			if (!(equal.equals("="))) {
				errors.setText("Invalid operator in line " + lineNum);
				return; }
						
			String num1 = tok.nextToken();
			val1 = expressionCheck(num1); // this passes the 3rd token into my method that checks to see if it is a valid variable or a valid number
			System.out.println(var + " currently equals " + val1);
			
			int tokensLeft = tok.countTokens();
			for (int i2 = 1; i2 < tokensLeft; i2+=2) { //if I have 2 or more tokens left there are still operations to complete
				if (i2 == tok.countTokens()) { //the amount of tokens left cannot be odd, it must be 2 or more for it to be a valid statement
					errors.setText("Invalid number of tokens in line " +lineNum);
					return; }
				String operator = tok.nextToken();
				String num2 = tok.nextToken();
				val2 = expressionCheck(num2); //this checks to make sure that the 5th token in the line is a valid variable or number
				if (operator.equals("+"))
					val1 += val2; //for each operation, I am only applying it to val1 because there is no hierarchy in this arithmetic
				else if (operator.equals("-"))
					val1 -= val2;
				else if (operator.equals("/"))
					val1 /= val2;
				else if (operator.equals("*"))
					val1 *= val2;
				else { //it must be one of the 4 defined operators or else it is invalid 
					errors.setText("Invalid operator " + operator + " in line " + lineNum);
					return; }
				System.out.println(var + " now equals " + val1);
			}
			if (newVar != 1) //if my counter is still 0, this means that the variable being defined was NOT new so we set its value to something else
				varValue.set(varName.indexOf(var), val1);
			else //this means that my counter is 1 and the variable was new so we have to add the value and not set it because that would give us an IndexOutofBounds
				varValue.add(val1);
		}
			
		private double expressionCheck(String num) {
			double val = 0;
				if (varName.contains(num)) { //if the variable already exists, get the value of it and return it
					return (double) varValue.get(varName.indexOf(num));
				}
				if ((Character.isLetter(num.charAt(0))) && !(varName.contains(num))) { //if the variable is valid but does not exist in the ArrayList it is an error
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return val; }
				try //if the value is NOT a variable it could be a number
				{
					val = Double.parseDouble(num);
				}
				catch (Exception e) { //it is not a valid variable or number, it is an error
					errors.setText("Invalid expression in line " + lineNum);
				}
				return val;
		} 
} 

		

	
		

	