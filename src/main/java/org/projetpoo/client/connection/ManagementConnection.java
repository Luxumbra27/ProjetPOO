package org.projetpoo.client.connection;


import org.projetpoo.client.users.RemoteUser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ManagementConnection{

	public String remoteHost;
	public int remotePort;
	private Socket _socket;
	private BufferedReader _in;
	private PrintWriter _out;
	private ObjectOutputStream _outObj;
	private ObjectInputStream _inObj;


	public ManagementConnection(String name, int remoteP){

		remoteHost = name;
		remotePort = remoteP;

	}

	public ArrayList<RemoteUser> getConnectedUsers() throws Exception {
		_out.println("getConnectedUsers");
		ArrayList<RemoteUser> lst = null;
		try {
			lst = (ArrayList<RemoteUser>) _inObj.readObject();
		} catch (Exception e){
			e.printStackTrace();
		}
		if (lst == null){
 			throw new Exception("Could not retrieve connected users list.");
		} else {
			return lst;
		}
	}


	public boolean isConnected(){
		if (_socket != null){
			return _socket.isConnected();
		} else {
			return false;
		}
	}

	public String getReply(){
		try {
			return _in.readLine();
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	public void sendMessage(String str){
	    _out.println(str);
    }


	public void connect() throws Exception{

	    _socket = new Socket(remoteHost, remotePort);
	    _socket.setSoTimeout(0);
	    _socket.setKeepAlive(true);
		_out = new PrintWriter(_socket.getOutputStream(),true);
		_outObj = new ObjectOutputStream(_socket.getOutputStream());
		_in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
		_inObj = new ObjectInputStream(_socket.getInputStream());
		_out.println(""); // prevents initial bug

    }

    /*public RemoteUser getRemoteUserBySocket(Socket sock){
		try{
			updateConnectedUsers();
		} catch (Exception e){
			e.printStackTrace();
		}
		for (RemoteUser user: _connectedList){

			if (sock.getInetAddress().getHostName().equals(user.getHostname()) && (sock.getPort() == user.getPort())){
				return user;
			}
		}
		System.out.println("[DBG] Can't find user in actual list.");
		return null;
	}*/

}
