package com.Panchal.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

//Client data read 
public class ClientWorker extends Thread{
	private InputStream in;
	private JTextArea textArea;
	public ClientWorker(InputStream in, JTextArea textArea) {
		this.in=in;
		this.textArea=textArea;
	}
	@Override
	public void run(){
		BufferedReader br =new BufferedReader(new InputStreamReader(in));
		String Line;
		try {
		while(true) {
			 Line=br.readLine();// \n
			 System.out.println("Line Read...."+Line);
			 textArea.setText(textArea.getText()+ Line+"\n");
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
