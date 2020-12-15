import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Lab1 extends JFrame implements ActionListener {
	private JTextField assemblerInstruction;
	private JTextField binaryInstruction;
	private JTextField hexInstruction;
	private JLabel errorLabel;
	
	public Lab1() {
		setTitle("PDP-11");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		assemblerInstruction = new JTextField();
		assemblerInstruction.setBounds(25, 24, 134, 28);
		getContentPane().add(assemblerInstruction);
		assemblerInstruction.setColumns(10);

		JLabel lblAssemblyLanguage = new JLabel("Assembly Language");
		lblAssemblyLanguage.setBounds(25, 64, 160, 16);
		getContentPane().add(lblAssemblyLanguage);

		binaryInstruction = new JTextField();
		binaryInstruction.setBounds(25, 115, 180, 28);
		getContentPane().add(binaryInstruction);
		binaryInstruction.setColumns(10);

		hexInstruction = new JTextField();
		hexInstruction.setBounds(236, 115, 134, 28);
		getContentPane().add(hexInstruction);
		hexInstruction.setColumns(10);

		JLabel lblBinary = new JLabel("Binary Instruction");
		lblBinary.setBounds(25, 155, 190, 16);
		getContentPane().add(lblBinary);

		JLabel lblHexEquivalent = new JLabel("Hex Instruction");
		lblHexEquivalent.setBounds(236, 155, 131, 16);
		getContentPane().add(lblHexEquivalent);

		errorLabel = new JLabel("");
		errorLabel.setBounds(25, 235, 280, 16);
		getContentPane().add(errorLabel);

		JButton btnEncode = new JButton("Encode");
		btnEncode.setBounds(230, 25, 117, 29);
		getContentPane().add(btnEncode);
		btnEncode.addActionListener(this);

		JButton btnDecode = new JButton("Decode Binary");
		btnDecode.setBounds(30, 183, 170, 29);
		getContentPane().add(btnDecode);
		btnDecode.addActionListener(this);

		JButton btnDecodeHex = new JButton("Decode Hex");
		btnDecodeHex.setBounds(230, 183, 150, 29);
		getContentPane().add(btnDecodeHex);
		btnDecodeHex.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		errorLabel.setText("");
		if (evt.getActionCommand().equals("Encode")) {
			encode();
		} else if (evt.getActionCommand().equals("Decode Binary")) {
			decodeBin();
		} else if (evt.getActionCommand().equals("Decode Hex")) {
			decodeHex();
		}
	}

	public static void main(String[] args) {
		Lab1 window = new Lab1();
		window.setVisible(true);
	}

	String shortToHex(short x) {
		String ans="";
		for (int i=0; i<4; i++) {
			int hex = x & 15;
			char hexChar = "0123456789ABCDEF".charAt(hex);
			ans = hexChar + ans;
			x = (short)(x >> 4);
		}
		return ans;
	}

	String shortToBinary(short x) {
		String ans="";
		for(int i=0; i<16; i++) {
			ans = (x & 1) + ans;
			x = (short)(x >> 1);
		}
		return ans;
	}
	
/************************************************************************/
/* Put your implementation of the encode, decodeBin, and decodeHex      */
/* methods here. You may add any other methods that you think are       */
/* appropriate. However, you should not change anything in the code     */
/* that I have written.                                                 */
/************************************************************************/
/*
 Name: Zach Macadam
 Class: Techniques in Programming
 Semester: Spring 2018
 Due Date: 2/13/2018
 */
	public void encode()
	{
		//these lines declare and initialize my variables 
		short assemblerShort = 0;
		short source = 0;
		short destination = 0;
		String assemblerBinary = "";
		String assemblerHex = "";
		String assemblerSpace = assemblerInstruction.getText();
		//the trim ensures that there is only 1 space separating the assembler code
		String assemblerBig = assemblerSpace.trim().replaceAll(" +"," ").toUpperCase();
		String assemblerTrimmed = "";
		
		//these if, else if, and else statements are very important to determine if the entry is valid
		if (assemblerBig.startsWith("ADD ")) {
			assemblerShort = 24576;
			assemblerTrimmed = assemblerBig.replace("ADD ", ""); }
		else if (assemblerBig.startsWith("SUB ")) {
			assemblerShort = -8192;
			assemblerTrimmed = assemblerBig.replace("SUB ", ""); }
		else if (assemblerBig.startsWith("MOV ")) {
			assemblerShort = 16384;
			assemblerTrimmed = assemblerBig.replace("MOV ", ""); }
		else if (assemblerBig.startsWith("MOVB ")) {
			assemblerShort = -16384;
			assemblerTrimmed = assemblerBig.replace("MOVB ", ""); }
		else if (assemblerBig.startsWith("CMP ")) {
			assemblerShort = 0;
			assemblerTrimmed = assemblerBig.replace("CMP ", ""); }
		else if (assemblerBig.startsWith("CMPB ")) {
			assemblerShort = -32768;	
			assemblerTrimmed = assemblerBig.replace("CMPB ", ""); }
		else errorLabel.setText("Invalid Assembler Instruction");
		//this number keeps track of the bit positions and will eventually be converted into binary
		System.out.println(assemblerShort);

		String assemblerDigits = assemblerTrimmed.replaceAll("\\D+","");//this string isolates the registry positions
		System.out.println(assemblerDigits);
		int digits = 0;
		try
			{digits = Integer.parseInt(assemblerDigits);}
		catch (Exception e) {errorLabel.setText("Invalid Assembler Instruction"); return;}
			source = (short)(digits/10); //the number corresponding to bits 7-9
		destination = (short)(digits%10);//the number corresponding to bits 1-3
		if (source > 7 || destination > 7)
			errorLabel.setText("Invalid Registry Number"); //111 in binary = 7, making 7 the maximum registry number
		else source = (short)((source << 6)); //this finds the correct value needed to add to arrive at the correct binary result
		System.out.println(source); //unless it is 0, this number is difference from the number input to the program
		assemblerShort = (short) (source + destination + assemblerShort);
		
		String assemblerText = assemblerTrimmed.replaceAll("\\d",""); //this string removes the numbers
		//this first block of if, else if, else statements determines the orientation of the source R
		//and adds the correct number to our short relating to the assembler code -> binary conversion
		if (assemblerText.startsWith("R,")); 
		else if (assemblerText.startsWith("(R),"))
			assemblerShort = (short)(512 + assemblerShort);
		else if (assemblerText.startsWith("-(R),"))
			assemblerShort = (short)(2048 + assemblerShort);
		else if (assemblerText.startsWith("(R)+"))
			assemblerShort = (short)(1024 + assemblerShort);
		else errorLabel.setText("Invalid Assembler Instruction");

		//this block of if, else if, else statements determines the orientation of the destination R
		//and adds the correct number to our short relating to the assembler code -> binary conversion
		if (assemblerText.endsWith(",R")); 
		else if (assemblerText.endsWith(",(R)"))
				assemblerShort = (short)(8 + assemblerShort);
		else if (assemblerText.endsWith(",-(R)"))
			assemblerShort = (short)(32 + assemblerShort);
		else if (assemblerText.endsWith(",(R)+"))
			assemblerShort = (short)(16 + assemblerShort);
		else errorLabel.setText("Invalid Assembler Instruction");

		binaryInstruction.setText("");
		hexInstruction.setText("");
		if (errorLabel.getText().equals("")) {
		assemblerBinary = shortToBinary(assemblerShort); //my short is the correct value, it just needs to converted to binary
		assemblerHex = shortToHex(assemblerShort);
		binaryInstruction.setText(assemblerBinary);
		hexInstruction.setText(assemblerHex);}
	}
	public void decodeBin()
	{
		//these lines declare and initialize my variables
		String binaryDecoded = "";
		String binaryTrimmed = binaryInstruction.getText().trim();
		int binInput = 0;
		short destinationR = 0;
		short sourceR = 0;
		short sourceF = 0;
		if (binaryTrimmed.length()!=16) //binary inputs must be 16 bits long
			errorLabel.setText("Illegal Binary Entry");
		try {binInput = Integer.parseInt((binaryInstruction.getText()),2);}//this converts the String to a Binary integer
		catch (Exception e) {errorLabel.setText("Illegal Binary Entry"); return;}
		System.out.println(binInput); 
		short binShort = (short) binInput; //my original input converted to a short
		

		if ((short)(binInput & 0xF000)==-4096)
				errorLabel.setText("Illegal Instruction");
		//these if, else if, else statements determine if the first 4 bits are a valid assembly command
		//and remove the first 4 bits by & them with 0
		if ((short)(binInput & 0xF000)==24576) {
			binaryDecoded = "ADD ";
			binInput = (short)(binInput & 0x0FFF); }
		else if ((short)(binInput & 0xF000)==-8192){
			binaryDecoded = "SUB ";
			binInput = (short)(binInput & 0x0FFF); }
		else if ((short)(binInput & 0xF000)==16384) {
			binaryDecoded = "MOV ";
			binInput = (short)(binInput & 0x0FFF); }
		else if ((short)(binInput & 0xF000)==0) {
			binaryDecoded = "CMP ";
			binInput = (short)(binInput & 0x0FFF); }
		else if ((short)(binInput & 0xF000)==-16384) {
			binaryDecoded = "MOVB ";
			binInput = (short)(binInput & 0x0FFF); }
		else if ((short)(binInput & 0xF000)==-32768) {
			binaryDecoded = "CMPB ";
			binInput = (short)(binInput & 0x0FFF); }
		else errorLabel.setText("Illegal Instruction");
		
		System.out.println(binInput); //this number is the 12 bits regarding the source and destination registries
		destinationR = (short) (binInput & 0x7); //isolates the first 3 bits, the destination registry number
		sourceF = (short)(binInput & 0x1C0); //isolates bits 7-9, the source registry number
		sourceR = (short)(((short)(binInput >> 6)) & 0x7);//finds the true binary value of the source registry number 
		binInput = (short) (binInput - sourceF - destinationR);
		System.out.println(binInput); //this number contains only the bits relating to the orientation of R
		
		//this if, else if, else block determines how the R should look based on the bits in positions 4-6 and 10-12
		if (binInput >= 512 && binInput < 1024) {
			binaryDecoded = binaryDecoded+"(R"+sourceR+")";
			binaryDecoded = binaryDecoded + assemblerFinal((binInput - 512), destinationR); }
		else if (binInput >= 1024 && binInput < 2048) {
			binaryDecoded = binaryDecoded+"(R"+sourceR+")+";
			binaryDecoded = binaryDecoded + assemblerFinal((binInput - 1024), destinationR); }
		else if (binInput >= 2048) {
			binaryDecoded = binaryDecoded+"-(R"+sourceR+")";
			binaryDecoded = binaryDecoded + assemblerFinal((binInput - 2048), destinationR); }
		else if (binInput == 32) 
			binaryDecoded = binaryDecoded+"R"+sourceR+",-(R"+destinationR+")";
		else if (binInput == 16) 
			binaryDecoded = binaryDecoded+"R"+sourceR+",(R"+destinationR+")+";
		else if (binInput == 8) 
			binaryDecoded = binaryDecoded+"R"+sourceR+",(R"+destinationR+")";
		else if (binInput == 0)
			binaryDecoded = binaryDecoded+"R"+sourceR+",R"+destinationR; 
		else errorLabel.setText("Illegal Instruction"); //if the final value equals anything other than 0, there is an illegal bit
		

		System.out.print(binaryDecoded);//the finaly assembly instruction, if it was a valid input
		assemblerInstruction.setText("");
		hexInstruction.setText(shortToHex((short)binShort)); 
		if (errorLabel.getText().equals("")) 
			assemblerInstruction.setText(binaryDecoded);
		}

		String assemblerFinal(int x, short y)
		{
			String binaryDecoded = "";
			if (x == 32) {
				binaryDecoded = ",-(R"+y+")"; x = x-32; }
			else if (x == 16) {
				binaryDecoded = ",(R"+y+")+"; x = x-16; }
			else if (x == 8) {
				binaryDecoded = ",(R"+y+")"; x = x-8; }
			else if (x == 0)
				binaryDecoded = ",R"+y;
			else errorLabel.setText("Illegal Instruction");		
			
			if (x != 0 )
				errorLabel.setText("Illegal Instruction");//if the final value equals anything other than 0, there is an illegal bit
			return binaryDecoded;
		}
		public void decodeHex()
		{
			//instead of doing another set of code, I convert the Hex input, if it is valid, to binary and decode the binary input
			
			
			
			String hexTrimmed = hexInstruction.getText().trim();
			if (hexTrimmed.length()!=4) {
				errorLabel.setText("Hex Instruction must be 4 Digits");
				binaryInstruction.setText("");
				assemblerInstruction.setText(""); }
			else { 
			int hexInt = Integer.parseInt(hexInstruction.getText(), 16);
			String hexBin = shortToBinary((short)hexInt);
			binaryInstruction.setText(hexBin);
			decodeBin(); }
		}
}


