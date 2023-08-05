package com.Panchal.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread=Worker 
//worker need a job to perform 
// for job you give Runnable 
//Once a job is created via Runnable so write the job logic in a run function
//Assign the job to Thread / worker 
public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;    //inputstream and outputstream is per client 
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket , Server server) throws IOException {
		this.server=server;
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream();//Read client data
		out=clientSocket.getOutputStream();//client data write 
		System.out.println("New Client Comes....");
	}
	@Override
	public void run() {
		//Read data from the client and Boardcast the data to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String Line;
		try {
		while(true) {
			
				Line=br.readLine();// \n 
				System.out.println("Line Read...."+Line);
				if(Line.equalsIgnoreCase("quit")) {
					break;//client chat end 
				}
			//	out.write(Line.getBytes());// data send to the client 
				//Boardcast to all client 
				for(ServerWorker serverWorker : server.workers) {
					Line=Line+"\n";
					serverWorker.out.write(Line.getBytes());
				}
			} 
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(in!=null) {
				in.close();
			}
			if(out!=null) {
				out.close();
			}
			if(clientSocket!=null) {
				clientSocket.close();
			}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
