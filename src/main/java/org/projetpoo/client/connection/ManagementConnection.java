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
	private ArrayList<RemoteUser> _connectedList;


	public ManagementConnection(String name, int remoteP){

		remoteHost = name;
		remotePort = remoteP;

	}

	public void updateConnectedUsers() throws Exception {
		_out.println("[CMD]");
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
			_connectedList = lst;
		}
	}

	public ArrayList<RemoteUser> getConnectedList(){
		return _connectedList;
	}

	public boolean isConnected(){
		if (_socket != null){
			return _socket.isConnected();
		} else {
			return false;
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
		//_inObj = new ObjectInputStream(new BufferedInputStream(_socket.getInputStream()));

    }

    public RemoteUser getRemoteUserBySocket(Socket sock) throws Exception{
		try{
			updateConnectedUsers();
		} catch (Exception e){
			e.printStackTrace();
		}
		for (RemoteUser user: _connectedList){

			if (sock.getInetAddress().getHostName().equals(user.getStringIP()) && (sock.getPort() == user.getPort())){
				return user;
			}
			throw new Exception("User not found in the connected users.");
		}
		return null;
	}

}
