package org.projetpoo.client.users;

import java.net.InetAddress;

public class RemoteUser {
	private InetAddress clientAddress ;
	private int clientPort ;
	
	public RemoteUser () {
		this.clientAddress = null ;
		this.clientPort = 0 ;
	}
	
	public RemoteUser (InetAddress clientAddress, int clientPort) {
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
	
	public int getPort () {
		return this.clientPort ;
	}
	 

}
