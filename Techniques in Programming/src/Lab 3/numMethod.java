import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class numMethod extends JFrame implements ActionListener {
	JButton open = new JButton("Next Program");
	JTextArea result = new JTextArea(20,40);
	JLabel errors = new JLabel();
	JScrollPane scroller = new JScrollPane();
	
	public numMethod	() {
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
	//	processProgram();
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
	int goLine;
	int lineNum;
	int newVar;
	double val1;
	double val2;
	String currentLine;
	StringTokenizer tok;
	ArrayList<String> lineCount = new ArrayList<String>();
	ArrayList<String> varName = new ArrayList<String>();
	ArrayList<Double> varValue = new ArrayList<Double>();
	
	public void processProgram() {
		System.out.println("MEGA PENIS");	
		BufferedReader file;
		String fn = getFileName();
		System.out.println(fn);
		if (fn != null) {
			result.setText("");
			try {
				file = new BufferedReader(new FileReader(fn));
				String line;
				lineCount.clear();
				varName.clear();
				varValue.clear();
				lineCount.add("");
					while ((line = file.readLine()) != null) {
						line.trim();
						lineCount.add(line);
						System.out.println(line);}
					System.out.println(lineCount.size());
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
			if (!((String)lineCount.get(lineCount.size() - 1)).equals("END")) {
				errors.setText("END must be last line of program"); return; }
			goLine = 1;
			lineNum = 0;
			while (goLine < lineCount.size()) {
				System.out.println(goLine);
			if (goLine >= lineCount.size() || goLine <= 0) {
				errors.setText("Invalid GOTO statement in line " + lineNum); return; }
			currentLine = (String)lineCount.get(goLine);
			System.out.println(currentLine);
			lineNum = goLine;
			goLine += 1;
			
			if (currentLine.contains("E")) 
				 errors.setText("Invalid use of END, must be last line only in " + lineNum);
			if (!errors.getText().equals("")) {
					result.setText("");
					return; }
			}
		} 
		private void decodeLine(String code) {
				String varLine = code;
				System.out.println(code);
				tok = new StringTokenizer(code);
				System.out.println(tok.countTokens());
					for (int i=0; i==tok.countTokens(); i++) {
					String check = tok.nextToken();
					System.out.println(check);
					if (check.equals("END")) {
						errors.setText("Invalid use of END in line " + lineNum);
						return; }
					}
			if (code.startsWith("GOTO")) {
				tok.nextToken();
				String goVar = tok.nextToken();
				System.out.println(goVar);
				try {
					goLine = Integer.parseInt(goVar);
					System.out.println(goLine);
					return;
				}
				catch (Exception e) {
					errors.setText("Invalid GOTO statement in line " + lineNum);
					return;
				}
			}
			if (code.startsWith("PRINT")) {
				System.out.println(tok.countTokens());
				if (tok.countTokens() > 2) {
					errors.setText("Invalid print statement in line " + lineNum);
					return; }
				tok.nextToken();
				String var = tok.nextToken();
				if (!(var.charAt(0) >= 'a' && var.charAt(0) <= 'z')) {
					errors.setText("Invalid variable to print in line " + lineNum);
					return; }
				if (varName.contains(var)) {
					val1 = (double) varValue.get(varName.indexOf(var));
					result.append(String.format("%.2f", Double.valueOf(val1))+ "\n");
				}
				else  {
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return;
					}
				return;
				}
			if (code.startsWith("IF")) {
				tok.nextToken();
				String var = tok.nextToken();
				System.out.println(var);
				if (!(var.charAt(0) >= 'a' && var.charAt(0) <= 'z')) {
					errors.setText("Invalid variable for IF statement in line " + lineNum);
					return; }
				if (!(varName.contains(var))) {
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return; }
				else {
					val1 = (double) varValue.get(varName.indexOf(var));
				}
				String is = tok.nextToken();
				System.out.println(is);
				if (!(is.equals("IS"))) {
					System.out.println("ASDASDASDASDASD");
					errors.setText("Invalid statement, IS must follow variable in line " + lineNum);
					System.out.print(errors.getText());
					return; }
				else { 
					String val = tok.nextToken();
					if (varName.contains(val)) {
						val2 = (double) varValue.get(varName.indexOf(val));
					}
					else {
						try {
							val2 = Double.parseDouble(val);					
						}
						catch (Exception e) {
							errors.setText("Invalid variable in line " + lineNum);
							return;
						}
				}
				String then = tok.nextToken();
				if (!(then.equals("THEN"))) {
					errors.setText("Invalid statement, THEN is not present in line " + lineNum);
					return; }
				if (val1 == val2) {
					String statement = tok.nextToken();
					while (tok.hasMoreTokens())
						statement = statement + " " + tok.nextToken();
				decodeLine(statement);
					}
				}
				return;
			}
			System.out.println("LASDALSJDAKSDJASLD");
			defineVariable(varLine);
		}
			
		public void defineVariable(String code) {
			newVar = 0;
			val1 = 0;
			StringTokenizer tok = new StringTokenizer(code);
			System.out.println(tok.countTokens());
			if (tok.countTokens() < 3) {
					errors.setText("Invalid variable assignment in line " + lineNum);
					return; }
			
			String var = tok.nextToken();
	
			for (int i = 1; i < var.length(); i++) {
				System.out.println("ASLKDJASDLKJSDLKJSDLKASJD");
				System.out.println(i);
				if (Character.isLetterOrDigit(var.charAt(i))==false) {
					errors.setText("Invald variable, letters and numbers only in line " + lineNum);
					return; }
				}
			if (!(var.charAt(0) >= 'a' && var.charAt(0) <= 'z')) {
				errors.setText("Invalid variable name in line " + lineNum);
				return;
			}
			
			if (!(varName.contains(var))) {
					varName.add(var);
					newVar = 1; } 	
			
				String equal = tok.nextToken();
			if (!(equal.equals("="))) {
				errors.setText("Invalid operator in line " + lineNum);
				return; }
					
			
			
			String num1 = tok.nextToken();
			val1 = expressionCheck(num1);
				
			int tokensLeft = tok.countTokens();
			for (int i2 = 1; i2 < tokensLeft; i2+=2) {
				System.out.println(tok.countTokens() + "TOK");
				if (i2 == tok.countTokens()) {
					errors.setText("Invalid number of tokens in line " +lineNum);
					return; }
				String operator = tok.nextToken();
				String num2 = tok.nextToken();
				val2 = expressionCheck(num2);
				if (operator.equals("+"))
					val1 += val2;
				else if (operator.equals("-"))
					val1 -= val2;
				else if (operator.equals("/"))
					val1 /= val2;
				else if (operator.equals("*"))
					val1 *= val2;
				else { errors.setText("Invalid operator in line " + lineNum); return; }
			}
			if (newVar != 1)
				varValue.set(varName.indexOf(var), val1);
			else 
				varValue.add(val1);
		}
			
		private double expressionCheck(String num) {
			double val = 0;
				if (varName.contains(num)) {
					return (double) varValue.get(varName.indexOf(num));
				}
				if ((num.charAt(0) >= 'a' && num.charAt(0) <= 'z') && !(varName.contains(num))) {
					errors.setText("Invalid variable, does not exist in line " + lineNum);
					return val; }
				try
				{
					val = Double.parseDouble(num);
				}
				catch (Exception e) {
					errors.setText("Invalid expression in line " + lineNum);
				}
				return val;
		} 
} 

		

	
		

	