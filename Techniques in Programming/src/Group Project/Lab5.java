import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

/*
Program: Lab 5
Name: Zach Macadam, Damon Ramirez, Noah Springborn, and Riley Durbin
Class: Techniques in Programming
Semester: Spring 2018
Due Date: 5/2/2018
*/

public class Lab5 extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel label;
	private JTextField textField;
	private JLabel lblServerAddress;
	private JTextField textField_1;
	private JLabel lblPortNumber;
	private JTextField textField_2;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnUserList;
	private JScrollPane scrollPane_1;
	private JTextArea txtrWelcomeToChat;
	private boolean connected;
	private Client client;

	private int defaultPort;
	private String defaultHost;

	//this creates my chat client GUI
	Lab5(String host, int port) {

		defaultPort = port;
		defaultHost = host;

		frame = new JFrame();
		frame.setBounds(100, 100, 451, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField("anonymous");
		textField.setBounds(9, 375, 415, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		label = new JLabel("Enter Username Below");
		label.setBounds(151, 360, 158, 14);
		frame.getContentPane().add(label);

		lblServerAddress = new JLabel("Server Address:");
		lblServerAddress.setBounds(10, 406, 106, 14);
		frame.getContentPane().add(lblServerAddress);

		textField_1 = new JTextField(host);
		textField_1.setBounds(118, 406, 106, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		lblPortNumber = new JLabel("Port Number:");
		lblPortNumber.setBounds(251, 406, 89, 14);
		frame.getContentPane().add(lblPortNumber);

		textField_2 = new JTextField("" + port);
		textField_2.setBounds(335, 406, 89, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		btnLogout = new JButton("Logout");
		btnLogout.setEnabled(false);
		btnLogout.addActionListener(this);
		btnLogout.setBounds(170, 431, 89, 23);
		frame.getContentPane().add(btnLogout);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(61, 431, 89, 23);
		frame.getContentPane().add(btnLogin);

		btnUserList = new JButton("User List");
		btnUserList.setEnabled(false);
		btnUserList.addActionListener(this);
		btnUserList.setBounds(279, 431, 89, 23);
		frame.getContentPane().add(btnUserList);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(9, 11, 416, 344);
		frame.getContentPane().add(scrollPane_1);

		txtrWelcomeToChat = new JTextArea();
		txtrWelcomeToChat.setEditable(false);
		txtrWelcomeToChat.setText("Welcome to the Chat Room! \n");
		scrollPane_1.setViewportView(txtrWelcomeToChat);

		frame.setVisible(true);
		textField.requestFocus();
	}

	//this simple method appends a message to the main chat JTextArea
	void append(String str) {
		txtrWelcomeToChat.append(str);
	}
	//if the connection is lost or a user voluntarily logs out, this method sets the client back to default settings
	void connectionLost() {
		btnLogin.setEnabled(true);
		btnLogout.setEnabled(false);
		btnUserList.setEnabled(false);
		label.setText("Enter Username Below");
		textField.setText("anonymous");
		textField_1.setText(defaultHost);
		textField_2.setText("" + defaultPort);
		textField_1.setEditable(true);
		textField_2.setEditable(true);
		textField.removeActionListener(this);
		connected = false;
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		//if the logout button is clicked
		if (o == btnLogout) {
			txtrWelcomeToChat.setText(""); //empty the chat window
			client.sendMessage("/logout"); //tell the server that someone is logging out
			connectionLost(); //reset the client back to default settings
			return;
		}
		//if the userlist button is clicked
		if (o == btnUserList) {
			client.sendMessage("/userlist"); //tell the server that someone is requesting the userlist
			return;
		}
		if (connected) {
			client.sendMessage(textField.getText()); //send a message to the server
			textField.setText("");
			return;
		}
		//if the login button is clicked
		if (o == btnLogin) {
			txtrWelcomeToChat.setText("");
			String username = textField.getText().trim(); //get the username from the textfield
			if (username.length() == 0) //username cannot be empty
				return;
			String server = textField_1.getText().trim(); //get the server from the textfield
			if (server.length() == 0) //server cannot be empty
				return;
			String portNumber = textField_2.getText().trim(); //get the port number from the textfield
			if (portNumber.length() == 0) //port number cannot be empty
				return;
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			} catch (Exception en) {
			}
			client = new Client(server, port, username, this); //create a new instance a client
			if (!client.start()) //if there is an error creating the client, the program execution is halted
				return;
			textField.setText("");
			label.setText("Enter your message below");
			connected = true;

			btnLogin.setEnabled(false);
			btnLogout.setEnabled(true);
			btnUserList.setEnabled(true);
			textField_2.setEditable(false);
			textField_1.setEditable(false);
			textField.addActionListener(this);
		}
	}
	//this creates the GUI passing in the default host and the default port number
	public static void main(String[] args) {
		new Lab5("localhost", 5000);
	}

	class Client {
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private Socket socket;
		private String server, username;
		private int port;
		private Lab5 cg;
		
		//this creates a Client with the details obtained from the textfields and the last constructor being the GUI itself
		Client(String server, int port, String username, Lab5 cg) {
			this.server = server;
			this.port = port;
			this.username = username;
			this.cg = cg;
		}
		//this method attempts to start the client, if at any point a false boolean is returned, the program execution is halted
		public boolean start() {
			//this try catch attempts to create a new socket and connect to the host using the given port number
			try {
				socket = new Socket(server, port);
			} catch (Exception b) {
				display("Error connecting to Server");
				return false;
			}
			//if this code is reached, a connection to the server has been successfully made
			String msg = "Connection accepted, Welcome " + username + "!" + "\n Type !commands for chat commands";
			display(msg);

			//this try catch attempts to create the I/O Streams using the I/O streams from the socket that has just been created 
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException i) {
				display("Error creating streams");
				return false;
			}
			//if this code is reached, the streams were created properly and a new thread can be started
			new ServerThread().start();
			
			
			//this try catch attempts to write the username to the server, if it fails, the socket is closed
			try {
				oos.writeObject(username);
			} catch (IOException i) {
				display("Exception during login");
				disconnect();
				return false;
			}
			//if this code is reached, everything has gone smoothly
			return true;
		}
		//this simply method appends a message to the client's GUI
		private void display(String msg) {
			cg.append(msg + "\n");
		}
		//this method sends our messages from the client to the server
		void sendMessage(String msg) {
			//if the message is calling for the commands, we do not want to send it to the server, just print back the list of commands to the user
			if (msg.trim().equals("!commands")) {
				display("****************COMMANDS**************** \n/logout: terminates connection to server\n/userlist: displays list of connected users \n"
						+ "/whisper x msg: sends a message to 'x' on the user list\n");
			//if the message is the logout command, reset the client to default settings and tell the server that the client is logging out
			} else if (msg.trim().equals("/logout")) {
				try {
					oos.writeObject(msg);
				} catch (IOException e) { }
				connectionLost();
			//if anything else is sent, it is treated as a regular message
			} else
				try {
					oos.writeObject(msg);
				} catch (IOException e) {
					display("Error writing to server");
				}
		}
		//this method is used when there is an error writing to the server, it closes both the I/O streams and the socket itself
		private void disconnect() {
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e) {
			}
			try {
				if (oos != null)
					oos.close();
			} catch (Exception e) {
			}
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}

		}
	}
	//this threaded class is quite simple but very important
	class ServerThread extends Thread {
		public void run() {
			//keep running until an error occurs
			while (true) {
				try {
					String msg = (String) client.ois.readObject(); //read any messages sent by the server
					append(msg); //add them to the chat window
				} catch (IOException e) { 
					client.display("Server has closed connection");
					break; //something has gone wrong, so break out of the infinite loop
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
