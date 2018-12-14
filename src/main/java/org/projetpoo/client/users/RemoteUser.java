package org.projetpoo.client.users;

import java.io.Serializable;
import java.net.InetAddress;

public class RemoteUser extends UserInformation implements Serializable {

	private InetAddress clientAddress ;
	private int clientPort ;
	
	public RemoteUser (String nickname, InetAddress clientAddress, int clientPort) {

		super(nickname);
		this.clientAddress = clientAddress ;
		this.clientPort = clientPort ;
	}
	
	public void setIPAddress (InetAddress IPAddress) {

		this.clientAddress = IPAddress ;
	}
	
	public void setPort (int port) {

		this.clientPort = port ;
		}
	
	public InetAddress getIPAddress () {

		return this.clientAddress;
	}

	public String getStringIP(){

		return clientAddress.getHostName();
	}
	
	public int getPort () {

		return this.clientPort ;
	}
	 

}
