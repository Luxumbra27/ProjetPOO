package org.projetpoo.client.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class RemoteConnection {
	
	Socket socket;
	PrintWriter out;
	BufferedReader br;
	
	public RemoteConnection() {}
	
	public void connect() {
		
		try {
			System.out.println("[CLIENT] Attempting to connect...");
			socket = new Socket(InetAddress.getLocalHost(),1234);
			System.out.println("[CLIENT] Now connected !");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("hello from client !");
			String input = "";
			try {
				input = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("[CLIENT] Received message : \"" + input + "\"");
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		RemoteConnection test = new RemoteConnection();
		test.connect();
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	test.close();
		            }
		        },
		        // 10 seconds
		        10000 
		);
		
	}

}
