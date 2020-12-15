import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 Program: Lab 4 without optional GUI
 Name: Zach Macadam, Damon Ramirez, Noah Springborn, and Riley Durbin
 Class: Techniques in Programming
 Semester: Spring 2018
 Due Date: 5/2/2018
 */

public class Lab4 {
	private int special;
	private Vector<ClientThread> clients;
	private Lab4GUI serverG;
	private SimpleDateFormat sdf;
	private int port;
	private boolean forgeAhead;

	//if the user wants to run the server without the GUI, this constructor will be called first passing in null for the Lab4GUI object
	public Lab4(int port) {
		this(port, null);
	}
	//if the user ran the server from the Lab4GUI file, the GUI will be passed in or else it will be null
	public Lab4(int port, Lab4GUI serverG) {
		this.serverG = serverG;
		this.port = port;
		sdf = new SimpleDateFormat("HH:mm:ss");
		clients = new Vector<ClientThread>();
	}

	public static void main(String[] args) {
		int portNumber = 5000;
		Lab4 server = new Lab4(portNumber);
		server.startServer();
	}
	//this method will start the server on the designated port
	public void startServer() {
		forgeAhead = true;
		try {
			ServerSocket serverSocket = new ServerSocket(port); //this creates the ServerSocket on port 5000
			//this while loop is the core of the server, once this loop stops executing, so does the server
			while (forgeAhead) {
				display("Waiting for Clients on port: " + port);
				Socket socket = serverSocket.accept(); //when a connection to a client is found, accept that connection
				if (!forgeAhead) //if for some reason the boolean was set to false, break out of the loop
					break;
				ClientThread t = new ClientThread(socket); //this server is built to handle multiple clients simultaneously, so a thread for each client is created
				clients.add(t); //clients are kept track of in a thread-safe vector
				t.start(); //the thread is started
			}
			//this is run when forgeAhead is no longer true
			try {
				serverSocket.close();  //this cuts off any new connections from forming
				//this for loop makes sure that all sockets and IOStreams are closed depending on how many clients and held in the vector
				for (int i = 0; i < clients.size(); i++) {
					ClientThread tc = clients.get(i);
					try {
						tc.ois.close();
						tc.oos.close();
						tc.socket.close();
					} catch (IOException e) {
					}
				}
				//if for some reason we cannot close the server, this catch will display an exception
			} catch (IOException e) {
				display("Exception closing server and clients: " + e);
			}
			//if for some reason the ServerSocket could not be created, this catch will display an exception
		} catch (IOException e) {
			String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
			display(msg);
		}
	}

	//this method is purely so that the Lab4GUI can stop the server using a button
	protected void stop() {
		forgeAhead = false;
		try {
			new Socket("localhost", port); //this connects to the server as a client to exit statement
		} catch (IOException e) {

		}
	}
	//this method displays messages to the server
	private void display(String msg) {
		String time = sdf.format(new Date()) + " " + msg;
		if (serverG == null) //if there is no Lab4GUI running, it prints it to the console
			System.out.println(time);
		else
			serverG.appendEvent(time + "\n"); //but if there is a Lab4GUI running, it prints it to the event log
	}

	//this is our very important method that sends messages to all connected clients
	private synchronized void broadcast(String message) {
		String time = sdf.format(new Date());
		String msg = time + " " + message + "\n";
		if (serverG == null) //if the Lab4GUI is not running, print the message to the console
			System.out.println(msg);
		else
			serverG.appendChat(msg); //but if there is a Lab4GUI running, print it to the view-only chat room

		//this for loop not only checks to see if all the clients in the vector are still connected, but also writes the chat message to all of them
		for (int i = clients.size() - 1; i >= 0; i--) {
			ClientThread ct = clients.get(i);
			if (!ct.writeMsg(msg)) { //if the server cannot write to the client, they must have disconnected
				clients.remove(i);   //so remove them from the vector
				display("Disconnected user" + ct.username); //and add it to the event log
			}
		}
	}
	//this method is for when clients logout using the command or button
	synchronized void remove(int id) { //each client has a unique id , this is passed in to identify who is logging out
		for (int i = 0; i < clients.size(); i++) { //search through all the clients in the vector
			ClientThread ct = clients.get(i);
			if (ct.id == id) { //if the id of the client matches the id that was passed in, we have the right client
				clients.remove(i); //remove them from the vector
				return;
			}
		}
	}

	//this inner-class is the ClientThread that creates a new instance for each client connected
	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream ois;
		ObjectOutputStream oos;
		int id;
		String username;
		String cm;
		String date;

		ClientThread(Socket socket) {
			id = special++; //by incrementing my special int, each ClientThread has a id unique to only them
			this.socket = socket; //the socket passed in to the ClientThread is from when the server accepts a connection
			try {
				//these I/O streams allow messages to be sent and written
				//Object I/O streams were originally chosen to hopefully do pictures but was not accomplished in the time frame
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				username = (String) ois.readObject(); //the client is designed so that the first message that the InputStream reads must be the username 
				broadcast(username + " has connected"); //tell everyone in the chat room that someone else has connected
				if (serverG != null)
					display(username + " has connected"); //if there is a Lab4GUI present, add to the event log that a user has connected
			} catch (IOException e) {
				display("Exception creating streams " + e);
				return;
			} catch (ClassNotFoundException e) {
				//necessary exception eclipse made me add
			}
			date = new Date().toString() + "\n";
		}

		public void run() {
			boolean forgeAhead = true;
			//while true keeps it going forever until an issue arises
			while (forgeAhead) {
				try {
					cm = (String) ois.readObject(); //try to read a message from the client
				} catch (IOException e) {
					display(username + " Exception reading streams: " + e);
					break;
				} catch (ClassNotFoundException e) {

				}
				//if the message is the logout command, tell people in the server that the user has left and stop the while loop
				if (cm.equals("/logout")) {
					if (serverG != null)
						display(username + " disconnected from server");
					broadcast(username + " disconnected from server");
					forgeAhead = false;
				//if the message is the userlist command, write back to the client who called the method that list of users connected to the server
				} else if (cm.equals("/userlist")) {
					writeMsg("List of users connected at " + sdf.format(new Date()) + "\n");
					for (int i = 0; i < clients.size(); i++) {
						ClientThread ct = clients.get(i);
						writeMsg((i + 1) + ") " + ct.username + " since " + ct.date);
					}
				//if the message is the whisper command, write a message only to the client number specified by the client whispering
				} else if (cm.startsWith("/whisper")) {
					String msg = "";
					int clientid = 0;
					StringTokenizer tok = new StringTokenizer(cm);
					String whisper = tok.nextToken();
					try {
						clientid = Integer.parseInt(tok.nextToken()); //if the user used the command correctly, this is a number on the userlist
						ClientThread ct = clients.get(clientid - 1); //subtract one because a vector starts at 0
						while (tok.hasMoreTokens()) { //reconstruct the original message into a string
							msg = msg + tok.nextToken();
						}
						String time = sdf.format(new Date());
						ct.writeMsg(time + " whisper from " + username + ": " + msg + "\n"); //write the message only to the client specified
					} catch (Exception er) {
						display("Invalid whisper attempt from a client");
					}
				//if no commands are used, broadcast the message to all
				} else
					broadcast(username + ": " + cm);
			}
			//if we reach these commands, the loop has been broken because the user logged out so we must remove them from the list
			remove(id);
			//the I/O Streams and socket are no longer needed and should be closed
			close();
		}
		//this method closes the I/O Streams and the socket because they are no longer in use
		private void close() {
			try {
				if (oos != null)
					oos.close();
			} catch (Exception e) {
			}
			try {
				if (ois != null)
					ois.close();
			} catch (Exception e) {
			}
			try {
				if (socket != null)
					socket.close();
			} catch (Exception e) {
			}
		}
		//this method checks to see if the client is still connected, if they are, it writes a message to them
		private boolean writeMsg(String msg) {
			if (!socket.isConnected()) {
				close();
				return false;
			}
			try {
				oos.writeObject(msg);
			} catch (IOException e) {
				display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}
	}
}
