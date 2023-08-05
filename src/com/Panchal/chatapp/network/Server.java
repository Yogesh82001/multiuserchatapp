// chit chat application 

package com.Panchal.chatapp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.Panchal.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server Start and waiting for the clients to join.....");
		handleClientRequset();
	}
	// multiple client handshacking 
	public void handleClientRequset() throws IOException {
		while(true) {
		Socket clientSocket=	serverSocket.accept();// Handshaking 
		//per client per Thread 
		ServerWorker serverWorker =new ServerWorker(clientSocket, this);// creating a new worker/Thread
		workers.add(serverWorker);
		serverWorker.start();
		}
	}
	/*Single Client */
	/*
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server started and waiting for the Client connection .....");
	Socket socket=	serverSocket.accept();// Handshaking 
	System.out.println("Client joins the server ");
InputStream in =	socket.getInputStream();//raed bytes from the network
byte arr []=in.readAllBytes();
String str=new String(arr);//Bytes convert into string 
System.out.println("Message Rec from the client "+str);
in.close();
	socket.close();
	}*/
	public static void main(String[] args) throws IOException {
		Server server =new Server();
	}
}
