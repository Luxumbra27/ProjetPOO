package org.projetpoo.client.connection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class RemoteConnection {
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader br;
	
	public RemoteConnection() {}
	
	public void connect() throws Exception {
		
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
				e.printStackTrace();
			}
			System.out.println("[CLIENT] Received message : \"" + input + "\"");
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void printReply() throws Exception{
		System.out.println(br.readLine());
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
