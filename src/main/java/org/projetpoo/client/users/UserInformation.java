package org.projetpoo.client.users;

public class UserInformation {
	private String nickname ;
	
	public UserInformation (String nickname) {
		this.nickname = nickname;
	}
	
	public void setNickname (String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname () {
		return this.nickname ;
	}
	
}
