package org.projetpoo.client.users;

public class LocalUser {
	private boolean is_connected ;
	
	public void setConnected () {
		this.is_connected = true ;
	}
	
	public void setDisconnected () {
		this.is_connected = false ;
	}

}
