package org.projetpoo.client.users;

import java.io.Serializable;

public class RemoteUser extends UserInformation implements Serializable {

	private String remoteHostname ;
	private int remotePort ;
	
	public RemoteUser (String nickname, String remoteHostname, int remotePort) {

		super(nickname);
		this.remoteHostname = remoteHostname ;
		this.remotePort = remotePort ;
	}
	
	public void setIPAddress (String IPAddress) {

		this.remoteHostname = IPAddress ;
	}
	
	public void setPort (int port) {

		this.remotePort = port ;
	}

	public String getHostname(){

		return remoteHostname;
	}
	
	public int getPort () {

		return this.remotePort ;
	}
	 

}
