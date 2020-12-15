import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Program: Lab 4 with GUI
Name: Zach Macadam, Damon Ramirez, Noah Springborn, and Riley Durbin
Class: Techniques in Programming
Semester: Spring 2018
Due Date: 5/2/2018
*/

public class Lab4GUI extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9018256961170244064L;
	private JButton startAndStop;
	private JTextArea chatRoom;
	private JTextArea eventTracker;
	private JTextField portNumber;
	private Lab4 server;

	public static void main(String[] args) {
		new Lab4GUI(5000);
	}
	//this creates the option GUI for the server
	Lab4GUI(int port) {
		server = null;
		JPanel north = new JPanel();
		north.add(new JLabel("Port number: "));
		portNumber = new JTextField("" + port);
		north.add(portNumber);
		startAndStop = new JButton("Start");
		startAndStop.addActionListener(this);
		north.add(startAndStop);
		add(north, BorderLayout.NORTH);

		JPanel middle = new JPanel(new GridLayout(2, 1));
		chatRoom = new JTextArea(75, 75);
		chatRoom.setEditable(false);
		appendChat("Chat Room \n");
		middle.add(new JScrollPane(chatRoom));
		eventTracker = new JTextArea(75, 75);
		eventTracker.setEditable(false);
		appendEvent("Event Log \n");
		middle.add(new JScrollPane(eventTracker));
		add(middle);

		addWindowListener(this);
		setSize(500, 500);
		setVisible(true);
	}
	//this simple method appends a message to the eventLog
	void appendEvent(String str) {
		eventTracker.append(str);
	}
	//this simple method appends a message to the view-only chat room
	void appendChat(String str) {
		chatRoom.append(str);
	}
	//this method is used for my startAndStop button
	public void actionPerformed(ActionEvent e) {
		if (server != null) { //if the server is not null, the start must has been used so the stop button is visible
			server.stop(); //so the server should be stopped
			server = null;
			portNumber.setEditable(true);
			startAndStop.setText("Start"); //the button is no longer being used to stop the server so reset it back to start
			return; //the next lines are for when the button is start, not stop, so we return out of the method
		}
		int port;
		//if this try is reached it is because the user is trying to start a server
		try {
			port = Integer.parseInt(portNumber.getText().trim());
		} catch (Exception d) {
			appendEvent("Invalid port");
			return;
		}
		//create a new instance of the server, passing in the port number from the TextField and the GUI from this file
		server = new Lab4(port, this);
		//start the server as a thread
		new ServerRunning().start();
		startAndStop.setText("Stop");
		portNumber.setEditable(false);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		if (server != null) {
			try {
				server.stop();
			} catch (Exception close) {
			}
			server = null;
		}
		dispose();
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	

	class ServerRunning extends Thread {
		public void run() {
			server.startServer(); //this should run until an issue occurs because the method called on has a while loop 
			startAndStop.setText("Start"); //if this code is reached the server stopped and the option to start it again is given
			portNumber.setEditable(true); //allow the user to edit the port number
			appendEvent("Server Stopped \n"); 
			server = null;
		}
	}
}
